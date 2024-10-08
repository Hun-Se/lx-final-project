<template>
  <component :is="renderHeader()" />
  <div id="app">
    <div id="conversation" style="margin-top: 10ex;" v-if="!chatStore.isInChat">
      <div class="input-container">
        <label for="userName" style="font-weight: bold; font-size: 1.2em;">
          이름:
          <input id="userName" v-model="userNameInput" type="text" required />
        </label>
        <button
          id="enter-button"
          @click="enterChat(userNameInput)"
          class="btn btn-primary btn-lg"
        >
          입장하기
        </button>
      </div>
    </div>
    <chatting-input-view v-else />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useChatStore } from '@/stores/chat';
import { useRouter } from 'vue-router';
import ChattingInputView from './ChattingInputView.vue'; // 채팅방 컴포넌트 불러오기
import { initializeWebSocket } from '@/api/chat';

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
const router = useRouter();
const userNameInput = ref("");

// 채팅 입장 처리
const enterChat = (name) => {
  if (chatStore.isInChat) return; // 이미 채팅에 입장한 경우 처리
  chatStore.enterChat(name); 
  initializeWebSocket(name, chatStore); 
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

.input-container {
  display: flex; 
  justify-content: space-between; 
  align-items: center; 
  gap: 10px; 
  margin-top: 10px;
}

input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  flex-grow: 1; 
}

button {
  margin-right: 102ex;
  width: 14ex;
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
