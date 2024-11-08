<template>
  <component :is="renderHeader()" />

  <div class="container">

    <!-- 검색 및 배너-->
    <div class="mt-20">

      <div style="display: flex; align-items: center;">

        <input v-model="searchByType" class="form-check-input" type="radio" name="flexRadioDefault" value="time"
          checked>
        <label class="form-check-label lh-base" for="flexRadioDefault1">
          <div style="font-size: 20px; margin-left: 10px;">
            등시간 검색
          </div>

        </label>
        <input v-model="searchByType" class="form-check-input ms-4" type="radio" name="flexRadioDefault"
          id="flexRadioDefault2" value="keyWord">


        <label class="form-check-label lh-base" for="flexRadioDefault2">
          <div style="font-size: 20px; margin-left: 10px;">
            키워드 검색
          </div>
        </label>

      </div>

      <!-- 기존 검색 바 -->
      <div v-if="searchByType === 'tme'" class="search-bar mt-9" id="search" style="margin-left: 1ex;">
        <input class="orgin" type="text" v-model="location" placeholder="위치를 입력하세요" />
        에서
        <select class="trans" v-model="selectedTransport">
          <option disabled value="">이동수단</option>
          <option value="walking">도보</option>
          <option value="driving">자차</option>
          <option value="public_transport">대중교통</option>
        </select>
        로
        <input class="input-hour" type="number" v-model.number="hours" placeholder="시간 입력" min="0" step="1" />
        내에
        <span> 시간 </span>
        <input class="input-min" type="number" v-model.number="minutes" placeholder="분 입력" min="0" max="59"
          step="1" /><span>분 내에 있는 매물을 </span>
        <button @click="submit" class="btn btn-primary ">검색</button>
      </div>

      <!--등시간 검색-->
      <div v-if="searchByType === 'time'" style="font-size: 30px;">
        <div class="mt-10" style="display: flex; align-items: center;">
          <input class="input-location text-center" style="margin-right: 10px; margin-left: 10px; width: 400px;"
            placeholder="위치를 입력해주세요">
          에서

          <select style=" font-size: 30px; height: 50px; border-radius: 10px ; margin-right: 10px; margin-left: 20px;">
            <option disabled selected>이동수단</option>
            <option value="walking">도보</option>
            <option value="driving">자차</option>
            <option value="public_transport">대중교통</option>
          </select>로

          <input class="input-time text-center" type="number" placeholder="0" min="0">
          시간

          <input class="input-time text-center" type="number" placeholder="0" min="0" max="59">
          분

          <span style="margin-left: 10px; margin-right: 20px;">내에 있는 매물</span>
          <button type="button" class="btn btn-primary btn-lg">검색</button>
        </div>

      </div>

      <!-- 키워드 검색 -->
      <div v-else-if="searchByType === 'keyWord'" class="mt-10">
        <div class="mt-10" style="display: flex; align-items: center;">
          <input class="input-location text-center" placeholder="지역, 지하철, 대학 등 키워드를 입력해주세요"
            style="width: 1000px; margin:auto">
          <button type="button" class="btn btn-primary btn-lg" style="margin:auto">검색</button>
        </div>
      </div>

    </div>

    <!-- 매물 카드-->
    <div class="row mt-20">

      <div class="col-3">
        <div @click="goToMap" class="card card-effect" style="height: auto; ">
          <div class="card-body">
            <div class="text-center mb-5" style="font-size: 30px; font-weight: 600;">원 / 투룸</div>
            <div class="row text-center" style="display: flex; align-items: center;">
              <div class="col-7" style="color: gray;">
                <div>주택 / 빌라, 오피스텔,</div>
                <div class="mt-2">아파트까지 모~든</div>
                <div class="mt-2"> 전월세 매물을 한번에!</div>
              </div>
              <div class="col-5"><img src="/assets/img/oneroom.png" style="width: 95px;"></div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-3">
        <div @click="goToMap" class="card card-effect" style="height: 212px; ">
          <div class="card-body">
            <div class="text-center mb-5" style="font-size: 30px; font-weight: 600;">주택 / 빌라</div>
            <div class="row text-center" style="display: flex; align-items: center;">
              <div class="col-7" style="color: gray;">
                <div>원하는 빌라, 주택</div>
                <div class="mt-2">전월세 매물 모두 여기!</div>
              </div>
              <div class="col-5"><img src="/assets/img/villa.png" style="width: 120px;"></div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-3">
        <div @click="goToMap" class="card card-effect" style="height: auto; ">
          <div class="card-body">
            <div class="text-center mb-5" style="font-size: 30px; font-weight: 600;">오피스텔</div>
            <div class="row text-center" style="display: flex; align-items: center;">
              <div class="col-7" style="color: gray;">
                <div>다양한 정보와</div>
                <div class="mt-2">다양한 매물!</div>
              </div>
              <div class="col-5"><img src="/assets/img/officetel.png" style="width: 82px;"></div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-3">
        <div @click="goToMap" class="card card-effect" style="height: auto; ">
          <div class="card-body">
            <div class="text-center mb-5" style="font-size: 30px; font-weight: 600;">아파트</div>
            <div class="row text-center" style="display: flex; align-items: center;">
              <div class="col-7" style="color: gray;">
                <div>풍부한 단지정보 및</div>
                <div class="mt-2">다양한 아파트 매물!</div>
              </div>
              <div class="col-5"><img src="/assets/img/apartment.png" style="width: 100px;"></div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 홍보 배너-->
    <div class="row mt-20">

      <!-- 배너 -->
      <div class="col-8">
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" data-bs-interval="4000" style="padding: 0;" >
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
              aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
              aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
              aria-label="Slide 3"></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src="/assets/img/banner1.png" alt="1번 배너" style="width:100%  ;">
            </div>
            <div class="carousel-item">
              <img src="/assets/img/banner2.png" alt="2번 배너" style="width:100% ;">
            </div>
            <div class="carousel-item">
              <img src="/assets/img/banner3.png" alt="3번 배너" style="width:100% ;">
            </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev" >
            <span class="carousel-control-prev-icon" aria-hidden="true"  style="background-color: grey;"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true" style="background-color: grey;"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>

      </div>

      <!--AI 집값 예측-->
      <div class="col-4">
        <div class="card" style="height: 320px; ">
          <div class="card-body text-center">
            <div class="d-flex justify-content-around">
              <img src="/assets/img/decrease_graph.png" style="width: 100px;">
              <img src="/assets/img/increase_graph.png" style="width: 100px;">
            </div>
            <div class="mt-5" style="font-size: 30px; font-weight: 500;">
              내가 찜한 집의
            </div>
            <div class="mt-5" style="font-size: 30px; font-weight: 500;">
              미래 시세 어떻게 될까?
            </div>
            <div class="mt-5">
              <button type="button" class="btn btn-primary btn-lg" style="margin:auto"> AI 집값 예측 바로가기</button>
            </div>
          </div>
        </div>
      </div>


    </div>


  </div>

  <div v-if="userName" class="d-flex">{{ userName }}님 안녕하세요.</div>

</template>

<script setup>
import { ref, onMounted, nextTick } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";
import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import { useMobileStore } from "@/stores/mobile";
import { storeToRefs } from "pinia";

const searchByType = ref("time")


const store = useMobileStore();
const { isMobile } = storeToRefs(store);

const renderHeader = () => {
  if (isMobile.value) {
    return MobileHeader;
  } else {
    return Header;
  }
};

// AI 가격 예측 페이지로 이동

function goToAiRealEstate() {
  router.push({ path: "/ai_real_estate" });
}

// 매물지도 페이지로 이동
function goToMap() {
  router.push({ path: "/map" });
}

// 중개 수수료 경매 페이지로 이동
function goToAuction() {
  router.push({ path: "/user_auction" });
}

// 게시판 페이지로 이동
function goToBoard() {
  router.push({ path: "/board" });
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

const userName = ref("");
const userPk = ref(null);
const router = useRouter();

const goToLogin = () => {
  router.push({ name: "login" });
};

onMounted(async () => {
  const storedUserPk = sessionStorage.getItem("userPk");
  if (storedUserPk) {
    try {
      const response = await axios.get(`/api/users/userName/${storedUserPk}`);
      userName.value = response.data;
      await nextTick(); // DOM 업데이트 후 v-if 평가
    } catch (error) {
      console.error("Failed to load user name:", error);
    }
  } else {
    console.log("User is not logged in");
  }
});


</script>

<style>
/*가을룩*/
.carousel {
  margin: 0 auto;
  padding: 20px 0;
  max-width: 1500px;
  /* 최대 너비 조정 */
  overflow: visible;
  display: flex;
}

.carousel-inner {
  border: 2px solid #ccc; /* 원하는 테두리 색상 */
  border-radius: 10px; /* 둥근 모서리를 원할 경우 */
  padding: 5px; /* 테두리와 이미지 사이 간격 */
}

.card-effect {
  transition: transform 0.3s ease;
  /* 부드러운 애니메이션 */
}

.card-effect:hover {
  transform: translateY(-10px);
  /* 마우스를 올렸을 때 카드가 위로 10px 떠오름 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 그림자 효과 추가 */
  cursor: pointer;
}

.input-location {
  font-size: 30px;
  height: 50px;
  border-radius: 10px;
  transition: transform 0.3s ease;
}

.input-location:hover {
  transform: translateY(-10px);
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
  height: 50px;
  margin-left: 20px;
  margin-right: 5px;
  font-size: 30px;
  width: 50px;
  border-radius: 10px;
}

.Scard {
  width: 250px;
  /* 카드 너비 조정 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 그림자 효과 */
  display: flex;
  /* 플렉스 박스 사용 */
  flex-direction: column;
  /* 세로 방향 정렬 */
  justify-content: flex-start;
  /* 위쪽 정렬 */
  align-items: center;
  /* 중앙 정렬 */
  min-height: 350px;
  /* 카드 최소 높이 */
  margin-right: 20px;
  /* 카드 간격 */
  margin-bottom: 20px;
  background-image: url("/assets/img/bg_card_ai.webp"),
    linear-gradient(#837af0, #d2a2ff, #d2a2ff, #e1b0ff);
  background-size: cover;
  background-repeat: no-repeat;
  /* background-color: #457fca; */
  /* background: #457fca; fallback for old browsers */
  /* background: -webkit-linear-gradient(
    to bottom,
    #5691c8,
    #4684d4
  ); Chrome 10-25, Safari 5.1-6 */
  /* background: linear-gradient(
    to bottom,
    #5691c8,
    #4684d4
  ); W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  padding: 15px;
  border-radius: 15px;
  font-size: 16px;
}

.card-map {
  background-image: url("/assets/img/bg_card_map.webp"),
    linear-gradient(#ffbe7b, #ff9999);
}

.Scard img {
  width: 100%;
  /* 카드 내에서 이미지가 카드 폭에 맞도록 설정 */
  height: 250px;
  /* 고정 높이 설정 */
  margin-top: 10px;
  /* 위로 이동시키기 위한 마진 조정 */
  border: 2px solid #fff;
  /* 테두리 추가 (색상 및 두께 조정 가능) */
  border-radius: 12px;
  /* 테두리 모서리 둥글게 설정 */
}

.slider-text {
  color: #000;
  /* 텍스트 색상 (검정) */
  text-align: center;
  /* 텍스트 중앙 정렬 */
  font-size: 16px;
  /* 글씨 크기 조정 */
}

.group {
  will-change: transform;
  animation: scrolling 20s linear infinite;
  display: flex;
  gap: 30px;
  /* 카드 사이의 간격 */
}

@keyframes scrolling {
  0% {
    transform: translateX(0);
  }

  100% {
    transform: translateX(-100%);
  }
}

.carousel:hover .group {
  animation-play-state: paused;
  /* 마우스 오버 시 애니메이션 일시 정지 */
}

@media (max-width: 1100px) {
  .group {
    will-change: none;
    animation: none;
  }

  .carousel {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    padding: 10px;
  }

  .carousel .group {
    display: flex;
    justify-content: center;
    flex: 1 1 calc(33.333% - 20px);
    text-align: center;
  }

  .carousel .Scard {
    min-height: 140px;
    /* 카드 최소 높이 */
    margin-right: 0;
    /* 카드 간격 */
  }

  .display-1 {
    font-size: 2em;
    padding: 10px;
  }

  .display-5 {
    font-size: 1.2em;
    padding: 10px;
  }
}

@media (max-width: 900px) {
  .group {
    will-change: none;
    animation: none;
  }

  .carousel {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    flex: 1;
    padding: 10px;
  }

  .carousel .Scard {
    width: 140px;
    height: 30px;
    min-height: 140px;
    /* 카드 최소 높이 */
    margin-right: 0;
    /* 카드 간격 */
  }

  .display-1 {
    font-size: 2em;
    padding: 10px;
  }

  .display-5 {
    font-size: 1.2em;
    padding: 10px;
  }
}
</style>
