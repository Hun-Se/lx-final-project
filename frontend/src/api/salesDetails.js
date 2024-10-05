import { requestConfig } from '../../app.config';
import { requestPostAsync } from './index';
import { useSalesDetailsStore } from "@/stores/salesDetails";
import { storeToRefs } from 'pinia';

/// 매물 리스트 가져오기
const handleError = (err) => {
  if (err.code === "ECONNABORTED") {
    console.log(`서버 요청 시 타임아웃 : ${requestConfig.timeout}`);
  } else {
    console.error(`서버 요청 시 에러 : ${err.message}`);
  }
};

export const requestSalesDetailsListGlobal = async () => {
  const url = '/salesDetails/salesDetails-list';

  try {
    const response = await requestPostAsync(url, {});

    if (response.data && response.data.data) {
      console.log(`response -> ${JSON.stringify(response)}`);

      const salesDetailsStore = useSalesDetailsStore();
      const { salesDetails } = storeToRefs(salesDetailsStore);

      // 매물 리스트 업데이트
      salesDetails.value = response.data.data;
      console.log('Updated salesDetails list:', salesDetails.value);
    } else {
      console.warn('응답 데이터가 없습니다.');
    }

  } catch (err) {
    handleError(err);
    // 사용자에게 에러 메시지를 보여주는 추가 로직을 여기에 추가할 수 있습니다.
  }
};
