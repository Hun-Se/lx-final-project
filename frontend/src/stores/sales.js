import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

import { requestPostAsync } from '../api/index';

export const useMaruStore = defineStore('maru', () => {

  //==== begin::매물 =====//

  // 매물 리스트
  const sales = ref([]);
  const errorMessage = ref(null); // 에러 메시지 추가

  // 매물 리스트 요청하기
  async function fetchSalesList(data) {
    const url = '/sales/sales-list';
    
    try {
      const response = await requestPostAsync(url, data);
      console.log(`Fetched sales data: ${JSON.stringify(response)}`);

      // 응답 데이터를 스토어에 저장
      sales.value = response.data.data;

      // 에러 메시지 초기화
      errorMessage.value = null; 
      return response.data;
    } catch (err) {
      // 에러 처리
      if (err.code === "ECONNABORTED") {
        errorMessage.value = '서버 요청 시 타임아웃 발생'; // 사용자에게 알림
      } else {
        errorMessage.value = `서버 요청 시 에러: ${err.message}`;
      }
      console.error(errorMessage.value);
      return null;
    }
  }

  return { 
    sales, 
    fetchSalesList, 
    errorMessage // 에러 메시지 반환
  };
});


