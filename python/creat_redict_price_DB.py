# 필요한 라이브러리 불러오기
import pandas as pd
import numpy as np
from sqlalchemy import create_engine
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error, mean_absolute_error, r2_score
from xgboost import XGBRegressor
import matplotlib.pyplot as plt

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
# label_encoder = LabelEncoder()
# df['construction_company'] = label_encoder.fit_transform(df['construction_company'])

# subway_distance를 기준으로 역세권, 초역세권, 비역세권으로 범주화하여 가변수 생성
def categorize_subway_distance(distance):
    if pd.isnull(distance):  # None 값 확인
        return 0  # None 값은 1500m 이상으로 간주하여 부정적 영향을 줌
    elif distance <= 200:
        return 2  # 초역세권 (200m 이내)
    elif distance <= 500:
        return 1  # 역세권 (500m 이내)
    elif distance <= 1500:
        return 0  # 비역세권 (1500m 이내)

# 기존 df 데이터에 대해 subway_distance를 바탕으로 새로운 가변수 추가
df['subway_proximity'] = df['subway_distance'].apply(categorize_subway_distance)

# 2024년도 모델 (deal_year 컬럼 없이)
df['price_per_area_2024'] = df['price_per_area']
X_2024 = df[['area', 'floor', 'construction_year', 'school_distance', 'subway_proximity', 
             'total_floors', 'number_of_buildings']]
y_2024 = df[['price_per_area_2024']]

# 2024년 데이터 정규화
scaler_2024 = MinMaxScaler()
X_2024_scaled = scaler_2024.fit_transform(X_2024)

# 데이터 분할
X_train_2024, X_test_2024, y_train_2024, y_test_2024 = train_test_split(X_2024_scaled, y_2024, test_size=0.3, random_state=42)

# 2024년 모델 학습
xgb_model_2024 = XGBRegressor()
xgb_model_2024.fit(X_train_2024, y_train_2024)
rf_model_2024 = RandomForestRegressor(random_state=42)
rf_model_2024.fit(X_train_2024, y_train_2024)

# 2024년 예측 및 평가
y_pred_xgb_2024 = xgb_model_2024.predict(X_test_2024)
y_pred_rf_2024 = rf_model_2024.predict(X_test_2024)
y_pred_ensemble_2024 = (y_pred_xgb_2024 + y_pred_rf_2024) / 2

# 거래금액으로 변환
y_test_total_price_2024 = y_test_2024['price_per_area_2024'] * df.loc[y_test_2024.index, 'area']
y_pred_total_price_2024 = y_pred_ensemble_2024 * df.loc[y_test_2024.index, 'area']

# 2024년 평가 지표 계산
rmse_2024 = mean_squared_error(y_test_total_price_2024, y_pred_total_price_2024, squared=False)
mae_2024 = mean_absolute_error(y_test_total_price_2024, y_pred_total_price_2024)
r2_2024 = r2_score(y_test_total_price_2024, y_pred_total_price_2024)

print("\n모델 평가 결과 (2024)")
print("RMSE:", rmse_2024)
print("MAE:", mae_2024)
print("R²:", r2_2024)

# 새로운 데이터 예측 (2024년)
new_data_2024 = pd.DataFrame({'area': [59.9426], 'floor': [18], 'construction_year': [2009],
                              'school_distance': [189], 'subway_proximity': [189], 
                              'total_floors': [13], 'number_of_buildings': [6]})
# 주요 건설사 리스트 정의
major_construction_companies = ['삼성물산', '현대건설', 'GS건설', '롯데건설', 'HDC현대건설', '대우건설', '한화건설', '현대엔지니어링', '디엘이앤씨', '포크소이앤씨', '에스케이에코플랜트']  # 주요 건설사 이름 리스트

# construction_company 열에 대해 주요 건설사는 1, 그 외는 0으로 인코딩
# new_data_2024['construction_company'] = new_data_2024['construction_company'].apply(
#     lambda x: 0 if x in major_construction_companies else 0
# )
new_data_scaled_2024 = scaler_2024.transform(new_data_2024)

predicted_price_per_area_xgb_2024 = xgb_model_2024.predict(new_data_scaled_2024)
predicted_price_per_area_rf_2024 = rf_model_2024.predict(new_data_scaled_2024)
predicted_price_per_area_ensemble_2024 = (predicted_price_per_area_xgb_2024 + predicted_price_per_area_rf_2024) / 2

# 면적을 곱하여 거래금액 예측 (2024년)
predicted_total_price_2024 = predicted_price_per_area_ensemble_2024[0] * new_data_2024['area'][0]
print("\nPredicted Total Transaction Price for New Data (2024):", predicted_total_price_2024)


# 2025년도 모델 (deal_year 컬럼 포함)
df['price_per_area_2025'] = df['price_per_area']
X_2025 = df[['area', 'floor', 'construction_year', 'deal_year', 'school_distance', 
             'subway_proximity', 'total_floors', 'number_of_buildings']]
y_2025 = df[['price_per_area_2025']]

# 2025년 데이터 정규화
scaler_2025 = MinMaxScaler()
X_2025_scaled = scaler_2025.fit_transform(X_2025)

# 데이터 분할
X_train_2025, X_test_2025, y_train_2025, y_test_2025 = train_test_split(X_2025_scaled, y_2025, test_size=0.3, random_state=42)

# 2025년 모델 학습
xgb_model_2025 = XGBRegressor()
xgb_model_2025.fit(X_train_2025, y_train_2025)
rf_model_2025 = RandomForestRegressor(random_state=42)
rf_model_2025.fit(X_train_2025, y_train_2025)

# 2025년 예측 및 평가
y_pred_xgb_2025 = xgb_model_2025.predict(X_test_2025)
y_pred_rf_2025 = rf_model_2025.predict(X_test_2025)
y_pred_ensemble_2025 = (y_pred_xgb_2025 + y_pred_rf_2025) / 2

# 거래금액으로 변환 (2025)
y_test_total_price_2025 = y_test_2025['price_per_area_2025'] * df.loc[y_test_2025.index, 'area']
y_pred_total_price_2025 = y_pred_ensemble_2025 * df.loc[y_test_2025.index, 'area']

# 2025년 평가 지표 계산
rmse_2025 = mean_squared_error(y_test_total_price_2025, y_pred_total_price_2025, squared=False)
mae_2025 = mean_absolute_error(y_test_total_price_2025, y_pred_total_price_2025)
r2_2025 = r2_score(y_test_total_price_2025, y_pred_total_price_2025)

print("\n모델 평가 결과 (2025)")
print("RMSE:", rmse_2025)
print("MAE:", mae_2025)
print("R²:", r2_2025)

# 2025년 새 데이터 예측
new_data_2025 = pd.DataFrame({'area': [59.9426], 'floor': [18], 'construction_year': [2009], 'deal_year' : [2023],
                              'school_distance': [189], 'subway_proximity': [189], 
                              'total_floors': [13], 'number_of_buildings': [6]})
# 주요 건설사 리스트 정의
major_construction_companies = ['삼성물산', '현대건설', 'GS건설', '롯데건설', 'HDC현대건설', '대우건설', '한화건설', '현대엔지니어링', 'LH', '풍림산업', '삼성건설']  # 주요 건설사 이름 리스트
# # construction_company 열에 대해 주요 건설사는 1, 그 외는 0으로 인코딩
# new_data_2025['construction_company'] = new_data_2025['construction_company'].apply(
#     lambda x: 0 if x in major_construction_companies else 0
# )

new_data_scaled_2025 = scaler_2025.transform(new_data_2025)

predicted_price_per_area_xgb_2025 = xgb_model_2025.predict(new_data_scaled_2025)
predicted_price_per_area_rf_2025 = rf_model_2025.predict(new_data_scaled_2025)
predicted_price_per_area_ensemble_2025 = (predicted_price_per_area_xgb_2025 + predicted_price_per_area_rf_2025) / 2

# 면적을 곱하여 거래금액 예측 (2025년)
predicted_total_price_2025 = predicted_price_per_area_ensemble_2025[0] * new_data_2025['area'][0]
print("\nPredicted Total Transaction Price for New Data (2025):", predicted_total_price_2025)


# RandomForest 모델에서 변수 중요도 확인
rf_feature_importances = rf_model_2024.feature_importances_
xgb_feature_importances = xgb_model_2024.feature_importances_

# 특성 이름 설정
feature_names = X_2024.columns

# RandomForest 중요도 시각화
plt.figure(figsize=(12, 6))
plt.barh(feature_names, rf_feature_importances, align='center', alpha=0.7)
plt.xlabel("Feature Importance")
plt.title("Feature Importance in RandomForest (2024 Model)")
plt.show()

# XGBoost 중요도 시각화
plt.figure(figsize=(12, 6))
plt.barh(feature_names, xgb_feature_importances, align='center', alpha=0.7, color='orange')
plt.xlabel("Feature Importance")
plt.title("Feature Importance in XGBoost (2024 Model)")
plt.show()