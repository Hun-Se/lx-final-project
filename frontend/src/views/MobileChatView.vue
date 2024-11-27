<template>
  <MobileHeader title="채팅"></MobileHeader>
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
                  <a href="#" class="text-gray-900 text-hover-primary fs-2 fw-bold me-1">김중개</a>
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
                <button class="btn btn-sm btn-icon btn-light-primary">
                  <i class="ki-duotone ki-dots-square fs-2"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 매물정보 카드 -->
    <div class="card mt-5 mb-5 mb-xl-10">
      <div class="card-body container-prp-info">
        <li class="property-item-mobile">
          <a href="#" class="styled-roomLink">
            <img src="/assets/img/home.jpg" alt="매물 이미지" class="property-image-mobile" />
            <div class="styled-RoomDetail">
              <h1 class="styled-price">
                <span><span class="badge badge-light-primary">월세</span> 70만원 / <span class="badge badge-light-info">보증금</span>500만원</span>
              </h1>
              <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">원룸</p>
              <p class="styled__RoomType-sc-1b8f2kq-5 XdHPA">서울시 종로구 자하문로36길 16-14</p>
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
              <div
                class="d-flex"
                :class="msg.userPkSender === userStore.user?.userPk ? 'justify-content-end' : 'justify-content-start'"
              >
                <div
                  class="p-5 rounded"
                  :class="msg.userPkSender === userStore.user?.userPk ? 'bg-light-primary text-gray-900' : 'bg-light-info text-gray-900'"
                >
                  <!-- 사용자 이름 안전하게 표시 -->
                  <strong>{{ msg.userPkSender === userStore.user?.userPk ? userStore.user?.userName : msg.userName || '시스템' }}</strong>:
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
          <button class="btn btn-primary btn-message" @click="submitMessage">전송</button>
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
import { useRouter } from "vue-router";

// 모바일 환경 설정
const store = useMobileStore();
const { isMobile } = store;

// Header 렌더링 함수
const renderHeader = () => (isMobile.value ? MobileHeader : Header);

// Pinia 스토어에서 사용자 정보 가져오기
const userStore = useUserStore();
const user = userStore.user; // storeToRefs 대신 직접 참조

// Vue Router 인스턴스
const router = useRouter();

// WebSocket 관련 설정
const chatStore = useChatStore();
const newMessage = ref("");

onMounted(() => {
  userStore.initializeUser?.(); // Pinia 상태 초기화

  console.log("Debug: userStore.user:", userStore.user);

  if (!userStore.user || !userStore.user.userPk) {
    console.error("User information is missing. Redirecting to login page.");
    router.push("/login");
    return;
  }

  console.log("User is valid. Initializing WebSocket.");
  initializeWebSocket(userStore.user.userName, chatStore);
});

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
</script>

<style scoped>
/* 기존 스타일 유지 */
</style>
