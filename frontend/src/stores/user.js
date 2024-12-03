import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { requestUserGlobal } from "@/api/user";

export const useUserStore = defineStore("user", () => {
  // 사용자 정보 상태
  const user = ref(null);

  // 사용자 이름
  const userName = computed(() => user.value?.userName || "");

  // 초기화 함수: 세션 스토리지에서 복원
  function initializeUser() {
    const savedUser = sessionStorage.getItem("user");
    if (savedUser) {
      user.value = JSON.parse(savedUser);
    }
  }

  // 사용자 정보 요청
  async function fetchUser(data) {
    try {
      const response = await requestUserGlobal(data);
      if (response) {
        user.value = response;
        sessionStorage.setItem("user", JSON.stringify(response)); // 세션에 저장
      }
    } catch (error) {
      console.error(`Error fetching user data: ${error.message}`);
    }
  }

  // 사용자 정보 초기화 (로그아웃 시 호출)
  function clearUser() {
    user.value = null;
    sessionStorage.removeItem("user"); // 세션 스토리지 초기화
  }

  return {
    user,
    userName,
    fetchUser,
    initializeUser,
    clearUser,
  };
});
