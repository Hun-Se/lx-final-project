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
const bidProperties = ref([]);
const selectedPropertyId = ref(null); // 선택된 매물 ID
const feeEstimate = ref(null); // 중개 수수료 계산 결과
const inspectedAuctions = ref([]); // 임장 요청된 경매 데이터 리스트
const inputFee = ref(0); // 입찰 금액 입력
const userName = ref(""); // userName 상태 추가
const totalProperties = ref("");

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
            fetchUserName(userPk); // userName을 가져오는 함수 호출
            fetchTotalProperties(userPk);
        } else {
            console.error("UserPk is not available in the session.");
        }
    } catch (error) {
        console.error("Error fetching user session:", error);
        alert("사용자 정보를 불러오는데 문제가 발생했습니다.");
    }
}

// userName을 가져오는 함수
async function fetchUserName(userPk) {
    try {
        const response = await axios.get(`/api/users/userName/${userPk}`);
        userName.value = response.data; // userName 값을 설정
    } catch (error) {
        console.error("Error fetching user name:", error);
        alert("사용자 이름을 불러오는데 문제가 발생했습니다.");
    }
}

// totalProperties을 가져오는 함수
async function fetchTotalProperties(userPk) {
    try {
        const response = await axios.get(`/api/bid/count/user/${userPk}`);
        totalProperties.value = response.data; // userName 값을 설정
    } catch (error) {
        console.error("Error fetching totalProperties:", error);
        alert("매물의 개수를 불러오는데 문제가 발생했습니다.");
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

// 중개인이 임장 요청한 매물 중 경매가 진행된 매물 데이터를 불러오는 함수
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
                prpTransType: updatedAuction.feeEstimate.prpTransType
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
async function fetchUserBids() {
    console.log("selectedUserPk (userPk):", selectedUserPk.value);
    try {
        // userPk를 직접 사용하여 입찰 목록을 가져옵니다.
        const response = await axios.get(`/api/bid/user/${selectedUserPk.value}`);
        bidProperties.value = response.data;

        console.log(response.data);
    } catch (error) {
        console.error("Error fetching user bids:", error);
        alert("내가 참여한 입찰 정보를 불러오는데 문제가 발생했습니다.");
    }
}



// 컴포넌트가 마운트될 때 데이터 불러오기
onMounted(() => {
    fetchUserPk();
    fetchUserBids();
});
</script>

<template>
    <component :is="renderHeader()" />
    
    <div class="d-flex flex-column flex-column-fluid justify-content-center align-items-center flex-grow-1">
    
        <div id="kt_app_content" class="app-content flex-column-fluid d-flex flex-grow-1 flex-column">
    
            <div id="kt_app_content_container" class="app-container container-xxl d-flex justify-content-center align-items-center flex-grow-1 p-5">
    
                <div class="d-flex flex-column justify-content-center align-items-center flex-grow-1">
    
    
    
                    <div class="contents-container">
    
                        <!-- 상단 인사말 및 안내 텍스트 -->
    
                        <div class="sections">

                          <!-- 상단 인사말 및 안내 텍스트 -->
                          <div class="section">
                            <h2>안녕하세요, <span class="bold">{{ userName }} 공인중개사님</span>!</h2>
    
                            <p>현재 참여 가능한 중개수수료 역경매 매물은 총 <span class="bold">{{ totalProperties }}</span>건 입니다.</p>
                          </div>


                          <!-- 집 보여주기 안내 및 버튼 -->
                          <div class="section">
                            <div class="show-house">
    
                                <p>입찰 대기 매물이 없으신가요? <br />

                                    <span>집 보여주기 신청</span>을 통해 중개수수료 경매권을 얻을 수 있습니다!

                                </p>

                                <button class="show-house-btn">집 보여주기</button>

                            </div>
                          </div>
    
                            
    
                        </div>

    
                        <!-- 3개의 주요 섹션: 입찰 대기 매물, 나의 입찰 매물, 낙찰 결과 -->
    
                        <div class="sections">
    
                            <!-- Section 1: 입찰 대기 매물 -->
    
                            <div class="section">
    
                                <div class="section-header">
    
                                  <span class="section-number">1</span>
    
                                  <h3>입찰 대기 매물</h3>
    
                                </div>
    
                                <div v-if="filteredInspectedAuctions.length > 0">
    
                                  <div v-for="auction in filteredInspectedAuctions" :key="auction.auctionPk">
    
                                      <div class="property-info row">

                                          <!-- 첫째줄 : 입찰버튼 -->
                                          <div class="d-flex justify-content-between">
                                            <img :src="'/assets/img/' + auction.prpImg" alt="매물 이미지" class="property-img">

                                            <div>

                                              <h4>{{ auction.prpName }}</h4>
                                              
                                              <p>{{ getPropertyTypeText(auction.prpTransType) }} {{ auction.prpPrice }}원</p>
                                              
                                            </div>
                                              <button class="bid-btn" @click="openModal(auction.prpPk, auction.auctionPk)">입찰</button>

                                          </div>
                                              
                                          <!-- 둘째줄 : 경매수수료계산버튼 -->
                                          <span class="d-flex justify-content-between">

                                            <div>
                                              <p>경매 시작일: {{ auction.auctionStart }}</p>
                                              
                                              <p>경매 종료일: {{ auction.auctionEnd }}</p>
                                            </div>        

                                              <div>
                                                <button class="estimate-fee-btn" @click="toggleAgentList(auction.auctionPk, auction.prpPk)">중개수수료 계산</button>
                                              </div>
        
                                          </span>
    
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
    
    
    
                            <!-- Section 2: 나의 입찰 매물 -->
    
                            <div class="section">
    
                                <div class="section-header">
    
                                    <span class="section-number">2</span>
    
                                    <h3>나의 입찰 매물</h3>
    
                                </div>
    
                                <div v-if="bidProperties.length > 0">
    
                                    <div v-for="property in bidProperties" :key="property.prpPk">

                                        <div class="property-info row">

                                          <!-- 첫째줄 -->
                                          <div class="d-flex justify-content-between">
                                            <img :src="'/assets/img/' + property.prpImg" alt="매물 이미지" class="property-img">


                                            <div>
                                              <h4>{{ property.prpName }}</h4>
                                              
                                              <p>매매 {{ property.prpPrice }}원</p>
                                              
                                              
                                              <p>나의 입찰 금액: <strong>{{ property.bidAmount || 'N/A' }}</strong></p>
                                              
                                              <p>가장 저렴한 입찰 금액: <strong>{{ property.lowestBid || 'N/A' }}</strong></p>
                                            
                                            </div>
                                          </div>   
                                            
                                        </div>
    
                                    </div>
    
                                </div>
    
                                <div v-else>
    
                                    <p>참여한 경매가 없습니다.</p>
    
                                </div>
    
                            </div>
    
    
    
                            <!-- Section 3: 낙찰 결과 -->
    
                            <div class="section">
    
                                <div class="section-header">
    
                                    <span class="section-number">3</span>
    
                                    <h3>낙찰 결과</h3>
    
                                </div>
    
                                <div class="tabs">
    
                                    <button class="tab-btn" :class="{ active: selectedTab === 'success' }" @click="selectedTab = 'success'">낙찰 성공</button>
    
                                    <button class="tab-btn" :class="{ active: selectedTab === 'failure' }" @click="selectedTab = 'failure'">낙찰 실패</button>
    
                                </div>
    
                                <div v-for="result in filteredResults" :key="result.auctionPk" class="property-card">
    
                                    <img :src="'/assets/img/' + result.prpImg" alt="매물 이미지" class="property-img">
    
                                    <div class="property-info">
    
                                        <h4>{{ result.prpName }}</h4>
    
                                        <p>매매 {{ result.prpPrice }}원</p>
    
                                        <p>경매 시작일: {{ result.auctionStart }}</p>
    
                                        <p>경매 종료일: {{ result.auctionEnd }}</p>
    
                                        <button class="estimate-fee-btn" @click="toggleAgentList(result.auctionPk, result.prpPk)">중개수수료 계산</button>
    
                                    </div>
    
                                </div>
    
                            </div>
    
                        </div>
    
    
    
                        <!-- 모달 창: 입찰 참여 -->
    
                        <div v-if="isModalVisible" class="modal" @click.self="closeModal">
    
                            <div class="modal-content">
    
                                <h4>중개수수료 입찰 참여</h4>
    
                                <p>예상 중개수수료: {{ feeEstimate?.brokerageFee || 'N/A' }}원</p>
    
                                <input type="number" v-model="inputFee" placeholder="중개수수료를 입력하세요!">
    
                                <button @click="submitFee">참여</button>
    
                                <button @click="closeModal">취소</button>
    
                            </div>
    
                        </div>
    
                        <div v-if="isModalVisible" class="overlay" @click="closeModal"></div>
    
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
    max-height: 250px;
    /* 화면의 80%를 넘지 않도록 설정 */
    overflow-y: auto;
    /* 내용이 넘칠 경우 스크롤 추가 */
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    /* 내용이 수직 중앙 정렬되도록 설정 */
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

---------------------- .container {
    max-width: 1280px;
    margin: 0 auto;
    padding: 20px;
}

.header {
    text-align: center;
    margin-bottom: 20px;
}

.bold {
    font-weight: bold;
}

.highlight {
    color: #007bff;
    font-weight: bold;
}

.show-house {
    text-align: center;
    background-color: #f8f9fa;
    padding: 20px;
    border-radius: 8px;
    margin-bottom: 30px;
}

.show-house-btn {
    background-color: #ff6b6b;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 8px;
    font-size: 16px;
    cursor: pointer;
}

.sections {
    display: flex;
    justify-content: space-between;
    gap: 20px;
    margin-bottom: 30px;
}

.section {
    flex: 1;
    background-color: #f8f9fa;
    padding: 20px;
    border-radius: 8px;
}

.section-header {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-bottom: 20px;
}

.section-number {
    background-color: #007bff;
    color: white;
    border-radius: 50%;
    padding: 10px;
    font-weight: bold;
}

.property-card {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    background-color: white;
    border-radius: 8px;
    padding: 15px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.property-img {
    width: 100px;
    height: 100px;
    border-radius: 8px;
    margin-right: 15px;
}

.property-info {
    font-size: 16px;
}

.estimate-fee-btn {
  display: inline-block;
    background-color: #007bff;
    color: white;
    border: none;
    padding: 8px 15px;
    border-radius: 8px;
    cursor: pointer;
    font-size: 14px;
    margin-top: 10px;
}

.bid-btn {
    display: inline-block;
    background-color: #ff6b6b;
    color: white;
    border: none;
    padding: 8px 15px;
    border-radius: 8px;
    cursor: pointer;
    font-size: 14px;
    margin-top: 10px;
}


.tabs {
    display: flex;
    justify-content: center;
    gap: 10px;
    margin-bottom: 20px;
}

.tab-btn {
    background-color: #e9e9e9;
    color: #333;
    padding: 8px 15px;
    border-radius: 8px;
    cursor: pointer;
    font-size: 14px;
}

.tab-btn.active {
    background-color: #007bff;
    color: white;
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
    width: 400px;
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
