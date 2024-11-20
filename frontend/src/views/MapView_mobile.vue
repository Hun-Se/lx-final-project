<template>
    <MobileMapHeader></MobileMapHeader>
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
        <a class="fab-action fab-action-2" @click="toggleChatView">
          <img class="ai-image" src="/assets/img/ai_icon.svg" alt="" />
        </a>
        <a class="fab-action fab-action-3">
          <i class="bi bi-funnel"></i>
        </a>
        <a class="fab-action fab-action-4 fs-8"> 히트맵 </a>
      </div>
    </div>
    <section
      class="styled__ListContainer gwaQon"
      style="
        position: absolute;
        border-color: rgb(237, 237, 237);
        transform: translate3d(0px, 566px, 0px);
        height: 574px;
        z-index: 8888;
      "
    >
      <div
        class="styled__Handle lcTxFA"
        @dragstart="handleDragStart"
        @drop="handleDrop"
        @dragover.prevent
        @dragenter.prevent
      >
        <div
          class="kKaHnd"
          draggable="true"
          style="width: 30px; background-color: rgb(223, 223, 223)"
        ></div>

        <div class="jtFEOu">
          <button class="styled__Tab-sc-1bidayn-1 cjOCPb" @click="toggleChatView">
            {{ buttonText }}
          </button>
        </div>

        <div
          class="kKaHnd"
          draggable="true"
          style="width: 30px; background-color: rgb(223, 223, 223)"
        ></div>
      </div>

      <!-- 매물 -->
      <div class="styled_Container hjmZNO" style="overflow-y: auto">
        <ul class="styled_RoomList jbrqZi">
          <!-- AI 채팅 -->
          <div v-if="isChatViewActive">
            <!-- 매물 조건 박스들 (상단 문단) -->
            <div>
              <div style="display: flex; padding-left: 1ex; margin-top: 1ex; width: 55ex">
                <div
                  class="info-box"
                  style="flex: 1; text-align: center; margin-right: 2ex"
                >
                  <i class="bi bi-backpack3-fill" style="margin-left: 0.5ex"></i>
                  주변 학교있는 매물
                </div>
                <div class="info-box" style="flex: 1; text-align: center">
                  <i class="bi bi-tree-fill" style="margin-left: 1.5ex"></i>
                  500m 내 공원
                </div>
              </div>

              <div style="display: flex; padding-left: 1ex; width: 55ex">
                <div
                  class="info-box"
                  style="flex: 1; text-align: center; margin-right: 2ex"
                >
                  <i
                    class="bi bi-train-lightrail-front-fill"
                    style="margin-left: 1ex"
                  ></i>
                  15분 거리 지하철
                </div>
                <div class="info-box" style="flex: 1; text-align: center">
                  <i class="bi bi-shop"></i>
                  1Km 내에 대형 마켓
                </div>
              </div>
            </div>

            <!-- 입력창 및 버튼 (하단 문단) -->
            <div
              style="display: flex; align-items: center; width: 53ex; margin-left: 2ex"
            >
              <input
                type="text"
                name="AiGPT"
                placeholder="원하는 매물 조건을 입력해주세요."
                class="form-control"
                style="flex-grow: 1; margin-right: 0.5ex; width: 15ex"
              />
              <button
                @click="submitAiGPT"
                class="btn btn custom-btn"
                style="width: 10ex; margin-left: 1ex; color: white"
              >
                문의
              </button>
            </div>
          </div>

          <!-- 매물 리스트 -->
          <ul v-if="!isChatViewActive">
            <li
              v-for="(item, index) in sales"
              :key="item.prpPk"
              class="property-item-mobile"
              @click="toggleSalesDetail(item.prpPk)"
            >
              <a href="#" class="styled-roomLink">
                <img
                  :src="'/assets/img/' + item.prpImg"
                  alt="매물 이미지"
                  class="property-image-mobile"
                />
                <div class="styled-RoomDetail">
                  <h1 class="styled-price">
                    <!--                  가격 <span>{{ item.prpPrice }}</span>-->
                    <span>월 70만원</span>
                  </h1>
                  <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">원룸</p>
                  <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">보증금:500만원</p>
                  <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">
                    {{ item.prpAddrDetail }}
                  </p>
                  <p class="styled__RoomInfo-sc-1b8f2kq-6 iDAcJ">
                    {{ item.prpStrucType }}, {{ item.prpExclArea }}, 관리비 10만
                  </p>
                  <p class="styled__RoomDesc-sc-1b8f2kq-7 hVDije">
                    {{ item.prpDesc }}
                  </p>

                  <div class="styled__TagContainer-sc-1b8f2kq-8 jZjxOL">
                    <div class="animated_badge animated_badge_5 hide">
                      <div class="animated_stars">
                        <div class="animated_star plus"></div>
                        <div class="animated_star donut yellow"></div>
                        <div class="animated_star plus"></div>
                        <div class="animated_star donut"></div>
                        <div class="animated_star plus yellow"></div>
                      </div>
                      <svg
                        class="animated_badge_svg"
                        width="30"
                        height="30"
                        viewBox="0 0 84 99"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                      >
                        <path
                          class="badge_ribbon"
                          d="M0.761166 82.9447L17.3041 50.8886C17.5574 50.3979 18.1606 50.2053 18.6513 50.4586L43.875 63.4756C44.3658 63.7289 44.5583 64.332 44.305 64.8228L27.7788 96.8466C27.4294 97.5236 26.4817 97.5769 26.0586 96.9435L17.8756 84.694C17.6881 84.4133 17.3716 84.2461 17.0341 84.2495L1.65981 84.4033C0.906487 84.4108 0.415676 83.6142 0.761166 82.9447Z"
                          fill="#F04152"
                        />
                        <path
                          class="badge_ribbon"
                          d="M0.761166 82.9447L17.3041 50.8886C17.5574 50.3979 18.1606 50.2053 18.6513 50.4586L43.875 63.4756C44.3658 63.7289 44.5583 64.332 44.305 64.8228L27.7788 96.8466C27.4294 97.5236 26.4817 97.5769 26.0586 96.9435L17.8756 84.694C17.6881 84.4133 17.3716 84.2461 17.0341 84.2495L1.65981 84.4033C0.906487 84.4108 0.415676 83.6142 0.761166 82.9447Z"
                          fill="url(#paint0_linear)"
                        />
                        <mask
                          id="mask0"
                          mask-type="alpha"
                          maskUnits="userSpaceOnUse"
                          x="1"
                          y="50"
                          width="44"
                          height="46"
                        >
                          <path
                            d="M3.80623 77.0441L15.4697 54.4432C16.7361 51.9893 19.752 51.0266 22.2059 52.293L40.3204 61.6412C42.7743 62.9076 43.737 65.9235 42.4707 68.3774L30.8904 90.817C29.1437 94.2016 24.4052 94.4685 22.2895 91.3014L19.0777 86.4935C18.1402 85.09 16.5578 84.2543 14.8701 84.2712L8.29946 84.3369C4.53283 84.3745 2.07879 80.3915 3.80623 77.0441Z"
                            fill="#71A1F4"
                          />
                          <path
                            d="M3.80623 77.0441L15.4697 54.4432C16.7361 51.9893 19.752 51.0266 22.2059 52.293L40.3204 61.6412C42.7743 62.9076 43.737 65.9235 42.4707 68.3774L30.8904 90.817C29.1437 94.2016 24.4052 94.4685 22.2895 91.3014L19.0777 86.4935C18.1402 85.09 16.5578 84.2543 14.8701 84.2712L8.29946 84.3369C4.53283 84.3745 2.07879 80.3915 3.80623 77.0441Z"
                            fill="url(#paint1_linear)"
                          />
                        </mask>
                        <g class="badge_ribbon" mask="url(#mask0)">
                          <rect
                            width="9.73445"
                            height="46.9502"
                            transform="matrix(0.894023 0.448022 -0.445194 0.895434 24.272 60.4499)"
                            fill="#FCD977"
                          />
                        </g>
                        <path
                          class="badge_ribbon right"
                          d="M56.5365 97.1074L38.5 65.8673C38.2239 65.389 38.3877 64.7774 38.866 64.5013L63.4476 50.3091C63.9259 50.0329 64.5375 50.1968 64.8137 50.6751L82.832 81.8838C83.2129 82.5435 82.7458 83.3698 81.9841 83.3836L67.2552 83.6511C66.9177 83.6572 66.6061 83.8332 66.4266 84.1191L58.2494 97.1392C57.8487 97.7772 56.9132 97.7598 56.5365 97.1074Z"
                          fill="#F04152"
                        />
                        <path
                          class="badge_ribbon right"
                          d="M56.5365 97.1074L38.5 65.8673C38.2239 65.389 38.3877 64.7774 38.866 64.5013L63.4476 50.3091C63.9259 50.0329 64.5375 50.1968 64.8137 50.6751L82.832 81.8838C83.2129 82.5435 82.7458 83.3698 81.9841 83.3836L67.2552 83.6511C66.9177 83.6572 66.6061 83.8332 66.4266 84.1191L58.2494 97.1392C57.8487 97.7772 56.9132 97.7598 56.5365 97.1074Z"
                          fill="url(#paint2_linear)"
                        />
                        <mask
                          id="mask1"
                          mask-type="alpha"
                          maskUnits="userSpaceOnUse"
                          x="38"
                          y="49"
                          width="45"
                          height="47"
                        >
                          <path
                            d="M53.2165 91.357L40.5 69.3314C39.1193 66.9399 39.9387 63.882 42.3301 62.5013L59.9835 52.3091C62.375 50.9283 65.4329 51.7477 66.8137 54.1392L79.4394 76.0076C81.3438 79.3061 79.0082 83.4376 75.2001 83.5068L69.419 83.6118C67.7314 83.6424 66.1732 84.5224 65.2756 85.9517L61.7808 91.5162C59.7774 94.7061 55.0999 94.6192 53.2165 91.357Z"
                            fill="#71A1F4"
                          />
                          <path
                            d="M53.2165 91.357L40.5 69.3314C39.1193 66.9399 39.9387 63.882 42.3301 62.5013L59.9835 52.3091C62.375 50.9283 65.4329 51.7477 66.8137 54.1392L79.4394 76.0076C81.3438 79.3061 79.0082 83.4376 75.2001 83.5068L69.419 83.6118C67.7314 83.6424 66.1732 84.5224 65.2756 85.9517L61.7808 91.5162C59.7774 94.7061 55.0999 94.6192 53.2165 91.357Z"
                            fill="url(#paint3_linear)"
                          />
                        </mask>
                        <g class="badge_ribbon right" mask="url(#mask1)">
                          <rect
                            width="9.73445"
                            height="46.9502"
                            transform="matrix(0.860033 -0.510239 0.512954 0.858416 50.3103 65.1699)"
                            fill="#FCD977"
                          />
                        </g>
                        <circle
                          cx="40.5"
                          cy="37.5"
                          r="33.5"
                          fill="#DBDFE7"
                          stroke="#FCD977"
                          stroke-width="8"
                        />
                        <circle
                          class="badge_circle"
                          cx="40.5"
                          cy="37.5"
                          r="29.5"
                          fill="#FCD977"
                          stroke="#FFA826"
                          stroke-width="4"
                        />
                        <path class="badge_number" fill="#FFA826" />
                        <defs>
                          <linearGradient
                            id="paint0_linear"
                            x1="31.2632"
                            y1="56.9671"
                            x2="13.7695"
                            y2="90.8654"
                            gradientUnits="userSpaceOnUse"
                          >
                            <stop stop-color="#A31523" />
                            <stop offset="1" stop-color="#F04152" stop-opacity="0" />
                          </linearGradient>
                          <linearGradient
                            id="paint1_linear"
                            x1="31.2632"
                            y1="56.9671"
                            x2="13.7695"
                            y2="90.8654"
                            gradientUnits="userSpaceOnUse"
                          >
                            <stop stop-color="#27539F" />
                            <stop offset="1" stop-color="#71A1F4" stop-opacity="0" />
                          </linearGradient>
                          <linearGradient
                            id="paint2_linear"
                            x1="51.1568"
                            y1="57.4052"
                            x2="70.2299"
                            y2="90.4407"
                            gradientUnits="userSpaceOnUse"
                          >
                            <stop stop-color="#A31523" />
                            <stop offset="1" stop-color="#F04152" stop-opacity="0" />
                          </linearGradient>
                          <linearGradient
                            id="paint3_linear"
                            x1="51.1568"
                            y1="57.4052"
                            x2="70.2299"
                            y2="90.4407"
                            gradientUnits="userSpaceOnUse"
                          >
                            <stop stop-color="#27539F" />
                            <stop offset="1" stop-color="#71A1F4" stop-opacity="0" />
                          </linearGradient>
                        </defs>
                      </svg>
                    </div>
                    <span class="badge badge-light-warning">우수중개사</span>
                    <div class="btn btn-light-danger btn-sm" @click="onClickMoveReport">신고</div>
                  </div>
                </div>
              </a>
            </li>
          </ul>
        </ul>
      </div>
    </section>
    <NaverMap></NaverMap>
  </div>

  <MobileBottomTapBar></MobileBottomTapBar>
</template>

<script setup>
import { ref, computed, reactive, onMounted, onUnmounted } from "vue";
import { useRouter } from "vue-router";
import { useSaleStore } from "@/stores/property.js";
import NaverMap from "@/components/NaverMap.vue";
import { storeToRefs } from "pinia";
import Header2 from "@/components/Header2.vue";
import MobileMapHeader from "@/components/MobileMapHeader.vue";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";
import { useChatStore } from "@/stores/chat";


//********채팅**********
const chatStore = useChatStore();
const isChatViewActive = ref(false); // 상태 관리

// AI GPT 제출
const submitAiGPT = () => {
  // 문의 로직 구현
  console.log("AI GPT 문의 제출");
};

// 버튼 텍스트에 대한 반응형 값 계산
const buttonText = computed(() => (isChatViewActive.value ? "AI Chat" : "매물"));

// 채팅 보기 토글
const toggleChatView = () => {
  isChatViewActive.value = !isChatViewActive.value;
};

// **********매물**********
function goToAuction() {
  router.push({ path: "/user_auction" });
}

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

// 매물 하단바 드래그앤드롭 및 선택
// 상태 관리
const draggedElement = ref(null); // 드래그된 요소 저장
const originalHeight = ref(574); // 초기 height
const originalTransformY = ref(573); // 초기 transform Y값

// 드래그 시작
const handleDragStart = (event) => {
  draggedElement.value = event.target;
};

// 드롭
const handleDrop = (event) => {
  if (draggedElement.value) {
    // 드래그된 위치의 Y값 계산
    const deltaY = event.clientY - event.target.getBoundingClientRect().top;

    // 높이 및 transform 값 계산
    const newHeight = Math.max(originalHeight.value - deltaY, 100);
    const newTransformY = originalTransformY.value + deltaY;

    // 스타일 업데이트
    event.target.style.height = `${newHeight}px`;
    event.target.style.transform = `translate3d(0px, ${newTransformY}px, 0px)`;

    // 상태 저장
    originalHeight.value = newHeight;
    originalTransformY.value = newTransformY;

    // 초기화
    draggedElement.value = null;
  }
};

// 신고이동
function onClickMoveReport() {
  router.push({path:"/mobile_prp_report"});
}
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
  top: 7rem;
  left: 2.2rem;
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
  border: 5px solid var(--color-bg-blue1);
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
  height: 4px;
  width: 4px;
  background-color: white;
  border-radius: 50%;
  top: 50%;
  transform: translateX(0%) translateY(-50%) rotate(0deg);
  opacity: 1;
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

.map-container a {
  color: red;
}

/* 모바일 하단탭 */
.gwaQon {
  display: flex;
  flex-direction: column;
  width: 100%;
  border-top: 1px solid;
  background-color: rgb(255, 255, 255);
  position: absolute;
  left: 0px;
  right: 0px;
  bottom: 90px;
  z-index: 110;
}

.lcTxFA {
  flex: 0 0 auto;
  padding-top: 16px;
  position: relative;
}

.kKaHnd {
  height: 4px;
  border-radius: 4px;
  position: absolute;
  top: 8px;
  left: 50%;
  transform: translateX(-50%);
  transition: 250ms;
}

.jtFEOu {
  flex: 0 0 auto;
  display: grid;
  grid-template-columns: repeat(1, minmax(0px, 1fr));
  height: 33px;
  padding: 0px 20px;
  border-bottom: 1px solid rgb(237, 237, 237);
}

.cjOCPb {
  height: 100%;
  padding: 0px 0px 7px;
  color: rgb(101, 101, 101);
  font-size: 14px;
  line-height: 24px;
  font-weight: 400;
  border-width: 0px 0px 2px;
  border-top-style: initial;
  border-right-style: initial;
  border-left-style: initial;
  border-top-color: initial;
  border-right-color: initial;
  border-left-color: initial;
  border-image: initial;
  border-bottom-style: solid;
  border-bottom-color: transparent;
  background-color: transparent;
}

.hjmZNO {
  -webkit-box-flex: 1;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  width: 100%;
  position: relative;
  overflow: hidden;
}

.jbrqZi {
  padding-top: 4px;
}

/* 모바일 매물 리스트 */
.property-item-mobile {
  display: flex;
}

.styled-RoomDetail {
  display: flex;
  flex-direction: column;
}

.styled-price {
  flex: 0 0 auto;
  color: rgb(34, 34, 34);
  font-size: 16px;
  line-height: 26px;
}

.XdHPA {
  flex: 0 0 auto;
  color: rgb(101, 101, 101);
  font-size: 12px;
  line-height: 20px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.iDAcJ {
  flex: 0 0 auto;
  color: rgb(101, 101, 101);
  font-size: 12px;
  line-height: 20px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.hVDije {
  flex: 0 0 auto;
  color: rgb(151, 151, 151);
  font-size: 12px;
  line-height: 20px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.jZjxOL {
  flex: 0 0 auto;
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
  margin-top: auto;
  padding-top: 8px;
  align-items: center;
  color: gold;
}

.property-image-mobile {
  width: 100%;
  height: 100%;
}

.styled-roomLink {
  display: grid;
  grid-template-columns: 153px minmax(0px, 1fr);
  column-gap: 14px;
  padding: 16px 20px;
}

.custom-btn {
  background-color: #373773;
  border-color: #373773;
}

.custom-btn:hover {
  background-color: #fa7000 !important;
  border-color: #fa7000 !important;
}

.info-box {
  background-color: var(--color-bg-third);
  border-radius: 20px;
  padding: 10px 15px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  gap: 10px;
}

.info-box i {
  font-size: 1.5rem;
  color: var(--color-bg-blue1);
}

.mobile-search-input {
  display: inline-block;
  width: 50px;
  padding: 0;
  text-align: center;
}

.button-time-search {
  color: #fff;
  background: var(--color-bg-blue1);
}

.mobile-time-input {
  width: 30px;
  height: 20px;
}
</style>
