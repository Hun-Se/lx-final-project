import { defineStore } from "pinia";
import { ref } from "vue";

export const useMobileStore = defineStore("mobileStore", () => {
  const isMobile = ref(false);

  const mobile = window.matchMedia(`(min-width: 700px)`);
  isMobile.value = !mobile.matches;

  function handleMobileChange(e) {
    isMobile.value = !e.matches;
  }

  mobile.addEventListener("change", handleMobileChange);
  return { isMobile };
});
