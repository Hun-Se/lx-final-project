<template>
  <HomeMobileHeader title="홈페이지"> </HomeMobileHeader>
  <div
    class="container"
    style="flex: 1; margin-top: 40px; margin-bottom: 60px; overflow-y: auto"
  >
    <!-- 검색 바-->
    <div>
      <div class="mt-12 ms-2" style="display: flex; align-items: center">
        <input
          v-model="searchByType"
          class="form-check-input"
          type="radio"
          name="flexRadioDefault"
          value="time"
          checked
        />
        <label class="form-check-label lh-base ms-3" for="flexRadioDefault1">
          등시간 검색
        </label>
        <input
          v-model="searchByType"
          class="form-check-input ms-4"
          type="radio"
          name="flexRadioDefault"
          id="flexRadioDefault2"
          value="keyWord"
        />
        <label class="form-check-label lh-base ms-3" for="flexRadioDefault2" style="">
          <div>키워드 검색</div>
        </label>
      </div>

      <!--등시간 검색-->
      <div v-if="searchByType === 'time'">
        <div class="mt-3 d-flex justify-content-around">
          <div class="">
            <div class="d-flex align-items-center">
              <input class="text-center form-check-input" style="width: 180px" placeholder="위치를 입력해주세요" />
              <span class="ms-2 me-2">에서</span>

              <select class="form-check-input" style="width: 50px">
                <option disabled selected>이동수단</option>
                <option value="walking">도보</option>
                <option value="driving">자차</option>
                <option value="public_transport">대중교통</option></select
              >
              <span class="ms-2 me-2">로</span>
            </div>
            <div class="mt-3">
              <input
                class="text-center form-check-input"
                type="number"
                placeholder="0"
                min="0"
                style="width: 50px"
              />
              시간

              <input
                class="text-center form-check-input"
                type="number"
                placeholder="0"
                min="0"
                max="59"
                style="width: 50px"
              />
              분
              <span>내에 있는 매물</span>
            </div>
          </div>
          <div class="d-flex align-items-center">
            <button type="button" class="btn btn-primary btn-sm custom-btn">검색</button>
          </div>
        </div>
      </div>

      <!-- 키워드 검색 -->
      <div v-else-if="searchByType === 'keyWord'">
        <div>
          <input
            placeholder="키워드를 입력해주세요"
            style="width: 130px; margin: auto"
          />
          <button type="button" class="btn btn-primary btn-sm">검색</button>
        </div>
      </div>
    </div>

    <!-- 매물 카드 -->

    <div class="row mt-5 move-page-cards">
      <div class="col-6">
        <div @click="goToMap" class="card">
          <div class="card-body">
            <div
              class="text-center mb-2"
              style="font-size: 18px; font-weight: 500"
            >
              원 / 투룸
            </div>
            <div
              class="row text-center"
              style="display: flex; align-items: center"
            >
              <div class="col-7" style="color: gray; font-size: 7px">
                <div>주택 / 빌라, 오피스텔,</div>
                <div class="mt-2">아파트까지 모~든</div>
                <div class="mt-2">전월세 매물을 한번에!</div>
              </div>
              <div class="col-5">
                <img src="/assets/img/oneroom.png" style="width: 40px" />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-6">
        <div @click="goToMap" class="card">
          <div class="card-body">
            <div
              class="text-center mb-2"
              style="font-size: 18px; font-weight: 500"
            >
              주택 / 빌라
            </div>
            <div
              class="row text-center"
              style="display: flex; align-items: center"
            >
              <div class="col-7" style="color: gray; font-size: 7px">
                <div>원하는 빌라, 주택</div>
                <div class="mt-2">전월세 매물 모두 여기!</div>
              </div>
              <div class="col-5">
                <img src="/assets/img/villa.png" style="width: 50px" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row move-page-cards">
      <div class="col-6 p-2">
        <div @click="goToMap" class="card">
          <div class="card-body">
            <div
              class="text-center mb-2"
              style="font-size: 18px; font-weight: 500"
            >
              오피스텔
            </div>
            <div
              class="row text-center"
              style="display: flex; align-items: center"
            >
              <div class="col-7" style="color: gray; font-size: 7px">
                <div>다양한 정보와</div>
                <div class="mt-2">다양한 매물!</div>
              </div>
              <div class="col-5">
                <img src="/assets/img/officetel.png" style="width: 40px" />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-6 p-2">
        <div @click="goToMap" class="card">
          <div class="card-body">
            <div
              class="text-center mb-2"
              style="font-size: 18px; font-weight: 500"
            >
              아파트
            </div>
            <div
              class="row text-center"
              style="display: flex; align-items: center"
            >
              <div class="col-7" style="color: gray; font-size: 7px">
                <div>풍부한 단지정보 및</div>
                <div class="mt-2">다양한 아파트 매물!</div>
              </div>
              <div class="col-5">
                <img src="/assets/img/apartment.png" style="width: 50px" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 배너 -->
    <div class="mt-5">
      <div>
        <div
          id="carouselExampleIndicators"
          class="carousel slide"
          data-bs-ride="carousel"
          data-bs-interval="4000"
          style="padding: 0"
        >
          <div class="carousel-indicators">
            <button
              type="button"
              data-bs-target="#carouselExampleIndicators"
              data-bs-slide-to="0"
              class="active"
              aria-current="true"
              aria-label="Slide 1"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleIndicators"
              data-bs-slide-to="1"
              aria-label="Slide 2"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleIndicators"
              data-bs-slide-to="2"
              aria-label="Slide 3"
            ></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img
                src="/assets/img/banner1.png"
                alt="1번 배너"
                style="width: 100%"
              />
            </div>
            <div class="carousel-item">
              <img
                src="/assets/img/banner2.png"
                alt="2번 배너"
                style="width: 100%"
              />
            </div>
            <div class="carousel-item">
              <img
                src="/assets/img/banner3.png"
                alt="3번 배너"
                style="width: 100%"
              />
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev"
          >
            <span
              class="carousel-control-prev-icon"
              aria-hidden="true"
              style="background-color: grey"
            ></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next"
          >
            <span
              class="carousel-control-next-icon"
              aria-hidden="true"
              style="background-color: grey"
            ></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
    </div>

    <!-- AI 예측 -->
    <div class="mt-2">
      <div>
        <div class="card" style="height: auto">
          <div class="card-body text-center">
            <div style="font-size: 15px; font-weight: 500">
              내가 찜한 집의 미래 시세 어떻게 될까?
            </div>
            <div class="mt-5 d-flex justify-content-around">
              <img src="/assets/img/decrease_graph.png" style="width: 50px" />
              <button
                type="button"
                class="btn btn-sm custom-btn"
                style="margin: auto; color:white"
              >
                AI 집값 예측 바로가기
              </button>
              <img src="/assets/img/increase_graph.png" style="width: 50px" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <MobileBottomTapBar></MobileBottomTapBar>
</template>

<script setup>
import MobileHeader from "@/components/HomeMobileHeader.vue";
import { ref, onMounted, nextTick } from "vue";
import { useRouter } from "vue-router";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";
import HomeMobileHeader from "@/components/HomeMobileHeader.vue";

const router = useRouter();
const searchByType = ref("time");

// 매물지도 페이지로 이동
function goToMap() {
  router.push({ path: "/map" });
}
</script>

<style scoped>
.move-page-cards .card {
  height: 120px;
  overflow: hidden;
}

.form-check-input:checked {
  background-color: var(--color-bg-blue1);
}

.custom-btn {
  background-color: #373773;
  border-color: #373773;
}

.custom-btn:hover {
  background-color: #fa7000 !important;
  border-color: #fa7000 !important;
}
</style>
