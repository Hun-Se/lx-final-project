import { requestConfig } from '../../app.config';
import { requestPostAsync } from './index';
import { useSalesStore } from "@/stores/sales";
import { storeToRefs } from 'pinia';

// 오류 처리 함수
const handleError = (err) => {
	if (err.code === "ECONNABORTED") {
	  console.log(`서버 요청 시 타임아웃 : ${requestConfig.timeout}`);
	} else {
	  console.error(`서버 요청 시 에러 : ${err.message}`);
	}
};

export const requestSalesListGlobal = async () => {
	const url = '/sales/sales-list'; // 여기서 URL을 직접 하드코딩

	try {
	  const response = await requestPostAsync(url, {});
  
	  if (response.data && response.data.data) {
		console.log(`response -> ${JSON.stringify(response)}`);
  
		const salesStore = useSalesStore();
		const { sales } = storeToRefs(salesStore);
  
		// 매물 리스트 업데이트
		sales.value = response.data.data;
		console.log('Updated sales list:', sales.value);
	  } else {
		console.warn('응답 데이터가 없습니다.');
	  }
  
	} catch (err) {
	  handleError(err);
	}
};
