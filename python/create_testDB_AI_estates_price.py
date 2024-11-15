import requests
import xml.etree.ElementTree as ET
import pandas as pd
import urllib.parse
import json
import re
from sqlalchemy import create_engine

pd.set_option('display.max_columns', None)

# 국토교통부 실거래가 API 호출 함수
def get_real_estate_data(service_key, region_code, year, start_month=1, end_month=12, page_no=1, rows=10):
    print("실거래가 API 호출 시작")
    decoded_service_key = urllib.parse.unquote(service_key)
    
    all_data = []
    
    # 월별로 API 호출
    for month in range(start_month, end_month + 1):
        print(f"{month}월 데이터 요청 중...")
        url = "http://apis.data.go.kr/1613000/RTMSDataSvcAptTradeDev/getRTMSDataSvcAptTradeDev"
        params = {
            'serviceKey': decoded_service_key,  
            'LAWD_CD': region_code,             
            'DEAL_YMD': f"{year}{month:02d}",   
            'pageNo': page_no,                  
            'numOfRows': rows                   
        }
        
        # GET 요청 보내기
        response = requests.get(url, params=params)
        
        if response.status_code == 200:
            root = ET.fromstring(response.content)
            
            # XML 데이터 파싱하여 필요한 정보 추출
            for item in root.findall(".//item"):
                all_data.append({
                    '법정동': item.findtext("umdNm"),
                    '단지명': item.findtext("aptNm"),
                    'floor': item.findtext("floor"),
                    '지번': item.findtext("jibun"),
                    'area': item.findtext("excluUseAr"),
                    'transaction_price': item.findtext("dealAmount"),
                    'construction_year': item.findtext("buildYear"),
                    'deal_year': item.findtext("dealYear")
                })
        else:
            print(f"API 호출 실패: {response.status_code}, {response.text}")
    
    df = pd.DataFrame(all_data)
    print("실거래가 데이터프레임 생성 완료")
    return df

# 아파트 정보(단지코드, 단지동수) CSV 파일 로드
aptinfo_csv_path = "C:\\lx\\final_project\\lx-final-project\\python\\aptinfo.csv"
aptinfo_csv_data = pd.read_csv(aptinfo_csv_path, encoding='cp949')

# 주요 컬럼 확인
print("CSV 파일의 컬럼명:", aptinfo_csv_data.columns)

# 단지 고유번호 추출 함수
def get_complex_id_from_csv(aptinfo_csv_data, address):
    # 주소 비교를 위해 불필요한 공백 제거
    address_clean = re.sub(r'\s+', '', address)
    print(f"주소 매칭 시도 중: {address}")
    
    for index, row in aptinfo_csv_data.iterrows():
        csv_address = row['주소']  # CSV 파일에서 주소 컬럼명에 맞게 설정
        complex_id = row['단지고유번호']  # CSV 파일에서 단지 고유번호 컬럼명에 맞게 설정
        
        # 공백을 제거한 주소와 비교하여 매칭이 필요하면 쓸 것
        csv_address_clean = re.sub(r'\s+', '', str(csv_address))
        
        if address_clean == csv_address_clean:
            print(f"매칭 성공: {address} -> {complex_id}")
            return complex_id
    print(f"매칭 실패: {address}")
    return '알 수 없음'

# 단지동수 추출 함수
def get_dong_cnt_from_csv(aptinfo_csv_data, address):
    # 주소 비교를 위해 불필요한 공백 제거
    address_clean = re.sub(r'\s+', '', address)
    print(f"주소 매칭 시도 중: {address}")
    
    for index, row in aptinfo_csv_data.iterrows():
        csv_address = row['주소']  # CSV 파일에서 주소 컬럼명에 맞게 설정
        dong_cnt = row['동수']  # CSV 파일에서 단지 고유번호 컬럼명에 맞게 설정
        
        # 공백을 제거한 주소와 비교하여 매칭이 필요하면 쓸 것
        csv_address_clean = re.sub(r'\s+', '', str(csv_address))
        
        if address_clean == csv_address_clean:
            print(f"매칭 성공: {address} -> {dong_cnt}")
            return dong_cnt
    print(f"매칭 실패: {address}")
    return '알 수 없음'

# 기존 데이터프레임에 단지 고유번호 추가 함수
def add_complex_id_from_csv(df, aptinfo_csv_data):
    print("단지 고유번호 추가 시작")
    complex_ids = []
    
    for index, row in df.iterrows():
        umdNm = row['법정동']  # 예시 데이터의 법정동 컬럼
        jibun = row['지번']    # 예시 데이터의 지번 컬럼
        
        # 매칭을 위한 전체 주소 조합
        address = f"서울특별시 종로구 {umdNm} {jibun}"
        
        # CSV에서 단지 고유번호 추출
        complex_id = get_complex_id_from_csv(aptinfo_csv_data, address)
        complex_ids.append(complex_id)
        print(f"단지 고유번호 추가: {address} -> {complex_id}")
    
    # 기존 데이터프레임에 단지 고유번호 컬럼 추가
    df['단지 고유번호'] = complex_ids
    print("단지 고유번호 추가 완료")
    return df

# 기존 데이터프레임에 단지 동수 추가 함수
def add_dong_cnt_from_csv(df, aptinfo_csv_data):
    print("단지 동수 추가 시작")
    dong_cnts = []
    
    for index, row in df.iterrows():
        umdNm = row['법정동']  # 예시 데이터의 법정동 컬럼
        jibun = row['지번']    # 예시 데이터의 지번 컬럼
        
        # 매칭을 위한 전체 주소 조합
        address = f"서울특별시 종로구 {umdNm} {jibun}"
        
        # CSV에서 단지 고유번호 추출
        dong_cnt = get_dong_cnt_from_csv(aptinfo_csv_data, address)
        dong_cnts.append(dong_cnt)
        print(f"단지 동수 추가: {address} -> {dong_cnt}")
    
    # 기존 데이터프레임에 단지 고유번호 컬럼 추가
    df['number_of_buildings'] = dong_cnts
    print("단지 동수 추가 완료")
    return df

#지상 층수 csv파일 로드
floor_csv_path = "C:\\lx\\final_project\\lx-final-project\\python\\floor.csv"
floor_csv_data = pd.read_csv(floor_csv_path, encoding='cp949')

# 단지 고유번호 추출 함수
def get_total_floor_from_csv(floor_csv_data, complex_id):
    print(f"아파트 층수 추가 시도 중: {complex_id}")
    
    for index, row in floor_csv_data.iterrows():
        csv_floor = row['지상층수']  # CSV 파일에서 주소 컬럼명에 맞게 설정
        csv_complex_id = row['단지고유번호']  # CSV 파일에서 단지 고유번호 컬럼명에 맞게 설정
        
        if complex_id == csv_complex_id:
            print(f"매칭 성공: {complex_id} -> {csv_floor}")
            return csv_floor
    print(f"매칭 실패: {complex_id}")
    return '알 수 없음'

# 기존 데이터프레임에 단지 층수 추가 함수
def add_total_floor_from_csv(df, floor_csv_data):
    print("아파트 층수 추가 시작")
    floors = []
    
    for index, row in df.iterrows():
        df_complex_id = row['단지 고유번호']  # df 데이터의 단지 고유번호 컬럼
        
        # CSV에서 단지 고유번호 추출
        floor = get_total_floor_from_csv(floor_csv_data, df_complex_id)
        floors.append(floor)
        print(f"단지 층수 추가: {df_complex_id} -> {floor}")
    
    # 기존 데이터프레임에 단지 고유번호 컬럼 추가
    df['total_floors'] = floors
    print("아파트 층수 추가 완료")
    return df

# JSON 파일에서 시공사 데이터를 로드하고 매칭
def load_construction_data(json_path):
    print("load_construction_data 함수 시작")
    try:
        # 파일을 열고 JSON 데이터를 읽어옴
        with open(json_path, 'r', encoding='utf-8') as f:
            construction_data = json.load(f)
        
        # JSON 파일이 성공적으로 로드되었음을 알림
        print("JSON 파일이 성공적으로 로드되었습니다.")
        
        return construction_data

    except FileNotFoundError:
        print(f"파일을 찾을 수 없습니다: {json_path}")
    except json.JSONDecodeError as e:
        print(f"JSON 파일을 파싱하는 중 오류가 발생했습니다: {str(e)}")
    except Exception as e:
        print(f"알 수 없는 오류가 발생했습니다: {str(e)}")

# 시공사 수작업
def get_construction_company(danjimyeong, construction_data):
    danjimyeong_clean = re.sub(r'\(.*?\)', '', danjimyeong).replace(" ", "")
    for item in construction_data:
        if isinstance(item, dict) and '단지명' in item:  # 각 항목이 딕셔너리인지 확인
            if item['단지명'].replace(" ", "") == danjimyeong_clean:
                return item.get('시공사', '알 수 없음')
    # 규칙 기반 시공사 매칭
    if '현대' in danjimyeong_clean:
        return '현대건설'
    elif '자이' in danjimyeong_clean:
        return 'GS건설'
    elif '갑을' in danjimyeong_clean:
        return '갑을건설'
    elif '효성' in danjimyeong_clean:
        return '효성건설'
    elif '한양' in danjimyeong_clean:
        return '한양건설'
    elif '굿모닝힐' in danjimyeong_clean:
        return '동문건설'
    elif '그랑블' in danjimyeong_clean:
        return '서해종합건설'
    elif '꿈에그린' in danjimyeong_clean:
        return '한화건설'
    elif '더샵' in danjimyeong_clean:
        return '포스코건설'
    elif '더제니스' in danjimyeong_clean:
        return '두산건설'
    elif '더플레티넘' in danjimyeong_clean:
        return '쌍용건설'
    elif '더휴' in danjimyeong_clean:
        return '한신공영'
    elif '데시앙' in danjimyeong_clean:
        return '태영건설'
    elif '디에이치' in danjimyeong_clean:
        return '현대건설'
    elif '디에트르' in danjimyeong_clean:
        return '대방건설'
    elif '래미안' in danjimyeong_clean:
        return '삼성물산'
    elif '로얄듀크' in danjimyeong_clean:
        return '동원개발'
    elif '르네상스' in danjimyeong_clean:
        return '삼부토건'
    elif '르엘' in danjimyeong_clean:
        return '롯데건설'
    elif '리슈빌' in danjimyeong_clean:
        return '계룡건설'
    elif '리첸시가' in danjimyeong_clean:
        return '금호건설'
    elif '우미' in danjimyeong_clean:
        return '우미건설'
    elif '미래도' in danjimyeong_clean:
        return '모아건설'
    elif '미소지움' in danjimyeong_clean:
        return '신성건설'
    elif '베라체' in danjimyeong_clean:
        return '한일건설'
    elif '베르디움' in danjimyeong_clean:
        return '호반건설'
    elif 'SKView' in danjimyeong_clean:
        return 'SK건설'
    elif '블루밍' in danjimyeong_clean:
        return '벽산엔지니어링'
    elif '한라' in danjimyeong_clean:
        return '한라건설'
    elif '센트레빌' in danjimyeong_clean:
        return '동부건설'
    elif '아이파크' in danjimyeong_clean:
        return 'HDC현대산업개발'
    elif '안단테' in danjimyeong_clean:
        return 'LH'
    elif '웰츠' in danjimyeong_clean or '스위첸' in danjimyeong_clean:
        return 'KCC건설'
    elif '스타힐스' in danjimyeong_clean:
        return '서희건설'
    elif '아너스빌' in danjimyeong_clean:
        return '경남건설'
    elif '아모리움' in danjimyeong_clean:
        return '건영'
    elif '롯데캐슬' in danjimyeong_clean:
        return '롯데건설'
    elif '푸르지오' in danjimyeong_clean:
        return '대우건설'
    elif '호반' in danjimyeong_clean:
        return '호반건설'
    elif '편한세상' in danjimyeong_clean:
        return '대림산업'
    elif '아이원' in danjimyeong_clean:
        return '풍림건설'
    elif '아이유쉘' in danjimyeong_clean:
        return '우방산업'
    elif '아크로' in danjimyeong_clean:
        return '대림산업'
    elif '에버빌' in danjimyeong_clean:
        return '현진에버빌'
    elif '에일린' in danjimyeong_clean:
        return 'IS동서'
    elif '엑슬루타워' in danjimyeong_clean:
        return '풍림산업'
    elif '엔파트' in danjimyeong_clean:
        return '유진그룹'
    elif '엘리움' in danjimyeong_clean:
        return '대방산업개발'
    elif '예미지' in danjimyeong_clean:
        return '금성백조'
    elif '오네뜨' in danjimyeong_clean:
        return '남해종합건설'
    elif '월드메르디앙' in danjimyeong_clean:
        return '월드건설사업'
    elif '웰카운티' in danjimyeong_clean:
        return '인천도시개발공사'
    elif '위브' in danjimyeong_clean:
        return '두산건설'
    elif '유보라' in danjimyeong_clean:
        return '반도건설'
    elif '유토빌' in danjimyeong_clean:
        return '신일건업'
    elif '이지더원' in danjimyeong_clean:
        return '라인건설&동양건설'
    elif '칸타빌' in danjimyeong_clean:
        return '대원'
    elif '코아루' in danjimyeong_clean:
        return '한국토지신탁'    
    elif '트레지움' in danjimyeong_clean:
        return '두산건설'
    elif '파라곤' in danjimyeong_clean:
        return '라인건설&동양건설'
    elif '파밀리에' in danjimyeong_clean:
        return '신동아건설'
    elif '퍼스트빌' in danjimyeong_clean:
        return '우남건설'
    elif '포레나' in danjimyeong_clean:
        return '한화건설'
    elif '프라디움' in danjimyeong_clean:
        return '시티건설'
    elif '풍경채' in danjimyeong_clean:
        return '제일건설'
    elif '하늘채' in danjimyeong_clean:
        return '코오롱글로벌'
    elif '한내들' in danjimyeong_clean:
        return '유승종합건설'
    elif '해링턴플레이스' in danjimyeong_clean:
        return '효성중공업'
    elif '해피트리' in danjimyeong_clean:
        return '신일'
    elif '휴먼빌' in danjimyeong_clean:
        return '일신건설'
    elif '휴먼시아' in danjimyeong_clean:
        return 'LH'
    elif '힐데스하임' in danjimyeong_clean:
        return '원건설'
    elif '힐스테이트' in danjimyeong_clean:
        return '현대건설'
    elif 'S클래스' in danjimyeong_clean:
        return '중흥건설'
    elif '더블유' in danjimyeong_clean:
        return 'IS건설'
    elif '파크팰리스' in danjimyeong_clean:
        return '삼성건설'
    elif '동대문' in danjimyeong_clean:
        return 'LH'
    elif '스페이스본' in danjimyeong_clean:
        return '풍림산업'
    elif '경희궁의아침' in danjimyeong_clean:
        return '쌍용'
    elif '디팰리스' in danjimyeong_clean:
        return '대림산업'
    elif '삼전' in danjimyeong_clean:
        return '삼전건설'

    return '알 수 없음'

# 네이버 Geocoding API를 사용하여 위도, 경도를 추출하는 함수
def get_lat_lon(latlon_client_id, latlon_client_secret, address):
    headers = {
        'X-NCP-APIGW-API-KEY-ID': latlon_client_id,  
        'X-NCP-APIGW-API-KEY': latlon_client_secret  
    }
    
    url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode"
    params = {'query': address}  # 주소 입력
    
    response = requests.get(url, headers=headers, params=params)
    
    if response.status_code == 200:
        result = response.json()
        if 'addresses' in result and len(result['addresses']) > 0:
            latitude = result['addresses'][0]['y']
            longitude = result['addresses'][0]['x']
            return latitude, longitude
        else:
            return None, None
    else:
        print(f"API 호출 실패: {response.status_code}")
        return None, None

# 카카오 API로 가까운 역, 학교, 대형마트 검색
def search_nearby_places(KAKAO_API_KEY, latitude, longitude, category_group_code, radius=1000):
    url = 'https://dapi.kakao.com/v2/local/search/category.json'
    headers = {
        'Authorization': f'KakaoAK {KAKAO_API_KEY}'
    }
    params = {
        'category_group_code': category_group_code,
        'x': longitude,  # 경도
        'y': latitude,   # 위도
        'radius': radius,  # 반경 (미터 단위)
        'sort': 'distance'  # 거리순으로 정렬
    }
    
    response = requests.get(url, headers=headers, params=params)
    
    if response.status_code == 200:
        return response.json()  # 결과 반환
    else:
        print(f"카카오 API 호출 실패: {response.status_code}")
        return None

# 전체 프로세스 통합: 주소 -> 위도/경도 -> 가까운 역, 학교, 대형마트 검색
def find_nearby_facility(service_key, latlon_client_id, latlon_client_secret, kakao_api_key, region_code, year, construction_data):
    print("find_nearby_facility 함수 시작")
    # 실거래가 데이터 가져오기
    df_real_estate = get_real_estate_data(service_key, region_code, year)
    
    # 추가 컬럼 초기화
    latitudes, longitudes = [], []
    school_names, school_distances = [], []
    station_names, station_distances = [], []
    mart_names, mart_distances = [], []
    construction_companies = []

    # 각 행에 대해 정보 추가
    for index, row in df_real_estate.iterrows():
        address = f"{row['법정동']} {row['지번']}"
        lat, lon = get_lat_lon(latlon_client_id, latlon_client_secret, address)
        
        if lat and lon:
            latitudes.append(lat)
            longitudes.append(lon)
            
            # 가까운 학교 검색
            school_result = search_nearby_places(kakao_api_key, lat, lon, 'SC4')
            if school_result and 'documents' in school_result and len(school_result['documents']) > 0:
                school_names.append(school_result['documents'][0]['place_name'])
                school_distances.append(school_result['documents'][0]['distance'])
            else:
                school_names.append(None)
                school_distances.append(None)
            
            # 가까운 지하철역 검색
            station_result = search_nearby_places(kakao_api_key, lat, lon, 'SW8')
            if station_result and 'documents' in station_result and len(station_result['documents']) > 0:
                station_names.append(station_result['documents'][0]['place_name'])
                station_distances.append(station_result['documents'][0]['distance'])
            else:
                station_names.append(None)
                station_distances.append(None)

            # 가까운 대형마트 검색
            mart_result = search_nearby_places(kakao_api_key, lat, lon, 'MT1')
            if mart_result and 'documents' in mart_result and len(mart_result['documents']) > 0:
                mart_names.append(mart_result['documents'][0]['place_name'])
                mart_distances.append(mart_result['documents'][0]['distance'])
            else:
                mart_names.append(None)
                mart_distances.append(None)
        else:
            latitudes.append(None)
            longitudes.append(None)
            school_names.append(None)
            school_distances.append(None)
            station_names.append(None)
            station_distances.append(None)
            mart_names.append(None)
            mart_distances.append(None)

        # 시공사 정보 추가
        construction_company = get_construction_company(row['단지명'], construction_data)
        construction_companies.append(construction_company)

    # DataFrame에 위도, 경도, 학교, 역 및 시공사 정보 추가
    df_real_estate['위도'] = latitudes
    df_real_estate['경도'] = longitudes
    df_real_estate['가장 가까운 학교'] = school_names
    df_real_estate['school_distance'] = school_distances
    df_real_estate['가장 가까운 역'] = station_names
    df_real_estate['subway_distance'] = station_distances
    df_real_estate['가장 가까운 마트'] = mart_names
    df_real_estate['mart_distance'] = mart_distances
    df_real_estate['construction_company'] = construction_companies

    return df_real_estate

# API 호출 테스트
service_key = "RRVSLKXYrGQqsBNwejThJVPjdPE0ybHp2LdWNOEHX9RpXYRNne2eIbWH8Jla5qMfivBIR2VEuUED0ah1NoCZlQ%3D%3D"
latlon_client_id = "65axbgtkif"
latlon_client_secret = "KDkhC7N8r6VIT6aSS3Zu0cAzrP9fo5K3Er14ArPo"
kakao_api_key = "88911399f394d4bf0c2c7434b9563ca2"
region_code = "11110"  # 종로구 지역 코드
year = [2020,2021,2022]

# JSON 파일에서 시공사 데이터 로드
construction_data = load_construction_data('C:\\lx\\final_project\\lx-final-project\\python\\apt_bldr.json')

# 각 연도별 데이터를 결합하여 처리하는 함수
def get_combined_data_for_years(service_key, latlon_client_id, latlon_client_secret, kakao_api_key, region_code, years, construction_data):
    combined_df = pd.DataFrame()
    for year in years:
        print(f"{year}년 데이터를 처리 중...")
        df_year = find_nearby_facility(service_key, latlon_client_id, latlon_client_secret, kakao_api_key, region_code, year, construction_data)
        combined_df = pd.concat([combined_df, df_year], ignore_index=True)
    return combined_df

# 실거래가 데이터 및 관련 정보 가져오기
df_result = get_combined_data_for_years(service_key, latlon_client_id, latlon_client_secret, kakao_api_key, region_code, year, construction_data)

# CSV 파일에서 단지 고유번호 추가
df_result_with_complex_id = add_complex_id_from_csv(df_result, aptinfo_csv_data)
df_result_with_dong_cnt = add_dong_cnt_from_csv(df_result, aptinfo_csv_data)
df_result_with_total_floor = add_total_floor_from_csv(df_result, floor_csv_data)

# 시공사 데이터 수작업
df_result.loc[df_result['단지명'] == '아르젠종로', 'construction_company'] = '석장건설주식회사'
df_result.loc[df_result['단지명'] == '크래스빌', 'construction_company'] = '금화이앤씨'
df_result.loc[df_result['단지명'] == '종로아인스빌', 'construction_company'] = '청오건설주식회사'
df_result.loc[df_result['단지명'] == '버넷하임1', 'construction_company'] = '코렘시스'
df_result.loc[df_result['단지명'] == '블루빌', 'construction_company'] = '라메드'
df_result.loc[df_result['단지명'] == '렉스빌', 'construction_company'] = '거묵종합건설'

# 결과 확인
if df_result is not None:
    print(df_result.head(50))

# df_result를 CSV 파일로 저장
df_result.to_csv("real_estate_data2.csv", index=False, encoding='cp949')
print("3년치 데이터 CSV 파일로 저장 완료: real_estate_data2.csv")
