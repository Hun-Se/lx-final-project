import { apiInstance } from "./index.js";

const api = apiInstance();

export const requestSalesList = async () => {
  const url = "api/properties";
  try {
    const response = await api.get(url);
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};

export const requestSaleById = async (pk) => {
  const url = `api/properties/${pk}`;
  try {
    const response = await api.get(url);
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};
