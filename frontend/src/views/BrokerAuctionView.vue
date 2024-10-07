<script setup>
import { ref, onMounted } from "vue";
import Header from "@/components/Header.vue";
import axios from "axios";

// 상태 관리
const isModalVisible = ref(false);
const isAgentListVisible = ref(false);
const properties = ref([]);
const feeEstimate = ref(null);
const auctionDetails = ref([]);

// 함수 정의
function openModal() {
  isModalVisible.value = true;
}

function closeModal() {
  isModalVisible.value = false;
}

function toggleAgentList() {
  isAgentListVisible.value = !isAgentListVisible.value;
}

// API 호출: 매물 리스트, 예상 수수료 및 경매 내역 불러오기
async function fetchProperties() {
  try {
    const response = await axios.get('/api/properties/agent');
    properties.value = response.data;

    if (properties.value.length > 0) {
      const propertyId = properties.value[0].prp_pk;

      // 예상 수수료 정보 호출
      const feeResponse = await axios.get(`/api/auction/fee-estimate/${propertyId}`);
      feeEstimate.value = feeResponse.data;

      // 경매 현황 정보 호출
      const auctionResponse = await axios.get(`/api/auction/agent-auctions/${propertyId}`);
      auctionDetails.value = auctionResponse.data;
    }
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

// 컴포넌트가 마운트될 때 데이터 불러오기
onMounted(() => {
  fetchProperties();
});
</script>

<template>
  <Header />
  <div class="d-flex flex-column justify-content-center align-items-center flex-grow-1">
    <h2 class="auction-title">경매낙찰 화면</h2>
    
    <div v-if="properties.length > 0" class="property-list">
      <!-- 매물 정보 -->
      <div v-for="property in properties" :key="property.prp_pk" class="property-item">
        <div class="property-info">
          <img :src="`/assets/img/${property.prp_img}`" alt="home image" />
          <div class="property-details">
            <div>{{ property.prp_name }}</div>
            <div class="property-price">매매 {{ property.prp_price }}원</div>
            <button class="toggle-button" @click="toggleAgentList">
              경매 현황 보기
            </button>
          </div>
        </div>
        <button class="join-button" @click="openModal">참여</button>
      </div>

      <!-- 예상 수수료 테이블 -->
      <div v-if="feeEstimate" class="fee-table">
        <h3>중개수수료 요율표에 따른 중개수수료 계산</h3>
        <table>
          <thead>
            <tr>
              <th>#</th>
              <th>적요</th>
              <th>금액</th>
              <th>비고</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>매매가</td>
              <td>{{ feeEstimate.price }}원</td>
              <td></td>
            </tr>
            <tr>
              <td>2</td>
              <td>상한 요율</td>
              <td>0.6%</td>
              <td></td>
            </tr>
            <tr class="highlight">
              <td>3</td>
              <td>중개 수수료</td>
              <td>{{ feeEstimate.fee }}원</td>
              <td>기준 금액 × 요율</td>
            </tr>
            <tr>
              <td>4</td>
              <td>부가세 포함</td>
              <td>{{ feeEstimate.vat }}원</td>
              <td>VAT 포함</td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 중개수수료 경매 현황 -->
      <div v-if="auctionDetails.length > 0" class="agent-list">
        <h3>중개수수료 경매 현황</h3>
        <div v-for="auction in auctionDetails" :key="auction.agentName" class="agent-item">
          <img src="/assets/img/home.jpg" alt="공인중개사 아이콘" class="agent-icon" />
          <span>{{ auction.agentName }}</span>
          <span class="agent-price">{{ auction.bidAmount }}원</span>
          <button class="agent-bid-button">낙찰</button>
        </div>
      </div>
    </div>

    <p v-else>임장신청한 매물이 없습니다.</p>

    <!-- 모달 창 -->
    <div v-if="isModalVisible" class="modal" id="modal">
      <div>예상 중개수수료: {{ feeEstimate ? feeEstimate.fee : '' }}원</div>
      <input type="number" placeholder="중개수수료를 입력하세요!" />
      <div>
        <button class="modal-button" @click="closeModal">참여</button>
        <button class="modal-button" @click="closeModal">취소</button>
      </div>
    </div>

    <!-- 모달 백그라운드 -->
    <div v-if="isModalVisible" class="overlay" @click="closeModal"></div>
  </div>
</template>
