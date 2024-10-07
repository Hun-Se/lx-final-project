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
  
        <!-- 로그인 버튼 -->
        <button type="submit">Login</button>
      </form>
  
      <!-- 계정 생성으로 이동하는 버튼 -->
      <button @click="goToCreateAccount">Create Account</button>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  import { useRouter } from 'vue-router'; // vue-router 가져오기
  
  // 사용자 ID와 비밀번호 상태 변수
  const userId = ref('');  // 사용자 ID
  const userPw = ref('');  // 비밀번호
  
  const router = useRouter(); // vue-router 인스턴스 사용
  
  // 로그인 함수
  const login = async () => {
    try {
      // 서버로 로그인 요청 전송
      const response = await axios.post('/api/users/login', {
        userId: userId.value,
        userPw: userPw.value
      });
      console.log('Login Success', response.data);
    } catch (error) {
      console.error('Login Failed', error.response?.data || error.message);
    }
  };
  
  // Create Account 뷰로 이동하는 함수
  const goToCreateAccount = () => {
    router.push('/create_account'); // Create Account 페이지로 이동
  };
  </script>
  