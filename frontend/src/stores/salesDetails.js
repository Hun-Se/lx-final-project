import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

import { requestPostAsync } from '../api/index';

export const useSalesDetailsStore = defineStore('salesDetails', () => {

  // 매물 상세 정보
  const saleDetails = ref([]);
  const selectedSaleDetailsId = ref(null);
  const selectedSaleDetails = ref(null);

  // 매물 상세 정보 요청하기
  async function fetchSalesDetails(salesId) {
    const url = '/saleDetails/saleDetails-list';
    
    try {
      const response = await requestPostAsync(url, { id: salesId });
      console.log(`response -> ${JSON.stringify(response)}`);

      // 매물 상세 정보 업데이트
      saleDetails.value = response.data.data; 
      
      // 선택된 매물 정보 업데이트
      selectedSaleDetails.value = saleDetails.value.find(item => item.id === salesId);
      return response.data;

    } catch(err) {
      if (err.code === "ECONNABORTED") {
        console.log(`서버 요청 시 타임아웃`);
      } else {
        console.error(`서버 요청 시 에러 : ${err.message}`);
      }
      return null;
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
    clearSelectedSalesDetails 
  }
});

