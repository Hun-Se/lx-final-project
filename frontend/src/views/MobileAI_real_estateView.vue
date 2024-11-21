<template>
  <!-- renderHeader를 호출하지 않고 그대로 사용 -->
  <MobileHeader title="매물 가격 예측 비교"></MobileHeader>
  
  <div class="mt-18 fs-2x">
    <!-- 전체 컨테이너 -->
    <div class="favorites-container">
      <h3 class="card-title text-gray-900 fs-3 fw-bold mb-3">가격 예측 그래프</h3>
      <div class="fw-semibold fs-7 text-muted mb-3" data-v-cb9438ee="">* 선택한 매물의 가격 예측을 표시합니다.(최대 5개) </div>

      <!-- 관심 매물 선택 리스트 -->
      <div class="selection-list mt-1">
        <div v-for="(apartment, index) in visibleApartments" :key="index" class="d-flex align-items-center form-check mb-3 text-gray-800 fw-bold text-hover-primary fs-6">
          <input
              class="form-check-input mobile-prp-select-chart"
              type="checkbox"
              :id="favorite-${index}"
              :value="apartment"
              :disabled="selectedApartments.length >= 5 && !selectedApartments.includes(apartment)"
              @change="toggleFavorite(apartment)"
          />
          <label class="ms-2 form-check-label" :for="favorite-${index}">
            {{ apartment.name }}
          </label>
        </div>
        <div class="text-center">
          <a href="#" @click="showAllApartments()" class="">
            <i class="bi bi-chevron-down"></i>
          </a>
        </div>
      </div>

      <!-- 선택된 매물 정보 -->

      <!-- 가격 예측 차트 -->
      <div class="chart-wrapper">
      <!--  선택된 매물 정보      -->
        <div v-if="selectedApartments.length === 0" class="text-muted fw-semibold fs-6" >찜한 매물 목록에서 매물을 선택해주세요.</div>
        <div v-for="(apartment, index) in selectedApartments" :key="index" class="d-flex fw-semibold align-items-center mb-3">
          <div class="bullet w-8px h-3px rounded-2 me-3" :style="{ backgroundColor: getBulletColor(index) }" ></div>
          <span class="fs-6" >{{ apartment.name }}</span>
          <p class="ms-3" :style="{ fontSize: '1rem' }">
            2024-2025년 변동률: <span :style="{ color: calculateChangeRate(apartment).color}" >{{ calculateChangeRate(apartment).rate }}%</span>
          </p>
        </div>
        <canvas id="comparisonChart"></canvas>
      </div>
    </div>
  </div>

  <MobileBottomTapBar></MobileBottomTapBar>
</template>

<script setup>
import { onMounted, ref, watch, nextTick, computed } from 'vue';
import Chart from 'chart.js/auto';
// import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";

// 모바일 여부에 따른 Header 컴포넌트 결정
const isMobile = ref(window.innerWidth < 768);
// const renderHeader = computed(() => (isMobile.value ? MobileHeader : Header));

// 기존 favoriteApartments 데이터를 서버로부터 받아오도록 수정
const favoriteApartments = ref([{
  name: '없음', predictPrice: [0, 0, 0, 0]
}]);

// 서버로부터 관심 매물 데이터 가져오기
const fetchFavoriteProperties = async () => {
  try {
    const response = await axios.get('/api/favprp/1'); // user_pk = 1
    console.log('API Response:', response.data); // 서버에서 받아온 원본 데이터를 로그로 출력
    console.log('Data Length:', response.data.length);

    // 데이터 변환 및 favoriteApartments 업데이트
    favoriteApartments.value = []; // 기존 데이터를 초기화
    response.data.forEach((item) => {
      favoriteApartments.value.push({
        name: item.prpName, // 이름 매핑
        predictedPrices: [
          item.predict2022, // 2022 예측값
          item.predict2023, // 2023 예측값
          item.predict2024, // 2024 예측값
          item.predict2025, // 2025 예측값
        ],
      });
    });

    console.log('Updated Favorite Apartments:', favoriteApartments.value); // 변환 후 데이터 확인
  } catch (error) {
    console.error('Error fetching favorite properties:', error); // 에러 로그
  }
};

const colors = ['#33cc33', '#ffcc00', '#ff7f00', '#33cc33', '#3366ff'];

const getBulletColor = (index) => {
  return colors[index % colors.length]; // Use modulo to loop through colors
};

//  리스트 쪼개기 및 접기 펼치기 기능 시작
const isShowAllApartments = ref(false);
const visibleApartments = ref([]);
// visibleApartments.value = isShowAllApartments.value ?  favoriteApartments.value :  favoriteApartments.value.slice(0,3);

watch(favoriteApartments, (newValue) => {
  console.log('Favorite Apartments Updated:', newValue); // favoriteApartments 변화 감지
  visibleApartments.value = isShowAllApartments.value
    ? favoriteApartments.value
    : favoriteApartments.value.slice(0, 3); // 기본 3개만 표시
});


function showAllApartments() {
  isShowAllApartments.value = !isShowAllApartments.value;
  console.log(isShowAllApartments.value);
}

//  리스트 쪼개기 및 접기 펼치기 끝

const selectedApartments = ref([]);
const chartInstance = ref(null);

const toggleFavorite = (apartment) => {
  const index = selectedApartments.value.findIndex(a => a.name === apartment.name);
  if (index === -1 && selectedApartments.value.length < 5) {
    selectedApartments.value.push(apartment);
  } else if (index !== -1) {
    selectedApartments.value.splice(index, 1);
  }
  renderChart();
};

const calculateChangeRate = (apartment) => {
  const price2024 = apartment.predictedPrices[3];
  const price2025 = apartment.predictedPrices[4];
  const changeRate = ((price2025 - price2024) / price2024) * 100;
  return {
    rate: changeRate.toFixed(2),
    color: changeRate > 0 ? '#FA7000' : '#0d1c3b',
  };
};

const renderChart = async () => {
  if (selectedApartments.value.length === 0) return;

  await nextTick();

  const chartElement = document.getElementById('comparisonChart');
  if (!chartElement) return;

  const ctx = chartElement.getContext('2d');
  if (chartInstance.value) {
    chartInstance.value.destroy();
  }

  chartInstance.value = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['2021년', '2022년', '2023년', '2024년', '2025년'],
      datasets: selectedApartments.value.map((apartment, index) => {
        const colorIndex = index % colors.length; // 색상 배열 순환
        const color = colors[colorIndex];

        const price2024 = apartment.predictedPrices[3];
        const price2025 = apartment.predictedPrices[4];
        // const color = price2025 > price2024 ? '#FA7000' : '#0d1c3b';
        return {
          label: apartment.name,
          data: apartment.predictedPrices,
          borderColor: color,
          backgroundColor: color,
          fill: false,
          tension: 0.1,
        };
      }),
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        y: {
          beginAtZero: false,
          title: {
            display: true,
            text: '가격 (만원)',
          },
        },
      },
      plugins: {
        legend: {
          position: 'top',
        },
      },
    },
  });
};

watch(selectedApartments, () => renderChart());

// onMounted에 API 호출 연결
onMounted(async () => {
  await fetchFavoriteProperties();
  renderChart(); // 데이터 로드 후 차트 렌더링
});
</script>

<style scoped>
.favorites-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 10px;
}

.selection-list,
.selected-info {
  width: 100%;
  max-height: 200px;
  overflow-y: auto;
  margin-bottom: 1rem;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 10px;
  --bs-card-box-shadow: var(--bs-root-card-box-shadow);
  --bs-card-border-color: var(--bs-root-card-border-color);
  border: 1px solid var(--bs-card-border-color);
  box-shadow: var(--bs-card-box-shadow);
}

.selection-list h2,
.selected-info h3 {
  font-size: 1em;
  margin-bottom: 10px;
}

.chart-wrapper {
  width: 100%;
  height: 310px;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 10px;
  --bs-card-box-shadow: var(--bs-root-card-box-shadow);
  --bs-card-border-color: var(--bs-root-card-border-color);
  border: 1px solid var(--bs-card-border-color);
  overflow: scroll;
  box-shadow: var(--bs-card-box-shadow);
}

.mobile-prp-select-chart:checked {
  background-color: var(--color-bg-third);
  border-color: var(--color-bg-third);
}
</style>