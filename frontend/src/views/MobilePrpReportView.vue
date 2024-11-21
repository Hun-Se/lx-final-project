<template>
  <MobileHeader></MobileHeader>
  <div class="container mt-20">
    <label class="fs-4 fw-semibold form-label mt-3">
      <span class="required">신고 분류 선택</span>
    </label>

    <!-- 셀렉트박스 반복 렌더링 -->
    <div
        class="select-menu mt-5"
        v-for="(menu, index) in menus"
        :key="index"
        :class="{ active: menu.isActive }"
    >
      <div class="select-btn form-select" @click="toggleMenu(index)">
        <span class="sBtn-text">{{ menu.selected || menu.placeholder }}</span>
        <i class="bx bx-chevron-down"></i>
      </div>
      <ul class="options" v-show="menu.isActive">
        <li
            class="option"
            v-for="(option, optionIndex) in menu.options"
            :key="optionIndex"
            @click="selectOption(index, option)"
        >
          <span class="option-text">{{ option }}</span>
        </li>
      </ul>
    </div>
    <label class="fs-4 fw-semibold form-label mt-10">
      <span class="required">신고내용 입력</span>
    </label>
    <div class="mt-3 mb-3">
      <div class="form-floating">
        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
        <label for="floatingTextarea" style="color: grey">신고내용의 정확한 확인을 위해서 구체적으로 기재해 주세요.</label>
      </div>
    </div>
    <label class="fs-4 fw-semibold form-label mt-5">
      <span class="required">증빙자료 선택</span>
      <div class="fw-semibold mt-3 fs-7 text-muted">
        광고화면과 문자내용은 어플 내에서 자동으로 취합하여 전송됩니다.
      </div>
    </label>
    <div class="d-flex justify-content-between mt-3">
      <div class="form-check d-flex align-items-center">
        <input
            class="form-check-input"
            type="checkbox"
            value=""
            id="flexCheckDefault"
        />
        <label class="form-check-label ms-2" for="flexCheckDefault">
          광고화면
        </label>
      </div>
      <div class="form-check d-flex align-items-center">
        <input
            class="form-check-input"
            type="checkbox"
            value=""
            id="flexCheckChecked"
            checked
        />
        <label class="form-check-label ms-2" for="flexCheckChecked">
          녹취
        </label>
      </div>
      <div class="form-check d-flex align-items-center">
        <input
            class="form-check-input"
            type="checkbox"
            value=""
            id="flexCheckChecked"
        />
        <label class="form-check-label ms-2" for="flexCheckChecked">
          문자내용
        </label>
      </div>
      <div class="form-check d-flex align-items-center">
        <input
            class="form-check-input"
            type="checkbox"
            value=""
            id="flexCheckChecked"
        />
        <label class="form-check-label ms-2" for="flexCheckChecked">
          기타자료
        </label>
      </div>
    </div>

    <label class="fs-4 fw-semibold form-label mt-5">
      <span class="">자료 첨부</span>
    </label>
    <div class="mt-3 mb-3">
      <input class="form-control" type="file" id="formFileMultiple" multiple />
    </div>
    <div class="btn-group d-flex justify-content-center mt-6">
      <button class="btn btn-light-dark">취소</button>
      <button class="btn btn-report" @click="onClickMoveMap">확인</button>
    </div>
  </div>

  <MobileBottomTapBar></MobileBottomTapBar>
</template>

<script setup>
import MobileHeader from "@/components/MobileHeader.vue";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";
import { ref } from 'vue';
import { useRouter} from "vue-router"

// 메뉴 데이터
const menus = ref([
  {
    placeholder: '대분류 선택',
    options: ['명시의무 위반', '광고주체', '부당한 표시·광고'],
    isActive: false,
    selected: '',
  },
  {
    placeholder: '중분류 선택',
    options: ['중개사무소', '개업공인중개사', '중개보조원', '중개대상물'],
    isActive: false,
    selected: '',
  },
  {
    placeholder: '소분류 선택',
    options: [
      '상호 명시위반',
      '소재지 명시위반',
      '전화번호 명시위반',
      '등록번호 명시위반',
    ],
    isActive: false,
    selected: '',
  },
]);

// 특정 메뉴를 열거나 닫는 함수
const toggleMenu = (index) => {
  menus.value = menus.value.map((menu, menuIndex) => ({
    ...menu,
    isActive: menuIndex === index ? !menu.isActive : false,
  }));
};

// 옵션 선택 함수
const selectOption = (menuIndex, option) => {
  menus.value[menuIndex].selected = option;
  menus.value[menuIndex].isActive = false;
};


const router = useRouter();

const onClickMoveMap = () => {
  router.push({ path: "/mobile_map"});
}
</script>

<style scoped>
.select-menu {
  width: 100%;
}
.select-menu .select-btn {
  display: flex;
  height: 25px;
  background: #fff;
  padding: 20px;
  font-size: 1rem;
  font-weight: 400;
  border-radius: 8px;
  align-items: center;
  cursor: pointer;
  justify-content: space-between;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.select-btn i {
  font-size: 25px;
  transition: 0.3s;
}
.select-menu.active .select-btn i {
  transform: rotate(-180deg);
}
.select-menu .options {
  position: relative;
  padding: 20px;
  margin-top: 10px;
  border-radius: 8px;
  background: #fff;
  box-shadow: 0 0 3px rgba(0, 0, 0, 0.1);
  display: none;
}
.select-menu.active .options {
  display: block;
}
.options .option {
  display: flex;
  height: 30px;
  cursor: pointer;
  padding: 0 16px;
  border-radius: 8px;
  align-items: center;
  background: #fff;
}
.options .option:hover {
  background: #f2f2f2;
}
.option i {
  font-size: 25px;
  margin-right: 12px;
}
.option .option-text {
  font-size: 12px;
  color: #333;
}

.btn-report {
  color: var(--color-bg-white);
  background: var(--color-bg-blue1);
}

.form-check-input:checked {
  background-color: var(--color-bg-third);
  border-color: var(--color-bg-third);
}

.btn-group {
  padding-bottom: 7rem;
}
</style>
