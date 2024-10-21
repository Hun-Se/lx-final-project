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
const isAgentListVisible = ref(false);
const selectedUserPk = ref(null); // userPk 값을 담을 변수
const properties = ref([]); // 매물 리스트
const selectedPropertyId = ref(null); // 선택된 매물 ID
const feeEstimate = ref(null); // 중개 수수료 계산 결과

// 모달 열기/닫기 함수
function openModal(propertyId) {
  selectedPropertyId.value = propertyId;
  fetchFeeEstimateByPropertyId(propertyId); // 해당 매물의 수수료 정보 불러오기
  isModalVisible.value = true;
}

function closeModal() {
  isModalVisible.value = false;
}

// 공인중개사 리스트 토글 함수
function toggleAgentList() {
  isAgentListVisible.value = !isAgentListVisible.value;
}

// 사용자 세션에서 userPk를 가져오는 함수
async function fetchUserPk() {
  try {
    const userPk = sessionStorage.getItem("userPk");
    console.log("Fetched userPk from session:", userPk); // 로그 추가

    // userPk가 설정된 후에 매물 리스트를 가져옵니다.
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
    console.log("Fetched properties:", properties.value); // 확인용 로그 추가
  // 매물 리스트를 불러온 후 첫 번째 매물의 ID로 수수료 계산 호출
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
    // propertyId가 제대로 전달되고 있는지 확인
    console.log("PropertyId:", propertyId);

    const feeResponse = await axios.get(`/api/auction/calculate/${propertyId}`);
    feeEstimate.value = feeResponse.data;

    // properties에 매물 데이터가 제대로 있는지 확인
    console.log("Fee estimate response:", feeEstimate.value);

    // 매물 데이터에서 매매가를 찾는 부분
    feeEstimate.value.price = properties.value.find(
      (p) => p.prpPk === Number(propertyId)
    )?.prpPrice || 'N/A';

    console.log("Calculated Price for property:", feeEstimate.value.price);
  } catch (error) {
    console.error("Error calculating fee:", error);
    alert("중개수수료 계산에 문제가 발생했습니다.");
  }
}



// 필터링된 매물 리스트 (AgentListVisible에 따라 필터링)
const filteredProperties = computed(() => {
  console.log("Filtered Properties:", properties.value);
  return properties.value;
});

// 컴포넌트가 마운트될 때 데이터 불러오기
onMounted(() => {
  fetchUserPk(); // 사용자 세션에서 userPk를 가져오고 매물 리스트 가져오기
});
</script>

<template>
  <component :is="renderHeader()" />
  <div class="d-flex flex-column flex-column-fluid justify-content-center align-items-center flex-grow-1">
    <div id="kt_app_content" class="app-content flex-column-fluid d-flex flex-grow-1 flex-column">
      <div id="kt_app_content_container"
        class="app-container container-xxl d-flex justify-content-center align-items-center flex-grow-1 p-5">
        <div class="d-flex flex-column justify-content-center align-items-center flex-grow-1">
          <div class="property-list">
            <div v-for="property in filteredProperties" :key="property.prpPk" class="property-item">
              <div class="property-info">
                <img :src="'/assets/img/' + property.prpImg" alt="home image" />
                <div class="property-details">
                  <div>{{ property.prpName }}</div>
                  <div class="property-price">매매 {{ property.prpPrice }}원</div>
                  <button class="toggle-button" @click="toggleAgentList">
                    {{ isAgentListVisible ? "닫기" : "경매 현황 보기" }}
                  </button>
                </div>
              </div>
              <button class="join-button" @click="openModal(property.prpPk)">참여</button>
            </div>

            <div v-if="isAgentListVisible" class="agent-list">
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

          <div v-if="isModalVisible" class="modal" id="modal">
            <div>예상 중개수수료: {{ feeEstimate?.brokerageFee || 'N/A' }}원</div>
            <div>부가세: {{ feeEstimate?.vat || 'N/A' }}원</div>
            <div>총 수수료: {{ feeEstimate?.totalFee || 'N/A' }}원</div>
            <input type="number" placeholder="중개수수료를 입력하세요!" />
            <div>
              <button class="modal-button" @click="closeModal">참여</button>
              <button class="modal-button" @click="closeModal">취소</button>
            </div>
          </div>

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
  width: 80%;
  max-width: 500px;
  height: auto;
  overflow-y: auto;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(5px);
  z-index: 999;
}
</style>
