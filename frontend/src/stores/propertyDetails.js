import { ref } from "vue";
import { defineStore } from "pinia";

import { requestSaleById } from "@/api/property.js";

export const useSalesDetailsStore = defineStore("salesDetails", () => {
  // 매물 상세 정보
  const selectedSaleDetails = ref(null);

  // 매물 상세 정보 요청하기
  async function fetchSalesDetails(pk) {
    const response = await requestSaleById(pk);

    // 응답 데이터를 스토어에 저장
    if (response) {
      console.log(`response -> ${JSON.stringify(response)}`);

      // 선택된 매물 정보 업데이트
      selectedSaleDetails.value = response;
      console.log("Updated sales list:", sales.value);
    } else {
      console.warn("응답 데이터가 없습니다.");
    }
  }

  return {
    fetchSalesDetails,
  };
});
