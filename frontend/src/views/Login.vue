<template>
    <div>
      <h2>Login</h2>
      <form @submit.prevent="login">
        <!-- 사용자 ID 입력 -->
        <label for="userId">User ID</label>
        <input v-model="userId" type="text" id="userId" required />
  
        <!-- 비밀번호 입력 -->
        <label for="userPw">Password</label>
        <input v-model="userPw" type="password" id="userPw" required />
  
        <button type="submit">Login</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  
  // 사용자 ID와 비밀번호 상태 변수
  const userId = ref('');  // 사용자 ID
  const userPw = ref('');  // 비밀번호
  
  // 로그인 함수
  const login = async () => {
    try {
      // 서버로 로그인 요청 전송
      const response = await axios.post('/api/auth/login', {
        userId: userId.value,
        userPw: userPw.value
      });
      console.log('Login Success', response.data);
    } catch (error) {
      console.error('Login Failed', error.response?.data || error.message);
    }
  };
  </script>
  