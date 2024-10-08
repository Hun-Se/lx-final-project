<template>
  <div id="app">
    <div id="conversation" style="margin-top: 10ex;" v-if="!chatStore.isInChat">
      <label for="userName" style="font-weight: bold; font-size: 1.2em;">
        이름:
        <input id="userName" v-model="userNameInput" type="text" required />
      </label>
      <button
        id="enter-button"
        @click="enterChat(userNameInput)"
        class="btn btn-primary btn-lg btn-block"
      >
        입장
      </button>
    </div>
    <!-- 채팅방 컴포넌트를 불러오기 -->
    <chatting-input-view v-else />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useChatStore } from '@/stores/chat'; 
import { useRouter } from 'vue-router'; 
import ChattingInputView from './ChattingInputView.vue'; // 채팅방 컴포넌트 불러오기
import { initializeWebSocket } from '@/api/chat';

const chatStore = useChatStore(); 
const router = useRouter(); 
const userNameInput = ref("");

// 채팅 입장 처리
const enterChat = (name) => {
  if (chatStore.isInChat) return; // 이미 채팅에 입장한 경우 처리
  chatStore.enterChat(name); 
  initializeWebSocket(name, chatStore); // 사용자 이름과 chatStore를 인자로 전달
  router.push('/chatting'); // 채팅 페이지로 이동
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
  align-self: flex-end; /* 사용자 메시지 오른쪽 정렬 */
  text-align: right; /* 사용자 메시지 오른쪽 정렬 */
}

.other-message {
  background-color: #fdecf8; 
  align-self: flex-start; /* 상대방 메시지 왼쪽 정렬 */
  text-align: left; /* 상대방 메시지 왼쪽 정렬 */
}

form {
  display: flex;
  flex-direction: column;
  margin: 20px;
}

label {
  margin-bottom: 10px;
}

input {
  margin-bottom: 10px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 5px; 
}

button {
  padding: 5px;
  border-radius: 5px; 
}
</style>
