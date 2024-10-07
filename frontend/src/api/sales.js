import { apiInstance } from "./index.js";

const api = apiInstance();

export const requestSalesListGlobal = async () => {
  const url = "api/prp";
  try {
    const response = await api.post(url, {});
    console.log(response.data[0]);
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};
