import { useSalesDetailsStore } from "@/stores/salesDetails";
import { storeToRefs } from "pinia";

import { apiInstance } from "./index.js";

const api = apiInstance();

export const requestSalesDetailsListGlobal = async () => {
  const url = "api/salesDetails/salesDetails-list";

  try {
    const response = await api.post(url, {});
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};
