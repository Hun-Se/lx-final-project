import pandas as pd
from sqlalchemy import create_engine

# MySQL 연결 정보 설정
username = "root"       # MySQL 사용자 이름
password = "rootroot"        # MySQL 비밀번호
host = "localhost"           # 서버 주소 (localhost는 로컬 서버)
database = "real_estate_data" # 데이터베이스 이름

# CSV 파일에서 데이터 불러오기
print("csv파일 로드 시작")
df_result = pd.read_csv('real_estate_data3.csv', encoding='cp949')
print("csv파일 로드 완료")

# 필요한 컬럼만 선택하여 새로운 DataFrame 생성
df_selected = df_result[[
    "area", "transaction_price", "floor", "construction_year", "deal_year",
    "school_distance", "subway_distance", "total_floors", 
    "number_of_buildings", "construction_company", "mart_distance"
]]

# 컬럼명을 데이터베이스에 맞게 변경
df_selected.columns = [
    "area", "transaction_price", "floor", "construction_year", "deal_year",
    "school_distance", "subway_distance", "total_floors", 
    "number_of_buildings", "construction_company", "mart_distance"
]

# transaction_price 컬럼의 쉼표 제거 및 정수형 변환
df_selected['transaction_price'] = df_selected['transaction_price'].replace({',': ''}, regex=True).astype(int)

# SQLAlchemy 엔진 생성
print("DB접속 시작")
engine = create_engine(f"mysql+pymysql://{username}:{password}@{host}/{database}")

# 데이터프레임을 MySQL에 저장
df_selected.to_sql('transaction_data', con=engine, if_exists='replace', index=False)
print("데이터가 성공적으로 MySQL 데이터베이스에 저장되었습니다.")