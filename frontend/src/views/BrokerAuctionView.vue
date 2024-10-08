<script setup>
import { ref, onMounted } from "vue";
import Header from "@/components/Header.vue";
import axios from "axios";

// 상태 관리
const isModalVisible = ref(false); // 모달 열기/닫기 상태
const isAgentListVisible = ref(false); // 중개사 리스트 열기/닫기 상태
const properties = ref([]); // 매물 리스트
const feeEstimate = ref(null); // 중개 수수료 계산 결과
const auctionDetails = ref([]); // 경매 현황 정보
const userBidAmount = ref(0); // 사용자가 입력한 중개수수료
const selectedPropertyId = ref(null); // 선택된 매물 ID

// 로그인된 사용자와 중개사 타입 정보 (임시 지정)
const brokerId = 2; // 중개사 ID
const userId = 1; // 사용자 ID

// 모달 열기/닫기 함수
function openModal(propertyId) {
  selectedPropertyId.value = propertyId;
  fetchFeeEstimateByPropertyId(propertyId);  // 해당 매물의 수수료 정보 불러오기
  isModalVisible.value = true;
}

function closeModal() {
  isModalVisible.value = false;
}

// 공인중개사 리스트 토글 함수
function toggleAgentList() {
  isAgentListVisible.value = !isAgentListVisible.value;
}

// 매물 리스트, 중개 수수료, 경매 현황 데이터 가져오기
async function fetchProperties() {
  try {
    const response = await axios.get('/api/properties/agent', { params: { agentId: brokerId } });
    properties.value = response.data;

    // 첫 번째 매물에 대해 중개수수료 계산
    if (properties.value.length > 0) {
      const propertyId = properties.value[0].prp_pk;
      fetchFeeEstimateByPropertyId(propertyId);
    }
  } catch (error) {
    console.error("Error fetching properties:", error);
    alert("매물 데이터를 불러오는데 문제가 발생했습니다.");
  }
}

// 매물 ID를 이용한 중개수수료 계산
async function fetchFeeEstimateByPropertyId(propertyId) {
  try {
    const feeResponse = await axios.get(`/api/auction/calculate/${propertyId}`);
    feeEstimate.value = feeResponse.data;
  } catch (error) {
    console.error("Error calculating fee:", error);
    alert("중개수수료 계산에 문제가 발생했습니다.");
  }
}

// 입찰 참여 함수
async function submitBid(bidAmount) {
  try {
    const response = await axios.post('/api/auction/submit-bid', {
      bidAmount: bidAmount,
      propertyId: selectedPropertyId.value,
      userId: userId,
      brokerId: brokerId
    });
    console.log('입찰 완료:', response.data);
    closeModal();
  } catch (error) {
    console.error("입찰 실패:", error);
    alert("입찰에 실패했습니다.");
  }
}

// 컴포넌트가 마운트될 때 데이터 불러오기
onMounted(() => {
  fetchProperties(); // 매물 리스트 가져오기
});
</script>

<template>
  <Header />
  <div
    class="d-flex flex-column flex-column-fluid justify-content-center align-items-center flex-grow-1"
  >
    <div
      id="kt_app_content"
      class="app-content flex-column-fluid d-flex flex-grow-1 flex-column"
    >
      <div
        id="kt_app_content_container"
        class="app-container container-xxl d-flex justify-content-center align-items-center flex-grow-1 p-5"
      >
        <div
          class="d-flex flex-column justify-content-center align-items-center flex-grow-1"
        >
          <!-- 여기에 타이틀 추가 -->
          <h2 class="auction-title">경매낙찰 화면</h2>
          <div class="property-list">
            <div class="property-item" v-for="property in properties" :key="property.prp_pk">
              <div class="property-info">
                <img src="/assets/img/home.jpg" alt="home image" />
                <div class="property-details">
                  <div>{{ property.prp_name }}</div>
                  <div class="property-price">매매 {{ property.prp_price }}원</div>
                  <button class="toggle-button" @click="toggleAgentList">
                    경매 현황 보기
                  </button>
                </div>
              </div>
              <button class="join-button" @click="openModal(property.prp_pk)">참여</button>
            </div>

            <!-- 접이식 공인중개사 리스트 -->
            <div v-if="isAgentListVisible" class="agent-list">
              <h3>중개수수료 요율표에 따른 중개수수료 계산</h3>

              <!-- 예상 수수료 표 -->
              <table class="fee-table">
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
                    <td>{{ feeEstimate.price }}</td>
                    <td></td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>상한 요율</td>
                    <td>0.6%</td>
                    <td>12억 원 ~ 15억 원 상한 요율 0.6%</td>
                  </tr>
                  <tr class="highlight">
                    <td>3</td>
                    <td>중개 수수료</td>
                    <td>{{ feeEstimate.brokerageFee }}</td>
                    <td>기준 금액 × 요율</td>
                  </tr>
                  <tr>
                    <td>4</td>
                    <td>부가세 포함</td>
                    <td>{{ feeEstimate.vat }}</td>
                    <td>VAT 10%</td>
                  </tr>
                </tbody>
              </table>

              <h3>중개수수료 경매 현황</h3>
              <div class="agent-item" v-for="auction in auctionDetails" :key="auction.agentName">
                <img src="/assets/img/home.jpg" alt="공인중개사 아이콘" class="agent-icon" />
                <span>{{ auction.agentName }}</span>
                <span class="agent-price">{{ auction.bidAmount }}원</span>
                <button class="agent-bid-button">낙찰</button>
              </div>
            </div>
          </div>

          <!-- 모달 창 -->
          <div v-if="isModalVisible" class="modal" id="modal">
            <div>예상 중개수수료: {{ feeEstimate ? feeEstimate.brokerageFee : '' }}원</div>
            <input type="number" v-model="userBidAmount" placeholder="중개수수료를 입력하세요!" />
            <div>
              <button class="modal-button" @click="submitBid(userBidAmount)">참여</button>
              <button class="modal-button" @click="closeModal">취소</button>
            </div>
          </div>

          <!-- 모달 백그라운드 -->
          <div v-if="isModalVisible" class="overlay" @click="closeModal"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 스타일 */
.property-list {
  margin: 20px;
}

.property-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f8f9fa;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.property-info {
  display: flex;
  align-items: center;
}

.property-info img {
  width: 100px;
  height: 100px;
  border-radius: 8px;
  margin-right: 20px;
}

.property-details {
  font-size: 16px;
}

.property-price {
  color: #dc3545;
  font-weight: bold;
}

.toggle-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

.join-button {
  background-color: #ff6b6b;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}

.agent-list {
  background-color: #f0f0f0;
  padding: 10px;
  border-radius: 8px;
  margin-top: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
}

.fee-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

.fee-table th,
.fee-table td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: center;
}

.fee-table th {
  background-color: #f8f9fa;
}

.fee-table tr.highlight {
  background-color: #e9f5ff;
}

.agent-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  margin-bottom: 10px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.agent-icon {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.agent-price {
  color: #dc3545;
  font-weight: bold;
  margin-left: auto;
  margin-right: 20px;
}

.agent-bid-button {
  background-color: #ff6b6b;
  color: white;
  border: none;
  padding: 5px 15px;
  border-radius: 5px;
  cursor: pointer;
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 30px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  z-index: 1000;
  width: 50%;
  max-width: 1000px;
  height: auto;
  overflow-y: auto;
}

.modal input {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
}

.modal-button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-right: 10px;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 999;
}

.auction-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center;
}
</style>
