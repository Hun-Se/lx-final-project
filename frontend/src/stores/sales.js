import { ref } from "vue";
import { defineStore } from "pinia";

import { requestSalesListGlobal } from "@/api/sales";

export const usePrpStore = defineStore("sale", () => {
  //==== begin::매물 =====//

  // 매물 리스트
  const sales = ref([]);
  1;
  // 매물 리스트 요청하기
  async function fetchSalesList() {

    const response = await requestSalesListGlobal();
    console.log(`Fetched sales data: ${JSON.stringify(response)}`);

    // 응답 데이터를 스토어에 저장
    if (response) {
      // 매물 리스트 업데이트
      sales.value = response.data;
      console.log("Updated sales list:", sales.value);
    } else {
      console.warn("응답 데이터가 없습니다.");
    }
  }

  return {
    sales,
    fetchSalesList,
  };
});
