<template>

<h1 class="display-1" style="text-align: center; margin-top: 2ex;">
  LX가 만든 부동산 데이터 플랫폼
</h1>

<h1 class="display-5" style="text-align: center;">
  고객을 위한 부동산 직거래 정보, LX부동산
</h1>

	
	<div class="nanum-pen-script-regular" style="margin-top: 6ex;">
    <div class="carousel" style="margin-bottom: 2ex;">
      <div class="group">
        <div class="Scard">
          <img src="/assets/img/sales01.png" alt="이미지 1" class="card-image">
          <p class="slider-text" style="margin-top: 3ex; font-size: 1.6em; color: white; font-weight: bold;">가격 예측 AI</p> 
        </div>  
      </div>
      <div class="group">
        <div class="Scard">
          <img src="/assets/img/sales01.png" alt="이미지 2" class="card-image">
          <p class="slider-text" style="margin-top: 3ex; font-size: 1.6em; color: white; font-weight: bold;">가격 예측 AI</p> 
        </div>
      </div>
      <div class="group">
        <div class="Scard">
          <img src="/assets/img/sales01.png" alt="이미지 3" class="card-image">
          <p class="slider-text" style="margin-top: 3ex; font-size: 1.6em; color: white; font-weight: bold;">가격 예측 AI</p> 
        </div>  
      </div>
      <div class="group">
        <div class="Scard">
          <img src="/assets/img/sales01.png" alt="이미지 4" class="card-image">
          <p class="slider-text" style="margin-top: 3ex; font-size: 1.6em; color: white; font-weight: bold;">가격 예측 AI</p> 
        </div> 
      </div>
      <div class="group">
        <div class="Scard">
          <img src="/assets/img/sales01.png" alt="이미지 5" class="card-image">
          <p class="slider-text" style="margin-top: 3ex; font-size: 1.6em; color: white; font-weight: bold;">가격 예측 AI</p> 
        </div>  
      </div>
      <div class="group">
        <div class="Scard">
          <img src="/assets/img/sales01.png" alt="이미지 5" class="card-image">
          <p class="slider-text" style="margin-top: 3ex; font-size: 1.6em; color: white; font-weight: bold;">가격 예측 AI</p> 
        </div>  
      </div>
    </div>

    <div class="d-grid gap-2 col-5 mx-auto">
      <button type="button" class="btn btn-secondary" style="border-radius: 10ex; font-weight: bold;">로그인하고 더 많은 기능보기</button>
    </div>

</div>

</template>

<script setup>
 
 import { ref, onMounted } from 'vue';
 import router from '@/router/index.js';


  // 스토어 불러오기
  import { useMaruStore } from "@/stores/sales";
  import { storeToRefs } from 'pinia';


  // 페이지네이션 불러오기
  import Pagination from '@/components/Pagination.vue';
  import { usePagination } from '@/util/pagination.js';

  import { Modal } from 'bootstrap';

  // 업로드 불러오기
  import { useUpload } from '@/util/upload.js';

  // 업로드 사용하기
  const { upload } = useUpload();


  // 스토어 사용하기
  const maruStore = useMaruStore();
  const { course, courseCount } = storeToRefs(maruStore);

  // 페이지네이션 사용하기
  const { makePagination } = usePagination();

  // 페이지네이션 객체
  const pagination = ref({});

  // 업로드 대화상자를 위한 변수
  let uploadModal;

  const nameInput = ref('');


  onMounted(() => {
    console.log('onMounted 호출됨.');
  
    // 코스 리스트 요청하기
    requestCourseListGlobal(1, 10);

  })
 
  /**
   * 리스트 요청하기
   */
   async function requestCourseListGlobal(page, perPage) {
    console.log(`requestCourseListGlobal 호출됨.`);
    
    try {
      const params = {
        requestCode: '1001',
        page: page,
        perPage: perPage
      }

      const result = await maruStore.requestCourseListGlobal(params);

      // 페이지네이션 처리
      pagination.value = makePagination(result.data.header);
      console.log(`pagination object : ${JSON.stringify(pagination.value)}`);
 
    } catch(err) {
      console.error(`웹서버 요청 중 에러 발생 : ${err}`);
    }

  }


  // 업로드 대화상자
  function showUploadDialog() {

    const elem = document.querySelector('#uploadDialog');
    uploadModal = new Modal(elem);
    uploadModal.show();

  }


  const selectedFile = ref('');

  async function getFileName(files) {
    selectedFile.value = files[0];
    await base64();
  }

  function base64() {
    // 비동기적으로 동작하기 위하여 promise를 return 해준다.
    return new Promise(resolve => {
      // 업로드된 파일을 읽기 위한 FileReader() 객체 생성
      let a = new FileReader()
      // 읽기 동작이 성공적으로 완료됐을 때 발생
      a.onload = e => {
        resolve(e.target.result)
        // 썸네일을 보여주고자 하는 <img>에 id값을 가져와 src에 결과값을 넣어준다.
        const previewImage = document.getElementById('preview')
        previewImage.src = e.target.result
      }
      // file 데이터를 base64로 인코딩한 문자열. 이 문자열을 브라우저가 인식하여 원래 데이터로 만들어준다.
      a.readAsDataURL(selectedFile.value);
    })
  }

  async function uploadFile() {
    console.log(`uploadFile 호출됨 -> name : ${selectedFile.value.name}, type : ${selectedFile.value.type}`);
    
    try {
      const response = await upload(selectedFile.value, (progress) => {
        console.log(`업로드 progress : ${progress}`);
      })

      console.log(`response -> ${JSON.stringify(response)}`);


      // 코스 정보 추가
      const name = nameInput.value;
      const picture = response.data.filename;

      requestCourseCreate(name, picture);

      //uploadModal.hide();

    } catch(err) {
      console.error(`에러 -> ${err}`); 
    }

  }

  
  /**
   * 추가 요청하기
   */
   async function requestCourseCreate(name, picture) {
    
    const data = {
      name: name,
      group_id: 'G01',
      group_name: '북한산',
      duration: 50,
      distance: 2300,
      level: 2,
      picture: picture
    }

    try {
        const response = await axios({
            method: 'post',
            baseURL: 'http://172.168.10.126:8001',
            url: '/maru/course-create',
            data: data,
            responseType: 'json'
        })

        console.log(response.data);

        // 리스트 요청
        requestCourseListGlobal(1, 10);

        // 대화상자 닫기
        if (uploadModal) {
          uploadModal.hide();
        }

    } catch(err) {
        result.value = `에러 -> ${err}`;
    }

  }


</script>

<style>
/*가을룩*/
.carousel {
    margin: 0 auto;
    padding: 20px 0;
    max-width: 1500px; /* 최대 너비 조정 */
    overflow: visible; 
    display: flex;
}

.Scard {
    width: 250px; /* 카드 너비 조정 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 그림자 효과 */
    display: flex; /* 플렉스 박스 사용 */
    flex-direction: column; /* 세로 방향 정렬 */
    justify-content: flex-start; /* 위쪽 정렬 */
    align-items: center; /* 중앙 정렬 */
    min-height: 350px; /* 카드 최소 높이 */
    margin-right: 20px; /* 카드 간격 */
    margin-bottom: 20px;
    background:#457fca;    /* fallback for old browsers */
    background: -webkit-linear-gradient(to bottom,#5691c8, #4684d4);   /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to bottom,#5691c8, #4684d4);  /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    padding: 15px; 
    border-radius: 15px;  
    font-size:16px; 
    
}

.Scard img {
    width: 100%; /* 카드 내에서 이미지가 카드 폭에 맞도록 설정 */
    height: 250px; /* 고정 높이 설정 */   
    margin-top: 10px; /* 위로 이동시키기 위한 마진 조정 */
    border: 2px solid #fff; /* 테두리 추가 (색상 및 두께 조정 가능) */
    border-radius: 12px; /* 테두리 모서리 둥글게 설정 */
}

.slider-text {
    margin-top: 10px; /* 이미지와 텍스트 사이 간격 조정 */
    color: #000; /* 텍스트 색상 (검정) */
    text-align: center; /* 텍스트 중앙 정렬 */
    font-size: 16px; /* 글씨 크기 조정 */
}
.group {
    will-change: transform; 
    animation: scrolling 20s linear infinite;
    display: flex;
    gap: 30px; /* 카드 사이의 간격 */
}

@keyframes scrolling {
    0% {
        transform: translateX(0);
    }
    100% {
        transform: translateX(-100%);
    }
}

.carousel:hover .group {
    animation-play-state: paused; /* 마우스 오버 시 애니메이션 일시 정지 */
}


</style>