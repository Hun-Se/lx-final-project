import { ref } from "vue";
import { defineStore } from "pinia";
import { getExamples } from "@/api/example";
export const useExampleStore = defineStore("example", () => {
  const data = ref([]);
  async function fetchGetExamples() {
    data.value = await getExamples();
    console.log(data);
  }
  return { data, fetchGetExamples };
});