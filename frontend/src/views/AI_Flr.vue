<script setup>
import { ref } from 'vue';
import axios from 'axios';

// data properties
const flrPk = ref(''); // 허위매물신고 PK 입력 변수
const chatMessages = ref([]); // 채팅 메시지 목록

// 플라스크 이용해서 문제의 대화 끌어오는 메서드
const fetchChatPk = async () => {
    try {
        console.log("Fetching chatPk for flrPk:", flrPk.value);
        const response = await axios.get(`/api/chat/flr/${flrPk.value}`);
        const chatPk = response.data.chat_pk;
        console.log("Fetched chatPk:", chatPk);

        if (chatPk) {
            await fetchChatMessages(chatPk);
        } else {
            console.error("채팅방을 찾을 수 없습니다.");
        }
    } catch (error) {
        console.error("Error fetching chatPk:", error);
    }
};

// 전체 대화 끌어오는 메서드
const fetchChatMessages = async (chatPk) => {
    try {
        console.log("Fetching chat messages for chatPk:", chatPk);
        const response = await axios.get(`/api/chat/${chatPk}/messages`);
        chatMessages.value = response.data;
        console.log("Fetched chat messages:", chatMessages.value);
    } catch (error) {
        console.error("Error fetching chat messages:", error);
    }
};
</script>

<template>
    <div class="d-flex flex-column flex-column-fluid justify-content-center align-items-center flex-grow-1">
        <div id="kt_app_content" class="app-content flex-column-fluid d-flex flex-grow-1 flex-column">
            <div id="kt_app_content_container" class="app-container container-xxl d-flex justify-content-center align-items-center flex-grow-1 p-5">
                <div class="d-flex flex-column justify-content-center align-items-center flex-grow-1">
                    <div>
                        <input type="text" v-model="flrPk" placeholder="허위매물신고 PK를 입력하세요" />
                        <button @click="fetchChatPk">채팅 내역 조회</button>
                    </div>
                    <div v-if="chatMessages.length">
                        <div v-for="message in chatMessages" :key="message.chatmesPk" 
                             :class="['message', { 'highlighted': message.isProblematic }]">
                            <strong>{{ message.senderType }} :</strong> {{ message.chatmesContent }}
                            <div>{{ message.chatmesDatetime }}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<style>
.message {
    border: 1px solid #ddd;
    padding: 8px;
    margin: 4px 0;
}

.highlighted {
    background-color: #ffe6e6; /* 문제 메시지에 대해 하이라이트 표시 */
    border-color: #ff0000;
}
</style>