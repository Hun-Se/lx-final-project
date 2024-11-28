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

// 내매물 조회
export const requestMyProperty = async (user_pk) => {
  const url = `api/properties/agent?agentPk=` + user_pk;
  try {
    const response = await api.get(url);
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};

// 매물등록
export const createProperty = async (data) => {
  const url = `api/properties/create`
  try {
    const response = await api.post(url, data);
    return response.data;
  } catch(err) {
    console.error(`매물등록 에러 : ${err}`);
    return null;
  }
}

export const requestMyInterestPrp = async (userPk) => {
  const url = `api/favprp/${Number(userPk)}`;
  try {
    const response = await api.get(url);
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};

export const addInterestPrp = async (data) => {
  const url = "api/favprp/add";
  try {
    const response = await api.post(url, data);
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};

// 관심 매물 삭제
export const deleteInterestPrp = async (prpPk) => {
  const url = `api/favprp/delete`;
  try {
    const response = await api.delete(url, { data: { prpPk } });
    return response.data;
  } catch (err) {
    console.error(`관심 매물 삭제 에러 : ${err}`);
    return null;
  }
};