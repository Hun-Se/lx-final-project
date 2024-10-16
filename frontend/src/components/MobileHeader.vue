<template>
  <header class="header">
    <div class="logo cursor-pointer" @click="goToHome">
      <h1>내매물받아줘</h1>
    </div>

    <nav class="navigation"></nav>
  </header>
</template>

<script setup>
import { ref, onMounted } from "vue";

const isLoggedIn = ref(false);
const username = ref("");

// 컴포넌트가 마운트될 때 localStorage에서 사용자 이름을 가져옴
onMounted(() => {
  const storedUsername = localStorage.getItem("username");
  if (storedUsername) {
    isLoggedIn.value = true;
    username.value = storedUsername;
  }
});

// 로그아웃 함수
const logout = async () => {
  try {
    // 서버에 로그아웃 요청 (세션 무효화)
    const response = await axios.post("/api/logout"); // 경로 확인
    if (response.status === 200) {
      localStorage.removeItem("username"); // localStorage에서 사용자 정보 삭제
      isLoggedIn.value = false;

      router.replace({ path: "/" }); // 로그아웃 후 메인 페이지로 리다이렉트
    }
  } catch (error) {
    console.error("로그아웃 중 오류 발생:", error);
  }
};

// 홈으로 이동 함수
function goToHome() {
  router.replace({ path: "/" });
}
</script>

<style scoped>
/* 헤더 */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 2rem;
  background-color: #fff;
  border-bottom: 1px solid #ddd;
}

.logo h1 {
  font-weight: bold;
  color: rgb(0, 119, 255);
  cursor: pointer;
}

.navigation {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-grow: 1;
}

.nav-center {
  display: flex;
  justify-content: left;
  flex-grow: 1;
  list-style: none;
  margin: 0;
  padding: 0px 0px 0px 20px;
}

.nav-right {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: flex-end;
  white-space: nowrap; /* 한 줄로 표시되도록 강제 */
}

.nav-right li {
  margin: 0 10px; /* 여백을 추가해서 간격 조절 */
  white-space: nowrap; /* 이정현님을 한 줄로 표시 */
  overflow: hidden; /* 텍스트가 길면 넘어가는 부분을 숨김 */
  text-overflow: ellipsis; /* 너무 길면 생략(...) 처리 */
  max-width: 100px; /* 너무 긴 이름을 제한 */
}

.nav-center li,
.nav-right li {
  margin: 0 15px;
}

.nav-center li a,
.nav-right li a {
  text-decoration: none;
  color: black;
  font-weight: bold;
}

.nav-center li a:hover,
.nav-right li a:hover {
  color: #d81f26;
}
</style>
