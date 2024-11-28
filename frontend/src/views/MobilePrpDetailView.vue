<template>
  <MobileHeader title="매물상세정보"></MobileHeader>
  <img v-if="selectedSalesDetails" :src="'assets/img/' + selectedSalesDetails.prpImg " class="img-prp-detail" alt="매물 이미지" />
  <div id="mainPage">
    <div v-if="selectedSalesDetails">
      <section class="container-prp-info">
        <div class="py-4 flex-row-fluid mt-3 bg-white p-3">
          <div class="card-header">
            <div class="">
              <div class="d-flex justify-content-between">
                <span class="badge badge-light-primary">매물번호 {{ selectedSalesDetails.prpPk }}</span>
                <div>
                  <div class="func">
                    <i
                        class="bi bi-heart-fill heart-icon"
                    ></i>
                  </div>
                </div>
              </div>
                <h2 class="fs-1 mt-5 font-weight-bold">가격 {{ selectedSalesDetails.prpPrice/10000 }}억 원</h2>
            </div>
          </div>
          <!--end::Card header-->
          <!--begin::Card body-->
          <div class="card-body pt-0">
            <p class="mt-3">매물명: {{ selectedSalesDetails.prpName }}</p>
            <ul class="styled__InfoList-gbrixp-9 gUXrsQ">
              <li class="styled__InfoItem-gbrixp-10 hieNij">
                <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path fill-rule="evenodd" clip-rule="evenodd" d="M9.57036 0.937323L9.66165 0.996L17.1616 6.53492C17.3478 6.6724 17.4672 6.88004 17.4941 7.10698L17.4999 7.20527L17.5005 11.1884C18.2781 12.0697 18.7499 13.2273 18.7499 14.495C18.7499 17.2564 16.5113 19.495 13.7499 19.495C12.2694 19.495 10.9392 18.8516 10.0237 17.8291L1.66659 17.8283C1.23922 17.8283 0.886996 17.5066 0.838858 17.0922L0.833252 16.995V7.20527C0.833252 6.97386 0.929363 6.75447 1.09591 6.59799L1.17152 6.53492L8.67152 0.996C8.93636 0.800409 9.28814 0.78085 9.57036 0.937323ZM13.7499 10.3283C11.4487 10.3283 9.58325 12.1938 9.58325 14.495C9.58325 16.7962 11.4487 18.6617 13.7499 18.6617C16.0511 18.6617 17.9166 16.7962 17.9166 14.495C17.9166 12.1938 16.0511 10.3283 13.7499 10.3283ZM14.1599 14.4201C14.1246 14.2257 13.9545 14.0783 13.7499 14.0783C13.5198 14.0783 13.3333 14.2649 13.3333 14.495V16.8283L13.34 16.9032C13.3752 17.0976 13.5454 17.245 13.7499 17.245C13.98 17.245 14.1666 17.0585 14.1666 16.8283V14.495L14.1599 14.4201ZM9.16658 1.66634L1.66659 7.20527V16.995L9.41905 16.9954C8.99349 16.2599 8.74992 15.4059 8.74992 14.495C8.74992 11.7336 10.9885 9.49501 13.7499 9.49501C14.8387 9.49501 15.8463 9.84304 16.6673 10.4339L16.6666 7.20527L9.16658 1.66634ZM14.3333 12.5783C14.3333 12.2562 14.0721 11.995 13.7499 11.995C13.4278 11.995 13.1666 12.2562 13.1666 12.5783C13.1666 12.9005 13.4278 13.1617 13.7499 13.1617C14.0721 13.1617 14.3333 12.9005 14.3333 12.5783Z" fill="#222222"></path></svg>
                <span class="ms-3">{{ prpType }}</span></li><li class="styled__InfoItem-gbrixp-10 hieNij">
              <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M6.66675 1.66699C7.09411 1.66699 7.44634 1.98869 7.49448 2.40314L7.50008 2.50033V12.4995L17.5001 12.5003C17.8946 12.5003 18.225 12.7744 18.3114 13.1426L18.3278 13.2365L18.3334 13.3337V17.5003C18.3334 17.9277 18.0117 18.2799 17.5973 18.3281L17.5001 18.3337H2.50008C2.07272 18.3337 1.72049 18.012 1.67235 17.5975L1.66675 17.5003V2.50033C1.66675 2.07296 1.98845 1.72074 2.4029 1.6726L2.50008 1.66699H6.66675ZM7.25675 13.332L3.08842 17.5003H7.50008V15.7295C7.50008 15.4994 7.68663 15.3128 7.91675 15.3128C8.1213 15.3128 8.29142 15.4602 8.3267 15.6546L8.33342 15.7295V17.5003H10.0001V15.7295C10.0001 15.4994 10.1866 15.3128 10.4167 15.3128C10.6213 15.3128 10.7914 15.4602 10.8267 15.6546L10.8334 15.7295V17.5003H12.5001V15.7295C12.5001 15.4994 12.6866 15.3128 12.9167 15.3128C13.1213 15.3128 13.2914 15.4602 13.3267 15.6546L13.3334 15.7295V17.5003H15.0001V15.7295C15.0001 15.4994 15.1866 15.3128 15.4167 15.3128C15.6213 15.3128 15.7914 15.4602 15.8267 15.6546L15.8334 15.7295V17.5003H17.5001V13.3337L7.25675 13.332ZM2.50008 2.50033H6.66675V12.7437L2.50008 16.9103V12.5003H4.27091L4.34581 12.4936C4.54019 12.4583 4.68758 12.2882 4.68758 12.0837C4.68758 11.8535 4.50103 11.667 4.27091 11.667H2.50008V10.0003H4.27091L4.34581 9.99361C4.54019 9.95833 4.68758 9.78821 4.68758 9.58366C4.68758 9.35354 4.50103 9.16699 4.27091 9.16699H2.50008V7.50033H4.27091L4.34581 7.49361C4.54019 7.45833 4.68758 7.28821 4.68758 7.08366C4.68758 6.85354 4.50103 6.66699 4.27091 6.66699H2.50008V5.00033H4.27091L4.34581 4.99361C4.54019 4.95833 4.68758 4.78821 4.68758 4.58366C4.68758 4.35354 4.50103 4.16699 4.27091 4.16699H2.50008V2.50033Z" fill="#222222"></path></svg>
              <span class="ms-3">{{selectedSalesDetails.prpExclArea}}㎡</span></li>
              <li class="styled__InfoItem-gbrixp-10 hieNij"><svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M13.3333 0.833008C13.7607 0.833008 14.1129 1.15471 14.1611 1.56916L14.1667 1.66634V18.333C14.1667 18.7604 13.845 19.1126 13.4305 19.1607L13.3333 19.1663H0.833333C0.40597 19.1663 0.053744 18.8446 0.00560644 18.4302L0 18.333V1.66634C0 1.23898 0.3217 0.886752 0.736149 0.838614L0.833333 0.833008H13.3333ZM6.66667 1.66634H0.833333V18.333H6.66667V1.66634ZM7.5 1.66634H13.3333V18.333H7.5V1.66634ZM16.4869 11.3235L16.5446 11.3717L17.7083 12.5355L18.872 11.3717C19.0167 11.2271 19.2412 11.211 19.4036 11.3235L19.4613 11.3717C19.6059 11.5164 19.622 11.7409 19.5095 11.9033L19.4613 11.961L17.7083 13.7139L15.9554 11.961C15.7927 11.7983 15.7927 11.5344 15.9554 11.3717C16.1 11.2271 16.3245 11.211 16.4869 11.3235ZM19.4654 7.87589L17.7083 6.06861L15.9513 7.87589L15.9039 7.93427C15.7937 8.09823 15.8129 8.3225 15.9596 8.46509L16.0179 8.51248C16.1819 8.62268 16.4062 8.60345 16.5487 8.45679L17.7083 7.26384L18.8679 8.45679L18.9249 8.50581C19.0857 8.62058 19.3105 8.60768 19.4571 8.46509C19.6221 8.30468 19.6258 8.04089 19.4654 7.87589Z" fill="#222222"></path></svg>
                <span class="ms-3">{{selectedSalesDetails.prpFloor}} 층</span></li>
              <li class="styled__InfoItem-gbrixp-10 hieNij"><svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M14.166 1.66699C14.5934 1.66699 14.9456 1.98869 14.9937 2.40314L14.9993 2.50033V9.74118C17.1559 10.2962 18.7493 12.2539 18.7493 14.5837C18.7493 17.3451 16.5108 19.5837 13.7493 19.5837C12.2493 19.5837 10.9036 18.9231 9.98712 17.877L9.68659 18.1175C9.40991 18.3388 9.02736 18.3589 8.73142 18.1778L8.64544 18.1175L7.49935 17.2003L6.35326 18.1175C6.07658 18.3388 5.69402 18.3589 5.39809 18.1778L5.3121 18.1175L4.16602 17.2003L3.01993 18.1175C2.68819 18.3828 2.21583 18.3536 1.919 18.0649L1.84862 17.9873C1.75406 17.8691 1.69393 17.728 1.67366 17.5794L1.66602 17.4667V2.50033C1.66602 2.07296 1.98772 1.72074 2.40216 1.6726L2.49935 1.66699H14.166ZM9.49117 17.2057C9.02077 16.4433 8.74935 15.5452 8.74935 14.5837C8.74935 11.8222 10.9879 9.58366 13.7493 9.58366C13.8897 9.58366 14.0286 9.58944 14.166 9.60077V2.50033H2.49935V17.4667L4.16602 16.1334L5.83268 17.4667L7.49935 16.1334L9.16518 17.4662L9.49117 17.2057ZM11.2493 5.83366C11.4795 5.83366 11.666 6.02021 11.666 6.25033C11.666 6.48044 11.4795 6.66699 11.2493 6.66699H5.41602C5.1859 6.66699 4.99935 6.48044 4.99935 6.25033C4.99935 6.02021 5.1859 5.83366 5.41602 5.83366H11.2493ZM9.99935 8.75033C9.99935 8.52021 9.8128 8.33366 9.58268 8.33366H5.41602C5.1859 8.33366 4.99935 8.52021 4.99935 8.75033C4.99935 8.98044 5.1859 9.16699 5.41602 9.16699H9.58268C9.8128 9.16699 9.99935 8.98044 9.99935 8.75033ZM7.91602 10.8337C8.14613 10.8337 8.33268 11.0202 8.33268 11.2503C8.33268 11.4804 8.14613 11.667 7.91602 11.667H5.41601C5.1859 11.667 4.99935 11.4804 4.99935 11.2503C4.99935 11.0202 5.1859 10.8337 5.41601 10.8337H7.91602ZM9.58268 14.5837C9.58268 12.2825 11.4482 10.417 13.7493 10.417C16.0505 10.417 17.916 12.2825 17.916 14.5837C17.916 16.8848 16.0505 18.7503 13.7493 18.7503C11.4482 18.7503 9.58268 16.8848 9.58268 14.5837ZM15.8592 13.1874C15.94 12.9719 16.1802 12.8627 16.3956 12.9435C16.5872 13.0153 16.6947 13.2131 16.6595 13.4075L16.6395 13.48L16.2256 14.5837H16.666C16.8961 14.5837 17.0827 14.7702 17.0827 15.0003C17.0827 15.2049 16.9353 15.375 16.7409 15.4103L16.666 15.417H15.9131L15.3895 16.8133C15.264 17.148 14.8153 17.1719 14.6436 16.885L14.6092 16.8133L13.7493 14.5203L12.8895 16.8133C12.764 17.148 12.3153 17.1719 12.1436 16.885L12.1092 16.8133L11.5856 15.417H10.8327C10.6026 15.417 10.416 15.2304 10.416 15.0003C10.416 14.7958 10.5634 14.6257 10.7578 14.5904L10.8327 14.5837H11.2731L10.8592 13.48C10.7784 13.2645 10.8876 13.0243 11.103 12.9435C11.2946 12.8717 11.5056 12.95 11.6069 13.1196L11.6395 13.1874L12.4993 15.4795L13.3592 13.1874C13.4847 12.8526 13.9334 12.8287 14.1051 13.1156L14.1395 13.1874L14.9993 15.4795L15.8592 13.1874Z" fill="#222222"></path></svg>
                <span class="ms-3">{{ selectedSalesDetails.prpDeposit }} 원</span>
              </li></ul>

            <section class="styled__StickyContainer-vbmenq-0 ilnRkT">
              <div class="styled__AgentContent-vbmenq-2 gNYtEj">
                <button class="btn btn-light-primary">
                  <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M19.9951 16.5543C20.2733 16.8333 20.3311 17.2578 20.1331 17.6208L20.0731 17.7183L19.9943 17.8083L17.9236 19.8798C17.8006 20.0021 17.6453 20.0861 17.4751 20.1213C14.7751 20.6786 11.8373 19.4313 8.66333 16.4351L8.38283 16.1658L8.10683 15.8936C4.71983 12.5073 3.28958 9.38506 3.87983 6.52606C3.90758 6.39106 3.96683 6.26356 4.05233 6.15631L4.12133 6.07906L6.19133 4.00831C6.51158 3.68806 7.01483 3.66331 7.37483 3.94681L7.46183 4.02406L7.50608 4.07356L9.79883 6.87556C10.0336 7.16281 10.0628 7.56181 9.88208 7.87756L9.82208 7.96981L8.27258 10.0346L8.32283 10.1973C8.57933 10.9676 9.19433 11.8811 10.1693 12.9236L10.3838 13.1486L10.6163 13.3848C11.8538 14.6216 12.9218 15.3843 13.8038 15.6783L13.9673 15.7278L16.0328 14.1791C16.2841 13.9908 16.6133 13.9511 16.9208 14.0808L17.0348 14.1371L17.1263 14.2023L19.9456 16.5093L19.9943 16.5543H19.9951Z" fill="#326CF9"></path>
                  </svg>
                </button>
                <button class="btn btn-light-primary">
                  <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M21 11.9688C21 16.3698 16.9706 19.9375 12 19.9375C10.5632 19.9375 9.77639 19.7407 8.57143 19.2105L4.93844 20.6327C4.55344 20.7834 4.16229 20.4283 4.27518 20.0305L5.14286 16.9737C3.59431 15.5424 3 14.1073 3 11.9688C3 7.56773 7.02944 4 12 4C16.9706 4 21 7.56773 21 11.9688Z" fill="#326CF9"></path></svg></button><div class="styled__BtnWrap-q82b0c-0 dKWwzw">
                <button type="button" class="btn btn-primary w-100" @click="clickChat"><span class="styled__Text-sc-18tw3zz-1 kivQQY">채팅 문의하기</span></button></div></div></section>
          </div>
          <!--end::Card body-->
        </div>
        <div class="py-4 flex-row-fluid mt-3 bg-white p-3">
          <!--begin::Card header-->
          <div class="card-header mt-3 mb-3">
            <div class="card-title">
              <h2 class="fs-1">가격 정보</h2>
            </div>
          </div>
          <!--end::Card header-->
          <!--begin::Card body-->
          <div class="card-body pt-0">
            <div class="table-responsive">
              <!--begin::Table-->
              <table class="table align-middle table-row-bordered mb-0 fs-6 gy-5 min-w-300px">
                <tbody class="fw-semibold ">
                <tr>
                  <td class="fw-bold">
                    {{ prpTransType }}
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpPrice}}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    관리비
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpMaintFee}}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    보증금
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpDeposit}}</td>
                </tr>
                </tbody>
              </table>
              <!--end::Table-->
            </div>
          </div>
          <!--end::Card body-->
        </div>
        <div class="py-4 flex-row-fluid mt-3 bg-white p-3">
          <!--begin::Card header-->
          <div class="card-header">
            <div class="card-title mt-3 mb-3">
              <h2 class="fs-1">상세 정보</h2>
            </div>
          </div>
          <!--end::Card header-->
          <!--begin::Card body-->
          <div class="card-body pt-0">
            <div class="table-responsive">
              <!--begin::Table-->
              <table class="table align-middle table-row-bordered mb-0 fs-6 gy-5 min-w-300px">
                <tbody class="fw-semibold ">
                <tr>
                  <td class="fw-bold">
                    방향
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpOri}}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    동 / 동개수
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpBlock}} / {{selectedSalesDetails.prp_total_floor}}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    층 / 총층수
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpFloor}} 층 / {{selectedSalesDetails.prp_total_floor}}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    호
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpUnit}}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    호
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpUnit}}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    전용면적
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpExclArea }}m²</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    방개수
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpNumRooms }}개</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    화장실 수
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpNumBaths }}개</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    주차가능수
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpParkingAvail }}대</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    건설사
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpConstr }}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    건설사
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpConstrYear }}연도</td>
                </tr>
                </tbody>
              </table>
              <!--end::Table-->
            </div>
          </div>
          <!--end::Card body-->
        </div>
        <div class="py-4 flex-row-fluid mt-3 bg-white p-3">
          <!--begin::Card header-->
          <div class="card-header mt-3 mb-3">
            <div class="card-title">
              <h2 class="fs-1">주변 시설 정보</h2>
            </div>
          </div>
          <!--end::Card header-->
          <!--begin::Card body-->
          <div class="card-body pt-0">
            <div class="table-responsive">
              <!--begin::Table-->
              <table class="table align-middle table-row-bordered mb-0 fs-6 gy-5 min-w-300px">
                <tbody class="fw-semibold ">
                <tr>
                  <td class="fw-bold">
                    가까운 지하철역
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpNearSub }}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    역 거리
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpNearSubDist }}m</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    가까운 학교
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpNearSchool }}</td>
                </tr>
                <tr>
                  <td class="fw-bold">
                    학교 거리
                  </td>
                  <td class="fw-bold text-gray-600">{{ selectedSalesDetails.prpNearSchoolDist }}m</td>
                </tr>
                </tbody>
              </table>
              <!--end::Table-->
            </div>
          </div>
          <!--end::Card body-->
        </div>

      </section>
    </div>
  </div>
  <MobileBottomTapBar></MobileBottomTapBar>
</template>

<script setup>
import MobileHeader from "@/components/MobileHeader.vue";
import { onMounted, ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { storeToRefs } from "pinia";
import { useSaleStore } from "@/stores/property.js";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";

const prpTransType = ref("");
const prpType = ref("");


onMounted(async () => {
  const salesId = route.query.id;
  // if (salesId) {
  const response = await store.fetchSalesDetails(salesId); // 매물 상세 정보 요청
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
  // }
});

// Pinia 스토어와 라우터 가져오기
const store = useSaleStore();
const { selectedSalesDetails } = storeToRefs(store);
const router = useRouter();
const route = useRoute();


function clickChat() {
  const saleId = route.query.id; // 현재 saleId 가져오기
  router.push({ path: "/mobile_chat", query: { saleId } });
}

</script>

<style scoped>
#mainPage {
  margin-top: 15px; /* 헤더 높이만큼 간격 추가 */
  background-color: #f9f9f9; /* 배경색 설정 (선택 사항) */
}

.img-prp-detail {
  margin-top: 45px;
}

h1 {
  text-align: center;
  margin-bottom: 1rem;
}

.img-prp-detail {
  width: 100%;
  height: 300px;
  z-index: 999;
}

.gUXrsQ {
  display: grid;
  grid-template-columns: max-content minmax(0px, 1fr);
  gap: 4px 32px;
  margin: 24px 0px;
}

.func {
  flex: 0 0 auto;
  display: flex
;
  -webkit-box-align: center;
  align-items: center;
  column-gap: 16px;
  margin-left: auto;
}

.ilnRkT {
  margin-top: auto;
  padding: 24px 20px;
  background: linear-gradient(rgba(255, 255, 255, 0) 0%, rgba(255, 255, 255, 0.5) 9%, rgb(255, 255, 255) 19%);
  position: sticky;
  bottom: 0px;
  z-index: 10;
}

.gNYtEj {
  display: grid
;
  grid-template-columns: 56px 56px minmax(0px, 1fr);
  column-gap: 8px;
}

.jSMQiW {
  display: flex
;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  width: 100%;
  height: 56px;
  padding: 0px;
  border: 1px solid rgb(50, 108, 249);
  border-radius: 2px;
  background-color: rgb(255, 255, 255);
  transition: 150ms ease-out;
  cursor: pointer;
}

.dKWwzw {
  position: relative;
}
</style>
