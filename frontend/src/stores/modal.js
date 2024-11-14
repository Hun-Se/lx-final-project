import { defineStore } from "pinia";
import { ref } from "vue";

export const useRegisterPrpModalStore = defineStore("registerPrpModal", () => {
  const isModalOpen = ref(false);
  const selected = ref(null);

  function openModal() {
    isModalOpen.value = true;
  }

  function closeModal() {
    isModalOpen.value = false;
  }

  return { isModalOpen, openModal, closeModal, selected };
});

export const usePublicLedgerModalStore = defineStore(
  "publicLedgerModal",
  () => {
    const isModalOpen = ref(false);
    const docNameRef = ref("");

    function openModal() {
      isModalOpen.value = true;
    }

    function closeModal() {
      isModalOpen.value = false;
      docNameRef.value = ""; // 모달닫기시 초기화 필요함
    }

    return { isModalOpen, docNameRef, openModal, closeModal };
  },
);
