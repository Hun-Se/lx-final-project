import pandas as pd
import numpy as np
from sqlalchemy import create_engine
from sklearn.preprocessing import MinMaxScaler
from xgboost import XGBRegressor
from sklearn.ensemble import RandomForestRegressor
from sqlalchemy import create_engine, text

# MySQL 연결 정보 설정
username = "root"
password = "rootroot"
host = "localhost"
database = "real_estate_data"
engine1 = create_engine(f"mysql+pymysql://{username}:{password}@{host}/{database}")

# PostgreSQL 연결 정보 설정
username = "postgres"  # PostgreSQL 사용자 이름
password = "postgis"  # PostgreSQL 비밀번호
host = "localhost"  # PostgreSQL 호스트 주소
port = "5432"  # PostgreSQL 포트 (기본값은 5432)
database = "real_estate"  # PostgreSQL 데이터베이스 이름
# PostgreSQL 연결 엔진 생성
engine2 = create_engine(f"postgresql+psycopg2://{username}:{password}@{host}:{port}/{database}")

# 데이터 불러오기
df = pd.read_sql("SELECT * FROM real_estate_data1", con=engine1)
dumy = pd.read_sql("SELECT * FROM prp", con=engine2)

# 전처리 및 범주화 함수
def categorize_subway_distance(distance):
    if pd.isnull(distance):
        return 0
    elif distance <= 200:
        return 2
    elif distance <= 500:
        return 1
    elif distance <= 1500:
        return 0

def categorize_school_distance(distance):
    if pd.isnull(distance):
        return 0
    elif distance <= 250:
        return 2
    elif distance <= 400:
        return 1
    elif distance <= 1500:
        return 0

# 평당가 계산 및 기타 전처리
df['price_per_area'] = df['transaction_price'] / df['area']
df['subway_proximity'] = df['subway_distance'].apply(categorize_subway_distance)
df['school_proximity'] = df['school_distance'].apply(categorize_school_distance)

# 필요한 피처 선택
X_2022 = df[['area', 'floor', 'construction_year', 'deal_year', 'school_proximity', 'subway_proximity', 'total_floors', 'number_of_buildings']]
y_2022 = df['price_per_area']

# 데이터 정규화 및 모델 학습
scaler = MinMaxScaler()
X_2022.replace('알 수 없음', np.nan, inplace=True)
X_2022_scaled = scaler.fit_transform(X_2022)

# 모델 정의 및 학습
xgb_model = XGBRegressor()
rf_model = RandomForestRegressor(random_state=42)
xgb_model.fit(X_2022_scaled, y_2022)
rf_model.fit(X_2022_scaled, y_2022)

query = """
    SELECT column_name
    FROM information_schema.columns
    WHERE table_schema = 'public' AND table_name = 'prp' AND column_name = 'prp_predict_2022';
    """
# Step 1: prp_dumy_data 테이블에 PRP_PREDICT_2023 컬럼 추가 (존재하지 않는 경우에만)
with engine2.connect() as conn:
    result = conn.execute(text(query))
    columns = result.fetchall()

if not columns:
    # 컬럼이 없으면 추가
    with engine2.connect() as conn:
        conn.execute(text("""
        ALTER TABLE public.prp
        ADD COLUMN "prp_predict_2022" NUMERIC;
        """))
        conn.commit()
    print("컬럼 'prp_predict_2022' 추가 완료")

# 예외 처리 및 컬럼 확인
if 'id' not in dumy.columns:
    dumy['id'] = dumy.index  # 'id'가 없으면 index를 'id'로 사용

# Step 2: dumy 데이터에 대해 반복적으로 예측 수행 및 데이터베이스 업데이트
for index, row in dumy.iterrows():
    # 필요한 변수 설정
    prp_excl_area = row['prp_excl_area']
    prp_floor = row['prp_floor']
    prp_constr_year = row['prp_constr_year']
    prp_trade_year = 2022
    school_proximity = categorize_school_distance(row['prp_near_school_dist'])
    subway_proximity = categorize_subway_distance(row['prp_near_sub_dist'])
    prp_total_floor = row['prp_total_floor']
    prp_total_block = row['prp_total_block']

    # 새 데이터프레임으로 스케일링 및 예측
    new_data = pd.DataFrame({
        'area': [prp_excl_area],
        'floor': [prp_floor],
        'construction_year': [prp_constr_year],
        'deal_year' : [prp_trade_year],
        'school_proximity': [school_proximity],
        'subway_proximity': [subway_proximity],
        'total_floors': [prp_total_floor],
        'number_of_buildings': [prp_total_block]
    })

    new_data_scaled = scaler.transform(new_data)
    pred_xgb = xgb_model.predict(new_data_scaled)
    pred_rf = rf_model.predict(new_data_scaled)
    pred_ensemble = (pred_xgb + pred_rf) / 2

    # 예측 거래 금액 계산
    predicted_total_price = pred_ensemble[0] * prp_excl_area

    # 업데이트 쿼리 실행
    with engine2.connect() as conn:
        result = conn.execute(
            text("""
            UPDATE prp
            SET "prp_predict_2022" = :predicted_price
            WHERE "prp_pk" = :id
            """),
            {"predicted_price": float(predicted_total_price), "id": row['prp_pk']}
        )
        conn.commit()

        # 결과 확인
        if result.rowcount > 0:
            print(f"ID {row['id']}의 예측 거래 금액이 성공적으로 업데이트되었습니다: {predicted_total_price}")
        else:
            print(f"ID {row['id']}에 해당하는 레코드가 없습니다. 업데이트 실패.")