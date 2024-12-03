<template>
  <header class="header">

    <div class="text-white" @click="goBack">
      <i class="bi bi-chevron-left text-white" ></i>
    </div>

    <!-- 헤더 제목 -->
    <h1 class="d-flex justify-content-center align-items-center fw-bold fs-5 text-white" style="line-height: 25px">
      <img class="img-logo" src="/assets/img/logo.png" alt="" @click="goToHome" />
      <span class="ms-3">{{ title }}</span>
    </h1>

    <!-- 사용자 정보 또는 로그인 버튼 -->
    <div class="d-flex align-items-center">
      <template v-if="isLoggedIn">
        <!-- 로그인 상태 -->
        <span class="text-white me-3">{{ username }}</span>
        <span class="text-white" @click="logout">Logout</span>
      </template>
      <template v-else>
        <!-- 비로그인 상태 -->
        <span class="text-white" @click="goToLogin">Login</span>
      </template>
    </div>
  </header>
</template>

<script setup>
import { ref, watch, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user"; // Pinia userStore 가져오기

defineProps({
  title: {
    type: String
  }
})

    const router = useRouter();
const userStore = useUserStore();

// 로그인 상태와 사용자 이름
const isLoggedIn = ref(false);
const username = ref("");

// userStore.user 상태 변경 감지
watch(
  () => userStore.user,
  (newUser) => {
    if (newUser && newUser.userName) {
      isLoggedIn.value = true;
      username.value = newUser.userName;
    } else {
      isLoggedIn.value = false;
      username.value = "";
    }
  },
  { immediate: true } // 컴포넌트가 마운트될 때도 즉시 실행
);

// 로그아웃 함수
const logout = () => {
  userStore.clearUser(); // Pinia 스토어 초기화
  isLoggedIn.value = false;
  username.value = "";
  router.push("/login"); // 로그아웃 후 로그인 페이지로 이동
};

// 뒤로 가기 함수
const goBack = () => {
  router.go(-1); // 이전 페이지로 이동
};


// 로그인 페이지로 이동
const goToLogin = () => {
  router.push("/login");
};

// 홈으로 이동 함수
const goToHome = () => {
  router.push("/mobile_home");
};

// Pinia 상태 초기화
onMounted(() => {
  if (userStore.initializeUser) {
    userStore.initializeUser();
  }
});
</script>

<style scoped>
/* 헤더 */
.header {
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1000;
  display: flex;
  padding: 0.5rem;
  justify-content: space-between;
  align-items: center;
  background-color: var(--color-bg-blue1);
  border-bottom: 1px solid #ddd;
}

.logo h1 {
  font-weight: bold;
  color: var(--color-bg-white);
  cursor: pointer;
}

.img-logo {
  width: 3rem;
  height: 3rem;
}

.btn-sm {
  padding: 0.3rem 0.6rem;
  font-size: 0.875rem;
}
</style>
