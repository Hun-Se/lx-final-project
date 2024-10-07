<template>
    <div class="register-page">
      <div class="register-form-container">
        <h2 class="register-title">회원가입</h2>
        <form @submit.prevent="register" class="register-form">
          <div class="input-group">
            <label for="userId">
              <i class="fas fa-user"></i> User ID
            </label>
            <input v-model="userId" type="text" id="userId" required placeholder="User ID 입력" />
          </div>
  
          <div class="input-group">
            <label for="userPw">
              <i class="fas fa-lock"></i> Password
            </label>
            <input v-model="userPw" type="password" id="userPw" required placeholder="비밀번호 입력" />
          </div>
  
          <div class="input-group">
            <label for="userName">
              <i class="fas fa-id-card"></i> Name
            </label>
            <input v-model="userName" type="text" id="userName" required placeholder="이름 입력" />
          </div>

                    <!-- 주민등록번호 추가 -->
                    <div class="input-group">
            <label for="userSsn">
              <i class="fas fa-id-badge"></i> SSN
            </label>
            <input v-model="userSsn" type="text" id="userSsn" required placeholder="주민등록번호 입력" />
          </div>
  
          <!-- 모바일 번호 -->
          <div class="input-group">
            <label for="userMobile">
              <i class="fas fa-phone"></i> Mobile
            </label>
            <input v-model="userMobile" type="text" id="userMobile" required placeholder="휴대폰 번호 입력" />
          </div>
  
          <!-- 지역 외래키 -->
          <div class="input-group">
            <label for="regionPk">
              <i class="fas fa-map-marker-alt"></i> Region
            </label>
            <input v-model="regionPk" type="text" id="regionPk" required placeholder="지역 입력" />
          </div>
  
          <!-- 사용자 이미지 업로드 -->
          <div class="input-group">
            <label for="userImg">
              <i class="fas fa-camera"></i> 이미지 업로드
            </label>
            <input @change="onFileChange" type="file" id="userImg" />
          </div>
  
          <!-- 사용자 권한 -->
          <div class="input-group">
            <label for="userPerm">
              <i class="fas fa-user-shield"></i> 권한
            </label>
            <select v-model="userPerm" id="userPerm" required>
              <option value="1">일반 사용자</option>
              <option value="2">공인 중개사</option>
            </select>
          </div>
  
          <button type="submit" class="register-btn">회원가입</button>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  
  const userId = ref('');
  const userPw = ref('');
  const userName = ref('');
  const userMobile = ref('');
  const regionPk = ref('');
  const userPerm = ref(2);
  const userImgFile = ref(null);
  
  const onFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      userImgFile.value = file;
    }
  };
  
  const register = async () => {
    try {
      const formData = new FormData();
      formData.append('userId', userId.value);
      formData.append('userPw', userPw.value);
      formData.append('userName', userName.value);
      formData.append('userMobile', userMobile.value);
      formData.append('regionPk', regionPk.value);
      formData.append('userPerm', userPerm.value);
  
      if (userImgFile.value) {
        formData.append('userImg', userImgFile.value);
      }
  
      const response = await axios.post('/api/users/create_account', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      console.log('Registration Success', response.data);
    } catch (error) {
      console.error('Registration Failed', error.response.data);
    }
  };
  </script>
  
  <style scoped>
  /* 배경 설정 */
  .register-page {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: url('/assets/img/realestate-bg.jpg') no-repeat center center fixed;
    background-size: cover;
  }
  
  /* 회원가입 폼 */
  .register-form-container {
    background-color: rgba(255, 255, 255, 0.9);
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 500px;
    text-align: center;
  }
  
  .register-title {
    font-size: 2.5em;
    margin-bottom: 20px;
    color: #2c3e50;
  }
  
  .input-group {
    margin-bottom: 15px;
    text-align: left;
  }
  
  .input-group label {
    font-size: 1.2em;
    color: #34495e;
    margin-bottom: 5px;
  }
  
  .input-group input,
  .input-group select {
    width: 100%;
    padding: 10px;
    font-size: 1em;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .register-btn {
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
  
  .register-btn:hover {
    background-color: #2ecc71;
  }
  </style>
  