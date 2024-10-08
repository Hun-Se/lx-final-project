<template>
  <component :is="renderHeader()" />
  <div>
    <h1 id="room-name" style="margin-left: 13ex; margin-top: 10ex;">채팅방</h1>
    <div id="conversation" class="chat-box">
      <div id="response">
        <div v-for="(msg, index) in chatStore.messages" :key="index" class="chat-message" :class="{
          'my-message': msg.userName === chatStore.userName,
          'other-message': msg.userName !== chatStore.userName
        }">
          <strong>{{ msg.userName }}</strong>: {{ msg.message }} <em>({{ msg.time }})</em>
        </div>
      </div>
      
      <div class="input-container">
        <input type="text" v-model="newMessage" placeholder="메세지를 입력해주세요." required />
        <button @click="submitMessage" class="btn btn-primary">전송하기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useChatStore } from '@/stores/chat';
import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import { useMobileStore } from "@/stores/mobile";
import { storeToRefs } from "pinia";

const store = useMobileStore();
const { isMobile } = storeToRefs(store);

const renderHeader = () => {
  if (isMobile.value) {
    return MobileHeader;
  } else {
    return Header;
  }
};

const chatStore = useChatStore();
const newMessage = ref("");

// 메시지 전송 처리
const submitMessage = () => {
  if (newMessage.value.trim() === "") return; // 빈 메시지 전송 방지
  chatStore.sendMessageToChat(newMessage.value); // 메시지 전송 로직
  newMessage.value = ""; // 입력창 비우기
};
</script>

<style scoped>
#conversation {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  width: 180ex;
  margin: 0 auto;
  max-width: 100%;
}

#response {
  max-height: 300px;
  overflow-y: auto;
  margin-bottom: 10px;
}

.chat-message {
  margin-bottom: 5px;
  padding: 8px;
  border-radius: 5px;

}

.my-message {
  background-color: #e7f4fe;
  align-self: flex-end;
  /* 사용자 메시지 오른쪽 정렬 */
  text-align: right;
  /* 사용자 메시지 오른쪽 정렬 */
}

.other-message {
  background-color: #fdecf8;
  align-self: flex-start;
  /* 상대방 메시지 왼쪽 정렬 */
  text-align: left;
  /* 상대방 메시지 왼쪽 정렬 */
}


.input-container {
  display: flex;
  justify-content: space-between; 
  align-items: center; 
  gap: 10px; 
}

input {
  flex-grow: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
