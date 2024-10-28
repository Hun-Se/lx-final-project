<template>
  <!-- Header -->
<header class="header">
     
        <span class="logo cursor-pointer" id="logo_h1" @click="goToHome">내매물받아줘</span>

        <!-- 검색 바 -->
        <span class="search-bar" id="search" style="margin-left: 1ex;">
          <input class="orgin"
            type="text"
            v-model="location"
            placeholder="위치를 입력하세요"
          />
          에서
          <select class="trans" v-model="selectedTransport">
            <option disabled value="">이동수단</option>
            <option value="walking">도보</option>
            <option value="driving">자차</option>
            <option value="public_transport">대중교통</option>
          </select>
          로
        <input class="input-hour"
            type="number"
            v-model.number="hours"
            placeholder="시간 입력"
            min="0"
            step="1"
          />
          내에
          <span> 시간 </span>
          <input class="input-min"
            type="number"
            v-model.number="minutes"
            placeholder="분 입력"
            min="0"
            max="59"
            step="1"
          /><span>분 내에 있는 매물을 </span>
          <button @click="submit" class="btn btn-primary btn-sm">검색</button>
        </span>   
      

        <div style="display: flex; align-items: center;">

        <a @click="goToMypage" class="cursor-pointer">
          <i class="bi bi-box2-heart-fill" style="color: #007bff; font-size: 24px;"></i>
        </a>
        <span style="font-weight: bold; margin-left: 80px;">{{ username }}님</span>
      </div>
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


// 마이페이지로 이동 함수
function goToMypage() {
  if (isLoggedIn.value) {
    router.replace({ path: "/mypage" });
  } else {
    alert("로그인이 필요합니다.");
    goToLoginpage(); // 로그인 안 된 상태에서 마이페이지 접근 시 로그인 페이지로 이동
  }
}

// 게시판 페이지로 이동 함수
function goToBoard() {
  router.replace({ path: "/board" });
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

#logo_h1 {
  font-weight: bold;
  color: rgb(0, 119, 255);
  cursor: pointer;
}

#search {
  font-weight: bold;
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
