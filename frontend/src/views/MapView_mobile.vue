<template>
  <div id="mainPage">
    <div class="header-featrues p-5">
      <div class="search-bar">
        <div class="ai-chat">
          <div></div>
          <div>AI공인중개사</div>
        </div>
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

          <input
            class="text-center"
            type="number"
            v-model.number="hours"
            placeholder="0"
            min="0"
          />
          시간

          <input
            class="text-center"
            type="number"
            v-model.number="minutes"
            placeholder="0"
            min="0"
            max="59"
          />
          분

          <span>내에 있는 매물</span>
          <button @click="submit" type="button" class="btn btn-primary btn-lg">
            검색
          </button>
        </div>
      </div>
    </div>

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
  background: var(--color-bg-blue2);
}

.search-bar {
  position: absolute;
  display: flex;
  top: 20px;
  left: 10%;
  z-index: 9999999;
}

.header-featrues {
  position: relative;
}
</style>
