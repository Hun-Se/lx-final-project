<template>
  <div
    v-if="isModalOpen"
    id="registerPrpModal"
    class="modal fade show"
    tabindex="-1"
    role="dialog"
    style="display: block; background: rgba(0, 0, 0, 0.5)"
  >
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h2 class="modal-title">매물등록</h2>
          <button
            type="button"
            class="close"
            @click="onClickCloseBtn"
            aria-label="Close"
          >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body-register">
          <!-- 거래 대상 -->
          <form>
            <div>
              <label for="input-title" class="form-label">주소검색</label>
              <input
                type="text"
                id="input-title"
                class="form-control"
                aria-describedby="passwordHelpBlock"
              />
              <label for="input-title" class="form-label mt-8">주소</label>
              <input
                type="text"
                id="input-addr"
                class="form-control"
                disabled
                aria-describedby="passwordHelpBlock"
              />
              <label for="input-title" class="form-label mt-8">상세주소</label>
              <input
                type="text"
                id="input-addr-detail"
                class="form-control"
                aria-describedby="passwordHelpBlock"
              />
              <div style="padding: 200px">지도</div>
              <label for="input-title" class="form-label mt-8">매물제목</label>
              <input
                type="text"
                id="input-title"
                class="form-control"
                aria-describedby="passwordHelpBlock"
              />
              <h3 class="form-label mt-8">거래 대상</h3>
              <div class="d-flex justify-content-between mt-3">
                <button
                  type="button"
                  class="btn"
                  :class="
                    filters.dealTarget === '기타'
                      ? 'btn-primary'
                      : 'btn-secondary'
                  "
                  @click="updateDealTarget('기타')"
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
                >
                  오피스텔
                </button>
              </div>
            </div>
            <div>
              <h3 class="form-label mt-8">거래 유형</h3>
              <div class="d-flex justify-content-between">
                <button
                  type="button"
                  class="btn"
                  :class="
                    activeInput === 'jeonse' ? 'btn-primary' : 'btn-secondary'
                  "
                  @click="updateDealType('jeonse')"
                >
                  전세
                </button>
                <button
                  type="button"
                  class="btn"
                  :class="
                    activeInput === 'wolse' ? 'btn-primary' : 'btn-secondary'
                  "
                  @click="updateDealType('wolse')"
                >
                  월세
                </button>
                <button
                  type="button"
                  class="btn"
                  :class="
                    activeInput === 'maemae' ? 'btn-primary' : 'btn-secondary'
                  "
                  @click="updateDealType('maemae')"
                >
                  매매
                </button>
              </div>
            </div>

            <div class="mt-8" v-if="activeInput === 'jeonse' || 'wolse'">
              <label for="input-deposit" class="form-label">보증금</label>
              <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="금액" />
                <span class="input-group-text">원</span>
              </div>
            </div>
            <div class="mt-8" v-if="activeInput === 'jeonse'">
              <label for="input-wolse" class="form-label">전세금</label>
              <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="금액" />
                <span class="input-group-text">원</span>
              </div>
            </div>

            <!-- 전세금, 월세, 매매금액 입력 -->
            <div
              class="mt-8"
              v-if="activeInput === 'wolse' || activeInput === 'all'"
            >
              <label for="input-wolse form-label" class="form-label"
                >월세</label
              >
              <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="금액" />
                <span class="input-group-text" id="basic-addon2">원</span>
              </div>
            </div>
            <div
              class="mt-8"
              v-if="activeInput === 'maemae' || activeInput === 'all'"
            >
              <label for="input-maemae form-label" class="form-label"
                >매매 금액</label
              >
              <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="금액" />
                <span class="input-group-text" id="basic-addon2">원</span>
              </div>
            </div>
            <!-- 전용 면적 선택 -->
            <div class="mt-8">
              <label for="input-maemae form-label" class="form-label"
                >공급 면적</label
              >
              <div class="d-flex">
                <div class="input-group me-3" style="width: 50%">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="공급면적"
                  />
                  <span class="input-group-text" id="basic-addon2"
                    >m<sup>2</sup></span
                  >
                </div>
                <div class="input-group me-3" style="width: 50%">
                  <input
                    type="text"
                    disabled
                    class="form-control"
                    placeholder="평"
                  />
                  <span class="input-group-text" id="basic-addon2">평</span>
                </div>
              </div>
            </div>
            <!-- 전용 면적 선택 -->
            <div class="mt-8">
              <label for="input-maemae form-label" class="form-label"
                >전용 면적</label
              >
              <div class="d-flex">
                <div class="input-group me-3" style="width: 50%">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="전용면적"
                  />
                  <span class="input-group-text" id="basic-addon2"
                    >m<sup>2</sup></span
                  >
                </div>
                <div class="input-group me-3" style="width: 50%">
                  <input
                    type="text"
                    disabled
                    class="form-control"
                    placeholder="평"
                  />
                  <span class="input-group-text" id="basic-addon2">평</span>
                </div>
              </div>
            </div>
            <div class="mt-8">
              <h3 class="form-label">구조</h3>
              <div class="d-flex justify-content-between mt-3">
                <button
                  type="button"
                  class="btn"
                  :class="
                    filters.structure === '전체'
                      ? 'btn-primary'
                      : 'btn-secondary'
                  "
                  @click="updateStructure('전체')"
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
              </div>
            </div>
            <!-- 층수 선택 -->
            <div class="mt-8">
              <h3 class="form-label">층수</h3>
              <div class="mt-3 d-flex justify-content-between">
                <button
                  type="button"
                  class="btn"
                  :class="
                    filters.floor === '전체' ? 'btn-primary' : 'btn-secondary'
                  "
                  @click="updateFloor('전체')"
                >
                  전체
                </button>
                <button
                  type="button"
                  class="btn"
                  :class="
                    filters.floor === '옥탑방' ? 'btn-primary' : 'btn-secondary'
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
                    filters.floor === '지상층' ? 'btn-primary' : 'btn-secondary'
                  "
                  @click="updateFloor('지상층')"
                  style="width: 13ex; margin-left: 1ex"
                >
                  지상층
                </button>
              </div>
            </div>
            <!--관리비-->
            <div class="mt-8">
              <label for="input-wolse" class="form-label">관리비</label>
              <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="금액" />
                <span class="input-group-text" id="basic-addon2">원</span>
              </div>
            </div>
            <div class="mt-8">
              <label for="input-wolse" class="form-label">주차대수</label>
              <div class="input-group mb-3">
                <input
                  type="text"
                  class="form-control"
                  placeholder="주차가능수"
                />
              </div>
            </div>
            <div class="mt-8">
              <label for="textarea-desc" class="form-label">추가설명</label>
              <textarea class="form-control mt-3" id="textarea-desc"></textarea>
            </div>
          </form>
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
import { useRegisterPrpModalStore } from "@/stores/modal";
import { storeToRefs } from "pinia";
import { ref, reactive } from "vue";

const storeModal = useRegisterPrpModalStore();
const { isModalOpen } = storeToRefs(storeModal);

// 필터 상태를 관리할 reactive 객체
const filters = reactive({
  dealTarget: "기타",
  dealType: "전체",
  structure: "전체",
  floor: "전체",
  area: "전체",
  jeonse: 0,
  wolse: 0,
  maemae: 0,
  prpSuppArea: 0,
  prpExclArea: 0,
  prpAddr: "",
  prpAddrDetail: "",
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

// 필터 제출
const submitFilters = () => {
  console.log("제출된 필터:", filters);
};

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

.modal-body-register {
  padding: 3.75rem;
}
</style>
