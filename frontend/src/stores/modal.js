import { defineStore } from "pinia";
import { ref } from "vue";

export const useRegisterPrpModalStore = defineStore("registerPrpModal", () => {
  const isModalOpen = ref(false);
  const selected = ref(null);

  function openModal() {
    isModalOpen.value = true;
    console.log(isModalOpen);
  }

  function closeModal() {
    isModalOpen.value = false;
    console.log("클릭");
  }

  return { isModalOpen, openModal, closeModal, selected };
});
