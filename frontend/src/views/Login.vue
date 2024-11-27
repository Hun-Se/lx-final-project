<template>
  <div class="login-container">
    <div class="login-form-wrapper">
      <h2 class="login-title">로그인</h2>
      <form @submit.prevent="login" class="login-form">
        <!-- 사용자 ID 입력 -->
        <div class="input-group">
          <label for="userId">User ID</label>
          <input
            v-model="userId"
            type="text"
            id="userId"
            required
            placeholder="Enter your User ID"
          />
        </div>

        <!-- 비밀번호 입력 -->
        <div class="input-group">
          <label for="userPw">Password</label>
          <input
            v-model="userPw"
            type="password"
            id="userPw"
            required
            placeholder="Enter your password"
          />
        </div>

        <!-- 로그인 버튼 -->
        <button type="submit" class="login-btn">Login</button>
      </form>

      <!-- 계정 생성으로 이동하는 버튼 -->
      <button @click="goToCreateAccount" class="create-account-btn">
        Create Account
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user"; // Pinia userStore 가져오기

// 입력 값 상태
const userId = ref("");
const userPw = ref("");

// Vue Router
const router = useRouter();

// Pinia 스토어
const userStore = useUserStore();

// 로그인 처리 함수
const login = async () => {
  try {
    const response = await axios.post("/api/users/login", {
      userId: userId.value,
      userPw: userPw.value,
    });

    // 성공 처리
    if (response.status === 200 && response.data) {
      console.log("Login Success:", response.data);

      // Pinia 상태에 사용자 정보 저장
      userStore.user = response.data;
      sessionStorage.setItem("user", JSON.stringify(response.data)); // 세션 스토리지에 저장

      router.push("/mobile_home"); // 메인 페이지로 이동
    } else {
      alert("로그인 실패: 잘못된 사용자 정보입니다.");
    }
  } catch (error) {
    if (error.response) {
      // 서버 에러
      console.error("Server error:", error.response.data);
      alert(`로그인 실패: ${error.response.data.message || "서버 오류 발생"}`);
    } else if (error.request) {
      // 네트워크 에러
      console.error("Network error:", error.message);
      alert("로그인 실패: 네트워크 문제로 서버에 연결할 수 없습니다.");
    } else {
      // 기타 에러
      console.error("Login error:", error.message);
      alert("로그인 실패: 알 수 없는 문제가 발생했습니다.");
    }
  }
};

// 계정 생성 페이지로 이동
const goToCreateAccount = () => {
  router.push("/create_account");
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: url("/assets/img/real_estate_bg.jpg"); /* 부동산 배경 이미지 */
  background-size: cover;
  background-position: center;
}

.login-form-wrapper {
  background-color: rgba(255, 255, 255, 0.9); /* 투명한 배경 */
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  text-align: center;
}

.login-title {
  font-size: 2.5em;
  margin-bottom: 20px;
  color: #2c3e50;
}

.input-group {
  margin-bottom: 15px;
  text-align: left;
}

.input-group label {
  display: block;
  font-size: 1.2em;
  color: #34495e;
  margin-bottom: 5px;
}

.input-group input {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.login-btn {
  width: 100%;
  padding: 12px;
  background-color: #27ae60;
  color: white;
  font-size: 1.2em;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-btn:hover {
  background-color: #2ecc71;
}

.create-account-btn {
  margin-top: 15px;
  padding: 10px;
  background-color: #3498db;
  color: white;
  font-size: 1em;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
}

.create-account-btn:hover {
  background-color: #2980b9;
}
</style>
