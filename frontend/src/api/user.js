import { requestConfig } from '../../app.config';
import { requestPost, requestPostAsync } from './index';

// 사용자 리스트 요청
export const requestUserList = () => {
  return new Promise((resolve, reject) => {
    // URL을 직접 문자열로 사용
    const url = '/user/user-list';
    
    requestPost(url, {}, (err, response) => {
      if (err) {
        handleError(err);
        reject(err);
        return;
      }

      console.log(`response -> ${JSON.stringify(response)}`);
      resolve(response.data.data);
    });
  });
};

// 사용자 정보를 요청하고 스토어에 저장
export const requestUserGlobal = async (data) => {
  // URL을 직접 문자열로 사용
  const url = '/user/user';

  try {
    const response = await requestPostAsync(url, data);
    console.log(`response -> ${JSON.stringify(response)}`);
    return response.data; 
  } catch (err) {
    handleError(err);
    return null;
  }
};

function handleError(err) {
  if (err.code === "ECONNABORTED") {
    console.log(`서버 요청 시 타임아웃 : ${requestConfig.timeout}`);
  } else {
    console.error(`서버 요청 시 에러 : ${err.message}`);
  }
}
