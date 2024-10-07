import { apiInstance } from "./index.js";

const api = apiInstance();

// 전체조회
async function getExamples() {
  try {
    const response = await api.get("api/example");
    return response.data;
  } catch (err) {
    console.error(err);
  }
}

// path 방식
async function getExampleById(id) {
  try {
    const response = await api.get(`/example/${id}`);
    return response.data;
  } catch (err) {
    console.error(err);
  }
}

// params 예시
// async function getExampleById(id) {
//   try {
//     const response = await api.get("/example", { id });
//     return response.data;
//   } catch (err) {
//     console.error(err);
//   }
// }

// POST 요청
async function createExample(data) {
  try {
    const response = await api.post("/example", data);
    return response.data;
  } catch (err) {
    console.error(err); // 에러를 콘솔에 출력
  }
}

// PUT 요청
async function updateExample(id, data) {
  try {
    const response = await api.put(`/example/${id}`, data);
    return response.data;
  } catch (err) {
    console.error(err);
  }
}

// DELETE 요청
async function deleteExample(id) {
  try {
    const response = await api.delete(`/example/${id}`);
    return response.data;
  } catch (err) {
    console.error(err);
  }
}

// PATCH 요청
async function partialUpdateExample(id, data) {
  try {
    const response = await api.patch(`/example/${id}`, data);
    return response.data;
  } catch (err) {
    console.error(err);
  }
}

export { getExamples };