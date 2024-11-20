<template>
  <header class="header">
    <div class="logo cursor-pointer" @click="goToHomeMobile">
      <img class="img-logo" src="/assets/img/logo.png" alt="" />
    </div>
    <div class="d-flex justify-content-between container-search ms-3 align-items-center">
        <input
            class="form-control mobile-search-input "
            v-model="location"
            placeholder="위치"
        />
        <span>에서</span>
        <select
            class="form-control mobile-search-input-type "
            v-model="selectedTransport"
        >
          <option value="" disabled selected>이동수단</option>
          <option value="walking">도보</option>
          <option value="driving">자차</option>
          <option value="public_transport">대중교통</option></select
        >
      <span>로</span>
      <input
          class="form-control mobile-time-input"
          type="text"
          v-model.number="hours"
          placeholder="0"
          min="0"
      />
      <span>시간</span>
      <input
          class="form-control mobile-time-input"
          type="text"
          v-model.number="minutes"
          placeholder="0"
          min="0"
          max="59"
      />
      <span>분</span>
      <button @click="submit" type="button" class="btn btn-sm button-time-search">
        검색
      </button>
    </div>
  </header>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter} from "vue-router";
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
function goToHomeMobile() {
  router.replace({ path: "/mobile_home" });
}


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
  align-items: center;
  background-color: var(--color-bg-blue1);
  border-bottom: 1px solid #ddd;
}

.logo h1 {
  font-weight: bold;
  color: var(--color-bg-white);
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

.img-logo {
  width: 3rem;
  height: 3rem;
}

.mobile-search-input {
  width: 70px;
  height: 20px;
  padding: 0;
  text-align: center;
}

.button-time-search {
  color: #fff;
  background: var(--color-bg-third);
}

.mobile-time-input {
  padding: 0;
  width: 20px;
  height: 20px;
  text-align: center;
}

.mobile-search-input-type {
  padding: 0;
  width: 55px;
  height: 20px;
  text-align: center;
}

.container-search span{
  margin: 0 0.2rem;
  color: #fff;
}
</style>
