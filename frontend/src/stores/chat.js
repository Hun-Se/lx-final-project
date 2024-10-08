
import { defineStore } from 'pinia';
import { sendMessage as sendMessageToServer } from '@/api/chat';

export const useChatStore = defineStore('chat', {
  state: () => ({
    isInChat: false, // 초기 값
    userName: '', // 사용자 이름
    messages: [], // 메시지 리스트
  }),
  
  actions: {
    enterChat(userName) {
      this.userName = userName;
      this.isInChat = true; // 채팅에 입장하면 true로 변경
    },
    
    sendMessageToChat(content) {
      const message = {
        userName: this.userName,
        message: content,
        time: new Date().toLocaleTimeString(),
      };
      this.messages.push(message); // 사용자 메시지 추가
      console.log(this.messages)
      sendMessageToServer(message); // 서버로 메시지 전송

      // 시스템 메시지 처리
      if (content.includes("입장하였습니다.")) {
        this.messages.push({
          userName: '시스템',
          message: content,
          time: new Date().toLocaleTimeString(),
        });
      }
    }
  }
});
