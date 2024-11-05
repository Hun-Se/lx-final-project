# 필요한 라이브러리 불러오기
import pandas as pd
import numpy as np
from sqlalchemy import create_engine
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.preprocessing import MinMaxScaler, LabelEncoder
from sklearn.multioutput import MultiOutputRegressor
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error, mean_absolute_error, r2_score
from xgboost import XGBRegressor

# MySQL 연결 정보 설정
username = "root"
password = "rootroot"
host = "localhost"
database = "real_estate_data"

# SQLAlchemy 엔진 생성 및 데이터 불러오기
engine = create_engine(f"mysql+pymysql://{username}:{password}@{host}/{database}")
df = pd.read_sql("SELECT * FROM transaction_data", con=engine)

# 데이터 전처리
df = df.dropna()  # 결측치 제거
df['price_per_area'] = df['transaction_price'] / df['area']  # 평단가 계산

# 시공사(Label Encoding)
label_encoder = LabelEncoder()
df['construction_company'] = label_encoder.fit_transform(df['construction_company'])

df['price_per_area_2024'] = df['price_per_area']

# 독립 변수(X)와 다중 타겟 종속 변수(y) 설정
X = df[['area', 'floor', 'construction_year', 'school_distance', 'subway_distance', 
        'total_floors', 'number_of_buildings', 'construction_company']]
y = df[['price_per_area_2024',]]

# 데이터 정규화
scaler = MinMaxScaler()
X = scaler.fit_transform(X)

# 데이터 분할
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

# 모델 설정 및 학습

# 1. XGBoost 모델 학습
xgb_model = MultiOutputRegressor(XGBRegressor())
xgb_model.fit(X_train, y_train)

# 2. 랜덤 포레스트 모델 학습
rf_model = MultiOutputRegressor(RandomForestRegressor(random_state=42))
rf_model.fit(X_train, y_train)

# 예측 및 앙상블
y_pred_xgb = xgb_model.predict(X_test)
y_pred_rf = rf_model.predict(X_test)
y_pred_ensemble = (y_pred_xgb + y_pred_rf) / 2

# 전체 거래금액 예측을 위해 면적을 곱하여 변환
y_test_total_price_2024 = y_test['price_per_area_2024'] * df.loc[y_test.index, 'area']
y_pred_total_price_2024 = y_pred_ensemble[:, 0] * df.loc[y_test.index, 'area']

# 평가 지표 계산
rmse_2024 = mean_squared_error(y_test_total_price_2024, y_pred_total_price_2024, squared=False)
mae_2024 = mean_absolute_error(y_test_total_price_2024, y_pred_total_price_2024)
r2_2024 = r2_score(y_test_total_price_2024, y_pred_total_price_2024)

print("\n모델 평가 결과 (2024)")
print("RMSE:", rmse_2024)
print("MAE:", mae_2024)
print("R²:", r2_2024)

# 새로운 데이터 예측
new_data = pd.DataFrame({'area': [85], 'floor': [10], 'construction_year': [2015], 
                         'school_distance': [300], 'subway_distance': [400], 
                         'total_floors': [20], 'number_of_buildings': [10], 
                         'construction_company': [1]})
new_data_scaled = scaler.transform(new_data)

# 2024년 예측
predicted_price_per_area_xgb = xgb_model.predict(new_data_scaled)
predicted_price_per_area_rf = rf_model.predict(new_data_scaled)
predicted_price_per_area_ensemble = (predicted_price_per_area_xgb + predicted_price_per_area_rf) / 2

# 면적을 곱하여 거래금액 예측
predicted_total_price_2024 = predicted_price_per_area_ensemble[0][0] * new_data['area'][0]

print("\nPredicted Total Transaction Price for New Data (2024):", predicted_total_price_2024)