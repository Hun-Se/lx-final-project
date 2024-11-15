<template>
  <!-- renderHeader를 호출하지 않고 그대로 사용 -->
  <component :is="renderHeader" />

  <div class="m-10 fs-2x">
    <h1>매물 가격 예측 비교</h1>

    <!-- 전체 컨테이너 -->
    <div class="favorites-container">
      <!-- 찜한 매물과 선택된 매물을 좌우 배치하는 flex 컨테이너 -->
      <div class="selection-wrapper">
        <!-- 찜한 매물 선택 (왼쪽) -->
        <div class="selection-list">
          <h2>찜한 매물 선택 (최대 5개)</h2>
          <div v-for="(apartment, index) in favoriteApartments" :key="index">
            <input
              type="checkbox"
              :id="`favorite-${index}`"
              :value="apartment"
              :disabled="selectedApartments.length >= 5 && !selectedApartments.includes(apartment)"
              @change="toggleFavorite(apartment)"
            />
            <label :for="`favorite-${index}`">{{ apartment.name }}</label>
          </div>
          <p v-if="selectedApartments.length >= 5" class="warning-note">* 최대 5개의 매물만 선택할 수 있습니다.</p>
        </div>

        <!-- 선택된 매물 정보 (오른쪽) -->
        <!-- 선택된 매물 정보 (오른쪽) -->
        <div class="selected-info">
          <div v-for="(apartment, index) in selectedApartments" :key="index" class="info">
            <h3>{{ apartment.name }}</h3>
            <p :style="{ color: calculateChangeRate(apartment).color }">
              2024-2025년 변동률: {{ calculateChangeRate(apartment).rate }}%
            </p>
          </div>
        </div>
      </div>
      <!-- 가격 예측 차트 -->
      <div class="chart-wrapper">
        <canvas id="comparisonChart"></canvas>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch, nextTick, computed } from 'vue';
import Chart from 'chart.js/auto';
import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";

// 모바일 여부에 따른 Header 컴포넌트 결정
const isMobile = ref(window.innerWidth < 768);
const renderHeader = computed(() => (isMobile.value ? MobileHeader : Header));

const favoriteApartments = ref([
  { name: '경희궁자이', predictedPrices: [120000, 123000, 125757, 126816, 126000] },
  { name: '경희궁롯데케슬', predictedPrices: [150000, 152000, 153709, 153187, 154000] },
  { name: '송파헬리오시티', predictedPrices: [220000, 225000, 230000, 235000, 240000] },
  { name: '잠실엘스', predictedPrices: [200000, 205000, 210000, 215000, 220000] },
  { name: '래미안퍼스티지', predictedPrices: [180000, 183000, 185000, 186000, 188000] },
  { name: '청담아이파크', predictedPrices: [170000, 175000, 178000, 179000, 181000] },
  { name: '한남더힐', predictedPrices: [250000, 255000, 260000, 265000, 268000] },
  { name: '타워팰리스', predictedPrices: [210000, 215000, 220000, 225000, 227000] },
  { name: '더샵오페라', predictedPrices: [230000, 235000, 240000, 242000, 245000] },
  { name: '위례자연앤힐스', predictedPrices: [160000, 162000, 164000, 166000, 168000] },
]);

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
        const price2024 = apartment.predictedPrices[3];
        const price2025 = apartment.predictedPrices[4];
        const color = price2025 > price2024 ? '#FA7000' : '#0d1c3b';
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

onMounted(() => renderChart());
</script>


<style scoped>
.favorites-container {
  width: 90%;
  margin: 0 auto;
  padding: 20px;
  background-color: #f0f4f8;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.selection-wrapper {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 20px;
  margin-bottom: 20px;
}

.selection-list {
  width: 45%;
  max-height: 320px;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  overflow-y: auto;
}

.selection-list h2 {
  font-size: 1.2em;
  color: #333;
  margin-bottom: 10px;
  font-weight: bold;
}

.selection-list label {
  margin-left: 8px;
  color: #555;
}

.selection-list input[type="checkbox"] {
  accent-color: #007bff;
}

.selected-info {
  width: 50%;
  max-height: 320px;
  overflow-y: auto;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.info {
  background: #f9f9f9;
  padding: 10px 15px;
  margin-bottom: 10px;
  border-left: 4px solid #007bff;
  border-radius: 5px;
  font-size: 0.95em;
  transition: background-color 0.2s ease;
}

.info:hover {
  background-color: #eef6ff;
}

.warning-note {
  color: red;
  font-size: 0.9em;
  font-weight: bold;
}

.chart-wrapper {
  width: 100%;
  height: 400px;
  margin-top: 20px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

canvas {
  width: 100% !important;
  height: 100% !important;
  border-radius: 10px;
}

h1 {
  font-size: 2em;
  color: #007bff;
  text-align: center;
  margin-bottom: 20px;
  font-weight: bold;
}

h2, h3 {
  color: #007bff;
}

p {
  color: #555;
}
</style>
