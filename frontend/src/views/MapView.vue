<template>
  <div id="mainPage">
    <div class="app-wrapper flex-column flex-row-fluid" id="kt_app_wrapper">
      <!--begin::사이드바-->
      <div
        id="kt_app_sidebar"
        class="app-sidebar flex-column custom-sidebar"
        :class="{ open: isOpen }"
      >
        <div
          id="kt_app_sidebar_toggle"
          class="app-sidebar-toggle btn btn-icon btn-shadow btn-sm btn-color-muted btn-active-color-primary h-30px w-30px position-absolute top-50 end-0 translate-middle-y"
          @click="toggleSidebar"
        >
          <i
            class="ki-duotone ki-black-left-line fs-3"
            :class="{ 'rotate-180': isOpen }"
          >
            <span class="path1"></span>
            <span class="path2"></span>
          </i>
        </div>

        <!--begin::로고이미지 및 검색(PC)-->
        <div
          class="app-sidebar-logo px-6 d-flex align-items-center"
          id="kt_app_sidebar_logo"
        >
          <span class="me-3" @click="goHome()">
            <img
              alt="Logo"
              src="/assets/media/framework-logos/go.png"
              class="h-25px app-sidebar-logo-default"
            />
            <img
              alt="Logo"
              src="/assets/media/framework-logos/go.png"
              class="h-20px app-sidebar-logo-minimize"
            />
          </span>

          <!-- 사이드바가 열릴 때만 보이는 검색창 -->
          <form v-if="isOpen" class="form-inline flex-grow-1 me-3">
            <div class="input-group" style="width: 220px">
              <input
                class="form-control"
                type="search"
                placeholder="매물을 검색하세요."
                aria-label="Search"
              />
              <button class="btn btn-outline-primary" type="submit">
                <i class="bi bi-search"></i>
              </button>
            </div>
          </form>

          <!--프로필-->
          <div class="profile">
            <img
              :src="user.user_image"
              alt="사용지 이미지"
              class="profile-image"
            />
          </div>
        </div>

        <!-- 최신순, 가격순, 면적순 링크를 사이드바가 열릴 때만 보이게 -->
        <div
          v-if="isOpen"
          id="sort-options"
          style="background-color: lightgray; padding: 10px; font-weight: 800"
        >
          <a
            id="sort-latest"
            style="margin-left: 2ex; cursor: pointer; font-weight: 800"
            >최신순</a
          >
          |
          <a id="sort-price" style="cursor: pointer; font-weight: 800"
            >가격순</a
          >
          |
          <a id="sort-area" style="cursor: pointer; font-weight: 800">면적순</a>

          <button
            type="button"
            class="btn btn-secondary"
            @click="showModal = true"
            style="margin-left: 8ex"
          >
            필터
          </button>

          <!-- Modal -->
          <div
            v-if="showModal"
            class="modal fade show"
            tabindex="-1"
            role="dialog"
            style="display: block; background: rgba(0, 0, 0, 0.5)"
          >
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">필터</h5>
                  <button
                    type="button"
                    class="close"
                    @click="showModal = false"
                    aria-label="Close"
                  >
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>

                <div class="modal-body">
                  <!-- 거래 대상 -->
                  <nav class="navbar navbar-light bg-light">
                    <form class="form-inline">
                      <h3 style="margin-left: 3ex; margin-top: 3ex">
                        거래 대상
                      </h3>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.dealTarget === '기타'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateDealTarget('기타')"
                        style="width: 10ex; margin-left: 3ex"
                      >
                        기타
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.dealTarget === '주택'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateDealTarget('주택')"
                        style="width: 10ex; margin-left: 3ex"
                      >
                        주택
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.dealTarget === '오피스텔'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateDealTarget('오피스텔')"
                        style="width: 14ex; margin-left: 3ex"
                      >
                        오피스텔
                      </button>
                    </form>
                  </nav>

                  <!-- 거래 유형 -->
                  <nav class="navbar navbar-light bg-light">
                    <form class="form-inline">
                      <h3 style="margin-left: 3ex; margin-top: 3ex">
                        거래 유형
                      </h3>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          activeInput === 'all'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateDealType('all')"
                        style="width: 10ex; margin-left: 3ex"
                      >
                        전체
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          activeInput === 'jeonse'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateDealType('jeonse')"
                        style="width: 10ex; margin-left: 2ex"
                      >
                        전세
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          activeInput === 'wolse'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateDealType('wolse')"
                        style="width: 10ex; margin-left: 2ex"
                      >
                        월세
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          activeInput === 'maemae'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateDealType('maemae')"
                        style="width: 10ex; margin-left: 2ex"
                      >
                        매매
                      </button>
                    </form>
                  </nav>

                  <!-- 전세금, 월세, 매매금액 입력 -->
                  <div v-if="activeInput === 'jeonse' || activeInput === 'all'">
                    <nav class="navbar navbar-light bg-light">
                      <form class="form-inline">
                        <h3 style="margin-left: 3ex; margin-top: 3ex">
                          전세금
                        </h3>
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
                        <h3 style="margin-left: 3ex; margin-top: 3ex">
                          보증금
                        </h3>
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
                        <h3 style="margin-left: 3ex; margin-top: 3ex">
                          매매 금액
                        </h3>
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

                  <!-- 구조 선택 -->
                  <nav class="navbar navbar-light bg-light">
                    <form class="form-inline">
                      <h3 style="margin-left: 3ex; margin-top: 3ex">구조</h3>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.structure === '전체'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateStructure('전체')"
                        style="width: 10ex; margin-left: 3ex"
                      >
                        전체
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.structure === '복층'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateStructure('복층')"
                        style="width: 10ex; margin-left: 2ex"
                      >
                        복층
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.structure === '오픈방'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateStructure('오픈방')"
                        style="width: 13ex; margin-left: 2ex"
                      >
                        오픈방
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.structure === '분리형'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateStructure('분리형')"
                        style="width: 13ex; margin-left: 2ex"
                      >
                        분리형
                      </button>
                    </form>
                  </nav>

                  <!-- 층수 선택 -->
                  <nav class="navbar navbar-light bg-light">
                    <form class="form-inline">
                      <h3 style="margin-left: 3ex; margin-top: 3ex">층수</h3>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.floor === '전체'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateFloor('전체')"
                        style="width: 10ex; margin-left: 3ex"
                      >
                        전체
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.floor === '옥탑방'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateFloor('옥탑방')"
                        style="width: 13ex; margin-left: 1ex"
                      >
                        옥탑방
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.floor === '반지하층'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateFloor('반지하층')"
                        style="width: 13ex; margin-left: 1ex"
                      >
                        반지하층
                      </button>
                      <button
                        type="button"
                        class="btn"
                        :class="
                          filters.floor === '지상층'
                            ? 'btn-primary'
                            : 'btn-secondary'
                        "
                        @click="updateFloor('지상층')"
                        style="width: 13ex; margin-left: 1ex"
                      >
                        지상층
                      </button>
                    </form>
                  </nav>

                  <!-- 전용 면적 선택 -->
                  <nav class="navbar navbar-light bg-light">
                    <form class="form-inline">
                      <h3 style="margin-left: 3ex; margin-top: 3ex">
                        전용 면적
                      </h3>
                      <table
                        class="table table-bordered text-center"
                        style="margin-left: 3ex"
                      >
                        <tbody>
                          <tr>
                            <td
                              class="table-primary"
                              style="vertical-align: middle"
                            >
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
                    </form>
                  </nav>
                </div>

                <div class="modal-footer">
                  <button
                    type="button"
                    class="btn btn-secondary"
                    @click="closeModal"
                  >
                    닫기
                  </button>
                  <button
                    type="button"
                    class="btn btn-primary"
                    @click="submitFilters"
                  >
                    결과 보기
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--end::로고이미지 및 검색-->

        <!--begin::사이드바 메뉴-->
        <div class="app-sidebar-menu overflow-hidden flex-column-fluid">
          <div id="kt_app_sidebar_menu_wrapper" class="app-sidebar-wrapper">
            <div
              id="kt_app_sidebar_menu_scroll"
              class="scroll-y my-5 mx-3"
              style="width: 600px"
              data-kt-scroll="true"
              data-kt-scroll-activate="true"
              data-kt-scroll-height="auto"
              data-kt-scroll-dependencies="#kt_app_sidebar_logo, #kt_app_sidebar_footer"
              data-kt-scroll-wrappers="#kt_app_sidebar_menu"
              data-kt-scroll-offset="5px"
              data-kt-scroll-save-state="true"
            >
              <div class="property-container">
                <!-- 매물 리스트 -->
                <div v-if="!selectedSalesId" class="property-list">
                  <ul class="property-items">
                    <li
                      v-for="(item, index) in sales"
                      :key="item.prpPk"
                      class="property-item"
                      @click="toggleSalesDetail(item.prpPk)"
                    >
                      <img
                        :src="'/assets/img/' + item.prpImg"
                        alt="매물 이미지"
                        class="property-image"
                        :style="{ width: '250px', height: 'auto' }"
                      />
                      <div class="property-details">
                        <p
                          class="property-name"
                          style="
                            font-size: 2ex;
                            font-weight: 800;
                            margin-top: 1ex;
                          "
                        >
                          {{ item.prpName }}
                        </p>
                        <p class="property-price">{{ item.prpPrice }}</p>
                        <p class="property-content">{{ item.prpDesc }}</p>
                      </div>
                    </li>
                  </ul>
                </div>

                <!-- 매물 상세 정보 -->
                <div v-if="selectedSalesId" class="property-detail">
                  <img
                    :src="'/assets/img/' + selectedSalesDetails.prpImg"
                    alt="매물 이미지"
                    class="property-image"
                    :style="{ width: '290px', height: 'auto' }"
                  />
                  <h3 style="margin-top: 2ex"><strong>상세 정보</strong></h3>
                  <p>{{ selectedSalesDetails.prpName }}</p>
                  <p>{{ selectedSalesDetails.prpPrice }}</p>
                  <p>{{ selectedSalesDetails.prpExclArea }}</p>
                  <p>{{ selectedSalesDetails.prpDesc }}</p>
                  <p>{{ selectedSalesDetails.prpDesc }}</p>
                  <p>{{ selectedSalesDetails.prpAddrDetail }}</p>

                  <button
                    type="button"
                    class="btn btn-outline-secondary btn-sm"
                    style="border-radius: 10px; margin-left: 18ex"
                    @click="toggleSalesDetail(null)"
                  >
                    v
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--end::사이드바 메뉴-->
      </div>
      <!--end::사이드바-->
    </div>
    <NaverMap></NaverMap>
  </div>
</template>

<script setup>
import { ref, computed, reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useSaleStore } from "@/stores/sales";
import NaverMap from "@/components/NaverMap.vue";
import { storeToRefs } from "pinia";

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

const goHome = () => {
  router.push({ path: "/" });
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

// 거래 대상 업데이트
const updateDealTarget = (target) => {
  filters.dealTarget = target;
};

// 거래 유형 업데이트
const updateDealType = (type) => {
  activeInput.value = type;
  filters.dealType = type;
};

// 구조 업데이트
const updateStructure = (structure) => {
  filters.structure = structure;
};

// 층수 업데이트
const updateFloor = (floor) => {
  filters.floor = floor;
};

// 전용 면적 업데이트
const updateArea = (area) => {
  filters.area = area;
};

// 모달 닫기
const closeModal = () => {
  // 모달 닫기 로직 구현
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

// 매물 클릭 시 상세 정보 토글
function toggleSalesDetail(salesId) {
  selectedSalesId.value = salesId;
  store.fetchSalesDetails(salesId);
}

//프로필 불러오기
const user = ref({
  user_id: 1,
  user_image: "/assets/img/user01.png",
});

// 모달 열림 상태 관리
const showModal = ref(false);
</script>

<style scoped>
/*===== begin::헤더,메인,푸터 기본화면 =====*/

#mainPage {
  width: 100%;
  height: 100%;
}

#headerPart {
  position: sticky;
  top: 0;
}

#footerPart {
  position: sticky;
  bottom: 0;
}

/*===== end::헤더,메인,푸터 기본화면 =====*/

/* 글씨체 */
.gothic-a1-thin {
  font-family: "Gothic A1", sans-serif;
  font-weight: 100;
  font-style: normal;
}

.gothic-a1-extralight {
  font-family: "Gothic A1", sans-serif;
  font-weight: 200;
  font-style: normal;
}

.gothic-a1-light {
  font-family: "Gothic A1", sans-serif;
  font-weight: 300;
  font-style: normal;
}

.gothic-a1-regular {
  font-family: "Gothic A1", sans-serif;
  font-weight: 400;
  font-style: normal;
}

.gothic-a1-medium {
  font-family: "Gothic A1", sans-serif;
  font-weight: 500;
  font-style: normal;
}

.gothic-a1-semibold {
  font-family: "Gothic A1", sans-serif;
  font-weight: 600;
  font-style: normal;
}

.gothic-a1-bold {
  font-family: "Gothic A1", sans-serif;
  font-weight: 700;
  font-style: normal;
}

.gothic-a1-extrabold {
  font-family: "Gothic A1", sans-serif;
  font-weight: 800;
  font-style: normal;
}

.gothic-a1-black {
  font-family: "Gothic A1", sans-serif;
  font-weight: 900;
  font-style: normal;
}

/* 구분선, 스크롤 */
.property-list {
  max-height: 650px;
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 10px;
  width: 320px;
}

.property-items {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.property-item {
  border: 2px solid rgb(224, 224, 224);
  padding: 16px;
  margin: 10px 0;
  background-color: #fff;
  transition: box-shadow 0.3s;
}

.property-detail {
  padding: 20px;
  background-color: #f5f5f5;
  width: 47ex;
}

.divider {
  height: 1px;
  background-color: #ccc;
  margin: 10px 0;
}

/*사이드바*/
.custom-sidebar {
  width: 350px;
  transition: width 0.3s ease, visibility 0.3s ease, opacity 0.3s ease;
}

.custom-sidebar.open {
  width: 350px;
}

.custom-sidebar:not(.open) {
  width: 23px;
}

.app-sidebar-content {
  transition: opacity 0.3s ease, visibility 0.3s ease;
  opacity: 1; /* 열림 상태에서 콘텐츠 표시 */
  visibility: visible;
}

.app-sidebar-content.hidden-content {
  opacity: 0; /* 닫힘 상태에서 콘텐츠 숨김 */
  visibility: hidden;
}

.ki-black-left-line {
  transition: transform 0.3s ease; /* 아이콘 애니메이션 */
}

.ki-black-left-line.rotate-180 {
  transform: rotate(180deg); /* 아이콘 회전 */
}

#kt_app_sidebar_toggle {
  position: fixed;
  top: 50%;
  right: 0;
  transform: translateY(-50%);
  z-index: 1000; /* 사이드바 위에 표시 */
}

/* 사이드바 내부 메뉴 */
.property-item {
  margin-bottom: 15px;
}

/* 정렬 옵션 스타일 */
.sort-options {
  background-color: lightgray;
  padding: 10px;
  font-weight: 800;
}

/* 프로필 */
.profile {
  display: flex;
  justify-content: center;
  align-items: center;
}

.profile-image {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 2px solid #440ef6;
  object-fit: cover;
}

/* 필터 테이블 */
.table-bordered {
  border: 1px solid #000;
}

.table-bordered td,
.table-bordered th {
  border: 1px solid #000;
}

/* 매물 디테일 */
.property-more-details {
  margin-top: 1ex;
  background-color: #f5f5f5;
  padding: 1ex;
}

.divider {
  border-bottom: 1px solid #ccc;
  margin: 1ex 0;
}

#kt_app_sidebar.collapsed #sort-options {
  display: none; /* 사이드바가 접혔을 때 숨김 */
}

#kt_app_sidebar:not(.collapsed) #sort-options {
  display: block; /* 사이드바가 펼쳐졌을 때 보임 */
}
#kt_app_sidebar.collapsed .form-inline {
  display: none; /* 사이드바가 접혔을 때 숨김 */
}

#kt_app_sidebar:not(.collapsed) .form-inline {
  display: block; /* 사이드바가 펼쳐졌을 때 보임 */
}
</style>
