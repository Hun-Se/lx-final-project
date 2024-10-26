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
const selectedUserPk = ref(null); // userPk 값을 담을 변수
const properties = ref([]); // 매물 리스트
const selectedPropertyId = ref(null); // 선택된 매물 ID
const feeEstimate = ref(null); // 중개 수수료 계산 결과
const inspectedAuctions = ref([]); // 임장 요청된 경매 데이터 리스트
const inputFee = ref(0); // 입찰 금액 입력


// 모달 열기/닫기 함수
function openModal(propertyId, auctionPk) {
  selectedPropertyId.value = propertyId;
  fetchFeeEstimateByPropertyId(propertyId, auctionPk); // 해당 매물의 수수료 정보 불러오기
  isModalVisible.value = true;
}

function closeModal() {
  isModalVisible.value = false;
}

// 특정 매물의 경매 현황 보기 토글 함수
function toggleAgentList(auctionPk, propertyId) {
  const auction = inspectedAuctions.value.find(item => item.auctionPk === auctionPk);
  if (auction) {
    auction.isAgentListVisible = !auction.isAgentListVisible;
    
    // 경매 현황을 보려고 할 때만 수수료 계산 실행
    if (auction.isAgentListVisible) {
      fetchFeeEstimateByPropertyId(propertyId, auctionPk);
    }
  } else {
    console.error(`Auction not found with auctionPk: ${auctionPk}`);
  }
}

// 사용자 세션에서 userPk를 가져오는 함수
async function fetchUserPk() {
  try {
    const userPk = sessionStorage.getItem("userPk");
    if (userPk) {
      selectedUserPk.value = userPk;
      await fetchAuctionPrp(userPk);
      fetchInspectedAuctions(userPk);
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

// 중개인이 임장 요청한 경매 데이터를 불러오는 함수
async function fetchInspectedAuctions(userPk) {
  try {
    const response = await axios.get(`/api/auction/agent/${userPk}/inspected-auctions`);
    inspectedAuctions.value = response.data.map(auction => ({
      ...auction,
      isAgentListVisible: false, // 각 경매 항목에 대해 개별적으로 상태 관리
      prpTransType: auction.prpTransType || 0 // 기본값을 설정하거나 API에서 받아온 값을 사용
    }));

    console.log("Inspected Auctions:", inspectedAuctions.value); // 데이터를 로그로 확인
  } catch (error) {
    console.error("Error fetching inspected auctions:", error);
    alert("임장 요청된 경매 데이터를 불러오는데 문제가 발생했습니다.");
  }
}


// 매물 ID를 이용한 중개수수료 계산
async function fetchFeeEstimateByPropertyId(propertyId, auctionPk) {
  try {
    const feeResponse = await axios.get(`/api/auction/calculate/${propertyId}`);
    console.log("Fee response data:", feeResponse.data);

    const auctionIndex = inspectedAuctions.value.findIndex(
      (auction) => auction.auctionPk === auctionPk
    );

    if (auctionIndex !== -1) {
      // 경매 수수료 데이터를 업데이트
      const updatedAuction = inspectedAuctions.value[auctionIndex];
      updatedAuction.feeEstimate = feeResponse.data;
      updatedAuction.feeEstimate.price = updatedAuction.prpPrice || 'N/A';

      // 모달의 예상 수수료 데이터에도 반영
      feeEstimate.value = {
        brokerageFee: updatedAuction.feeEstimate.brokerageFee,
        vat: updatedAuction.feeEstimate.vat,
        totalFee: updatedAuction.feeEstimate.totalFee,
        feeRate: updatedAuction.feeEstimate.feeRate,
        price: updatedAuction.feeEstimate.price,
        prpTransType : updatedAuction.feeEstimate.prpTransType
      };

      // 업데이트된 경매 항목 로그 출력
      console.log("Updated auction fee estimate:", updatedAuction);
    } else {
      console.error("Auction not found with auctionPk:", auctionPk);
    }
  } catch (error) {
    console.error("Error calculating fee:", error);
    alert("중개수수료 계산에 문제가 발생했습니다.");
  }
}

// 거래 유형을 텍스트로 변환하는 computed property 추가
function getPropertyTypeText(prpTransType) {
  switch (prpTransType) {
    case 0:
      return "매매";
    case 1:
      return "전세";
    case 2:
      return "월세";
    default:
      return "알 수 없음";
  }
}

// 모달에서 참여 버튼 클릭 시 중개수수료 제출 함수
async function submitFee() {
  if (!inputFee.value || isNaN(inputFee.value) || inputFee.value <= 0) {
    alert("올바른 중개수수료를 입력하세요.");
    return;
  }

  try {
    const bidData = {
      bidAmount: inputFee.value,
      auctionPk: selectedPropertyId.value,
      agentPk: selectedUserPk.value,
    };

    const response = await axios.post('/api/bid/create', bidData);
    alert(response.data);

    // 참여가 완료된 경매를 임장 요청된 리스트에서 제거하고, 참여 리스트에 추가
    const auction = inspectedAuctions.value.find(
      (item) => item.auctionPk === selectedPropertyId.value
    );
    if (auction) {
      properties.value.push(auction); // 참여 리스트에 추가
      inspectedAuctions.value = inspectedAuctions.value.filter(
        (item) => item.auctionPk !== selectedPropertyId.value
      ); // 임장 요청된 리스트에서 제거
    }

    closeModal();
  } catch (error) {
    console.error("Error placing bid:", error);
    alert("입찰을 제출하는데 문제가 발생했습니다.");
  }
}



// filteredInspectedAuctions 수정
const filteredInspectedAuctions = computed(() => {
  return inspectedAuctions.value.filter(auction => {
    return !properties.value.some(property => property.auctionPk === auction.auctionPk);
  });
});



// 내가 참여한 경매 입찰 목록 불러오기
async function fetchUserBids(userPk) {
  try {
    const response = await axios.get(`/api/bid/user/${userPk}`); 
    properties.value = response.data;
  } catch (error) {
    console.error("Error fetching user bids:", error);
    alert("내가 참여한 입찰 정보를 불러오는데 문제가 발생했습니다.");
  }
}

// 필터링된 매물 리스트
const filteredProperties = computed(() => properties.value);

// 컴포넌트가 마운트될 때 데이터 불러오기
onMounted(() => {
  fetchUserPk();
});

</script>

<template>
  <component :is="renderHeader()" />
  <div class="d-flex flex-column flex-column-fluid justify-content-center align-items-center flex-grow-1">
    <div id="kt_app_content" class="app-content flex-column-fluid d-flex flex-grow-1 flex-column">
      <div id="kt_app_content_container"
        class="app-container container-xxl d-flex justify-content-center align-items-center flex-grow-1 p-5">
        <div class="d-flex flex-column justify-content-center align-items-center flex-grow-1">

          <div class="content-container">
            <!-- Left Column: 입찰이 진행된 매물 리스트 -->
            <div class="left-column">
              <h3 class="title">김중개 중개사님이 참여한 중개수수료 경매</h3>
              <div v-if="filteredProperties.length > 0">
                <div v-for="property in filteredProperties" :key="property.prpPk" class="property-item">
                  <div class="property-info">
                    <img :src="'/assets/img/' + property.prpImg" alt="home image" />
                    <div class="property-details">
                      <div>{{ property.prpName }}</div>
                      <div class="property-price">매매 {{ property.prpPrice }}원</div>
                      <div>나의 입찰 금액: <strong>{{ property.myBid || 'N/A' }}</strong></div>
                      <div>가장 저렴한 입찰 금액: <strong>{{ property.lowestBid || 'N/A' }}</strong></div>
                    </div>
                  </div>
                </div>
              </div>
              <div v-else>
                <p>참여한 경매가 없습니다.</p>
              </div>
            </div>

            <!-- Right Column : 임장 요청된 경매 리스트 -->
            <div class="right-column">
            <h3 class="title">임장 요청된 경매</h3>
            <div v-for="auction in filteredInspectedAuctions" :key="auction.auctionPk" class="property-item">
              <div class="property-info">
                <img :src="'/assets/img/' + auction.prpImg" alt="home image" />
                <div class="property-details">
                  <div>{{ auction.prpName }}</div>
                  <div class="property-price">
                    {{ getPropertyTypeText(auction.prpTransType) }} {{ auction.prpPrice }}원
                  </div>
                  <div>경매 시작일: {{ auction.auctionStart }}</div>
                  <div>경매 종료일: {{ auction.auctionEnd }}</div>
                  <button class="toggle-button" @click="toggleAgentList(auction.auctionPk, auction.prpPk)">
            {{ auction.isAgentListVisible ? "닫기" : "경매 현황 보기" }}
          </button>
                </div>
                <button class="join-button" @click="openModal(auction.prpPk, auction.auctionPk)">참여</button>
              </div>

              <div v-if="auction.isAgentListVisible" class="agent-list">
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
                      <td>{{ auction.feeEstimate?.price || 'N/A' }}</td>
                      <td></td>
                    </tr>
                    <tr>
                      <td>2</td>
                      <td>상한 요율</td>
                      <td>{{ auction.feeEstimate?.feeRate || 'N/A' }}</td>
                      <td></td>
                    </tr>
                    <tr class="highlight">
                      <td>3</td>
                      <td>중개 수수료</td>
                      <td>{{ auction.feeEstimate?.brokerageFee || 'N/A' }}</td>
                      <td>기준 금액 × 요율</td>
                    </tr>
                    <tr>
                      <td>4</td>
                      <td>부가세</td>
                      <td>{{ auction.feeEstimate?.vat || 'N/A' }}</td>
                      <td>VAT 10%</td>
                    </tr>
                    <tr>
                      <td>5</td>
                      <td>중개수수료 + 부가세</td>
                      <td>{{ auction.feeEstimate?.totalFee || 'N/A' }}</td>
                      <td></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

          </div>

        </div>

        <div v-if="isModalVisible" class="modal" id="modal">
          <div class="modal-content">
            <div class="modal-fees">
              <div class="modal-fee">
                <span>예상 중개수수료:</span>
                <strong>{{ feeEstimate?.brokerageFee || 'N/A' }}원</strong>
              </div>
              <div class="modal-fee">
                <span>가장 낮은 중개수수료:</span>
                <strong>{{ feeEstimate?.lowestBid || 'N/A' }}원</strong>
              </div>
            </div>
            <div class="modal-input">
              <input
                type="number"
                v-model="inputFee"
                placeholder="중개수수료를 입력하세요!"
                class="fee-input"
              />
              <span>원</span>
            </div>
            <div class="modal-actions">
              <button class="modal-button" @click="submitFee">참여</button>
              <button class="modal-button cancel" @click="closeModal">취소</button>
            </div>
          </div>
        </div>

        <div v-if="isModalVisible" class="overlay" @click="closeModal"></div>

      </div>
    </div>

  </div>


</template>

<style scoped>
.content-container {
  display: flex;
  justify-content: space-between;
  gap: 20px;
  padding: 20px;
}

.left-column,
.right-column {
  width: 600px;
  flex: 1;
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.toggle-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
  font-size: 15px;
}

.join-button {
  margin-left: auto;
  /* 버튼을 오른쪽 끝으로 이동 */
  background-color: #ff6b6b;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 15px;
}

.property-item {
  margin-bottom: 20px;
}

.property-info {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  background-color: #f0f6fffb;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
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
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.fee-table {
  width: 100%;
  border-collapse: collapse;
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
  padding: 20px;
  border-radius: 16px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  z-index: 1000;
  width: 90%;
  max-width: 400px;
  max-height: 250px; /* 화면의 80%를 넘지 않도록 설정 */
  overflow-y: auto; /* 내용이 넘칠 경우 스크롤 추가 */
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center; /* 내용이 수직 중앙 정렬되도록 설정 */
}

.modal-content {
  width: 100%;
  text-align: center;
  box-shadow: none;
}

.modal-fees {
  margin-bottom: 20px;
}

.modal-fee {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
  font-size: 16px;
}

.modal-fee strong {
  font-size: 18px;
  color: #333;
}

.modal-input {
  display: flex;
  align-items: center;
  background-color: #f8f9fa;
  padding: 8px;
  border-radius: 8px;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.fee-input {
  flex: 1;
  border: none;
  background: transparent;
  outline: none;
  font-size: 16px;
  padding: 5px;
}

.fee-input::placeholder {
  color: #aaa;
}

.modal-input span {
  margin-left: 5px;
  color: #777;
}

.modal-actions {
  display: flex;
  gap: 10px;
}

.modal-button {
  flex: 1;
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-size: 16px;
}

.modal-button.cancel {
  background-color: #6c757d;
}

.modal-button:hover {
  background-color: #0056b3;
}

.modal-button.cancel:hover {
  background-color: #495057;
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

</style>
