<template>
  <div
    v-if="isModalOpen"
    id="publicLedgerModal"
    class="modal fade show modal-xl"
    tabindex="-1"
    role="dialog"
    style="display: block; background: rgba(0, 0, 0, 0.5)"
  >
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h2 class="modal-title">공부자료 열람</h2>
          <button
            type="button"
            class="close"
            @click="onClickCloseBtn"
            aria-label="Close"
          >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="p-10">
          <div class="">
            <div>
              <a href="#" class="tag fs-1"> 신고접수번호 C51743</a>
            </div>
            <div class="fw-bold mt-5 mb-3">
              중개사무소 소재지:
              <span class="text-gray-600 ms-1">강남구 언주로 703</span>
            </div>
            <div class="d-flex text-gray-800 mt-5 mb-3 container-badge">
              <!--begin::Accountnumber-->
              <div class="fw-bold pe-5">신고사유:</div>
              <!--end::Accountnumber-->
              <!--begin::Number-->
              <span
                class="badge signal badge-light-danger fw-bold me-2 px-4 py-3"
                >명시의무 위반</span
              >
              <span
                class="badge signal badge-light-warning fw-bold me-2 px-4 py-3"
                >중개사무소</span
              >
              <span
                class="badge signal badge-light-success fw-bold me-2 px-4 py-3"
                >상호명시 위반</span
              >
              <!--end::Number-->
            </div>
          </div>
          <div class="mt-10">
            <PdfViewer></PdfViewer>
          </div>
        </div>
        <div class="modal-footer" style="padding: 2rem">
          <button
            type="button"
            class="btn btn-secondary"
            @click="onClickCloseBtn"
          >
            닫기
          </button>
          <button type="button" class="btn btn-primary" @click="submitFilters">
            매물등록
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { usePublicLedgerModalStore } from "@/stores/modal";
import { storeToRefs } from "pinia";
import PdfViewer from "@/components/PdfViewer.vue";

const storeModal = usePublicLedgerModalStore();
const { isModalOpen } = storeToRefs(storeModal);

const onClickCloseBtn = () => {
  storeModal.closeModal();
};
</script>

<style scoped>
.fade {
  opacity: 0;
  transition: opacity 0.3s ease-in-out; /* 0.3초 동안 서서히 나타나거나 사라짐 */
}

/* show 클래스가 추가되면 opacity가 1이 되어 모달이 나타남 */
.show {
  opacity: 1;
}
</style>
