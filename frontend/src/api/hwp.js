import { apiInstance } from "./index.js";
import axios from "axios";

export const requestHwp = async () => {
  const url =
    "https://docsconverter-example.cloud.hancom.com/hwp/doc2pdf?file_path=__template%2Fdefault%2Fsample_ko-KR.hwp&function=async&ignore_cache=false&watermark=false";
  try {
    const response = await axios.get(url);
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};
