<template>
  <component :is="renderHeader()" />
  <div class="m-20 fs-2x">
    <h1>매물 가격 예측</h1>
    <div class="prediction">
      <h2>{{ region }} {{ buildingName }} 매물의 </h2>
      <h2>1년뒤 예상 가격 : {{ predictedPrice1 }} 만원</h2>
    </div>

    <div class="chart-container">
      <canvas id="priceChart1"></canvas>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import Chart from 'chart.js/auto';
import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import { useMobileStore } from "@/stores/mobile";
import { storeToRefs } from "pinia";


export default {
  name: 'PricePrediction',
  setup() {
    const store = useMobileStore();
    const { isMobile } = storeToRefs(store);

    const renderHeader = () => {
      if (isMobile.value) {
        return MobileHeader;
      } else {
        return Header;
      }
    };

    const predictedPrice1 = ref(126816); // 예측 가격 데이터
    const buildingName = ref('경희궁자이'); // 빌딩 이름 데이터
    const region = ref('서울시 종로구 평동'); // 지역 데이터
    const chart1 = ref(null);

    // 2024년과 2025년 데이터
    const data2024 = [125757, 153709];
    const data2025 = [126816, 153187];

    const createChart1 = () => {
      const ctx = document.getElementById('priceChart1').getContext('2d');
      chart1.value = new Chart(ctx, {
        type: 'bar', // 막대 그래프로 변경
        data: {
          labels: ['경희궁자이', '경희궁롯데케슬'], // 건물 레이블
          datasets: [
            {
              label: '2024',
              data: data2024, // 2024년 데이터
              backgroundColor: 'rgba(75, 192, 192, 0.6)', // 2024년 색상
              borderColor: 'rgba(75, 192, 192, 1)',
              borderWidth: 1,
            },
            {
              label: '2025',
              data: data2025, // 2025년 데이터
              backgroundColor: 'rgba(255, 99, 132, 0.6)', // 2025년 색상
              borderColor: 'rgba(255, 99, 132, 1)',
              borderWidth: 1,
            },
          ],
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          aspectRatio: 0.42,
          scales: {
            y: {
              beginAtZero: true,
              min: 120000,
              max: 160000, // Y축 범위 확장
              ticks: {
                stepSize: 5000, // Y축 간격 조정
              },
            },
          },
          plugins: {
            legend: {
              position: 'top', // 범례 위치 설정
            },
            tooltip: {
              callbacks: {
                label: function(context) {
                  const index = context.dataIndex; // 현재 막대의 인덱스
                  const value = context.raw; // 현재 막대의 값
                  const year = context.dataset.label; // 데이터의 레이블 (2024 또는 2025)
                  let percentageChange = '';

                  // 2024와 2025 데이터를 비교하여 상승률(%)을 계산
                  if (year === '2025') {
                    const price2024 = data2024[index];
                    const price2025 = data2025[index];
                    const change = ((price2025 - price2024) / price2024) * 100;
                    percentageChange = ` (${change.toFixed(2)}%)`;
                  }

                  return `${year}: ${value} 만원${percentageChange}`;
                }
              }
            }
          },
        },
      });
    };

    onMounted(() => {
      createChart1();
    });

    return {
      predictedPrice1,
      buildingName,
      region,
      chart1,
      renderHeader 
    };
  },
};
</script>

<style scoped>
.prediction {
  margin-bottom: 20px;
}

.chart-container {
  width: 80%;
  margin: 0 auto;
}
</style>
