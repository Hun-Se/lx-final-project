import { apiInstance } from "./index.js";

const api = apiInstance();

export const requestAiChatbot = async (body) => {
    const url = "api/chatbot";
    try {
        const response = await api.post(url, body);
        return response.data;
    } catch (err) {
        console.error(`서버 요청 시 에러 : ${err}`);
        return null;
    }
};
