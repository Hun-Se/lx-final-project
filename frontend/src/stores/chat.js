import { defineStore } from 'pinia';
import { sendMessage as sendMessageToServer } from '@/api/chat';

export const useChatStore = defineStore('chat', {
  state: () => ({
    isInChat: false, // 초기 값
    userPk: null, // 사용자 PK
    userName: '', // 사용자 이름
    messages: [], // 메시지 리스트
  }),
  
  actions: {
    // 채팅방에 입장할 때 사용자 정보 설정
    enterChat(userName, userPk) {
      this.userName = userName;
      this.userPk = userPk;
      this.isInChat = true; // 채팅에 입장하면 true로 변경
    },
    
    // 메시지를 서버와 상태에 추가
    sendMessageToChat(content) {
      const message = {
        chatmesContent: content,
        userPkSender: this.userPk,
        agentPkSender: null, // 일반 사용자로 가정
        chatPk: 1,
      };
      this.messages.push(message); // 사용자 메시지 추가
      console.log("Messages after sending:", this.messages);
      sendMessageToServer(message); // 서버로 메시지 전송
    }
  }
});
