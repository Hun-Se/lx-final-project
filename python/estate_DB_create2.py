import pandas as pd
from sqlalchemy import create_engine, text

# MySQL 연결 정보 설정
username = "root"
password = "rootroot"
host = "localhost"
database = "real_estate_data"

# CSV 파일에서 데이터 불러오기
print("CSV 파일 로드 시작")
df_result = pd.read_csv('real_estate_data2.csv', encoding='cp949')
print("CSV 파일 로드 완료")

# 필요한 컬럼만 선택하여 새로운 DataFrame 생성
df_selected = df_result[[
    "area", "transaction_price", "floor", "construction_year", "deal_year",
    "school_distance", "subway_distance", "total_floors", 
    "number_of_buildings"
]]

# 컬럼명을 데이터베이스에 맞게 변경
df_selected.columns = [
    "area", "transaction_price", "floor", "construction_year", "deal_year",
    "school_distance", "subway_distance", "total_floors", 
    "number_of_buildings"
]

# transaction_price 컬럼의 쉼표 제거 및 정수형 변환
df_selected['transaction_price'] = df_selected['transaction_price'].replace({',': ''}, regex=True).astype(int)

# SQLAlchemy 엔진 생성
print("DB 접속 시작")
engine = create_engine(f"mysql+pymysql://{username}:{password}@{host}/{database}")

# Step 1: 테이블을 수동으로 생성하고 id 컬럼을 자동 증가로 설정
with engine.connect() as conn:
    conn.execute(text("""
    CREATE TABLE IF NOT EXISTS real_estate_data2 (
        id INT AUTO_INCREMENT PRIMARY KEY,
        area FLOAT,
        transaction_price INT,
        floor INT,
        construction_year INT,
        deal_year INT,
        school_distance INT,
        subway_distance INT,
        total_floors INT,
        number_of_buildings INT
    );
    """))
print("테이블 생성 완료")

# Step 2: 데이터프레임을 MySQL에 저장 (기존 테이블에 추가)
df_selected.to_sql('real_estate_data2', con=engine, if_exists='append', index=False)
print("데이터가 성공적으로 MySQL 데이터베이스에 저장되었습니다.")
