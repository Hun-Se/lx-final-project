import { useChatStore } from '@/stores/chat'; // Pinia 스토어를 가져옵니다.

let webSocket;
let chatStore; // chatStore 변수를 선언합니다.

export const initializeWebSocket = (userName, store) => {
    chatStore = store; // chatStore를 초기화합니다.

    // WebSocket이 이미 열려 있는지 확인합니다.
    if (webSocket && webSocket.readyState === WebSocket.OPEN) { 
        console.log("WebSocket already connected");
        return; // 이미 연결된 경우
    }

    // WebSocket 연결을 초기화합니다.
    webSocket = new WebSocket("ws://localhost:9000/ws/chat");

    // WebSocket 연결이 열리면 호출되는 이벤트 핸들러입니다.
    webSocket.onopen = () => {
        console.log("WebSocket connection opened");
        const enterMessage = { 
            userName: userName, 
            message: `${userName} 입장하였습니다.`,
            time: new Date().toLocaleTimeString() // 현재 시간 추가
        }; 
        sendMessage(enterMessage); // 입장 메시지 전송
        chatStore.messages.push(enterMessage); // 입장 메시지를 스토어에 추가
    };

    // 서버에서 메시지를 수신할 때 호출되는 이벤트 핸들러입니다.
    webSocket.onmessage = (msg) => {
        const data = JSON.parse(msg.data);
        console.log("Received message:", data); // 수신한 메시지 로그

        // 중복된 메시지가 스토어에 추가되는 것을 방지합니다.
        const lastMessage = chatStore.messages[chatStore.messages.length - 1];
        if (!lastMessage || lastMessage.message !== data.message || lastMessage.time !== data.time) {
            chatStore.messages.push(data); // Pinia 스토어의 messages에 추가
        }
    };
    
    // WebSocket 연결이 닫힐 때 호출되는 이벤트 핸들러입니다.
    webSocket.onclose = () => {
        console.log("WebSocket connection closed");
        const exitMessage = { 
            userName: userName, 
            message: `${userName} 퇴장하였습니다.`,
            time: new Date().toLocaleTimeString() // 현재 시간 추가
        }; 
        sendMessage(exitMessage); // 퇴장 메시지 전송
        chatStore.messages.push({
            userName: '시스템',
            message: `${userName}님이 퇴장하셨습니다.`,
            time: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) // 시스템 메시지에 시간 추가
        });
    };

    // WebSocket에서 오류가 발생할 때 호출되는 이벤트 핸들러입니다.
    webSocket.onerror = (error) => {
        console.error("WebSocket error:", error);
    };
};

// 메시지를 서버로 전송하는 함수입니다.
export const sendMessage = (messageData) => {
    if (webSocket && webSocket.readyState === WebSocket.OPEN) {
        webSocket.send(JSON.stringify(messageData)); // 메시지를 JSON 형식으로 전송
    } else {
        console.error("WebSocket is not open. Current state:", webSocket.readyState);
    }
};