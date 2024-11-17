from flask import Flask, request, jsonify
import mysql.connector
import psycopg2
import json
import google.generativeai as genai
import re
from flask_cors import CORS
from psycopg2.extras import DictCursor

# API 설정
genai.configure(api_key="AIzaSyCCli_Zt0gxb16rl8s6HrRISFVZ5nSgd9U")
model = genai.GenerativeModel("gemini-1.5-flash")

# Flask 서버 인스턴스 생성
app = Flask(__name__)
CORS(app)

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
            evidenceChat += f"{message['chatmes_pk']}.{message['sender_type']} : \"{message['chatmes_content']}\", "
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
            + "출력 형식은 반드시 JSON 형식으로만 출력해줘. 예를 들어 {'부합여부': '부합 또는 미부합', '이유': '설명', '증빙자료에서 추출된 부합내용': '내용 또는 미부합'} 형식으로. 증빙자료에서 추출된 부합내용은 반드시 대화내용부분을 사용해줘 신고자와 피신고대상자도 구분해서 그리고 신고자와 피신고대상자 앞에있는 숫자도 그대로 가져와줘")
            
        response_text = response.text.strip("```json").strip("```").strip()
        response_data = {}
        response_data = json.loads(response_text)

        flr_decstatus = response_data.get('부합여부')
        flr_decscontent = response_data.get('이유')
        chatmes = response_data.get('증빙자료에서 추출된 부합내용')
        print(chatmes)
        numbers = re.findall(r'\b(\d+)(?=\.(피신고대상자|신고자))', chatmes)  # 정규표현식
        numbers = [int(num[0]) for num in numbers]  # 튜플의 첫 번째 요소만 가져와 숫자로 변환
        print(numbers)


        # 데이터베이스에 결과 저장
        connection = get_db_connection()
        cursor = connection.cursor()
        insert_query = '''
            INSERT INTO aidecs (flr_decstatus, flr_decscontent, chat_pk)
            VALUES (%s, %s, %s)
        '''
        cursor.execute(insert_query, (flr_decstatus, flr_decscontent, chat_pk))

        selec_aidecs_pk = '''
            SELECT aidecs_pk FROM aidecs WHERE chat_pk = %s
        '''
        cursor.execute(selec_aidecs_pk, (chat_pk,))
        aidecs_pk= cursor.fetchone()[0]

        insert_chatai = '''
            INSERT INTO chatai (aidecs_pk, chatmes_pk)
            VALUES (%s, %s)
        '''
        for number in numbers:
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

# 서버 실행 (디버그 모드 사용 시 코드 변경사항 자동 반영)
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
