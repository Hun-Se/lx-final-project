<template>
  <MobileHeader></MobileHeader>
  <div id="mainPage">
    <div class="fab-wrapper">
  <input id="fabCheckbox" type="checkbox" class="fab-checkbox" />
  <label class="fab" for="fabCheckbox">
    <span class="fab-dots fab-dots-1"></span>
    <span class="fab-dots fab-dots-2"></span>
    <span class="fab-dots fab-dots-3"></span>
  </label>
  <div class="fab-wheel">
    <a class="fab-action fab-action-1">
      <i class="bi bi-search"></i>
    </a>
    <a class="fab-action fab-action-2">
      <img class="ai-image" src="/assets/img/ai_icon.svg" alt="">
    </a>
        <a class="fab-action fab-action-3">
          <i class="bi bi-funnel"></i>
    </a>
        <a class="fab-action fab-action-4">
          <i class="bi bi-question-lg"></i>
    </a>
  </div>
</div>
    <!-- <div class="header-featrues p-5">
      <div class="search-bar">
        <div class="ai-chat text-white text-center p-3">
          <div><i class="bi bi-building text-white fs-1"></i></div>
          <div class="mt-3 fs-9" >매물필터</div>
        </div>
        <div class="ai-chat text-white text-center p-3">
          <div><i class="bi bi-building text-white fs-1"></i></div>
          <div class="mt-3 fs-9" >공인중개사</div>
        </div>
        <div style="display: none;">
          <div>
          <input
            class="text-center"
            v-model="location"
            placeholder="위치를 입력해주세요"
          />
          에서
            <select v-model="selectedTransport" style="">
              <option disabled selected>이동수단</option>
              <option value="walking">도보</option>
              <option value="driving">자차</option>
              <option value="public_transport">대중교통</option></select
            >로
          </div>
          <div class="d-flex align-items-center">
            <input
              class="form-control text-center input-time-mobile"
              type="number"
              v-model.number="hours"
              placeholder="0"
              min="0"
            />
            시간
            <input
            class="form-control text-center  input-time-mobile"
            type="number"
            v-model.number="minutes"
            placeholder="0"
            min="0"
            max="59"
          />
          분
          <span>내에 있는 매물</span>
          </div>
        </div>
        <button @click="submit" type="button" class="btn btn-primary btn-sm " style="display: none;">
            검색
      </button>
      </div>

    </div> -->

    <NaverMap></NaverMap>
  </div>
  <MobileBottomTapBar></MobileBottomTapBar>
</template>

<script setup>
import { ref, computed, reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useSaleStore } from "@/stores/property.js";
import NaverMap from "@/components/NaverMap.vue";
import { storeToRefs } from "pinia";
import Header2 from "@/components/Header2.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";

// isMobile 변수 정의 (화면 크기를 기준으로)
const isMobile = ref(window.innerWidth <= 768);

// 화면 크기가 변경될 때 모바일 여부를 업데이트
window.addEventListener("resize", () => {
  isMobile.value = window.innerWidth <= 768;
});

// 렌더할 헤더 선택
const computedHeader = computed(() => {
  return isMobile.value ? MobileHeader : Header2;
});

const router = useRouter();
const store = useSaleStore();
const { sales, selectedSalesDetails } = storeToRefs(store);

onMounted(() => {
  init();
  isMobile.value = window.innerWidth <= 768;
});

const init = () => {
  store.fetchSalesList();
  console.log(sales.value);
};

// 필터 상태를 관리할 reactive 객체
const filters = reactive({
  dealTarget: "기타",
  dealType: "전체",
  structure: "전체",
  floor: "전체",
  area: "전체",
  jeonse: { min: "", max: "" },
  wolse: { min: "", max: "" },
  maemae: { min: "", max: "" },
});

const activeInput = ref("all");

// 드롭다운 상태를 관리할 reactive 객체
const dropdownState = reactive({
  isDealTargetDropdownOpen: false,
  isDealTypeDropdownOpen: false,
  isStructureDropdownOpen: false,
  isFloorDropdownOpen: false,
  isAreaDropdownOpen: false,
});

// 드롭다운 상태 토글 함수
const toggleDropdown = (type) => {
  const dropdownKey = `is${type.charAt(0).toUpperCase() + type.slice(1)}DropdownOpen`;
  dropdownState[dropdownKey] = !dropdownState[dropdownKey];
};

// 거래 대상 업데이트
const updateDealTarget = (target) => {
  filters.dealTarget = target;
  dropdownState.isDealTargetDropdownOpen = false;
};

// 거래 유형 업데이트
const updateDealType = (type) => {
  filters.dealType = type;
  dropdownState.isDealTypeDropdownOpen = false;
};

// 구조 업데이트
const updateStructure = (structure) => {
  filters.structure = structure;
  dropdownState.isStructureDropdownOpen = false;
};

// 층수 업데이트
const updateFloor = (floor) => {
  filters.floor = floor;
  dropdownState.isFloorDropdownOpen = false;
};

// 전용 면적 업데이트
const updateArea = (area) => {
  filters.area = area;
  dropdownState.isAreaDropdownOpen = false;
};

// 필터 제출
const submitFilters = () => {
  console.log("제출된 필터:", filters);
  // 필터 적용 로직 구현
};

// 사이드바 초기 열림 상태
const isOpen = ref(true);

const toggleSidebar = () => {
  isOpen.value = !isOpen.value;
};

// 선택된 매물 ID
const selectedSalesId = ref(null);

// 매물 클릭 시 상세 정보 토글
function toggleSalesDetail(salesId) {
  selectedSalesId.value = salesId;
  store.fetchSalesDetails(salesId);
}

// 모달 상태 관리
const isModalOpen = ref(false);

// 모달 열기
const openModal = () => {
  isModalOpen.value = true;
};

// 모달 닫기
const closeModal = () => {
  isModalOpen.value = false;
};

// AI GPT 제출
const submitAiGPT = () => {
  // 문의 로직 구현
  console.log("AI GPT 문의 제출");
};
</script>

<style scoped>
#mainPage {
  height: 100vh;
  overflow-y: auto;
}

.ai-chat {
  margin-right: 1rem;
  background: var(--color-bg-blue1);
}

.search-bar {
  position: absolute;
  display: flex;
  top: 20px;
  left: 10%;
  z-index: 9999999;
  flex-wrap: nowrap;
}

.header-featrues {
  position: relative;
}

.input-time-mobile {
  font-size: 1rem;
  width: 30px;
  height: 30px;
  padding: 0;
}


* {
  box-sizing: border-box;
}

.fab-wrapper {
  position: fixed;
  top: 6rem;
  left: 3rem;
  z-index: 999999;
}
.fab-checkbox {
  display: none;
}
.fab {
  position: absolute;
  bottom: -1rem;
  right: -1rem;
  width: 2.5rem;
  height: 2.5rem;
  background: var(--color-bg-blue1);
  border-radius: 50%;
  transition: all 0.3s ease;
  z-index: 1;
  border: 1px solid var(--color-bg-blue1);
}


.fab-checkbox:checked ~ .fab:before {
  width: 90%;
  height: 90%;
  left: 5%;
  top: 5%;
  background-color: rgba(255, 255, 255, 0.2);
}
.fab:hover {
  background: var(--color-bg-blue1);
}

.fab-dots {
  position: absolute;
  height: 3px;
  width: 3px;
  background-color: white;
  border-radius: 50%;
  top: 50%;
  transform: translateX(0%) translateY(-50%) rotate(0deg);
  opacity: 1;
  animation: blink 3s ease infinite;
  transition: all 0.3s ease;
}

.fab-dots-1 {
  left: 15px;
  animation-delay: 0s;
}
.fab-dots-2 {
  left: 50%;
  transform: translateX(-50%) translateY(-50%);
  animation-delay: 0.4s;
}
.fab-dots-3 {
  right: 15px;
  animation-delay: 0.8s;
}

.fab-checkbox:checked ~ .fab .fab-dots {
  height: 3px;
}

.fab .fab-dots-2 {
  transform: translateX(-50%) translateY(-50%) rotate(0deg);
}

.fab-checkbox:checked ~ .fab .fab-dots-1 {
  width: 16px;
  border-radius: 10px;
  left: 50%;
  transform: translateX(-50%) translateY(-50%) rotate(45deg);
}
.fab-checkbox:checked ~ .fab .fab-dots-3 {
  width: 16px;
  border-radius: 10px;
  right: 50%;
  transform: translateX(50%) translateY(-50%) rotate(-45deg);
}

@keyframes blink {
  50% {
    opacity: 0.25;
  }
}

.fab-checkbox:checked ~ .fab .fab-dots {
  animation: none;
}

.fab-wheel {
  position: absolute;
  top: 0;
  left: 0;
  width: 10rem;
  height: 10rem;
  transition: all 0.3s ease;
  transform-origin: top left;
  transform: scale(0);
}

.fab-checkbox:checked ~ .fab-wheel {
  transform: scale(1);
}
.fab-action {
  position: absolute;
  background: var(--color-bg-third);
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  transition: all 1s ease;
  opacity: 0;
}

.fab-action i {
  color: #fff;
}

.fab-checkbox:checked ~ .fab-wheel .fab-action {
  opacity: 1;
}

.fab-action:hover {
  background-color: var(--color-bg-blue1);
}

.fab-wheel .fab-action-1 {
  right: 0.5rem;
  top: -2rem;
}

.fab-wheel .fab-action-2 {
  right: 2.5rem;
  top: 1.5rem;
}
.fab-wheel .fab-action-3 {
  left: 1.5rem;
  bottom: 2.5rem;
}
.fab-wheel .fab-action-4 {
  left: -2rem;
  bottom: 0rem;
}

.ai-image {
  width: 20px;
}

</style>
