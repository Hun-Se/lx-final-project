<template>
  <div class="m-20">
    <h1>매물 가격 예측</h1>
    <div class="prediction">
      <h2>{{ region }} {{ buildingName }} 매물의 </h2>
      <h2>1년뒤 예상 가격 : {{ predictedPrice1 }} 만원</h2>
    </div>

    <div class="chart-container">
      <canvas id="priceChart1"></canvas>
    </div>
  </div>

  <div class="m-20">
    <h1>매물 지역 가격 예측</h1>
    <div class="prediction">
      <h2>{{ region }} 지역의</h2>
      <h2>1년뒤 예상 가격 : {{ predictedPrice2 }} 만원</h2>
    </div>

    <div class="chart-container">
      <canvas id="priceChart2"></canvas>
    </div>
    <br>
    <br>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import Chart from 'chart.js/auto';

export default {
  name: 'PricePrediction',
  setup() {
    const predictedPrice1 = ref(476000); // 예측 가격 데이터
    const predictedPrice2 = ref(2250);
    const buildingName = ref('XI 아파트'); // 빌딩 이름 데이터
    const region = ref('서울시 반포동'); // 지역 데이터
    const chart1 = ref(null);
    const chart2 = ref(null);

    const createChart1 = () => {
      const ctx = document.getElementById('priceChart1').getContext('2d');
      chart1.value = new Chart(ctx, {
        type: 'line', // 꺾은 선 그래프
        data: {
          labels: [
            '2020 Q1', '2020 Q2', '2020 Q3', '2020 Q4',
            '2021 Q1', '2021 Q2', '2021 Q3', '2021 Q4',
            '2022 Q1', '2022 Q2', '2022 Q3', '2022 Q4',
            '2023 Q1', '2023 Q2', '2023 Q3', '2023 Q4'
          ], // 3개월 단위 레이블
          datasets: [
            {
              label: '예상가격',
              data: [
                400000, 405000, 405000, 410000, 
                418000, 420000, 430000, 428000, 
                438000, 448000, 460000, 459000, 
                458000, 465000, 469000, 476000
              ], // 가격 변동을 반영한 예측 데이터
              fill: false,
              pointRadius: 10, // 데이터 포인트 크기 조정
              borderColor: 'rgba(75, 192, 192, 1)',
              tension: 0.4, // 곡선 부드러움 조정
            },
            {
              label: '실가격',
              data: [
                405000, 410000, 412000, 418000, 
                424000, 430000, 438000, 435000, 
                445000, 452000, 460000, 458000, 
                464000, 470000, 475000
              ], // 가격 변동을 반영한 실제 가격 데이터
              fill: false,
              pointRadius: 10, // 데이터 포인트 크기 조정
              borderColor: 'rgba(255, 99, 132, 1)',
              tension: 0.4, // 곡선 부드러움 조정
            },
          ],
        },
        options: {
          responsive: true, // 창 크기에 따라 차트 자동 조정
          maintainAspectRatio: false, // 브라우저 창 크기 비율에 맞추어 조정
          aspectRatio: 0.42, // 차트 비율 설정
          scales: {
            y: {
              beginAtZero: true,
              min: 390000,
              max: 490000 // Y축 범위 확장
            },
          },
        },
      });
    };

    const createChart2 = () => {
      const ctx = document.getElementById('priceChart2').getContext('2d');
      chart2.value = new Chart(ctx, {
        type: 'line', // 꺾은 선 그래프
        data: {
          labels: [
            '2020 Q1', '2020 Q2', '2020 Q3', '2020 Q4',
            '2021 Q1', '2021 Q2', '2021 Q3', '2021 Q4',
            '2022 Q1', '2022 Q2', '2022 Q3', '2022 Q4',
            '2023 Q1', '2023 Q2', '2023 Q3', '2023 Q4'
          ], // 3개월 단위 레이블
          datasets: [
            {
              label: '예상가격',
              data: [
                1500, 1600, 1550, 1650, 
                1700, 1800, 1750, 1850, 
                1900, 2000, 1950, 2050,
                2100, 2150, 2200, 2250
              ], // 가격 변동을 반영한 예상 가격 데이터
              fill: false,
              pointRadius: 10, // 데이터 포인트 크기 조정
              borderColor: 'rgba(75, 192, 192, 1)',
              tension: 0.4 // 곡선 부드러움 조정
            },
            {
              label: '실가격',
              data: [
                1520, 1580, 1600, 1700, 
                1760, 1820, 1800, 1900, 
                1960, 2020, 2000, 2080,
                2120, 2180, 2240
              ], // 가격 변동을 반영한 실제 가격 데이터
              fill: false,
              pointRadius: 10, // 데이터 포인트 크기 조정
              borderColor: 'rgba(255, 99, 132, 1)',
              tension: 0.4 // 곡선 부드러움 조정
            },
          ],
        },
        options: {
          responsive: true, // 창 크기에 따라 차트 자동 조정
          maintainAspectRatio: false, // 브라우저 창 크기 비율에 맞추어 조정
          aspectRatio: 0.42, // 차트 비율 설정
          scales: {
            y: {
              beginAtZero: true,
              min: 1400,
              max: 2500
            },
          },
        },
      });
    };

    onMounted(() => {
      createChart1();
      createChart2();
    });

    return {
      predictedPrice1,
      predictedPrice2,
      buildingName,
      region,
      chart1,
      chart2
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
