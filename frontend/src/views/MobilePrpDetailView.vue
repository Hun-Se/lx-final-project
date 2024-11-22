<template>
  <MobileHeader title="매물 상세 정보"></MobileHeader>
  <div id="mainPage">
    <div v-if="selectedSalesDetails">
      <img :src="selectedSalesDetails.imageUrl" alt="매물 이미지" />
      <p>매물명: {{ selectedSalesDetails.prpName }}</p>
      <p>가격: {{ selectedSalesDetails.prpPrice/10000 }}억 원</p>
      <p>전용 면적: {{ selectedSalesDetails.prpExclArea }}m²</p>
      <p>층수 : {{ selectedSalesDetails.prpFloor }}층</p>
      <p>총층수 : {{ selectedSalesDetails.prpTotalFloor }}층</p>
      <p>동 : {{ selectedSalesDetails.prpBlock }}동</p>
      <p>단지 수 : {{ selectedSalesDetails.prpTotalBlock }}</p>
      <p>건설사: {{ selectedSalesDetails.prpConstr }}</p>
      <p>건축연도 : {{ selectedSalesDetails.prpConstrYear }}연도</p>

      <p>방 개수 : {{ selectedSalesDetails.prpNumRooms }}개</p>
      <p>화장실 수 : {{ selectedSalesDetails.prpNumBaths }}개</p>
      <p>방향 : {{ selectedSalesDetails.prpOri }}</p>
      <p>주차가능 수 : {{ selectedSalesDetails.prpParkingAvail }}대</p>

      <p>가까운 지하철역 : {{ selectedSalesDetails.prpNearSub }}</p>
      <p>역 거리 : {{ selectedSalesDetails.prpNearSubDist }}m</p>
      <p>가까운 학교 : {{ selectedSalesDetails.prpNearSchool }}</p>
      <p>학교 거리 : {{ selectedSalesDetails.prpNearSchoolDist }}m</p>
    </div>
    <div v-else>
      <p>로딩 중...</p>
    </div>
  </div>
</template>

<script setup>
import MobileHeader from "@/components/MobileHeader.vue";
import { onMounted } from "vue";
import { useRoute } from "vue-router";
import { storeToRefs } from "pinia";
import { useSaleStore } from "@/stores/property.js";

// Pinia 스토어와 라우터 가져오기
const store = useSaleStore();
const { selectedSalesDetails } = storeToRefs(store);
const route = useRoute();

onMounted(async () => {
  const salesId = route.query.id; // 쿼리에서 매물 ID 추출
  if (salesId) {
    await store.fetchSalesDetails(salesId); // 매물 상세 정보 요청
  }
});
console.log(selectedSalesDetails);
</script>

<style scoped>
#mainPage {
  margin-top: 70px; /* 헤더 높이만큼 간격 추가 */
  padding: 1rem; /* 콘텐츠에 패딩 추가 */
  background-color: #f9f9f9; /* 배경색 설정 (선택 사항) */
}

h1 {
  text-align: center;
  margin-bottom: 1rem;
}
</style>
