import { useChatStore } from '@/stores/chat';

let webSocket;
let chatStore;

export const initializeWebSocket = (userName, store) => {
    chatStore = store;

    // WebSocket이 이미 열려 있는지 확인
    if (webSocket && webSocket.readyState === WebSocket.OPEN) {
        console.log("WebSocket already connected");
        return;
    }

    // WebSocket 연결 초기화
    // webSocket = new WebSocket("ws://172.168.10.40:9000/ws/chat");
    webSocket = new WebSocket("ws://localhost:9000/ws/chat");
    // WebSocket 연결이 열리면 호출
    // webSocket.onopen = () => {
    //     console.log("WebSocket connection opened");
    //     const enterMessage = {
    //         chatmesContent: `${userName}님이 입장하였습니다.`,
    //         userPkSender: chatStore.userPk,
    //         agentPkSender: null,
    //         chatPk: 1,
    //     };
    //     sendMessage(enterMessage); // 입장 메시지 전송
    //     chatStore.messages.push({ ...enterMessage, isOwnMessage: true }); // 본인 메시지
    // };

    // WebSocket 메시지 수신
    webSocket.onmessage = (msg) => {
        const data = JSON.parse(msg.data);
        console.log("Received message from WebSocket:", data);

        // 메시지 중복 확인
        const isDuplicate = chatStore.messages.some(
            (message) =>
                message.chatmesContent === data.chatmesContent &&
                message.userPkSender === data.userPkSender &&
                message.chatPk === data.chatPk
        );

        if (!isDuplicate) {
            const isOwnMessage = data.userPkSender === chatStore.userPk;
            chatStore.messages.push({ ...data, isOwnMessage });

            // 본인의 메시지가 아니면 REST API로 저장
            if (!isOwnMessage) {
                saveMessageToDB(data);
            }
        }
    };

    // WebSocket 연결이 닫힐 때 호출
    webSocket.onclose = () => {
        console.log("WebSocket connection closed");
        const exitMessage = {
            chatmesContent: `${userName} 퇴장하였습니다.`,
            userPkSender: chatStore.userPk,
            agentPkSender: null,
            chatPk: 1,
        };
        sendMessage(exitMessage);

        chatStore.messages.push({
            chatmesContent: `${userName}님이 퇴장하셨습니다.`,
            userPkSender: null,
            agentPkSender: null,
            chatPk: 1,
        });
    };

    // WebSocket에서 오류가 발생할 때 호출
    webSocket.onerror = (error) => {
        console.error("WebSocket error:", error);
    };
};

// 메시지를 서버로 전송
export const sendMessage = (messageData) => {
    if (webSocket && webSocket.readyState === WebSocket.OPEN) {
        webSocket.send(JSON.stringify(messageData));
    } else {
        console.error("WebSocket is not open. Current state:", webSocket?.readyState || "NOT_INITIALIZED");
    }
};

// REST API를 통해 메시지를 데이터베이스에 저장
const saveMessageToDB = (data) => {
    fetch("http://localhost:9000/chat/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    })
        .then((response) => {
            if (!response.ok) {
                console.error("Failed to save message:", response.statusText);
            } else {
                console.log("Message saved to DB");
            }
        })
        .catch((error) => {
            console.error("Error while saving message:", error);
        });
};
