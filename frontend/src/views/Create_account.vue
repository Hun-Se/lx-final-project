<template>
    <div>
      <h2>Register</h2>
      <form @submit.prevent="register">
        <!-- 사용자 ID -->
        <label for="userId">User ID</label>
        <input v-model="userId" type="text" id="userId" required />
  
        <!-- 비밀번호 -->
        <label for="userPw">Password</label>
        <input v-model="userPw" type="password" id="userPw" required />
  
        <!-- 사용자 이름 -->
        <label for="userName">Name</label>
        <input v-model="userName" type="text" id="userName" required />
  
        <!-- 주민등록번호 -->
        <label for="userSsn">SSN</label>
        <input v-model="userSsn" type="text" id="userSsn" required />
  
        <!-- 모바일 번호 -->
        <label for="userMobile">Mobile</label>
        <input v-model="userMobile" type="text" id="userMobile" required />
  
        <!-- 이메일 -->
        <label for="userEmail">Email</label>
        <input v-model="userEmail" type="email" id="userEmail" required />
  
        <!-- 사용자 권한 -->
        <label for="userPerm">Permission</label>
        <select v-model="userPerm" id="userPerm" required>
          <option value="1">Admin</option>
          <option value="2">User</option>
        </select>
  
        <!-- 지역 외래키 -->
        <label for="regionPk">Region</label>
        <input v-model="regionPk" type="text" id="regionPk" required />
  
        <!-- 추가 지역 외래키 -->
        <label for="regionPk2">Region 2</label>
        <input v-model="regionPk2" type="text" id="regionPk2" required />
  
        <!-- 사용자 이미지 업로드 -->
        <label for="userImg">Image Upload</label>
        <input @change="onFileChange" type="file" id="userImg" />
  
        <button type="submit">Register</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  
  // 사용자 데이터
  const userId = ref('');       // 사용자 ID
  const userPw = ref('');       // 비밀번호
  const userName = ref('');     // 사용자 이름
  const userSsn = ref('');      // 주민등록번호
  const userMobile = ref('');   // 사용자 휴대폰 번호
  const userEmail = ref('');    // 이메일
  const userPerm = ref(2);      // 기본 권한 (2: User)
  const regionPk = ref('');     // 지역 외래키
  const regionPk2 = ref('');    // 추가 지역 외래키
  const userImgFile = ref(null); // 이미지 파일
  
  // 이미지 파일 선택 시 호출되는 함수
  const onFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      userImgFile.value = file; // 선택된 이미지 파일을 상태에 저장
    }
  };
  
  // 회원가입 요청 함수
  const register = async () => {
    try {
      const formData = new FormData();
  
      // FormData에 사용자 정보 추가
      formData.append('userId', userId.value);
      formData.append('userPw', userPw.value);
      formData.append('userName', userName.value);
      formData.append('userSsn', userSsn.value);
      formData.append('userMobile', userMobile.value);
      formData.append('userEmail', userEmail.value);
      formData.append('userPerm', userPerm.value);
      formData.append('regionPk', regionPk.value);
      formData.append('regionPk2', regionPk2.value);
  
      // 이미지 파일이 있는 경우 FormData에 추가
      if (userImgFile.value) {
        formData.append('userImg', userImgFile.value);
      }
  
      // axios를 이용해 POST 요청
      const response = await axios.post('/api/auth/register', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',  // 파일 전송을 위한 헤더
        }
      });
      
      console.log('Registration Success', response.data);
    } catch (error) {
      console.error('Registration Failed', error.response.data);
    }
  };
  </script>
  