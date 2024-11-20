<template>

    <MobileHeader>
    </MobileHeader>
    <div class="container" style="width: 428px; height: 926px; ">
        <ul class="container-sub-menu d-flex justify-content-around fs-4 p-3">
            <li v-for="(menu, index) in menuList" :key="index" :class="['p-2', { selected: selectPageNum === index }]"
                @click="onClickSelectPage(index)">
                {{ menu }}
            </li>
        </ul>

        <!-- 내 매물 보기-->
        <div v-if="selectPageNum === 0">
            <button @click="onClickOpenRegisterPrp" class="btn btn-primary me-2">
                매물등록
              </button> 
              <button class="btn btn-danger">매물삭제</button>
            <div class="card mb-3 mt-3">
                <div class="row">
                    <div class="col-5">
                        <img src="/assets/img/home.jpg" class="img-fluid rounded-start w-100 h-100">
                    </div>
                    <div class="col-7">
                        <div class="card-body">
                            <h5 class="card-title"></h5>
                            <p><strong style="font-weight: bolder;">이름:</strong> 청운벽산빌리지</p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">가격:</strong> 백만원 </p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">면적:</strong>83 </p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">설명:</strong> 학군 우수</p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">주소:</strong> 서울시 종로구 자하문로
                                36길</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div v-if="selectPageNum === 1">
            <div class="card mb-3">
                <div class="row">
                    <div class="col-5">
                        <img src="/assets/img/home.jpg" class="img-fluid rounded-start w-100 h-100">
                    </div>
                    <div class="col-7">
                        <div class="card-body">
                            <h5 class="card-title"></h5>
                            <p><strong style="font-weight: bolder;">이름:</strong> 청운벽산빌리지</p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">가격:</strong> 백만원 </p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">면적:</strong>83 </p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">설명:</strong> 학군 우수</p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">주소:</strong> 서울시 종로구 자하문로
                                36길</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mb-3">
                <div class="row">
                    <div class="col-5">
                        <img src="/assets/img/home1.jpg" class="img-fluid rounded-start w-100 h-100">
                    </div>
                    <div class="col-7">
                        <div class="card-body">
                            <h5 class="card-title"></h5>
                            <p><strong style="font-weight: bolder;">이름:</strong> 청운벽산빌리지</p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">가격:</strong> 백만원 </p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">면적:</strong>83 </p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">설명:</strong> 학군 우수</p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">주소:</strong> 서울시 종로구 자하문로
                                36길</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div v-if="selectPageNum === 2">

            <div class="card mb-3">
                <div class="row">
                    <div class="col-5">
                        <img src="/assets/img/home1.jpg" class="img-fluid rounded-start w-100 h-100">
                    </div>
                    <div class="col-7">
                        <div class="card-body">
                            <p><strong style="font-weight: bolder;">신고날짜:</strong> 2024년 11월 10일</p>
                            <p style="margin-top: 3ex;"><strong style="font-weight: bolder;">이름:</strong> 청운벽산빌리지</p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">가격:</strong> 백만원 </p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">면적:</strong>83 </p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">설명:</strong> 학군 우수</p>
                            <p style="margin-top: 1ex;"><strong style="font-weight: bolder;">주소:</strong> 서울시 종로구 자하문로
                                36길</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>


    </div>
    <MobileBottomTapBar></MobileBottomTapBar>
    <RegisterPrpModal></RegisterPrpModal>
</template>




<script setup>
import MobileHeader from '@/components/MobileHeader.vue';
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMyPageStore } from "@/stores/myPage.js";
import { useRegisterPrpModalStore } from "@/stores/modal";
import RegisterPrpModal from "@/components/RegisterPrpModal.vue";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";


const router = useRouter();

const changeMypageStore = useMyPageStore();
const { selectPageNum } = storeToRefs(changeMypageStore);
const menuList = changeMypageStore.menuList;

const onClickSelectPage = async (num) => {
    selectPageNum.value = num;
    if (num === 1) {
        await saleStore.fetchInterestPrp(userPk);
    }
};

//매물 등록 모달

const storeModal = useRegisterPrpModalStore();
const onClickOpenRegisterPrp = () => {
  storeModal.openModal();
};

const onClickRegisterPrp = async () => {
  await createProperty(userPk);
  await saleStore.fetchSaleListByAgent(userPk);
};

</script>

<style scoped>
.container-sub-menu li {
    margin-right: 1em;
    cursor: pointer;
}

.container-sub-menu li.selected {
    color: var(--color-bg-blue2);
    border-bottom: 1px solid var(--color-bg-blue2);
}
</style>