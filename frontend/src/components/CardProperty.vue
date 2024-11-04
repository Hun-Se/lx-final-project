<template>
  <!--          매물 카드 시작-->
  <div class="property-list">
    <ul class="property-items d-flex">
      <li
        v-for="(item, index) in props.properties"
        :key="item.prpPk"
        @click="toggleSalesDetail(item.prpPk)"
        class="list-container m-3"
      >
        <div class="card p-5">
          <div class="img-container">
            <img
              :src="'/assets/img/' + item.prpImg"
              class="card-img-top"
              alt="..."
            />
            <input
              class="form-check-input prp-checkbox"
              type="checkbox"
              value=""
              aria-label="..."
            />
            <i
              v-if="selectPageNum === 1"
              class="bi bi-heart-fill heart-icon"
            ></i>
          </div>

          <div class="card-body">
            <h5 class="card-title">{{ item.prpName }}</h5>
            <p class="property-price mt-3">{{ item.prpPrice }}</p>
            <p class="card-text mt-3">
              {{ item.prpAddrDetail }}
            </p>
            <p class="card-text mt-3">
              {{ item.prpDesc }}
            </p>
          </div>
          <div class="d-flex justify-content-around">
            <a href="#" class="btn btn-primary btn-sm">경매신청</a>
            <a
              v-if="selectPageNum === 0"
              href="#"
              class="btn btn-primary btn-sm"
              >매물수정</a
            >
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { useMyPageStore } from "@/stores/myPage.js";
import { useSaleStore } from "@/stores/property.js";
import { storeToRefs } from "pinia";
import { deleteInterestPrp } from "@/api/property.js";

const myPageStore = useMyPageStore();
const { selectPageNum } = storeToRefs(myPageStore);

const props = defineProps({
  properties: Array,
});

async function removeInterestPrp(prpPk) {
  await deleteInterestPrp(prpPk);

  const saleStore = useSaleStore();
  const userPk = sessionStorage.getItem("userPk");
  await saleStore.fetchInterestPrp(userPk);
}
</script>

<style scoped>
.card-title {
  font-size: 16px;
  font-weight: bold;
}

.property-price {
  font-size: 14px;
  color: var(--color-bg-blue2);
}

.card-body {
  height: 120px;
}

.card-body {
  height: 160px;
}

.card-img-top {
  width: 100%;
  height: 150px;
}

.property-items {
  flex-wrap: wrap;
  overflow-y: scroll;
}

.img-container {
  position: relative;
}

.prp-checkbox {
  position: absolute;
  top: 10px;
  right: 10px;
  border: 1px solid #fff;
}

.property-items {
  width: 100%;
}

.list-container {
  flex-basis: calc(25% - 20px); /* 요소를 4개씩 배치, 간격을 감안해 너비 조정 */
  box-sizing: border-box;
}

@media (max-width: 1200px) {
  .list-container {
    flex-basis: calc(33.33% - 20px); /* 작은 화면에서는 3개씩 배치 */
  }
}

@media (max-width: 800px) {
  .list-container {
    flex-basis: calc(50% - 20px); /* 더 작은 화면에서는 2개씩 배치 */
  }
}

@media (max-width: 600px) {
  .list-container {
    flex-basis: 100%; /* 모바일 화면에서는 1개씩 배치 */
  }
}

.heart-icon {
  position: absolute;
  left: 10px;
  top: 10px;
  font-size: 25px;
  color: red;
  cursor: pointer;
}
</style>
