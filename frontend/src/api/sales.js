import { apiInstance } from "./index.js";

const api = apiInstance();

export const requestSalesListGlobal = async () => {
  const url = "api/sales/sales-list";
  try {
    const response = await api.post(url, {});
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};
