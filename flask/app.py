from flask import Flask, request, jsonify
import psycopg2
import json
import google.generativeai as genai
import re, os
import requests
from flask_cors import CORS
from psycopg2.extras import DictCursor

# GeMini API 설정
genai.configure(api_key="AIzaSyCCli_Zt0gxb16rl8s6HrRISFVZ5nSgd9U")
model = genai.GenerativeModel("tunedModels/flrfinetuning-dig8tu69m20x") #fine-tuning 된 모델 이름

# Clova speech API 설정
invoke_url = 'https://clovaspeech-gw.ncloud.com/external/v1/9659/60045bef6545eef6a85292c9d864bd06b1790aa331d6d84059cc5cbcfc420c17'
secret = 'd193e11a9efe40f2b599e0ca789d9c8c'

# Flask 서버 인스턴스 생성
app = Flask(__name__)
CORS(app)

# clova speech local으로부터 불러오기
def req_upload(file, completion, callback=None, userdata=None,
    forbiddens=None, boostings=None, wordAlignment=True,
        fullText=True, diarization=None, sed=None):
    request_body = {
        'language': 'ko-KR',
        'completion': completion,
        'callback': callback,
        'userdata': userdata,
        'wordAlignment': wordAlignment,
        'fullText': fullText,
        'forbiddens': forbiddens,
        'boostings': boostings,
        'diarization': diarization,
        'sed': sed,
    }
    headers = {
        'Accept': 'application/json;UTF-8',
        'X-CLOVASPEECH-API-KEY': secret
    }
    print(json.dumps(request_body, ensure_ascii=False).encode('UTF-8'))
    files = {
        'media': open(file, 'rb'),
        'params': (None, json.dumps(request_body, \
                    ensure_ascii=False).encode('UTF-8'), \
                            'application/json')
    }
    response = requests.post(headers=headers, url=invoke_url \
                + '/recognizer/upload', files=files)
    return response

# MySQL 데이터베이스 연결 설정
# postgreSQL로 전환으로 인한 주석
# def get_db_connection():
#     connection = mysql.connector.connect(
#         host='localhost',
#         user='root',
#         password='rootroot',
#         database='real_estate',
#         charset='utf8',
#         use_pure=True
#     )
#     return connection

def get_db_connection():
    connection = psycopg2.connect(
        host='localhost',
        database='real_estate',
        user='postgres',
        password='postgis'
    )
    return connection

# 간단한 라우팅 예시
@app.route('/', methods=['GET'])
def home():
    return "Hello, Flask!"

# 데이터베이스에서 사용자 정보 조회 예시
@app.route('/user/<int:user_id>', methods=['GET'])
def get_user(user_id):
    connection = get_db_connection()
    cursor = connection.cursor(cursor_factory=DictCursor) 
    cursor.execute('SELECT * FROM users WHERE id = %s', (user_id,))
    user = cursor.fetchone()
    cursor.close()
    connection.close()
    if user:
        return jsonify(user)
    else:
        return jsonify({'error': 'User not found'}), 404

@app.route('/chat/flr/<int:flr_pk>', methods=['GET'])
def get_chat_by_flr(flr_pk):
    connection = get_db_connection()
    cursor = connection.cursor(cursor_factory=DictCursor) 

    try:
        # flr_pk로부터 chat_pk를 조회
        query_get_chat_pk = '''
            SELECT chat_pk FROM flr WHERE flr_pk = %s
        '''
        cursor.execute(query_get_chat_pk, (flr_pk,))
        chat_pk_result = cursor.fetchone()

        if not chat_pk_result:
            return jsonify({'error': 'Chat PK not found for the given FLR PK'}), 404

        chat_pk = chat_pk_result['chat_pk']

        # chat_pk를 사용하여 채팅 메시지 조회
        query_chat_messages = '''
            SELECT cm.chatmes_pk, cm.chatmes_datetime, cm.chatmes_content,
                   CASE WHEN cm.user_pk_sender IS NOT NULL THEN '신고자'
                        WHEN cm.agent_pk_sender IS NOT NULL THEN '피신고대상자'
                        ELSE '알수없음' END AS sender_type
            FROM chatmes cm
            WHERE cm.chat_pk = %s
            ORDER BY cm.chatmes_datetime ASC
        '''
        cursor.execute(query_chat_messages, (chat_pk,))
        messages = cursor.fetchall()

        # 대화 내용을 문자열로 변환
        evidenceChat = ""
        for message in messages:
            evidenceChat += f"{message['chatmes_pk']}.{message['sender_type']} : '{message['chatmes_content']}', "
        evidenceChat = evidenceChat.rstrip(', ')

        # flr 데이터 변수 저장
        query_flr = '''
            SELECT flr_cate_upper, flr_cate_middle, flr_cate_lower, flr_content
            FROM flr
            WHERE chat_pk = %s
        '''
        cursor.execute(query_flr, (chat_pk,))
        flr_data = cursor.fetchone()

        if not flr_data:
            raise ValueError("No FLR data found for the given chat PK")

        upperCategory = flr_data['flr_cate_upper']
        middleCategory = flr_data['flr_cate_middle']
        lowerCategory = flr_data['flr_cate_lower']
        reportDetails = flr_data['flr_content']

        # AI 응답 생성
        response = model.generate_content(
            "부동산 중개대상물에 대한 부당한 광고가 의심되는 상황입니다. 사용자의 신고 카테고리는 "
            + upperCategory + ", " + middleCategory + ", " + lowerCategory + "입니다."
            + "신고 내용은 " + reportDetails
            + "이며, 증빙자료는 다음과 같습니다: " + evidenceChat
            + "전체 대화를 검토하고, 신고 내용과 증빙자료가 실제로 부합하는지 판단해줘. 만약 증빙자료가 신고 내용과 실제로 부합하지 않는다면 그 이유도 설명해줘."
            + "부합 여부가 명확하지 않을 경우에도 반드시 '미부합'으로 간주해."
            + "출력 형식은 반드시 JSON 형식으로만 출력해줘.")
       
        response_text = response.text

        try:
            response_data = json.loads(response_text)
            print("Parsed JSON Data:", response_data)
        except json.JSONDecodeError as e:
            print("JSON Decode Error:", str(e))
            print("Final Processed Text:", response_text)
            raise

        flr_decstatus = response_data.get('부합여부')
        flr_decscontent = response_data.get('이유')
        chatmes = response_data.get('증빙자료에서 추출된 부합내용')
        print("chatmes:", chatmes)
        print("chatmes type:", type(chatmes))

        if isinstance(chatmes, dict):
            # 딕셔너리 키-값을 문자열로 병합
            combined_chatmes = ' '.join([f"{key}: {value}" for key, value in chatmes.items()])
        else:
            # 이미 문자열인 경우 그대로 사용
            combined_chatmes = chatmes

        numbers = re.findall(r'\b(\d+)(?=\.(피신고대상자|신고자))', combined_chatmes)  # 정규표현식
        number_list = [int(num[0]) for num in numbers]  # 튜플의 첫 번째 요소만 가져와 숫자로 변환
        print(numbers)


        # 데이터베이스에 결과 저장
        connection = get_db_connection()
        cursor = connection.cursor()
        insert_query = '''
            INSERT INTO aidecs (flr_decstatus, flr_decscontent, chat_pk)
            VALUES (%s, %s, %s)
        '''
        cursor.execute(insert_query, (flr_decstatus, flr_decscontent, chat_pk))

        select_aidecs_pk = '''
            SELECT aidecs_pk FROM aidecs WHERE chat_pk = %s
        '''
        cursor.execute(select_aidecs_pk, (chat_pk,))
        aidecs_pk= cursor.fetchone()[0]

        insert_chatai = '''
            INSERT INTO chatai (aidecs_pk, chatmes_pk)
            VALUES (%s, %s)
        '''
        for number in number_list:
            cursor.execute(insert_chatai, (aidecs_pk, number))
        connection.commit()

    except Exception as e:
        print("Error:", str(e))  # 에러 메시지 출력
        connection.rollback()
        return jsonify({'error': str(e)}), 500

    finally:
        cursor.close()
        connection.close()

    # 결과 반환
    return jsonify({'flr_decstatus': flr_decstatus, 'flr_decscontent': flr_decscontent, 'chat_pk': chat_pk, '전체내용': response_data})

@app.route('/record/flr/<int:flr_pk>', methods=['GET'])
def get_record_script_by_flr(flr_pk):
    base_path = r'C:\lx-final-project\frontend\public\assets\record' #spring boot에서 저장한 파일 경로 사용
    file_path = os.path.join(base_path, f'record_{flr_pk}.m4a') #파일 이름도 spring boot에서 저장한 파일 이름 사용

    res = req_upload(file=file_path, completion='sync') #clova api 실행
    result = res.json()
    # 화자별 인식 결과 segment 추출
    segments = result.get('segments', [])
    speaker_segments = []
    for segment in segments:
        speaker_label = segment['speaker']['label']
        text = segment['text']
        speaker_segments.append({'speaker': speaker_label, 'text': text})

    # 화자별 인식 결과를 신고자와 피신고대상자로 매핑하여 순서대로 저장
    conversation_list = []
    for speaker_segment in speaker_segments:
        speaker_label = speaker_segment['speaker']
        text = speaker_segment['text']

        if speaker_label == '1':
            role = '신고자'
        elif speaker_label == '2':
            role = '피신고대상자'
        else:
            role = f'화자{speaker_label}'  # 화자 번호가 1, 2가 아닐 경우 대비
        conversation_list.append({role: text})

    connection = get_db_connection()
    cursor = connection.cursor(cursor_factory=DictCursor)

    # 데이터베이스에서 rec_pk 추출
    select_rec_pk = '''
            SELECT rec_pk FROM rec WHERE flr_pk = %s
        '''
    cursor.execute(select_rec_pk, (flr_pk,))
    rec_pk= cursor.fetchone()[0]

    # NULL이였던 flr 테이블의 rec_pk 수정
    update_flr_rec_pk = '''
            UPDATE flr SET rec_pk = %s WHERE flr_pk = %s
        '''
    cursor.execute(update_flr_rec_pk, (rec_pk, flr_pk))

    # 데이터베이스 recscr테이블에 녹취 script 저장
    for conversation in conversation_list:
            for role, text in conversation.items():
                user_pk_sender = None
                agent_pk_sender = None

                if role == '신고자':
                    user_pk_sender = 1
                elif role == '피신고대상자':
                    agent_pk_sender = 1

                insert_query = """
                INSERT INTO recscr (recscr_content, user_pk_sender, agent_pk_sender, rec_pk)
                VALUES (%s, %s, %s, %s)
                """
                cursor.execute(insert_query, (text, user_pk_sender, agent_pk_sender, rec_pk))

    connection.commit()

    # 저장된 recscr 테이블 기반 Gemini 사용해서 증거추출 코드 작성해야함
    connection = get_db_connection()
    cursor = connection.cursor(cursor_factory=DictCursor) 

    try:
        # flr_pk로부터 rec_pk를 조회
        query_get_rec_pk = '''
            SELECT rec_pk FROM flr WHERE flr_pk = %s
        '''
        cursor.execute(query_get_rec_pk, (flr_pk,))
        rec_pk_result = cursor.fetchone()

        if not rec_pk_result:
            return jsonify({'error': 'Chat PK not found for the given FLR PK'}), 404

        rec_pk = rec_pk_result['rec_pk']

        # rec_pk를 사용하여 스크립트 조회
        query_rec_script = '''
            SELECT rs.recscr_pk, rs.recscr_datetime, rs.recscr_content,
                   CASE WHEN rs.user_pk_sender IS NOT NULL THEN '신고자'
                        WHEN rs.agent_pk_sender IS NOT NULL THEN '피신고대상자'
                        ELSE '알수없음' END AS sender_type
            FROM recscr rs
            WHERE rs.rec_pk = %s
            ORDER BY rs.recscr_datetime ASC
        '''
        cursor.execute(query_rec_script, (rec_pk,))
        messages = cursor.fetchall()

        # 대화 내용을 문자열로 변환
        evidenceChat = ""
        for message in messages:
            evidenceChat += f"{message['recscr_pk']}.{message['sender_type']} : '{message['recscr_content']}', "
        evidenceChat = evidenceChat.rstrip(', ')

        print(evidenceChat)

        # flr 데이터 변수 저장
        query_flr = '''
            SELECT flr_cate_upper, flr_cate_middle, flr_cate_lower, flr_content
            FROM flr
            WHERE rec_pk = %s
        '''
        cursor.execute(query_flr, (rec_pk,))
        flr_data = cursor.fetchone()

        if not flr_data:
            raise ValueError("No FLR data found for the given chat PK")

        upperCategory = flr_data['flr_cate_upper']
        middleCategory = flr_data['flr_cate_middle']
        lowerCategory = flr_data['flr_cate_lower']
        reportDetails = flr_data['flr_content']

        # AI 응답 생성
        response = model.generate_content(
            "부동산 중개대상물에 대한 부당한 광고가 의심되는 상황입니다. 사용자의 신고 카테고리는 "
            + upperCategory + ", " + middleCategory + ", " + lowerCategory + "입니다."
            + "신고 내용은 " + reportDetails
            + "이며, 증빙자료는 다음과 같습니다: " + evidenceChat
            + "전체 대화를 검토하고, 신고 내용과 증빙자료가 실제로 부합하는지 판단해줘. 만약 증빙자료가 신고 내용과 실제로 부합하지 않는다면 그 이유도 설명해줘."
            + "부합 여부가 명확하지 않을 경우에도 반드시 '미부합'으로 간주해."
            + "출력 형식은 반드시 JSON 형식으로만 출력해줘.")
       

        response_text = response.text

        try:
            response_data = json.loads(response_text)
            print("Parsed JSON Data:", response_data)
        except json.JSONDecodeError as e:
            print("JSON Decode Error:", str(e))
            print("Final Processed Text:", response_text)
            raise

        flr_recdecstatus = response_data.get('부합여부')
        flr_recdecscontent = response_data.get('이유')
        recscr = response_data.get('증빙자료에서 추출된 부합내용')
        print("recscr:", recscr)
        print("recscr type:", type(recscr))

        if isinstance(recscr, dict):
            # 딕셔너리 키-값을 문자열로 병합
            combined_recscr = ' '.join([f"{key}: {value}" for key, value in recscr.items()])
        else:
            # 이미 문자열인 경우 그대로 사용
            combined_recscr = recscr

        numbers = re.findall(r'\b(\d+)\.\s*(피신고대상자|신고자)', combined_recscr)   # 정규표현식
        number_list = [int(num[0]) for num in numbers]  # 튜플의 첫 번째 요소만 가져와 숫자로 변환
        print(numbers)


        # 데이터베이스에 결과 저장
        connection = get_db_connection()
        cursor = connection.cursor()
        insert_query = '''
            INSERT INTO airecdecs (flr_recdecstatus, flr_recdecscontent, rec_pk)
            VALUES (%s, %s, %s)
        '''
        cursor.execute(insert_query, (flr_recdecstatus, flr_recdecscontent, rec_pk))

        select_airecdecs_pk = '''
            SELECT airecdecs_pk FROM airecdecs WHERE rec_pk = %s
        '''
        cursor.execute(select_airecdecs_pk, (rec_pk,))
        airecdecs_pk= cursor.fetchone()[0]

        insert_recscrai = '''
            INSERT INTO recscrai (airecdecs_pk, recscr_pk)
            VALUES (%s, %s)
        '''
        for number in number_list:
            cursor.execute(insert_recscrai, (airecdecs_pk, number))
        connection.commit()

    except Exception as e:
        print("Error:", str(e))  # 에러 메시지 출력
        connection.rollback()
        return jsonify({'error': str(e)}), 500

    finally:
        cursor.close()
        connection.close()

    # 결과 반환
    return jsonify({'flr_decstatus': flr_recdecstatus, 'flr_decscontent': flr_recdecscontent, 'chat_pk': rec_pk, '전체내용': response_data})

# 서버 실행 (디버그 모드 사용 시 코드 변경사항 자동 반영)
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
