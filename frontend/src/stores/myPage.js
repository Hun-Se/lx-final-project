import { defineStore } from "pinia";
import { ref } from "vue";

export const useMyPageStore = defineStore("myPageStore", () => {
  const menuList = ["내매물보기", "관심매물", "임장현황"];
  const selectPageNum = ref(0);

  return { menuList, selectPageNum };
});
