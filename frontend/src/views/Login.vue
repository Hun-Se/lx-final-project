<template>
    <div class="login-container">
      <div class="login-form-wrapper">
        <h2 class="login-title">로그인</h2>
        <form @submit.prevent="login" class="login-form">
          <!-- 사용자 ID 입력 -->
          <div class="input-group">
            <label for="userId">User ID</label>
            <input v-model="userId" type="text" id="userId" required placeholder="Enter your User ID" />
          </div>
  
          <!-- 비밀번호 입력 -->
          <div class="input-group">
            <label for="userPw">Password</label>
            <input v-model="userPw" type="password" id="userPw" required placeholder="Enter your password" />
          </div>
  
          <!-- 로그인 버튼 -->
          <button type="submit" class="login-btn">Login</button>
        </form>
  
        <!-- 계정 생성으로 이동하는 버튼 -->
        <button @click="goToCreateAccount" class="create-account-btn">Create Account</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  import { useRouter } from 'vue-router';
  
  const userId = ref('');
  const userPw = ref('');
  
  const router = useRouter();
  
  const login = async () => {
    try {
      const response = await axios.post('/api/users/login', {
        userId: userId.value,
        userPw: userPw.value,
      });

      // 로그인 성공 시 메인 페이지로 이동
      if (response.data !== 700) { // 로그인 성공
        console.log('Login Success', response.data);
        sessionStorage.setItem('userPk', response.data)

        router.push('/'); // 홈 페이지로 이동
      } else {
        console.error('Invalid credentials');
      }

    } catch (error) {
      console.error('Login Failed', error.response?.data || error.message);
    }
  };
  
  const goToCreateAccount = () => {
    router.push('/create_account');
  };
  </script>
  
  <style scoped>
  .login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-image: url('/assets/img/real_estate_bg.jpg'); /* 부동산 배경 이미지 */
    background-size: cover;
    background-position: center;
  }
  
  .login-form-wrapper {
    background-color: rgba(255, 255, 255, 0.9); /* 투명한 배경 */
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
    text-align: center;
  }
  
  .login-title {
    font-size: 2.5em;
    margin-bottom: 20px;
    color: #2c3e50;
  }
  
  .input-group {
    margin-bottom: 15px;
    text-align: left;
  }
  
  .input-group label {
    display: block;
    font-size: 1.2em;
    color: #34495e;
    margin-bottom: 5px;
  }
  
  .input-group input {
    width: 100%;
    padding: 10px;
    font-size: 1em;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .login-btn {
    width: 100%;
    padding: 12px;
    background-color: #27ae60;
    color: white;
    font-size: 1.2em;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .login-btn:hover {
    background-color: #2ecc71;
  }
  
  .create-account-btn {
    margin-top: 15px;
    padding: 10px;
    background-color: #3498db;
    color: white;
    font-size: 1em;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: 100%;
  }
  
  .create-account-btn:hover {
    background-color: #2980b9;
  }
  </style>
  