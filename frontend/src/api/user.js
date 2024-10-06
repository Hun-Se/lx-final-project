import { apiInstance } from "./index.js";

const api = apiInstance();

export const requestUserList = async () => {
  const url = "api/user/user-list";
  try {
    const response = await api.post(url, {});
    return response.data;
  } catch (err) {
    console.error(`서버 요청 시 에러 : ${err}`);
    return null;
  }
};

// 사용자 리스트 요청 (이전 파일)
// export const requestUserList = () => {
//   return new Promise((resolve, reject) => {
//     // URL을 직접 문자열로 사용
//     const url = "/user/user-list";

//     requestPost(url, {}, (err, response) => {
//       if (err) {
//         handleError(err);
//         reject(err);
//         return;
//       }

//       console.log(`response -> ${JSON.stringify(response)}`);
//       resolve(response.data.data);
//     });
//   });
// };

export const requestUserGlobal = async (data) => {
  const url = "api/user/user";
  try {
    const response = await api.post(url, data);
    return response.data;
  } catch (err) {
    handleError(err);
    return null;
  }
};

// 사용자 정보를 요청하고 스토어에 저장 (이전 파일)
// export const requestUserGlobal = async (data) => {
//   // URL을 직접 문자열로 사용
//   const url = "/user/user";

//   try {
//     const response = await requestPostAsync(url, data);
//     console.log(`response -> ${JSON.stringify(response)}`);
//     return response.data;
//   } catch (err) {
//     handleError(err);
//     return null;
//   }
// };
