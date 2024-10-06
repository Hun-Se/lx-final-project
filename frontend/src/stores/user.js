import { ref } from "vue";
import { defineStore } from "pinia";
import { requestUserGlobal } from "@/api/user";

export const useUserStore = defineStore("user", () => {
  // 사용자 정보 초기화
  const user = ref(null); // 초기값을 null로 설정

  // 사용자 정보 요청
  async function fetchUser(data) {
    try {
      const repsonse = await requestUserGlobal(data);
      if (repsonse && repsonse.data) {
        user.value = repsonse.data; // 스토어에 데이터 저장
      } else {
        console.warn("No user data received"); // 데이터가 없을 때 경고
      }
    } catch (error) {
      console.error(`Error fetching user data: ${error.message}`); // 에러 로깅
    }
  }

  return {
    user,
    fetchUser,
  };
});
