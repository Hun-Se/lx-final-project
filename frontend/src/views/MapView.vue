<template>
  <component :is="computedHeader" id="headerPart" />

  <div id="mainPage">
    <div class="app-wrapper flex-column flex-row-fluid" id="kt_app_wrapper">
      <div
        id="kt_app"
        class="app-sidebar flex-column custom-sidebar"
        :class="{ open: isOpen }"
        :style="{
          position: 'fixed',
          top: '70px',
          left: isOpen ? '0' : '-300px',
          height: 'calc(100vh - 70px)',
          overflowY: 'hidden',
          width: '350px',
          transition: 'left 0.3s',
        }"
      >
        <div
          id="kt_app_sidebar_toggle"
          class="app-sidebar-toggle btn btn-icon btn-shadow btn-sm btn-color-muted btn-active-color-primary h-30px w-30px position-absolute top-50 end-0 translate-middle-y"
          @click="toggleSidebar"
        >
          <i class="ki-duotone ki-black-left-line fs-3" :class="{ 'rotate-180': isOpen }">
            <span class="path1"></span>
            <span class="path2"></span>
          </i>
        </div>

        <!-- 주택, 오피스텔 버튼 -->
        <div id="sort-options" class="sort-options">
          <div class="btn-group">
            <!-- 버튼 클릭 시 드롭다운 토글 -->
            <button
              type="button"
              class="btn btn-primary btn-sm dropdown-toggle"
              @click="toggleDropdown('dealTarget')"
              style="border: 1px solid blue; background-color: white; color: blue"
              onmouseover="this.style.color='white'; this.style.backgroundColor='blue';"
              onmouseout="this.style.color='blue'; this.style.backgroundColor='white';"
            >
              기타 | 주택 | 오피스텔
            </button>
            <ul
              class="dropdown-menu"
              v-show="dropdownState.isDealTargetDropdownOpen"
              style="display: flex; justify-content: space-between"
            >
              <li style="margin-left: 3ex">
                <a
                  href="#"
                  class="btn"
                  :class="filters.dealTarget === '기타' ? 'btn-primary' : 'btn-secondary'"
                  @click.prevent="updateDealTarget('기타')"
                  style="width: 10ex; margin-left: -2ex"
                >
                  기타
                </a>
              </li>
              <li style="margin-left: 3ex">
                <a
                  href="#"
                  class="btn"
                  :class="filters.dealTarget === '주택' ? 'btn-primary' : 'btn-secondary'"
                  @click.prevent="updateDealTarget('주택')"
                  style="width: 10ex"
                >
                  주택
                </a>
              </li>
              <li style="margin-left: 3ex">
                <a
                  href="#"
                  class="btn"
                  :class="
                    filters.dealTarget === '오피스텔' ? 'btn-primary' : 'btn-secondary'
                  "
                  @click.prevent="updateDealTarget('오피스텔')"
                  style="width: 14ex"
                >
                  오피스텔
                </a>
              </li>
            </ul>
          </div>

          <!-- 전세, 월세, 매매 버튼 -->
          <div class="btn-group" style="margin-left: 3ex">
            <!-- 버튼 클릭 시 드롭다운 토글 -->
            <button
              type="button"
              class="btn btn-primary btn-sm dropdown-toggle"
              @click="toggleDropdown('dealType')"
              style="border: 1px solid blue; background-color: white; color: blue"
              onmouseover="this.style.color='white'; this.style.backgroundColor='blue';"
              onmouseout="this.style.color='blue'; this.style.backgroundColor='white';"
            >
              월세 | 전세 | 매매
            </button>
            <ul class="dropdown-menu" v-show="dropdownState.isDealTypeDropdownOpen">
              <ul
                style="
                  display: flex;
                  list-style-type: none;
                  padding: 0;
                  margin-bottom: 1ex;
                "
              >
                <li style="margin-right: 10px">
                  <button
                    type="button"
                    class="btn"
                    :class="activeInput === 'jeonse' ? 'btn-primary' : 'btn-secondary'"
                    @click="updateDealType('jeonse')"
                  >
                    전세
                  </button>
                </li>
                <li style="margin-right: 10px">
                  <button
                    type="button"
                    class="btn"
                    :class="activeInput === 'wolse' ? 'btn-primary' : 'btn-secondary'"
                    @click="updateDealType('wolse')"
                  >
                    월세
                  </button>
                </li>
                <li style="margin-right: 10px">
                  <button
                    type="button"
                    class="btn"
                    :class="activeInput === 'maemae' ? 'btn-primary' : 'btn-secondary'"
                    @click="updateDealType('maemae')"
                  >
                    매매
                  </button>
                </li>
              </ul>

              <!-- 전세금, 월세, 매매금액 입력 -->
              <div v-if="activeInput === 'jeonse' || activeInput === 'all'">
                <nav class="navbar navbar-light bg-light">
                  <form class="form-inline">
                    <h3 style="margin-left: 3ex; margin-top: 3ex">전세금</h3>
                    <input
                      v-model="filters.jeonse.min"
                      style="margin-left: 3ex; width: 15ex"
                      placeholder="최소값"
                    />
                    ~
                    <input
                      v-model="filters.jeonse.max"
                      style="width: 15ex"
                      placeholder="최대값"
                    />
                  </form>
                </nav>
              </div>
              <div v-if="activeInput === 'wolse' || activeInput === 'all'">
                <nav class="navbar navbar-light bg-light">
                  <form class="form-inline">
                    <h3 style="margin-left: 3ex; margin-top: 3ex">보증금</h3>
                    <input
                      v-model="filters.wolse.min"
                      style="margin-left: 3ex; width: 15ex"
                      placeholder="최소값"
                    />
                    ~
                    <input
                      v-model="filters.wolse.max"
                      style="width: 15ex"
                      placeholder="최대값"
                    />
                  </form>
                </nav>
              </div>
              <div v-if="activeInput === 'maemae' || activeInput === 'all'">
                <nav class="navbar navbar-light bg-light">
                  <form class="form-inline">
                    <h3 style="margin-left: 3ex; margin-top: 3ex">매매 금액</h3>
                    <input
                      v-model="filters.maemae.min"
                      style="margin-left: 3ex; width: 15ex"
                      placeholder="최소값"
                    />
                    ~
                    <input
                      v-model="filters.maemae.max"
                      style="width: 15ex"
                      placeholder="최대값"
                    />
                  </form>
                </nav>
              </div>
            </ul>
          </div>

          <!-- 구조 버튼-->
          <div class="btn-group" style="margin-top: 2ex">
            <button
              type="button"
              class="btn btn-primary btn-sm dropdown-toggle"
              @click="toggleDropdown('structure')"
              style="border: 1px solid blue; background-color: white; color: blue"
              onmouseover="this.style.color='white'; this.style.backgroundColor='blue';"
              onmouseout="this.style.color='blue'; this.style.backgroundColor='white';"
            >
              구조
            </button>
            <ul class="dropdown-menu" v-show="dropdownState.isStructureDropdownOpen">
              <li style="display: inline; margin-left: 3ex">
                <button
                  type="button"
                  class="btn"
                  :class="filters.structure === '전체' ? 'btn-primary' : 'btn-secondary'"
                  @click="updateStructure('전체')"
                  style="width: 10ex"
                >
                  전체
                </button>
              </li>
              <li style="display: inline; margin-left: 2ex">
                <button
                  type="button"
                  class="btn"
                  :class="filters.structure === '복층' ? 'btn-primary' : 'btn-secondary'"
                  @click="updateStructure('복층')"
                  style="width: 10ex"
                >
                  복층
                </button>
              </li>
              <li style="display: inline; margin-left: 2ex">
                <button
                  type="button"
                  class="btn"
                  :class="
                    filters.structure === '오픈방' ? 'btn-primary' : 'btn-secondary'
                  "
                  @click="updateStructure('오픈방')"
                  style="width: 13ex"
                >
                  오픈방
                </button>
              </li>
              <li style="display: inline; margin-left: 3ex">
                <button
                  type="button"
                  class="btn"
                  :class="
                    filters.structure === '분리형' ? 'btn-primary' : 'btn-secondary'
                  "
                  @click="updateStructure('분리형')"
                  style="width: 13ex; margin-top: 1ex"
                >
                  분리형
                </button>
              </li>
            </ul>
          </div>

          <!-- 층수 버튼-->
          <div class="btn-group" style="margin-left: 1ex; margin-top: 2ex">
            <button
              type="button"
              class="btn btn-primary btn-sm dropdown-toggle"
              @click="toggleDropdown('floor')"
              style="border: 1px solid blue; background-color: white; color: blue"
              onmouseover="this.style.color='white'; this.style.backgroundColor='blue';"
              onmouseout="this.style.color='blue'; this.style.backgroundColor='white';"
            >
              층수
            </button>
            <ul class="dropdown-menu" v-show="dropdownState.isFloorDropdownOpen">
              <li style="display: inline; margin-left: 3ex">
                <button
                  type="button"
                  class="btn"
                  :class="filters.floor === '전체' ? 'btn-primary' : 'btn-secondary'"
                  @click="updateFloor('전체')"
                  style="width: 10ex"
                >
                  전체
                </button>
              </li>
              <li style="display: inline; margin-left: 1ex">
                <button
                  type="button"
                  class="btn"
                  :class="filters.floor === '옥탑방' ? 'btn-primary' : 'btn-secondary'"
                  @click="updateFloor('옥탑방')"
                  style="width: 13ex"
                >
                  옥탑방
                </button>
              </li>
              <li style="display: inline; margin-left: 1ex">
                <button
                  type="button"
                  class="btn"
                  :class="filters.floor === '반지하층' ? 'btn-primary' : 'btn-secondary'"
                  @click="updateFloor('반지하층')"
                  style="width: 13ex"
                >
                  반지하층
                </button>
              </li>
              <li style="display: inline; margin-left: 3ex">
                <button
                  type="button"
                  style="width: 13ex; margin-top: 1ex"
                  class="btn"
                  :class="filters.floor === '지상층' ? 'btn-primary' : 'btn-secondary'"
                  @click="updateFloor('지상층')"
                >
                  지상층
                </button>
              </li>
            </ul>
          </div>

          <!-- 전용 면적 버튼과 모달 버튼을 포함한 그룹 -->
          <div class="btn-group" style="margin-left: 1ex; margin-top: 2ex; height: 5ex">
            <!-- 전용 면적 버튼 -->
            <button
              type="button"
              class="btn btn-primary btn-sm dropdown-toggle"
              @click="toggleDropdown('area')"
              style="border: 1px solid blue; background-color: white; color: blue"
              onmouseover="this.style.color='white'; this.style.backgroundColor='blue';"
              onmouseout="this.style.color='blue'; this.style.backgroundColor='white';"
            >
              전용 면적
            </button>
            <ul class="dropdown-menu" v-show="dropdownState.isAreaDropdownOpen">
              <table
                class="table table-bordered text-center"
                style="margin-left: 3ex; width: 40ex; margin-top: 2ex"
              >
                <tbody>
                  <tr>
                    <td class="table-primary" style="vertical-align: middle">
                      <button
                        type="button"
                        @click="updateArea('전체')"
                        class="btn btn-link text-black"
                      >
                        전체
                      </button>
                    </td>
                    <td>
                      <button
                        type="button"
                        @click="updateArea('10평 이하')"
                        class="btn btn-link"
                      >
                        10평 이하
                      </button>
                    </td>
                    <td>
                      <button
                        type="button"
                        @click="updateArea('10평대')"
                        class="btn btn-link"
                      >
                        10평대
                      </button>
                    </td>
                    <td>
                      <button
                        type="button"
                        @click="updateArea('20평대')"
                        class="btn btn-link"
                      >
                        20평대
                      </button>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <button
                        type="button"
                        @click="updateArea('30평대')"
                        class="btn btn-link"
                      >
                        30평대
                      </button>
                    </td>
                    <td>
                      <button
                        type="button"
                        @click="updateArea('40평대')"
                        class="btn btn-link"
                      >
                        40평대
                      </button>
                    </td>
                    <td>
                      <button
                        type="button"
                        @click="updateArea('50평대')"
                        class="btn btn-link"
                      >
                        50평대
                      </button>
                    </td>
                    <td>
                      <button
                        type="button"
                        @click="updateArea('60평 이상')"
                        class="btn btn-link"
                      >
                        60평 이상
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </ul>

            <!-- 모달 버튼 -->
            <button
              type="button"
              class="btn btn-primary"
              @click="openModal"
              style="
                margin-left: 1ex;
                margin-top: -6px;
                width: 70px;
                height: 45px;
                border-radius: 10%;
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: #007bff;
                border-color: #007bff;
                color: white;
              "
            >
              <i class="bi bi-robot" style="font-size: 3ex; color: white"></i>
              <!-- 아이콘 색상을 흰색으로 설정 -->
            </button>
          </div>
        </div>

        <div
          class="app-sidebar-menu overflow-hidden flex-column-fluid"
          style="
            margin-top: 2ex;
            margin-left: 1.5ex;
            height: calc(100vh - 100px);
            display: flex;
          "
        >
          <!-- 매물 리스트 사이드바 -->
          <div
            class="app-sidebar-wrapper"
            style="
              flex: 1;
              overflow-y: hidden;
              height: 100%;
              display: flex;
              flex-direction: column;
            "
          >
            <div class="property-container" style="flex: 1">
              <div
                class="property-list"
                style="overflow-y: auto; min-height: 300vh; flex: 1"
              >
                <ul class="property-items">
                  <li
                    v-for="(item, index) in sales"
                    :key="item.prpPk"
                    class="property-item"
                    @click="toggleSalesDetail(item.prpPk)"
                    style="position: relative"
                  >
                    <img
                      :src="'/assets/img/' + item.prpImg"
                      alt="매물 이미지"
                      class="property-image"
                      style="width: 250px; height: 237px"
                    />

                    <!-- 이미지 위에 반투명 텍스트 -->
                    <div
                      style="
                        position: absolute;
                        top: 15px;
                        left: 15px;
                        background-color: rgba(0, 0, 0, 0.3);
                        color: rgba(255, 255, 255, 0.7);
                        padding: 5px 10px;
                        font-weight: bold;
                        font-size: xx-large;
                        width: 250px;
                        height: 237px;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                      "
                    >
                      믿음집
                    </div>
                    <div
                      style="
                        display: flex; /* 플렉스 컨테이너로 설정 */
                        align-items: center; /* 수직 정렬 */
                        gap: 1ex; /* 버튼 간 간격 */
                      "
                    >
                      <button
                        type="button"
                        class="btn btn-primary btn-sm"
                        style="
                          font-size: smaller;
                          border: 1px solid #0d6efd;
                          background-color: white;
                          color: #0d6efd;
                          padding: 5px;
                          width: 50ex;
                          border-radius: 4px;
                          text-align: center; /* 텍스트 정렬 */
                        "
                        onmouseover="this.style.color='white'; this.style.backgroundColor='#0d6efd';"
                        onmouseout="this.style.color='#0d6efd'; this.style.backgroundColor='white';"
                        onmousedown="this.style.color='white'; this.style.backgroundColor='#0d6efd';"
                        onmouseup="this.style.color='#0d6efd'; this.style.backgroundColor='white';"
                      >
                        <i></i>상세보기
                      </button>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>

          <!-- 상세 정보 사이드바 -->
          <div
            v-if="selectedSalesId"
            class="app-sidebar-detail"
            style="
              position: fixed;
              right: 0;
              top: 60px;
              height: calc(100vh - 60px);
              width: 300px;
              padding: 1em;
              overflow-y: auto;
              background: white;
              border-left: 1px solid #ddd;
            "
          >
            <!-- btn-close 버튼 -->
            <button
              type="button"
              class="btn-close"
              style="
                position: absolute;
                top: 10px;
                right: 10px;
                background-color: white;
                border-radius: 30%;
                border: 1px solid #ddd;
              "
              @click="toggleSalesDetail(null)"
            ></button>

            <!-- AI Chat -->
            <div
              v-if="isModalOpen"
              class="modal-content"
              style="
                position: relative;
                top: 0;
                left: 0;
                max-width: 330px;
                width: 100%;
                margin-top: 3ex;
                margin-left: 0.5ex;
                border: 1px solid #007bff;
                border-radius: 0.5rem;
                padding: 1rem;
              "
            >
              <div class="modal-header">
                <h1
                  class="modal-title fs-5"
                  style="margin-left: 1ex; font-weight: bolder"
                >
                  AI Chat 서비스
                </h1>
              </div>
              <div class="modal-body">
                <!-- 모달 내용 -->
              </div>
              <div
                class="input-group mb-3"
                style="max-width: 300px; margin-left: 0.5ex; margin-top: 1ex"
              >
                <input
                  type="text"
                  name="AiGPT"
                  placeholder="원하시는 매물 조건을 입력해주세요."
                  class="form-control"
                />
                <button @click="submitAiGPT" class="btn btn-primary btn-sm">문의</button>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" @click="closeModal">
                  닫기
                </button>
              </div>
            </div>

            <!-- 매물 상세정보 -->
            <div
              class="property-details-section"
              v-if="selectedSalesDetails"
              style="flex: 1; overflow-y: auto; height: 100%; padding-left: 20px"
            >
            <p
              style="
                margin-top: 3ex;
                margin-bottom: 1ex;
                font-size: medium;
                font-weight: bolder;
                color: darkgray;
                display: flex;
                align-items: center; /* 모든 아이템을 세로로 가운데 정렬 */
              "
            >
              <strong style="color: black">매물</strong>
              {{ selectedSalesDetails.prpPrice }}

              <!-- 버튼과 아이콘들을 나란히 배치 -->
              <div
                style="
                  display: flex;
                  align-items: center; /* 버튼과 아이콘을 세로로 정렬 */
                  margin-left: 1ex; /* 왼쪽 여백을 주어 간격을 조정 */
                "
              >
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

                <!-- 아이콘들 나란히 배치 -->
                <i
                  style="color: black; margin-left: 1ex; font-size: large"
                  class="bi bi-share"
                ></i>
                <i
                  style="color: black; margin-left: 1ex; font-size: large"
                  class="bi bi-heart-fill"
                ></i>
              </div>
            </p>

              <img
                :src="'/assets/img/' + selectedSalesDetails.prpImg"
                alt="매물 이미지"
                class="property-image"
                style="width: 100%; height: auto"
              />
              <div
                class="detail-header"
                style="
                  font-size: small;
                  font-weight: bolder;
                  margin-top: 1ex;
                  margin-bottom: 1ex;
                  border: 1px solid #ccc;
                  padding: 5px;
                  display: inline-block;
                  border-radius: 6px;
                  border-width: 2px;
                "
              >
                매물번호{{ selectedSalesDetails.prpPrice }}
              </div>

              <p style="margin-top: 2ex; font-size: large; font-weight: bolder">
                <strong style="font-weight: bolder">가격</strong>
                {{ selectedSalesDetails.prpPrice }}
              </p>
              <p style="margin-top: 1ex; font-size: medium; margin-top: 2ex">
                {{ selectedSalesDetails.prpDesc }}
              </p>
              <p style="margin-top: 4ex">{{ selectedSalesDetails.prpName }}</p>
              <p style="margin-top: 2ex">
                <strong style="font-weight: bolder"
                  ><i class="bi bi-pin-map-fill"></i
                ></strong>
                {{ selectedSalesDetails.prpAddrDetail }}
              </p>
              <p style="margin-top: 2ex">
                <strong style="font-weight: bolder"><i class="bi bi-rulers"></i></strong>
                {{ selectedSalesDetails.prpExclArea }}
              </p>

              <button
                type="button"
                class="btn btn-primary btn-sm"
                style="
                  border: 1px solid #0d6efd;
                  background-color: white;
                  color: #0d6efd;
                  margin-top: 3ex;
                  width: 40ex;
                "
                onmouseover="this.style.color='white'; this.style.backgroundColor=' #0d6efd';"
                onmouseout="this.style.color=' #0d6efd'; this.style.backgroundColor='white';"
              >
                <i
                  class="bi bi-cash-coin"
                  onmouseover="this.style.color='white'; this.style.backgroundColor=' #0d6efd';"
                  onmouseout="this.style.color=' #0d6efd'; this.style.backgroundColor='white';"
                ></i
                >실거래가 보러가기
                <i
                  class="bi bi-arrow-right"
                  onmouseover="this.style.color='white'; this.style.backgroundColor=' #0d6efd';"
                  onmouseout="this.style.color=' #0d6efd'; this.style.backgroundColor='white';"
                ></i>
              </button>

              <div style="margin-top: 3ex; display: flex; align-items: center">
                <i
                  class="bi bi-telephone-fill"
                  style="
                    font-size: 22px;
                    background-color: white;
                    color: #0d6efd;
                    padding: 12px;
                    border: 2px solid #0d6efd;
                    display: inline-block;
                  "
                ></i>
                <i
                  class="bi bi-chat-fill"
                  style="
                    font-size: 22px;
                    margin-left: 1ex;
                    background-color: white;
                    color: #0d6efd;
                    padding: 12px;
                    border: 2px solid #0d6efd;
                    display: inline-block;
                  "
                ></i>
                <button
                  type="button"
                  class="btn btn-primary"
                  style="
                    width: 14ex;
                    margin-left: 1ex;
                    font-size: 19px;
                    padding: 10px 20px;
                    border-radius: 0;
                    line-height: 2;
                  "
                >
                  채팅하기
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div id="modalBackground">
      <!-- showCesium이 true일 때만 CesiumMap을 렌더링 -->
      <CesiumMap2 v-if="showCesium" />

      <!-- showCesium이 false일 때만 NaverMap을 렌더링 -->
      <NaverMap v-else />
    </div>
  </div>
</template>

<script setup>
import { ref, computed, reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useSaleStore } from "@/stores/property.js";
import NaverMap from "@/components/NaverMap.vue";
import { storeToRefs } from "pinia";
import Header2 from "@/components/Header2.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import axios from "axios";
import CesiumMap2 from "@/components/CesiumMap2.vue";

//*********cesium map*********
const showCesium = ref(false);

// goToCesium 메소드는 showCesium 값을 토글
const goToCesium = () => {
  showCesium.value = !showCesium.value;
};

// 매물지도 페이지로 이동
function goToMap() {
  router.push({ path: "/map" });
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

// 선택된 매물 ID 및 상세 정보
const selectedSalesId = ref(null);

// 매물 상세 정보 토글 함수 (Axios 사용)
async function toggleSalesDetail(prpPk) {
  if (prpPk) {
    selectedSalesId.value = prpPk;

    try {
      // 서버로부터 매물 상세 정보를 가져옴
      const response = await axios.get(`/api/properties/${prpPk}`);

      // 서버로부터 받은 데이터를 selectedSalesDetails에 저장
      selectedSalesDetails.value = response.data;
    } catch (error) {
      console.error("매물 상세 정보를 가져오는 데 실패했습니다:", error);
      selectedSalesDetails.value = {}; // 오류 발생 시 빈 객체 할당
    }
  } else {
    // 매물 상세보기를 닫을 때
    selectedSalesId.value = null;
    selectedSalesDetails.value = {};
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

// AI GPT 제출
const submitAiGPT = () => {
  // 문의 로직 구현
  console.log("AI GPT 문의 제출");
};
</script>

<style scoped>
.dropdown-menu {
  padding: 10px;
  border: 1px solid #ccc;
  display: block;
  position: absolute;
  top: 100%;
  left: 0;
  width: 340px;
  z-index: 9999;
}

.dropdown-menu.show {
  display: block;
  z-index: 9999;
}

/*===== begin::헤더,메인,푸터 기본화면 =====*/
#mainPage {
  height: 100vh;
  overflow-y: auto;
}

#headerPart {
  position: sticky;
  top: 0;
  z-index: 1000;
  background-color: #fff;
  height: 75px;
  border-bottom: 1px solid #ccc;
}

/* 구분선, 스크롤 */
.property-list {
  max-height: 650px;
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 10px;
  width: 320px;
  display: flex;
  flex-direction: column;
}

.property-detail {
  padding: 20px;
  background-color: #f5f5f5;
  width: 100%;
  max-width: 600px;
  margin-left: 30px;
}

.divider {
  height: 1px;
  background-color: #ccc;
  margin: 10px 0;
}

/*사이드바*/
.custom-sidebar {
  width: 0; /* Start closed */
  transition: width 0.3s ease;
}

.custom-sidebar.open {
  width: 350px;
}

.custom-sidebar:not(.open) {
  width: 23px;
  right: -10px;
}

.app-sidebar-content {
  opacity: 1;
  /* 열림 상태에서 콘텐츠 표시 */
  visibility: visible;
}

.app-sidebar-content.hidden-content {
  opacity: 0;
  /* 닫힘 상태에서 콘텐츠 숨김 */
  visibility: hidden;
}

.ki-black-left-line {
  transition: transform 0.3s ease;
  /* 아이콘 애니메이션 */
}

.ki-black-left-line.rotate-180 {
  transform: rotate(180deg);
  /* 아이콘 회전 */
}

#kt_app_sidebar_toggle {
  position: fixed;
  top: 50%;
  right: 0;
  transform: translateY(-50%);
  z-index: 1000;
  /* 사이드바 위에 표시 */
}

.property-item {
  border: 2px solid rgb(224, 224, 224);
  padding: 16px;
  margin: 10px 0;
  background-color: #fff;
}

/* 정렬 옵션 스타일 */
.sort-options {
  background-color: lightgray;
  padding: 10px;
  font-weight: 800;
  display: flex;
  flex-direction: column;
}

.detail-header {
  font-size: 1.5em; /* 제목 크기 */
  font-weight: bold; /* 굵게 */
  margin: 1em 0; /* 위 아래 여백 */
  color: #333; /* 텍스트 색상 */
  border-bottom: 1px solid #ddd; /* 하단 경계선 추가 */
  padding-bottom: 0.5em; /* 경계선과 텍스트 사이 여백 */
}

/* 필터 테이블 */
.table-bordered {
  border: 1px solid #000;
}

.table-bordered td,
.table-bordered th {
  border: 1px solid #000;
}

.divider {
  border-bottom: 1px solid #ccc;
  margin: 1ex 0;
}

#footerPart {
  position: sticky;
  bottom: 0;
}

/* 사이드바가 접혔을 때 옵션들 숨김 */
#kt_app:not(.open) #sort-options,
#kt_app:not(.open) .form-inline {
  display: none;
}

/* 사이드바가 펼쳐졌을 때 옵션들 표시 */
#kt_app.open #sort-options,
#kt_app.open .form-inline {
  display: block;
}

#kt_app {
  position: absolute;
  top: 75px;
  left: 0;
  right: 0;
  bottom: 0;
}

/* 전체 사이드바 구조 */
.app-sidebar-menu {
  display: flex; /* 두 개의 사이드바를 나란히 배치 */
}

.app-sidebar-detail {
  position: fixed; /* 사이드바를 화면에 고정 */
  left: 350px !important; /* 오른쪽에 배치 */
  top: 75px !important; /* 헤더의 높이만큼 아래로 배치 */
  width: 320px !important; /* 사이드바 너비 */
  padding: 1em; /* 내부 여백 */
  background: white; /* 배경색 */
  border-left: 1px solid #ddd; /* 왼쪽 경계선 */
}

/* 매물 리스트 스타일 */
.property-items {
  list-style-type: none; /* 리스트 스타일 제거 */
  padding: 0; /* 패딩 제거 */
}

.property-item {
  border: 2px solid rgb(224, 224, 224); /* 매물 아이템 테두리 */
  padding: 16px; /* 패딩 */
  margin: 10px 0; /* 여백 */
  background-color: #fff; /* 배경색 */
}

.property-item:hover {
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); /* 호버 시 그림자 효과 */
}

.property-details {
  margin-left: 10px; /* 내용 여백 */
}
</style>
