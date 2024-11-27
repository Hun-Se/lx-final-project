import { apiInstance } from "./index.js";

const api = apiInstance(); // Axios 인스턴스 생성

// 사용자 리스트 요청
export const requestUserList = async () => {
  const url = "api/user/user-list";
  try {
    const response = await api.post(url, {});
    return response.data; // 성공 시 데이터 반환
  } catch (err) {
    console.error(`서버 요청 시 에러: ${err.message}`);
    return null;
  }
};

// 사용자 정보를 요청
export const requestUserGlobal = async (data) => {
  const url = "api/user/user";
  try {
    const response = await api.post(url, data);
    return response.data; // 성공 시 데이터 반환
  } catch (err) {
    console.error(`Error in requestUserGlobal: ${err.message}`);
    return null;
  }
};
