<template>
  <MobileHeader title="신고페이지"></MobileHeader>
  <div class="container mt-20">
    <label class="fs-4 fw-semibold form-label mt-3">
      <span class="required">신고 분류 선택</span>
    </label>

    <!-- 셀렉트박스 반복 렌더링 -->
    <div
        class="select-menu mt-5"
        v-for="(menu, index) in menus"
        :key="index"
        :class="{ active: menu.isActive }"
    >
      <div class="select-btn form-select" @click="toggleMenu(index)">
        <span class="sBtn-text">{{ menu.selected || menu.placeholder }}</span>
        <i class="bx bx-chevron-down"></i>
      </div>
      <ul class="options" v-show="menu.isActive">
        <li
            class="option"
            v-for="(option, optionIndex) in menu.options"
            :key="optionIndex"
            @click="selectOption(index, option)"
        >
          <span class="option-text">{{ option }}</span>
        </li>
      </ul>
    </div>
    <label class="fs-4 fw-semibold form-label mt-10">
      <span class="required">신고내용 입력</span>
    </label>
    <div class="mt-3 mb-3">
      <div class="form-floating">
        <textarea v-model="reportContent" class="form-control" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
        <label for="floatingTextarea" style="color: grey">신고내용의 정확한 확인을 위해서 구체적으로 기재해 주세요.</label>
      </div>
    </div>
    <label class="fs-4 fw-semibold form-label mt-5">
      <span class="required">증빙자료 선택</span>
      <div class="fw-semibold mt-3 fs-7 text-muted">
        광고화면과 문자내용은 어플 내에서 자동으로 취합하여 전송됩니다.
      </div>
    </label>
    <div class="d-flex justify-content-between mt-3">
      <div class="form-check d-flex align-items-center"
        v-for="(option, index) in proofOptions" :key="index"
      >
        <input
            class="form-check-input"
            type="checkbox"
            :value="option"
            :id="'proofOption' + index"
            v-model="selectedProofOptions"
        />
        <label
            class="form-check-label ms-2"
            :for="'proofOption' + index"
        >
          {{ option }}
        </label>
      </div>
    </div>

    <label class="fs-4 fw-semibold form-label mt-5">
      <span class="">자료 첨부</span>
    </label>
    <div class="mt-3 mb-3">
      <input
          class="form-control"
          type="file"
          id="formFileMultiple"
          multiple
          @change="handleFileUpload"
      />
    </div>

    <div class="btn-group d-flex justify-content-center mt-6">
      <button class="btn btn-light-dark">취소</button>
      <button class="btn btn-report" @click="submitReport">확인</button>
    </div>
  </div>

  <MobileBottomTapBar></MobileBottomTapBar>
</template>

<script setup>
import MobileHeader from "@/components/MobileHeader.vue";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";
import { ref } from 'vue';
import { useRouter} from "vue-router"

const categoryData = {
  대분류: {
    "명시의무 위반": ["중개사무소", "개업공인중개사", "중개보조원", "중개대상물"],
    "광고주체": ["개업공인중개사가 아닌 자"],
    "부당한 표시·광고": ["부존재·허위 매물", "존재·허위매물", "중개의사 없는 매물", "거짓광고", "과장광고", "기만적인 표시·광고"],
  },
  중분류: {
    "중개사무소": ["상호 명시위반", "소재지 명시위반", "전화번호 명시위반","등록번호 명시위반"],
    "개업공인중개사": ["성명 명시위반"],
    "중개보조원": ["중개보조원 명시금"],
    "중개대상물": ["토지_소재지 명시위반", "토지_면적 명시위반",
      "토지_가격 명시위반", "토지_중개대상물 종류 명시위반",
      "토지_거래형태 명시위반", "건축물 소재지 명시위반","건축물_면적 명시위반",
      "건축물_가격 명시위반", "건축물_중개대상물 종류 명시위반", "건축물_거래형태 명시위반",
      "건축물_총 층수 명시위반", "건축물_입주가능일 명시위반", "건축물_방 수 및 욕실 수 명시위반",
      "건축물_행정기관 승인일자 명시위반", "건축물_주차대수 명시위반", "건축물_관리비 명시위반", "건축물_방향 명시위반",
      "입목_소재지 명시위반", "입목_면적 명시위반", "입목_가격 명시위반", "입목_수출,수량,수령 다름","입목_거래형태 명시위반",
      "공장재단_소재지 명시위반","공장재단_가격 명시위반","공장재단_거래형태 명시위반","광업재단_소재지 명시위반","광업재단_가격 명시위반",
      "광업재단_거래형태 명시위반"],
    "개업공인중개사가 아닌 자": ["중개보조원 광고", "대표 미병기 소속공인중개사 광고", "무자격 광고"],
    "부존재·허위 매물": ["표시·광고 위치 미존재", "표시·광고 위치의 중개대상물과 동일하지 않음"],
    "존재·허위매물": ["중개의뢰하지 않았음에도 임의 표시·광고(제3자 공동중개를 허용하지 않은 표시광고)", "표시·광고 당시부터, 계약체결 사실을 알고도 표시·광고", "중개의뢰하지 않았음에도 임의 표시·광고(제3자 공동중개를 허용하지 않은 표시광고)","거래불가 부동산 표시·광고"],
    "중개의사 없는 매물": ["타 중개대상물 계속 권유", "계약체결 인지 후 지체 없이 삭제 지연"],
    "거짓광고": ["실제 가격과 다르게 표시·광고", "실제 면적과 다르게 표시·광고", "다른 평면도나 사진 사용", "토지 용도를 다르게 표시·광고", "기타 거짓 표시·광고"],
    "과장광고": ["옵션 성능 과장", "관리비 표시 위반", "실제 방향과 다르게 표시·광고", "주요 교통시설과 거리를 직선거리로 표시·광고", "기타 과장 표시·광고"],
    "기만적인 표시·광고": ["선택에 중요한 사실 은폐·누락", "소비자 오인 소지"]
  },
};

const menus = ref([
  {
    placeholder: "대분류 선택",
    options: Object.keys(categoryData.대분류), // 대분류 옵션
    isActive: false,
    selected: "",
  },
  {
    placeholder: "중분류 선택",
    options: [], // 중분류는 대분류 선택 시 동적으로 변경
    isActive: false,
    selected: "",
  },
  {
    placeholder: "소분류 선택",
    options: [], // 소분류는 중분류 선택 시 동적으로 변경
    isActive: false,
    selected: "",
  },
]);

const proofOptions = ref(["광고화면", "녹취", "문자내용", "기타자료"]);
const selectedProofOptions = ref([]);
const reportContent = ref("");
const uploadedFiles = ref([]);

const toggleMenu = (index) => {
  menus.value = menus.value.map((menu, menuIndex) => ({
    ...menu,
    isActive: menuIndex === index ? !menu.isActive : false,
  }));
};

const selectOption = (menuIndex, option) => {
  menus.value[menuIndex].selected = option;
  menus.value[menuIndex].isActive = false;

  // 선택에 따라 다음 메뉴의 옵션 업데이트
  if (menuIndex === 0) {
    // 대분류 선택 시 중분류 업데이트
    menus.value[1].options = categoryData.대분류[option] || [];
    menus.value[1].selected = ""; // 중분류 초기화
    menus.value[2].options = []; // 소분류 초기화
    menus.value[2].selected = "";
  } else if (menuIndex === 1) {
    // 중분류 선택 시 소분류 업데이트
    menus.value[2].options = categoryData.중분류[option] || [];
    menus.value[2].selected = ""; // 소분류 초기화
  }
};

// 파일 업로드 핸들러
const handleFileUpload = (event) => {
  uploadedFiles.value = Array.from(event.target.files);
};

const submitReport = async () => {
  const userPk = sessionStorage.getItem('userPk') || 1;

  // 필수 값 확인
  if (!menus.value.every((menu) => menu.selected)) {
    alert("모든 카테고리를 선택해야 합니다.");
    return;
  }

  if (!reportContent.value) {
    alert("신고 내용을 입력해야 합니다.");
    return;
  }

  // 1. /api/flr/save 호출하여 flr 테이블에 신고 데이터 추가
  try{
    const flrSaveResponse = await axios.post('/api/flr/save', {
      userPk : 1,
      prpPk: 1,
      chatPk: null,
      recPk: null,
      flrCateUpper: menus.value[0].selected,
      flrCateMiddle: menus.value[1].selected,
      flrCateLower: menus.value[2].selected,
      flrContent: reportContent.value
    });

    console.log("flrSaveResponse:", flrSaveResponse.data); // 디버깅 코드 추가
    const flrPk = flrSaveResponse.data.flrPk;
    console.log("flrPk:", flrPk); // flrPk가 제대로 전달되는지 확인

    if (!flrPk) {
      alert("flr_pk가 정상적으로 생성되지 않았습니다.");
      return;
    }

    // 2. /api/audio/uplad 호출해서 녹음파일 저장하고 처리
    // FormData 선언
    const formData = new FormData();

    // 기본 데이터 추가
    formData.append("flr_pk", flrPk);

    // 파일 유효성 검사 및 추가
    if (uploadedFiles.value.length > 0) {
      const allowedExtensions = ["mp3", "wav", "m4a", "jpg", "png", "jpeg", "gif", "pdf", "doc", "docx"];
      const fileExtension = uploadedFiles.value[0].name.split(".").pop().toLowerCase();
      if (!allowedExtensions.includes(fileExtension)) {
        alert("지원되지 않는 파일 형식입니다.");
        return;
      }
      formData.append("file", uploadedFiles.value[0]); // 단일 파일 업로드 처리
    } else {
      alert("파일을 업로드해야 합니다.");
      return;
    }

    // 디버깅: formData 내용 확인
    for (let pair of formData.entries()) {
      console.log(pair[0] + ", " + pair[1]);
    }

    const response = await axios.post("/api/audio/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    console.log("서버 응답:", response.data);
    alert("신고가 성공적으로 접수되었습니다.");
  } catch (error){
    console.error("요청 실패:", error.response || error);
    alert("신고 접수 중 오류가 발생했습니다.");
  }
};

const router = useRouter();

const onClickMoveMap = () => {
  router.push({ path: "/mobile_map"});
}
</script>

<style scoped>
.select-menu {
  width: 100%;
}
.select-menu .select-btn {
  display: flex;
  height: 25px;
  background: #fff;
  padding: 20px;
  font-size: 1rem;
  font-weight: 400;
  border-radius: 8px;
  align-items: center;
  cursor: pointer;
  justify-content: space-between;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.select-btn i {
  font-size: 25px;
  transition: 0.3s;
}
.select-menu.active .select-btn i {
  transform: rotate(-180deg);
}
.select-menu .options {
  position: relative;
  padding: 20px;
  margin-top: 10px;
  border-radius: 8px;
  background: #fff;
  box-shadow: 0 0 3px rgba(0, 0, 0, 0.1);
  display: none;
}
.select-menu.active .options {
  display: block;
}
.options .option {
  display: flex;
  height: 30px;
  cursor: pointer;
  padding: 0 16px;
  border-radius: 8px;
  align-items: center;
  background: #fff;
}
.options .option:hover {
  background: #f2f2f2;
}
.option i {
  font-size: 25px;
  margin-right: 12px;
}
.option .option-text {
  font-size: 12px;
  color: #333;
}

.btn-report {
  color: var(--color-bg-white);
  background: var(--color-bg-blue1);
}

.form-check-input:checked {
  background-color: var(--color-bg-third);
  border-color: var(--color-bg-third);
}

.btn-group {
  padding-bottom: 7rem;
}
</style>
