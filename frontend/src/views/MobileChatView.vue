<template>
  <MobileHeader title="채팅페이지"></MobileHeader>
  <div class="mt-15 p-5">
  <!-- 중개사 정보 -->
  <div class="card mb-5 mb-xl-10">
      <div class="card-body">
        <div class="d-flex">
          <div class="d-flex flex-column align-items-center justify-content-center" style="width:100px;">
            <img src="assets/img/agent_example.png" alt="image" style="width: 50px; height: 50px;" />
          </div>
          <div class="flex-grow-1">
            <div class="d-flex justify-content-between align-items-start mb-2">
              <div class="d-flex flex-column">
                <div class="d-flex align-items-center mt-2 mb-2">
                  <a href="#" class="text-gray-900 text-hover-primary fs-2 fw-bold me-1">하정우</a>
                  <span class="badge badge-light-primary">신고 26회</span>
                </div>
                <div class="d-flex flex-wrap fw-semibold fs-6 pe-2">
                  <a href="#" class="text-warning d-flex align-items-center mb-1">
                    <i class="ki-duotone ki-verify fs-1 text-danger"></i>
                    <span class="text-danger">신뢰지수: 38%</span>
                  </a>
                  <a href="#" class="d-flex align-items-center text-gray-500 text-hover-primary me-5 mb-2 fs-7">
                    <i class="ki-duotone ki-profile-circle fs-4 me-1"></i> 한마음 공인중개사무소
                  </a>
                </div>
              </div>
              <div class="me-0">
                <button class="btn btn-sm btn-light-danger" @click="clickReport" style="width: 50px">
                  <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M12.0016 4V5.5M5.60156 6.6L6.75156 7.74999M18.4016 6.6L17.2516 7.75" stroke="#FF0000" stroke-linecap="square" stroke-linejoin="round"></path>
                    <rect x="6.5" y="15.5" width="11" height="3" rx="0.5" stroke="#FF0000"></rect>
                    <path d="M8.5 12C8.5 10.067 10.067 8.5 12 8.5C13.933 8.5 15.5 10.067 15.5 12V15.5H8.5V12Z" stroke="#FF0000"></path>
                  </svg>
                  신고
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 매물정보 카드 -->
    <div class="card mt-5 mb-5 mb-xl-10">
      <div class="container-prp-info">
        <li class="property-item-mobile">
          <a href="#" class="styled-roomLink">
            <img :src="'/assets/img/' + prpData.prpImg" alt="매물 이미지" class="property-image-mobile">
            <div class="styled-RoomDetail">
              <h1 class="styled-price">
                <span>
                  <span class="badge badge-light-primary">{{ prpTransType }}</span> {{ prpPrice}}
<!--                  <span v-if="prpData.prpDeposit" class="badge badge-light-info">/ 보증금</span>500만원-->
                </span>
              </h1>
              <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">{{ prpType }}</p>
              <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">{{ prpData.regionSiDo }} {{ prpData.regionSiGunGu }} {{ prpData.regionEupMyeonDong }}</p>
              <div class="styled__TagContainer-sc-1b8f2kq-8 jZjxOL"></div>
            </div>
          </a>
        </li>
      </div>
    </div>
    <!-- 채팅 -->
    <div class="flex-lg-row-fluid ms-lg-7 ms-xl-10">
      <div class="card" id="kt_chat_messenger">
        <div class="card-header" id="kt_chat_messenger_header">
          <div class="card-title">
            <div class="d-flex justify-content-center flex-column me-3">
              <a href="#" class="fs-4 fw-bold text-gray-900 text-hover-primary me-1 mb-2 lh-1">대화내용</a>
              <div class="mb-0 lh-1">
                <span class="badge badge-success badge-circle w-10px h-10px me-1"></span>
                <span class="fs-7 fw-semibold text-muted">활동중</span>
              </div>
            </div>
          </div>
        </div>

        <div class="card-body" id="kt_chat_messenger_body">
          <div id="response" class="scroll-y me-n5 pe-5 h-300px h-lg-auto">
            <!-- 채팅 메시지 -->
            <div
              v-for="(msg, index) in chatStore.messages"
              :key="index"
              class="chat-message mb-2"
            >
              <div class="d-flex" :class="msg.userPkSender === userStore.user?.userPk ? 'justify-content-end' : 'justify-content-start'">
                <strong class="">{{ msg.userPkSender === userStore.user?.userPk ? userStore.user?.userName : msg.userName || '시스템' }}</strong>
              </div>
              <div
                class="d-flex mt-3"
                :class="msg.userPkSender === userStore.user?.userPk ? 'justify-content-end' : 'justify-content-start'"
              >
                <div
                  class="p-5 rounded"
                  :class="msg.userPkSender === userStore.user?.userPk ? 'bg-light-primary text-gray-900' : 'bg-light-info text-gray-900'"
                >
                  <!-- 사용자 이름 안전하게 표시 -->
                  {{ msg.chatmesContent }}
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="card-footer pt-4" id="kt_chat_messenger_footer">
          <textarea
            class="form-control form-control-flush mb-3"
            rows="1"
            v-model="newMessage"
            placeholder="채팅내용 입력"
            @keyup.enter="submitMessage"
          ></textarea>
<!--          <button class="btn btn-primary btn-message" @click="submitMessage">전송</button>-->
          <div class="d-flex flex-stack">
            <!--begin::Actions-->
            <div class="d-flex align-items-center me-2">
              <button
                  class="btn btn-sm btn-icon btn-light-primary me-1"
                  type="button"
                  data-bs-toggle="tooltip"
                  aria-label="Coming soon"
                  data-bs-original-title="Coming soon"
                  data-kt-initialized="1"
              >
                <i class="ki-duotone ki-paper-clip fs-3"></i>
              </button>
              <button
                  class="btn btn-sm btn-icon btn-light-primary me-1"
                  type="button"
                  data-bs-toggle="tooltip"
                  aria-label="Coming soon"
                  data-bs-original-title="Coming soon"
                  data-kt-initialized="1"
              >
                <i class="ki-duotone ki-exit-up fs-3">
                  <span class="path1"></span>
                  <span class="path2"></span>
                </i>
              </button>
            </div>
            <!--end::Actions-->
            <!--begin::Send-->
            <button
                class="btn btn-primary btn-message"
                @click="submitMessage"
                type="button"
                data-kt-element="send"
            >
              <i class="bi bi-send"></i>
            </button>
            <!--end::Send-->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useChatStore } from "@/stores/chat";
import { useUserStore } from "@/stores/user";
import { initializeWebSocket, sendMessage } from "@/api/chat";
import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import { useMobileStore } from "@/stores/mobile";
import { useSaleStore } from "@/stores/property.js";
import {storeToRefs} from "pinia";
import { useRouter, useRoute } from "vue-router";

// 모바일 환경 설정
const store = useMobileStore();
const { isMobile } = store;

// Pinia 스토어에서 사용자 정보 가져오기
const userStore = useUserStore();
const user = userStore.user; // storeToRefs 대신 직접 참조

// WebSocket 관련 설정
const chatStore = useChatStore();
const newMessage = ref("");

// 선택했던 매물
const saleStore = useSaleStore();
const prpData = ref({});
const prpType = ref("");
const prpPrice = ref("");
const prpTransType = ref("");

const route = useRoute();
const router = useRouter();

onMounted(async () => {
  userStore.initializeUser?.(); // Pinia 상태 초기화

  console.log("Debug: userStore.user:", userStore.user);

  if (!userStore.user || !userStore.user.userPk) {
    console.error("User information is missing. Redirecting to login page.");
    router.push("/login");
    return;
  }

  console.log("User is valid. Initializing WebSocket.");
  initializeWebSocket(userStore.user.userName, chatStore);
  const  route = useRoute();
  const saleId = route.query.saleId; // 현재 saleId 가져오기
  const response = await saleStore.fetchSalesDetails(saleId);
  prpData.value = response;

  if (response.prpTransType === 1) {
    prpTransType.value = "매매";
  } else if (response.prpTransType === 2) {
    prpTransType.value = "전세";
  } else {
    prpTransType.value = "월세";
  }

  if (response.prpType === 1) {
    prpType.value = "아파트";
  } else if (response.prpType === 2) {
    prpType.value = "주택/빌라";
  } else if ((response.prpType === 3)){
    prpType.value = "오피스텔";
  } else if ((response.prpType === 4)){
    prpType.value = "분양";
  }

   prpPrice.value = formatCurrency(response.prpPrice);
});

function formatCurrency(value) {
  if (value >= 10000) {
    const billions = Math.floor(value / 10000); // 억 단위
    const millions = value % 10000; // 나머지 만원 단위
    return `${billions}억 ${millions > 0 ? `${millions}만` : ""}원`;
  }
  return `${value}만 원`; // 만원 이하인 경우
}

const submitMessage = () => {
  if (!newMessage.value.trim()) return;

  const messageData = {
    chatmesContent: newMessage.value.trim(),
    userPkSender: userStore.user.userPk,
    userName: userStore.user.userName,
    agentPkSender: null,
    chatPk: 1,
  };

  console.log("Sending message:", messageData);

  sendMessage(messageData);
  chatStore.messages.push(messageData);
  newMessage.value = "";
};

const clickReport = () => {
  const saleId = route.query.saleId;
  const chatPk = 1;
router.push({ path: "/mobile_prp_report", query: { saleId, chatPk } });
}
</script>

<style scoped>
/* 기존 스타일 유지 */
#conversation {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  width: 180ex;
  margin: 0 auto;
  max-width: 100%;
}
#response {
  max-height: 300px;
  overflow-y: auto;
  margin-bottom: 10px;
}
.chat-message {
  margin-bottom: 5px;
  padding: 8px;
  border-radius: 5px;
}
.my-message {
  background-color: #e7f4fe;
  align-self: flex-end;
  /* 사용자 메시지 오른쪽 정렬 */
  text-align: right;
  /* 사용자 메시지 오른쪽 정렬 */
}
.other-message {
  background-color: #fdecf8;
  align-self: flex-start;
  /* 상대방 메시지 왼쪽 정렬 */
  text-align: left;
  /* 상대방 메시지 왼쪽 정렬 */
}

.input-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
}

input {
  flex-grow: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
button {
  padding: 10px 20px;
  background-color: var(--color-bg-second);
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
button:hover {
  background-color: #0056b3;
}
.btn-message {
  background-color: var(--color-bg-blue1);
}
/* 모바일 매물 리스트 */
.property-item-mobile {
  display: flex;
}
.property-item-mobile a{
  display: flex;
}
.styled-RoomDetail {
  display: flex;
  flex-direction: column;
  width: 15rem;
}
.styled-price {
  flex: 0 0 auto;
  color: rgb(34, 34, 34);
  font-size: 1.1rem;
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
  width: 30%;
  height: 100%;
}

.styled-roomLink {
  display: grid;
  grid-template-columns: 153px minmax(0px, 1fr);
  column-gap: 14px;
}

.card-body {
  padding: 1rem 1rem;
}
</style>
