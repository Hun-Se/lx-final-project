<template>
  <div v-if="userName" class="d-flex">
    {{ userName }}님 안녕하세요.
  </div>
  <h1 class="display-1" style="text-align: center; margin-top: 2ex">
    LX가 만든 부동산 데이터 플랫폼
  </h1>

  <h1 class="display-5" style="text-align: center">
    고객을 위한 부동산 직거래 정보, LX부동산
  </h1>

  <div class="nanum-pen-script-regular" style="margin-top: 6ex">
    <div class="carousel" style="margin-bottom: 2ex">
      <div class="group">
        <div class="Scard">
          <img
            src="/assets/img/sales01.png"
            alt="이미지 1"
            class="card-image"
          />
          <p
            class="slider-text"
            style="
              margin-top: 3ex;
              font-size: 1.6em;
              color: white;
              font-weight: bold;
            "
          >
            가격 예측 AI
          </p>
        </div>
      </div>
      <div class="group">
        <div class="Scard">
          <img
            src="/assets/img/sales01.png"
            alt="이미지 2"
            class="card-image"
          />
          <p
            class="slider-text"
            style="
              margin-top: 3ex;
              font-size: 1.6em;
              color: white;
              font-weight: bold;
            "
          >
            가격 예측 AI
          </p>
        </div>
      </div>
      <div class="group">
        <div class="Scard">
          <img
            src="/assets/img/sales01.png"
            alt="이미지 3"
            class="card-image"
          />
          <p
            class="slider-text"
            style="
              margin-top: 3ex;
              font-size: 1.6em;
              color: white;
              font-weight: bold;
            "
          >
            가격 예측 AI
          </p>
        </div>
      </div>
      <div class="group">
        <div class="Scard">
          <img
            src="/assets/img/sales01.png"
            alt="이미지 4"
            class="card-image"
          />
          <p
            class="slider-text"
            style="
              margin-top: 3ex;
              font-size: 1.6em;
              color: white;
              font-weight: bold;
            "
          >
            가격 예측 AI
          </p>
        </div>
      </div>
      <div class="group">
        <div class="Scard">
          <img
            src="/assets/img/sales01.png"
            alt="이미지 5"
            class="card-image"
          />
          <p
            class="slider-text"
            style="
              margin-top: 3ex;
              font-size: 1.6em;
              color: white;
              font-weight: bold;
            "
          >
            가격 예측 AI
          </p>
        </div>
      </div>
      <div class="group">
        <div class="Scard">
          <img
            src="/assets/img/sales01.png"
            alt="이미지 5"
            class="card-image"
          />
          <p
            class="slider-text"
            style="
              margin-top: 3ex;
              font-size: 1.6em;
              color: white;
              font-weight: bold;
            "
          >
            가격 예측 AI
          </p>
        </div>
      </div>
    </div>

    <div v-if="!userName" class="d-grid gap-2 col-5 mx-auto">
      <button
        type="button"
        class="btn btn-secondary"
        style="border-radius: 10ex; font-weight: bold"
        @click="goToLogin">
        로그인하고 더 많은 기능보기
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const userName = ref('');
const userPk = ref(null);
const router = useRouter();

const goToLogin = () => {
  router.push({ name: 'login' });
};

onMounted(async () => {
  const storedUserPk = sessionStorage.getItem('userPk');
  if (storedUserPk) {
    try {
      const response = await axios.get(`/api/users/userName/${storedUserPk}`);
      userName.value = response.data;
      await nextTick(); // DOM 업데이트 후 v-if 평가
    } catch (error) {
      console.error('Failed to load user name:', error);
    }
  } else {
    console.log('User is not logged in');
  }
});
</script>

<style>
/*가을룩*/
.carousel {
  margin: 0 auto;
  padding: 20px 0;
  max-width: 1500px; /* 최대 너비 조정 */
  overflow: visible;
  display: flex;
}

.Scard {
  width: 250px; /* 카드 너비 조정 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 그림자 효과 */
  display: flex; /* 플렉스 박스 사용 */
  flex-direction: column; /* 세로 방향 정렬 */
  justify-content: flex-start; /* 위쪽 정렬 */
  align-items: center; /* 중앙 정렬 */
  min-height: 350px; /* 카드 최소 높이 */
  margin-right: 20px; /* 카드 간격 */
  margin-bottom: 20px;
  background: #457fca; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to bottom,
    #5691c8,
    #4684d4
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to bottom,
    #5691c8,
    #4684d4
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  padding: 15px;
  border-radius: 15px;
  font-size: 16px;
}

.Scard img {
  width: 100%; /* 카드 내에서 이미지가 카드 폭에 맞도록 설정 */
  height: 250px; /* 고정 높이 설정 */
  margin-top: 10px; /* 위로 이동시키기 위한 마진 조정 */
  border: 2px solid #fff; /* 테두리 추가 (색상 및 두께 조정 가능) */
  border-radius: 12px; /* 테두리 모서리 둥글게 설정 */
}

.slider-text {
  margin-top: 10px; /* 이미지와 텍스트 사이 간격 조정 */
  color: #000; /* 텍스트 색상 (검정) */
  text-align: center; /* 텍스트 중앙 정렬 */
  font-size: 16px; /* 글씨 크기 조정 */
}
.group {
  will-change: transform;
  animation: scrolling 20s linear infinite;
  display: flex;
  gap: 30px; /* 카드 사이의 간격 */
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
  animation-play-state: paused; /* 마우스 오버 시 애니메이션 일시 정지 */
}
</style>
