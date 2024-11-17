<template>
  <MobileHeader></MobileHeader>
  <div class="container mt-20">
    <label class="fs-4 fw-semibold form-label mt-3">
      <span class="required">신고 분류 선택</span>
    </label>
    <!-- 셀렉트박스   -->
    <div class="select-menu mt-5">
      <div class="select-btn form-select">
        <span class="sBtn-text">대분류 선택</span>
        <i class="bx bx-chevron-down"></i>
      </div>
      <ul class="options">
        <li class="option">
          <i class="bx bxl-github" style="color: #171515"></i>
          <span class="option-text">Github</span>
        </li>
        <li class="option">
          <i class="bx bxl-instagram-alt" style="color: #e1306c"></i>
          <span class="option-text">Instagram</span>
        </li>
        <li class="option">
          <i class="bx bxl-linkedin-square" style="color: #0e76a8"></i>
          <span class="option-text">Linkedin</span>
        </li>
        <li class="option">
          <i class="bx bxl-facebook-circle" style="color: #4267b2"></i>
          <span class="option-text">Facebook</span>
        </li>
        <li class="option">
          <i class="bx bxl-twitter" style="color: #1da1f2"></i>
          <span class="option-text">Twitter</span>
        </li>
      </ul>
    </div>
    <div class="select-menu mt-5">
      <div class="select-btn form-select">
        <span class="sBtn-text">중분류 선택</span>
        <i class="bx bx-chevron-down"></i>
      </div>
      <ul class="options">
        <li class="option">
          <i class="bx bxl-github" style="color: #171515"></i>
          <span class="option-text">Github</span>
        </li>
        <li class="option">
          <i class="bx bxl-instagram-alt" style="color: #e1306c"></i>
          <span class="option-text">Instagram</span>
        </li>
        <li class="option">
          <i class="bx bxl-linkedin-square" style="color: #0e76a8"></i>
          <span class="option-text">Linkedin</span>
        </li>
        <li class="option">
          <i class="bx bxl-facebook-circle" style="color: #4267b2"></i>
          <span class="option-text">Facebook</span>
        </li>
        <li class="option">
          <i class="bx bxl-twitter" style="color: #1da1f2"></i>
          <span class="option-text">Twitter</span>
        </li>
      </ul>
    </div>
    <div class="select-menu mt-5">
      <div class="select-btn form-select">
        <span class="sBtn-text">소분류 선택</span>
        <i class="bx bx-chevron-down"></i>
      </div>
      <ul class="options">
        <li class="option">
          <span class="option-text">명시의무 위반</span>
        </li>
        <li class="option">
          <i class="bx bxl-instagram-alt" style="color: #e1306c"></i>
          <span class="option-text">광고 주체</span>
        </li>
        <li class="option">
          <i class="bx bxl-linkedin-square" style="color: #0e76a8"></i>
          <span class="option-text">부당한 표시·광고</span>
        </li>
      </ul>
    </div>
    <label class="fs-4 fw-semibold form-label mt-10">
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
          checked
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
          checked
        />
        <label class="form-check-label ms-2" for="flexCheckChecked">
          기타자료
        </label>
      </div>
    </div>
    <label class="fs-4 fw-semibold form-label mt-10">
      <span class="">자료 첨부</span>
    </label>
    <div class="mt-3 mb-3">
      <input class="form-control" type="file" id="formFileMultiple" multiple />
    </div>
    <div class="btn-group d-flex justify-content-center p-10">
      <button class="btn btn-light-dark">취소</button>
      <button class="btn btn-report">확인</button>
    </div>
  </div>

  <MobileBottomTapBar></MobileBottomTapBar>
</template>

<script setup>
import MobileHeader from "@/components/MobileHeader.vue";
import { onMounted } from "vue";
import MobileBottomTapBar from "@/components/MobileBottomTapBar.vue";

onMounted(() => {
  const select_els = document.querySelectorAll(".select-menu");

  select_els.forEach((el) => {});

  const optionMenu = document.querySelector(".select-menu"),
    selectBtn = optionMenu.querySelector(".select-btn"),
    options = optionMenu.querySelectorAll(".option"),
    sBtn_text = optionMenu.querySelector(".sBtn-text");
  selectBtn.addEventListener("click", () =>
    optionMenu.classList.toggle("active"),
  );
  options.forEach((option) => {
    option.addEventListener("click", () => {
      let selectedOption = option.querySelector(".option-text").innerText;
      sBtn_text.innerText = selectedOption;
      optionMenu.classList.remove("active");
    });
  });
});
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
  height: 55px;
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
  font-size: 18px;
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
</style>
