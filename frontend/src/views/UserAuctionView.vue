<script setup>
import { ref, onMounted, computed } from "vue";
import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import { useMobileStore } from "@/stores/mobile";
import { storeToRefs } from "pinia";
import axios from "axios";

// 모바일 상태 관리
const store = useMobileStore();
const { isMobile } = storeToRefs(store);

// 컴포넌트 동적으로 렌더링
const renderHeader = () => {
  return isMobile.value ? MobileHeader : Header;
};

// 상태 관리
const isModalVisible = ref(false);
const isAgentListVisible = ref(true); // 기본적으로 요율표를 보이게 설정
const isAgentParticipating = ref(false); // 중개사 참여 여부 상태
const selectedUserPk = ref(null);
const properties = ref([]);
const selectedPropertyId = ref(null);
const feeEstimate = ref(null);

// 모달 열기/닫기 함수
function openModal(propertyId) {
  selectedPropertyId.value = propertyId;
  fetchFeeEstimateByPropertyId(propertyId);
  isModalVisible.value = true;
}

function closeModal() {
  isModalVisible.value = false;
}

// 사용자 세션에서 userPk를 가져오는 함수
async function fetchUserPk() {
  try {
    const userPk = sessionStorage.getItem("userPk");
    if (userPk) {
      selectedUserPk.value = userPk;
      await fetchAuctionPrp(userPk);
    } else {
      console.error("UserPk is not available in the session.");
    }
  } catch (error) {
    console.error("Error fetching user session:", error);
    alert("사용자 정보를 불러오는데 문제가 발생했습니다.");
  }
}

// 매물 리스트 불러오기 함수
async function fetchAuctionPrp(userPk) {
  try {
    const response = await axios.get(`/api/auction/user/${userPk}`);
    properties.value = response.data;

    if (properties.value.length > 0) {
      const firstPropertyId = properties.value[0].prpPk;
      selectedPropertyId.value = firstPropertyId;
      fetchFeeEstimateByPropertyId(firstPropertyId);
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

    feeEstimate.value.price = properties.value.find(
      (p) => p.prpPk === Number(propertyId)
    )?.prpPrice || 'N/A';

    isAgentParticipating.value = feeResponse.data.isAgentParticipating;

  } catch (error) {
    console.error("Error calculating fee:", error);
    alert("중개수수료 계산에 문제가 발생했습니다.");
  }
}

// 필터링된 매물 리스트
const filteredProperties = computed(() => {
  return properties.value;
});

// 컴포넌트가 마운트될 때 데이터 불러오기
onMounted(() => {
  fetchUserPk();
});
</script>

<template>
  <component :is="renderHeader()" />
  <div class="content-container d-flex justify-content-between p-5">
    <!-- Left Column -->
    <div class="left-column">
      <h3 class="title">김중개 중개사님이 참여한 중개수수료 경매</h3>
      <div v-if="filteredProperties.length > 0" class="property-item">
        <div class="property-info">
          <img :src="'/assets/img/' + filteredProperties[0].prpImg" alt="home image" />
          <div class="property-details">
            <div>{{ filteredProperties[0].prpName }}</div>
            <div class="property-price">매매 {{ filteredProperties[0].prpPrice }}원</div>
          </div>
        </div>
        <div class="agent-list">
          <h3>중개수수료 요율표에 따른 중개수수료 계산</h3>
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
                <td>거래가액</td>
                <td>{{ feeEstimate?.price || 'N/A' }}</td>
                <td></td>
              </tr>
              <tr>
                <td>2</td>
                <td>상한 요율</td>
                <td>{{ feeEstimate?.feeRate || 'N/A' }}</td>
                <td></td>
              </tr>
              <tr class="highlight">
                <td>3</td>
                <td>중개 수수료</td>
                <td>{{ feeEstimate?.brokerageFee || 'N/A' }}</td>
                <td>기준 금액 × 요율</td>
              </tr>
              <tr>
                <td>4</td>
                <td>부가세</td>
                <td>{{ feeEstimate?.vat || 'N/A' }}</td>
                <td>VAT 10%</td>
              </tr>
              <tr>
                <td>5</td>
                <td>중개수수료 + 부가세</td>
                <td>{{ feeEstimate?.totalFee || 'N/A' }}</td>
                <td></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- Right Column -->
    <div class="right-column">
      <h3 class="title">참여 가능한 경매</h3>
      <div v-for="property in filteredProperties" :key="property.prpPk" class="property-item">
        <div class="property-info">
          <img :src="'/assets/img/' + property.prpImg" alt="home image" />
          <div class="property-details">
            <div>{{ property.prpName }}</div>
            <div class="property-price">매매 {{ property.prpPrice }}원</div>
            <button class="join-button" @click="openModal(property.prpPk)">
              {{ isAgentParticipating ? "참여 완료" : "참여" }}
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.content-container {
  display: flex;
  justify-content: space-between;
  gap: 20px;
}

.left-column, .right-column {
  flex: 1;
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.title {
  margin-bottom: 20px;
  font-size: 18px;
  font-weight: bold;
}

.property-item {
  margin-bottom: 20px;
}

.property-info {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
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

.agent-list {
  background-color: #ffffff;
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.fee-table {
  width: 100%;
  border-collapse: collapse;
}

.fee-table th, .fee-table td {
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

.join-button {
  background-color: #ff6b6b;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}
</style>
