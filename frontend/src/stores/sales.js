import { ref } from "vue";
import { defineStore } from "pinia";

import { requestSalesList, requestSaleById } from "@/api/sales";

export const useSaleStore = defineStore("sale", () => {
  //==== begin::매물 =====//

  // 매물 리스트
  const sales = ref([]);
  const selectedSalesDetails = ref(null);

  // 매물 리스트 요청하기
  async function fetchSalesList() {
    const response = await requestSalesList({});
    console.log(`Fetched sales data: ${JSON.stringify(response)}`);

    // 응답 데이터를 스토어에 저장
    if (response) {
      // 매물 리스트 업데이트
      sales.value = response;
      console.log("Updated sales list:", sales.value);
    } else {
      console.warn("응답 데이터가 없습니다.");
    }
  }

  // 매물 상세 정보 요청하기
  async function fetchSalesDetails(pk) {
    const response = await requestSaleById(pk);

    // 응답 데이터를 스토어에 저장
    if (response) {
      console.log(`response -> ${JSON.stringify(response)}`);

      // 선택된 매물 정보 업데이트
      selectedSalesDetails.value = response;
      console.log("Updated sales list:", sales.value);
    } else {
      console.warn("응답 데이터가 없습니다.");
    }
  }

  return {
    sales,
    selectedSalesDetails,
    fetchSalesList,
    fetchSalesDetails,
  };
});
