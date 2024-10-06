import { ref, computed } from "vue";
import { defineStore } from "pinia";

import { requestSalesDetailsListGlobal } from "@/api/saleDetails";

export const useSalesDetailsStore = defineStore("salesDetails", () => {
  // 매물 상세 정보
  const saleDetails = ref([]);
  const selectedSaleDetailsId = ref(null);
  const selectedSaleDetails = ref(null);

  // 매물 상세 정보 요청하기
  async function fetchSalesDetails(salesId) {
    const url = "/saleDetails/saleDetails-list";

    const response = await requestSalesDetailsListGlobal(url, {
      id: salesId,
    });

    // 응답 데이터를 스토어에 저장
    if (response && response.data) {
      console.log(`response -> ${JSON.stringify(response)}`);

      // 매물 상세 정보 업데이트
      saleDetails.value = response.data;

      // 선택된 매물 정보 업데이트
      selectedSaleDetails.value = saleDetails.value.find(
        (item) => item.id === salesId
      );
      console.log("Updated sales list:", sales.value);
    } else {
      console.warn("응답 데이터가 없습니다.");
    }
  }

  // 선택된 매물 정보 초기화
  function clearSelectedSalesDetails() {
    selectedSaleDetailsId.value = null;
    selectedSaleDetails.value = null;
  }

  return {
    saleDetails,
    selectedSaleDetailsId,
    selectedSaleDetails,
    fetchSalesDetails,
    clearSelectedSalesDetails,
  };
});
