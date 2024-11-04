<template>
  <header class="header">
    <div class="logo cursor-pointer" @click="goToHome">
      <h1>믿음집</h1>
    </div>

    <nav class="navigation">
      <ul class="nav-center">
        <li><a @click="goToMap()" class="cursor-pointer">부동산</a></li>
        <li>
          <a @click="goToAiRealEstate()" class="cursor-pointer">AI가격예측</a>
        </li>
        <li><a @click="goToBoard()" class="cursor-pointer">지역게시판</a></li>
        <li><a @click="goToMypage" class="cursor-pointer">마이페이지</a></li>
      </ul>
      <ul class="nav-right cursor-pointer">
        <li v-if="isLoggedIn">{{ username }}님</li>
        <li v-if="isLoggedIn">
          <a @click="logout" class="cursor-pointer">Logout</a>
        </li>
        <li v-else>
          <a @click="goToLoginpage" class="cursor-pointer">Login</a>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
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

// AI 가격 예측 페이지로 이동
function goToAiRealEstate() {
  router.replace({ path: "/ai_real_estate" });
}

// 매물지도 페이지로 이동
function goToMap() {
  router.replace({ path: "/map" });
}

// 중개 수수료 경매 페이지로 이동
function goToAuction() {
  router.replace({ path: "/user_auction" });
}

// 로그인 페이지로 이동 함수
function goToLoginpage() {
  router.replace({ path: "/login" });
}

// 마이페이지로 이동 함수
function goToMypage() {
  const userPk = sessionStorage.getItem("userPk");
  if (userPk) {
    router.push({ path: "/my_page" });
  } else {
    alert("로그인이 필요합니다.");
    goToLoginpage(); // 로그인 안 된 상태에서 마이페이지 접근 시 로그인 페이지로 이동
  }
}

// 게시판 페이지로 이동 함수
function goToBoard() {
  router.replace({ path: "/board" });
}

// 버거킹 페이지로 이동 함수
function goToBurgerking() {
  router.replace({ path: "/burgerking" });
}

// 버거킹 페이지로 이동 함수
function goToBurgerGame() {
  router.replace({ path: "/burgerMenu" });
}
</script>

<style scoped>
/* 헤더 */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  border-bottom: 1px solid #ddd;
  padding: 2rem;
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
