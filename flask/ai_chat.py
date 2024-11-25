import os
import re
from langchain.utilities import SQLDatabase
from langchain.prompts import ChatPromptTemplate
from langchain.chat_models import ChatOpenAI
from langchain.vectorstores import FAISS
from langchain.embeddings import OpenAIEmbeddings
from langchain_core.output_parsers import StrOutputParser
from psycopg2 import connect
from dotenv import load_dotenv

# 환경 변수 로드
load_dotenv()
GENAI_API_KEY = os.getenv("GENAI_API_KEY")

# PostgreSQL 설정
DB_CONFIG = {
    "host": "localhost",
    "database": "real_estate",
    "user": "postgres",
    "password": "postgis",
}

# 데이터베이스 연결 함수
def get_db_connection():
    return connect(**DB_CONFIG)

# 데이터베이스 스키마 추출 함수
def get_db_schema():
    connection = connect(**DB_CONFIG)
    cursor = connection.cursor()
    schema_query = """
    SELECT table_name, column_name, data_type
    FROM information_schema.columns
    WHERE table_schema = 'public'
    ORDER BY table_name, ordinal_position;
    """
    cursor.execute(schema_query)
    rows = cursor.fetchall()
    cursor.close()
    connection.close()

    schema_info = []
    for table_name, column_name, data_type in rows:
        schema_info.append(f"Table: {table_name}, Column: {column_name}, DataType: {data_type}")
    return schema_info

# VectorStore 생성 및 저장
def create_vector_store(schema_info):
    embeddings = OpenAIEmbeddings(model="text-embedding-ada-002")
    vectorstore = FAISS.from_texts(schema_info, embeddings)
    vectorstore.save_local("vectorstore_schema")  # 저장
    return vectorstore

# VectorStore 로드 또는 생성
def load_or_create_vector_store():
    if os.path.exists("vectorstore_schema"):
        return FAISS.load_local(
                "vectorstore_schema",
                OpenAIEmbeddings(model="text-embedding-ada-002"),
                allow_dangerous_deserialization=True # 역직렬화 허용
                )
    else:
        schema_info = get_db_schema()
        return create_vector_store(schema_info)

# 관련 스키마 검색
def search_related_schema(question, vectorstore):
    related_schema = vectorstore.similarity_search(question, k=5)
    return [result.page_content for result in related_schema]

# LangChain 구성
def setup_langchain():
    db_uri = f"postgresql+psycopg2://{DB_CONFIG['user']}:{DB_CONFIG['password']}@{DB_CONFIG['host']}/{DB_CONFIG['database']}"
    db = SQLDatabase.from_uri(db_uri)
    model = ChatOpenAI(temperature=0, model_name="gpt-3.5-turbo")
    return db, model


def get_region_mapping():
    connection = get_db_connection()
    cursor = connection.cursor()
    query = """
    SELECT 
        region_pk, 
        CONCAT(region_si_do, ' ', region_si_gun_gu, ' ', region_eup_myeon_dong) AS full_address
    FROM region;
    """
    cursor.execute(query)
    rows = cursor.fetchall()
    cursor.close()
    connection.close()

    return {row[0]: row[1] for row in rows}

def get_property_type(type_code):
    property_types = {
        0: "원룸/투룸",
        1: "아파트",
        2: "주택/빌라",
        3: "오피스텔",
        4: "분양"
    }
    return property_types.get(type_code, "알 수 없음")

def process_ai_request(question):
    # VectorStore 로드
    vectorstore = load_or_create_vector_store()

    # 질문과 관련된 스키마 검색
    related_schema = search_related_schema(question, vectorstore)

    # LangChain 구성
    db, model = setup_langchain() 

    schema_info = """
1. Table `prp` (Property Listings):
    - Purpose: Stores information about real estate properties.
    - Columns:
        - `prp_pk`: SERIAL, Primary Key. Unique identifier for each property.
        - `prp_name`: VARCHAR(255). Name of the property (e.g., apartment name).
        - `prp_type`: INTEGER. Type of property (e.g., 1 = Apartment, 2 = Office, etc.).
        - `prp_near_sub`: INTEGER. References `substn.substn_pk`. Closest subway station.
        - `prp_near_sub_dist`: DECIMAL(10, 2). Distance to the nearest subway station (in meters).
        - Other columns: Include details such as price, deposit, area, rooms, bathrooms, and other features.
    - Relationships:
        - References `substn` table via `prp_near_sub` to get subway station details.
        - References `region` table via `region_pk` to get the region information.
        - References `coord` table via `coord_pk` to get the property’s coordinates.

2. Table `region` (Region Information):
    - Purpose: Stores hierarchical region information (e.g., city, district, town).
    - Columns:
        - `region_pk`: SERIAL, Primary Key. Unique identifier for each region.
        - `region_code`: INTEGER. Region code for administrative use.
        - `region_si_do`: VARCHAR(16). Name of the province or metropolitan city.
        - `region_si_gun_gu`: VARCHAR(16). Name of the district or county.
        - `region_eup_myeon_dong`: VARCHAR(16). Name of the town or neighborhood.
        - `region_ri`: VARCHAR(16). Subdivision within the region.
    - Relationships:
        - Referenced by `prp`, `users`, `agency`, and other tables to associate regions with properties, users, and agencies.

3. Table `coord` (Coordinate Information):
    - Purpose: Stores geographical coordinates of locations.
    - Columns:
        - `coord_pk`: SERIAL, Primary Key. Unique identifier for each coordinate.
        - `location`: GEOMETRY(POINT, 4326). Spatial data type for storing coordinates (latitude/longitude).
    - Features:
        - Includes spatial indexing (`GIST`) for efficient geospatial queries.
    - Relationships:
        - Referenced by `prp` for property locations and `substn` for subway station locations.

4. Table `substn` (Subway Station Information):
    - Purpose: Stores information about subway stations.
    - Columns:
        - `substn_pk`: SERIAL, Primary Key. Unique identifier for each subway station.
        - `substn_name`: VARCHAR(255). Name of the subway station.
        - `substn_line`: VARCHAR(32). Subway line(s) serving the station.
        - `coord_pk`: INTEGER. References `coord.coord_pk`. Coordinates of the subway station.
    - Relationships:
        - Referenced by `prp` via `prp_near_sub` to associate properties with subway stations.
        - Referenced by `betsubstn` to define relationships between subway stations.

5. Table `betsubstn` (Time and Distance Between Subway Stations):
    - Purpose: Stores information about the time and distance between subway stations.
    - Columns:
        - `betsubstn_pk`: SERIAL, Primary Key. Unique identifier for each record.
        - `betsubstn_time`: TIME. Travel time between two subway stations.
        - `betsubstn_dist`: DECIMAL(10, 3). Distance between two subway stations (in meters).
        - `substn_pk`: INTEGER. References `substn.substn_pk` (Start station).
        - `substn_pk2`: INTEGER. References `substn.substn_pk` (End station).
    - Relationships:
        - References `substn` table for start and end subway stations.

6. Table `users` (User Information):
    - Purpose: Stores user account details.
    - Columns:
        - `user_pk`: SERIAL, Primary Key. Unique identifier for each user.
        - `region_pk`: INTEGER. References `region.region_pk`. Primary preferred region of the user.
        - Other columns: Include user credentials, personal details, and preferences.
    - Relationships:
        - Referenced by `agent`, `assistant`, and other tables to associate users with roles.

7. Table `agent` (Real Estate Agent Information):
    - Purpose: Stores details about real estate agents.
    - Columns:
        - `agent_pk`: SERIAL, Primary Key. Unique identifier for each agent.
        - `user_pk`: INTEGER. References `users.user_pk`. Links agent to user details.
        - `agency_pk`: INTEGER. References `agency.agency_pk`. Links agent to their agency.
    - Relationships:
        - Associated with `agency` and `users` tables to link agents with users and agencies.

8. Table `agency` (Real Estate Agency Information):
    - Purpose: Stores details about real estate agencies.
    - Columns:
        - `agency_pk`: SERIAL, Primary Key. Unique identifier for each agency.
        - `region_pk`: INTEGER. References `region.region_pk`. Region where the agency is located.
        - `agent_pk`: INTEGER. References `agent.agent_pk`. Links agency to the primary agent.
    - Relationships:
        - Linked with `agent` and `region` tables.

9. Table `assistant` (Real Estate Assistant Information):
    - Purpose: Stores information about real estate assistants.
    - Columns:
        - `assistant_pk`: SERIAL, Primary Key. Unique identifier for each assistant.
        - `agency_pk`: INTEGER. References `agency.agency_pk`. Links assistant to an agency.
        - `user_pk`: INTEGER. References `users.user_pk`. Links assistant to user details.
    - Relationships:
        - Connected to `users` and `agency` tables.

---

### **Key Relationships**
1. **`prp` ↔ `substn`**:
    - `prp.prp_near_sub` references `substn.substn_pk`.
    - Used to find the nearest subway station to a property.

2. **`substn` ↔ `betsubstn`**:
    - `betsubstn.substn_pk` and `betsubstn.substn_pk2` reference `substn.substn_pk`.
    - Defines travel time and distance between subway stations.

3. **`prp` ↔ `region`**:
    - `prp.region_pk` references `region.region_pk`.
    - Used to associate properties with administrative regions.

4. **`prp` ↔ `coord`**:
    - `prp.coord_pk` references `coord.coord_pk`.
    - Used to store geospatial information about property locations.

5. **`agent` ↔ `agency`**:
    - `agent.agency_pk` references `agency.agency_pk`.
    - Associates agents with their respective agencies.

6. **`users` ↔ `region`**:
    - `users.region_pk` references `region.region_pk`.
    - Indicates the user's preferred region.

---

### **Schema Highlights**
- **Geospatial Features**:
    - `coord.location` uses PostGIS for geospatial queries (e.g., finding nearby properties or stations).
    - Spatial index (`GIST`) ensures efficient geospatial searches.

- **Subway Station Connections**:
    - `betsubstn` provides detailed travel time and distance between subway stations.
    - Useful for questions involving subway accessibility or commute times.

- **Real Estate Relationships**:
    - Properties are connected to agents, agencies, and assistants through hierarchical relationships.
    """

    combined_schema = """
    Basic Schema Information:
    {}

    Related Schema:
    {}
    """.format(
        schema_info,
        "\n".join(related_schema)  # 각 문자열을 줄바꿈으로 연결
    )

    # GPT 프롬프트: SQL 쿼리 생성
    template_query = """
    Based on the related schema below, write a PostgreSQL query to answer the user's question.
    Ensure that:
    - If the question is about properties near a school, only use the `prp_near_school` and `prp_near_school_dist` columns from the `prp` table.
    - All columns from the property table (`prp`) are included in the result.
    - The `location` column from the `coord` table is split into two separate columns: `latitude` and `longitude`.
    - Use the PostGIS functions `ST_Y` and `ST_X` to extract `latitude` and `longitude` from the `location` column.
    - Avoid using the `substn` table unless explicitly asked about subway stations.
    - Do not include markdown formatting (e.g., ```sql or ```) in your response.


    Schema:
    {combined_schema}

    Question: {question}

    SQL Query:
    """
    query_prompt = ChatPromptTemplate.from_template(template_query)

    # 입력 데이터
    query_input_data = {
        "combined_schema": combined_schema,
        "question": question
    }

    # SQL 쿼리 생성
    sql_query = model.predict(query_prompt.format(**query_input_data))

    if "역" in question:
        # 모든 "역" 앞 단어를 추출하여 처리
        station_names = re.findall(r'(\S+역)', question)  # "역"으로 끝나는 단어를 모두 찾음
        for station in station_names:
                station_base = station[:-1]  # "역"을 제외한 역 이름
                # SQL 쿼리에서 해당 역 이름을 처리
                sql_query = sql_query.replace(f"'{station}'", f"REPLACE('{station}', '역', '')")


    print("Generated SQL Query:", sql_query)

    # 데이터베이스 실행
    connection = get_db_connection()
    cursor = connection.cursor()
    cursor.execute(sql_query)
    columns = [desc[0] for desc in cursor.description]
    data = cursor.fetchall()
    cursor.close()
    connection.close()

    # 데이터 처리: JSON으로 변환
    result_data = [dict(zip(columns, row)) for row in data]

    region_mapping = get_region_mapping()
    
    # GPT에 전달할 프롬프트 생성
    properties_description = "\n".join(
        [
            f"""
            - 매물 이름: {entry.get('prp_name', '알 수 없음')}
            - 주소: {region_mapping.get(entry.get('region_pk'), '주소 정보 없음')}
            - 매물 종류: {get_property_type(entry.get('prp_type', -1))}
            - 거래 종류: {entry.get('prp_trans_type')}
            - 가격: {float(entry.get('prp_price', 0)) / 10000}억
            - 보증금: {float(entry.get('prp_deposit', 0)) / 10000}억
            - 설명: {entry.get('prp_desc', '알 수 없음')}
            - 지하철역과 거리: {entry.get('prp_near_sub_dist', '알 수 없음')}m
            - 주변학군지: {entry.get('prp_near_school', '알 수 없음')}
            """
            for entry in result_data
        ]
    )

    # GPT 프롬프트 작성
    agency_prompt = f"""
    당신은 전문 공인중개사입니다. 아래는 질문과 관련된 매물 정보입니다. 
    이 정보를 바탕으로 공인중개사가 말하는 것처럼 자연스럽고 친근하게 매물 정보를 설명해주세요.

    질문: {question}

    매물 정보:
    {properties_description}

    자연스럽고 친근한 응답:
    """

    agency_response = model.predict(agency_prompt).strip()

    # 최종 결과 반환
    return {
        "question": question,
        "sql_query": sql_query,
        "result_data": result_data,
        "friendly_response": agency_response
    }