import { ref } from "vue";
import { defineStore } from "pinia";
import { requestAiChatbot} from "@/api/aiChatbot.js";

export const useMapInstanceStore = defineStore("mapInstanceStore", () => {
    const map = ref(null);
    return { map };
});