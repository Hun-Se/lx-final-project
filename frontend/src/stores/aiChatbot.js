import { ref } from "vue";
import { defineStore } from "pinia";
import { requestAiChatbot} from "@/api/aiChatbot.js";

export const useAiChatbotStore = defineStore("aiChatStore", () => {
    const chatBotData = ref([]);
    const chatBotBody = ref({});
    async function fetchAiChatbot() {
        const data = await requestAiChatbot(chatBotBody.value);
        chatBotData.value.push(data);
    }
    return { chatBotData, chatBotBody, fetchAiChatbot };
});