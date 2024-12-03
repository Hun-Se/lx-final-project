<template>
  <MobileMapHeader></MobileMapHeader>
  <div v-if="isSpinner" style="transform: translateX(-35px) translateY(600px); z-index: 99999">
    <Spiner></Spiner>
  </div>
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
        <a class="fab-action fab-action-4 fs-8" @click="toggleHeatMap"> 히트맵 </a>
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
          <button class="styled__Tab-sc-1bidayn-1 cjOCPb">
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
            <div v-if="chatList.length === 0">
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
            <div v-else-if="chatList.length > 0" class="p-5 overflow-auto">
              <div v-for="(sendChat, index) in chatList" :key="index">
                <!-- 사용자 입력 -->
                <div class="d-flex flex-column align-items-end">
                  <div class="d-flex align-items-center mb-2">
                  </div>
                  <div class="p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end" data-kt-element="message-text">
                    {{ sendChat }}
                  </div>
                </div>

                <!-- AI 응답 -->
                <div v-if="chatBotData.length > index && chatBotData[index]?.result?.friendly_response" class="d-flex justify-content-start mt-5 mb-10">
                  <!--begin::Wrapper-->
                  <div class="d-flex flex-column align-items-start">
                    <!--begin::User-->
                    <div class="d-flex align-items-center mb-2">
                      <!--begin::Avatar-->
                      <div class="symbol symbol-35px symbol-circle">
                        <img alt="AiChat" src="/assets/img/chatbot_profile.jpg">
                      </div>
                      <div class="ms-3">
                        <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary me-1">챗봇</a>
                      </div>
                    </div>
                    <div class="p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start" data-kt-element="message-text">
                      <div v-html="formattedResponse(index)"></div>
                    </div>
                  </div>
                </div>


              </div>
            </div>
            <!-- 입력창 및 버튼 (하단 문단) -->
            <div
              style="display: flex; align-items: center; width: 53ex; margin-left: 2ex; padding-bottom: 8rem"
            >
              <input
                type="text"
                name="AiGPT"
                placeholder="원하는 매물 조건을 입력해주세요."
                class="form-control"
                v-model="chatQuestion"
                style="flex-grow: 1; margin-right: 0.5ex; width: 15ex"
              />
              <button
                @click="submitAiGPT"
                class="btn btn custom-btn"
                style="width: 10ex; margin-left: 1ex; color: white"
              >
                검색
              </button>
            </div>
          </div>

          <!-- 매물 리스트 -->
          <ul v-if="!isChatViewActive && chatBotData.length === 0">
            <li
              v-for="(item, index) in sales"
              :key="item.prpPk"
              class="property-item-mobile"
            >
              <a href="#" class="styled-roomLink">
                <img
                  :src="'/assets/img/' + item.prpImg"
                  alt="매물 이미지"
                  class="property-image-mobile"
                  @click="toggleSalesDetail(item.prpPk)"
                />
                <div class="styled-RoomDetail">
                  <h1 class="styled-price">
                    <!--                  가격 <span>{{ item.prpPrice }}</span>-->
                    <span>{{ item.prpPrice }} 만원</span>
                    <button
                      class="btn btn custom-btn"
                      style="
                        width: 10ex;
                        height: 3ex;
                        color: white;
                        background-color: var(--color-bg-blue1);
                        display: flex;
                        justify-content: center;
                        align-items: center;
                      "
                      @click="goToCesium"
                    >
                      3D 뷰
                    </button>
                  </h1>
                  <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">{{ item.prpName }}</p>
                  <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">
                    지역(코드) : {{ item.regionPk }}
                  </p>
                  <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">
                    전용면적 : {{ item.prpExclArea }}m^2
                  </p>
                  <p class="styled__RoomInfo-sc-1b8f2kq-6 iDAcJ">
                    {{ item.prpBlock }}동, {{ item.prpFloor }}층, {{ item.prpUnit }}호
                  </p>
                  <p class="styled__RoomDesc-sc-1b8f2kq-7 hVDije">
                    주소 : {{ item.regionSiDo + " " + item.regionSiGunGu + " " + item.regionEupMyeonDong}}
                  </p>
                </div>
              </a>
            </li>
          </ul>
          <ul v-else-if="!isChatViewActive && chatBotData.length > 0">
            <li
                v-for="(item, index) in chatBotData[chatBotData.length - 1].result.result_data"
                :key="item.prp_pk"
                class="property-item-mobile"
            >
              <a href="#" class="styled-roomLink">
                <img
                    :src="'/assets/img/' + item.prp_img"
                    alt="매물 이미지"
                    class="property-image-mobile"
                    @click="toggleSalesDetail(item.prp_pk)"
                />
                <div class="styled-RoomDetail">
                  <h1 class="styled-price">
                    <!--                  가격 <span>{{ item.prpPrice }}</span>-->
                    <span>{{item.prp_price/10000}}억 원</span>
                    <button
                        class="btn btn custom-btn"
                        style="
                        width: 10ex;
                        height: 3ex;
                        color: white;
                        background-color: var(--color-bg-blue1);
                        display: flex;
                        justify-content: center;
                        align-items: center;
                      "
                        @click="goToCesium"
                    >
                      3D 뷰
                    </button>
                  </h1>
                  <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">{{ item.prp_name }}</p>
                  <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">지역(코드) : {{item.region_pk}}</p>
                  <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">
                    전용면적 : {{ item.prp_excl_area }}m^2
                  </p>
                  <p class="styled__RoomInfo-sc-1b8f2kq-6 iDAcJ">
                    {{ item.prp_block }}동, {{ item.prp_floor }}층, {{ item.prp_unit }}호
                  </p>

                </div>
              </a>
            </li>
          </ul>
        </ul>
      </div>
    </section>
    <!-- 지도 렌더링 -->
    <div id="modalBackground" v-if="mapType === '네이버'">
      <!-- 네이버 지도를 렌더링 -->
      <NaverMap />
    </div>

    <div id="modalBackground" v-else-if="mapType === 'Cesium'">
      <!-- Cesium 지도를 렌더링 -->
      <CesiumMap2 />
    </div>

    <div id="modalBackground" v-if="isHeatMap">
      <!-- 히트맵을 렌더링 -->
      <CesiumHeatmap />
    </div>
  </div>
  <MobileBottomTapBar></MobileBottomTapBar>
</template>

<script setup>
import { ref, computed, reactive, onMounted, onUnmounted } from "vue";
import { useRouter } from "vue-router";
import { useSaleStore } from "@/stores/property.js";
import { useAiChatbotStore } from "@/stores/aiChatbot.js";
import NaverMap from "@/components/NaverMap.vue";
import { storeToRefs } from "pinia";
import Header2 from "@/components/Header2.vue";
import Spiner from "@/components/Spiner.vue";
import MobileMapHeader from "@/components/MobileMapHeader.vue";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";
import CesiumHeatmap from "@/components/CesiumHeatmap.vue";
import CesiumMap2 from "@/components/CesiumMap2.vue";

const mapType = ref("네이버");
const isHeatMap = ref(false);

function toggleHeatMap() {
  isHeatMap.value = !isHeatMap.value
}


// 3D 뷰 버튼 클릭 시 지도 타입을 순환
function goToCesium() {
  // 네이버와 Cesium을 번갈아 전환
  if (mapType.value === "네이버") {
    mapType.value = "Cesium";
  } else if (mapType.value === "Cesium") {
    mapType.value = "네이버";
  }
}

//********채팅**********
const aiChatbotStore = useAiChatbotStore();
const chatQuestion = ref("");
const { chatBotData, chatBotBody } = storeToRefs(aiChatbotStore);
const chatList = ref([]);
const isSpinner = ref(false);

const isChatViewActive = ref(false); // 상태 관리
// AI GPT 제출
const submitAiGPT = async () => {
  chatBotBody.value = {
      "question": chatQuestion.value
  }
  chatList.value.push(chatQuestion.value);
  chatQuestion.value = "";
  try {
    isSpinner.value = true;
  await aiChatbotStore.fetchAiChatbot();
  } catch(err) {
    console.log(err);
  } finally {
    isSpinner.value= false;
  }
};

// Format Friendly Response
const formatFriendlyResponse = (response) => {
  if (!response) return "";

  const items = response.split("\n").filter((line) => line.trim() !== "");
  let formattedResponse = "<ul>";

  items.forEach((line, index) => {
    if (line.startsWith(index + 1 + ".")) {
      // 매물 번호 처리
      formattedResponse += `<li><strong>${line}</strong><ul>`;
    } else if (line.startsWith("- ")) {
      // 항목 처리
      formattedResponse += `<li>${line.substring(2)}</li>`;
    } else {
      // 닫는 태그 처리
      formattedResponse += `</ul></li>`;
    }
  });

  formattedResponse += "</ul>";
  return formattedResponse;
};

// Friendly Response를 계산
const formattedResponse = (index) => {
  const response = chatBotData.value[index]?.result?.friendly_response || "";
  // 텍스트를 HTML 형식으로 변환 (예: 줄바꿈 처리)
  return response.replace(/\n/g, "<br>");
};

// 버튼 텍스트에 대한 반응형 값 계산
const buttonText = computed(() => (isChatViewActive.value ? "AI 챗봇" : "매물"));

// 채팅 보기 토글
const toggleChatView = () => {
  isChatViewActive.value = !isChatViewActive.value;
};


// isMobile 변수 정의 (화면 크기를 기준으로)
const isMobile = ref(window.innerWidth <= 768);

// 화면 크기가 변경될 때 모바일 여부를 업데이트
window.addEventListener("resize", () => {
  isMobile.value = window.innerWidth <= 768;
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

// 매물 클릭 시 상세 정보 요청 및 라우터 이동
async function toggleSalesDetail(salesId) {
  event.preventDefault(); // 페이지 이동 방지

  try {
    // Pinia의 fetchSalesDetails를 통해 API 요청
    await store.fetchSalesDetails(salesId);

    // 상세 페이지로 이동
    router.push({
      path: "/mobile_prp_detail",
      query: { id: salesId }, // 매물 ID를 쿼리로 전달
    });
  } catch (error) {
    console.error("매물 상세 정보를 가져오는 중 오류 발생:", error);
  }
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
  router.push({ path: "/mobile_prp_report" });
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
