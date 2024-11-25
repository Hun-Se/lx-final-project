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
  const url = `api/properties/interest?userPk=` + Number(userPk);
  try {
    const response = await api.get(url);
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};

export const deleteInterestPrp = async (prpPk) => {
  const url = `api/properties/interest/delete`;
  const data = {
    prpPk: prpPk,
  };
  try {
    const response = await api.delete(url, { data: data });
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};
