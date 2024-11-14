<template>
  <header class="header">
    <span class="logo cursor-pointer" id="logo_h1" @click="goToHome"
      >믿음집</span
    >

    <!--등시간 검색-->
    <div style="font-size: 20px">
      <div style="display: flex; align-items: center">
        <input
          class="input-location text-center"
          style="margin-right: 10px; margin-left: 10px; width: 400px"
          v-model="location"
          placeholder="위치를 입력해주세요"
        />
        에서

        <select
          v-model="selectedTransport"
          style="
            font-size: 20px;
            height: 40px;
            border-radius: 10px;
            margin-right: 10px;
            margin-left: 20px;
          "
        >
          <option disabled selected>이동수단</option>
          <option value="walking">도보</option>
          <option value="driving">자차</option>
          <option value="public_transport">대중교통</option></select
        >로

        <input
          class="input-time text-center"
          type="number"
          v-model.number="hours"
          placeholder="0"
          min="0"
        />
        시간

        <input
          class="input-time text-center"
          type="number"
          v-model.number="minutes"
          placeholder="0"
          min="0"
          max="59"
        />
        분

        <span style="margin-left: 10px; margin-right: 20px"
          >내에 있는 매물</span
        >
        <button @click="submit" type="button" class="btn btn-primary btn-lg">
          검색
        </button>
      </div>
    </div>

    <div style="display: flex; align-items: center">
      <a @click="goToMypage" class="cursor-pointer">
        <i
          class="bi bi-box2-heart-fill"
          style="color: #007bff; font-size: 24px"
        ></i>
      </a>
      <span style="font-weight: bold; margin-left: 80px">{{ username }}님</span>
    </div>
  </header>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();
const isLoggedIn = ref(false);
const username = ref("");
const location = ref("");
const selectedTransport = ref("");
const hours = ref("");
const minutes = ref("");
const isochrone = ref(null); // 폴리곤 객체
// 컴포넌트가 마운트될 때 localStorage에서 사용자 이름을 가져옴
onMounted(() => {
  const storedUsername = sessionStorage.getItem("username");
  if (storedUsername) {
    isLoggedIn.value = true;
    username.value = storedUsername;
  }
});
// 검색 및 폴리곤 그리기
function submit() {
  const totalMinutes =
    (parseInt(hours.value) || 0) * 3600 + (parseInt(minutes.value) || 0) * 60;

  if (!location.value || !selectedTransport.value || totalMinutes === 0) {
    alert("모든 필드를 올바르게 입력해주세요.");
    return;
  }

  geocodeAddress(location.value)
    .then((coords) => {
      return axios.get(`/api/isochrone/search`, {
        params: {
          lat: coords.lat,
          lng: coords.lng,
          transport: selectedTransport.value,
          duration: totalMinutes,
        },
      });
    })
    .then((response) => {
      drawIsochrone(response.data);
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("검색 중 오류가 발생했습니다.");
    });
}

// 주소를 좌표로 변환하는 함수
function geocodeAddress(address) {
  return new Promise((resolve, reject) => {
    if (!naver.maps.Service) {
      reject("Naver maps service is not loaded");
      return;
    }
    naver.maps.Service.geocode({ query: address }, (status, response) => {
      if (status !== naver.maps.Service.Status.OK) {
        reject("Geocoding failed");
        return;
      }
      const result = response.v2.addresses[0];
      if (result) {
        resolve({
          lat: parseFloat(result.y),
          lng: parseFloat(result.x),
        });
      } else {
        reject("No results found");
      }
    });
  });
}

// 폴리곤 그리기 함수
function drawIsochrone(data) {
  if (!window.mapInstance) {
    console.error("지도 객체가 초기화되지 않았습니다.");
    return;
  }

  if (isochrone.value) {
    isochrone.value.setMap(null);
  }

  const coords = data.results[0].shapes[0].shell.map(
    (point) => new naver.maps.LatLng(point.lat, point.lng),
  );
  isochrone.value = new naver.maps.Polygon({
    map: window.mapInstance,
    paths: coords,
    fillColor: "#ff0000",
    fillOpacity: 0.3,
    strokeColor: "#ff0000",
    strokeOpacity: 0.6,
    strokeWeight: 3,
  });
  window.mapInstance.setCenter(coords[0]);
}
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
  const userPk = sessionStorage.getItem("userPk");
  if (userPk) {
    router.replace({ path: "/my_page" });
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

.input-location {
  font-size: 20px;
  height: 40px;
  border-radius: 10px;
  transition: transform 0.3s ease;
}

.input-location:hover {
  transform: translateY(0px);
  /* 마우스를 올렸을 때 카드가 위로 10px 떠오름 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 그림자 효과 추가 */
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.input-time {
  height: 40px;
  margin-left: 20px;
  margin-right: 5px;
  font-size: 20px;
  width: 50px;
  border-radius: 10px;
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
  white-space: nowrap;
  /* 한 줄로 표시되도록 강제 */
}

.nav-right li {
  margin: 0 10px;
  /* 여백을 추가해서 간격 조절 */
  white-space: nowrap;
  /* 이정현님을 한 줄로 표시 */
  overflow: hidden;
  /* 텍스트가 길면 넘어가는 부분을 숨김 */
  text-overflow: ellipsis;
  /* 너무 길면 생략(...) 처리 */
  max-width: 100px;
  /* 너무 긴 이름을 제한 */
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
