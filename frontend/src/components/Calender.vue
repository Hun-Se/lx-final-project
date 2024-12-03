<template>
  <div class="modal-body">
    <div
      class="menu-state-bg menu-extended overflow-hidden overflow-lg-visible"
      data-kt-menu-dismiss="true"
    >
      <!--  캘린더 코드   -->
      <section class="ftco-section">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="calendar-section">
                <div class="row no-gutters">
                  <div class="">
                    <div class="calendar calendar-first" id="calendar_first">
                      <div class="calendar_header">
                        <button class="switch-month switch-left">
                          <i class="fa fa-chevron-left"></i>
                        </button>
                        <h2></h2>
                        <button class="switch-month switch-right">
                          <i class="fa fa-chevron-right"></i>
                        </button>
                      </div>
                      <div class="calendar_weekdays"></div>
                      <div class="calendar_content"></div>
                    </div>
                  </div>
                </div>
                <!-- End Row -->
              </div>
              <!-- End Calendar -->
            </div>
          </div>
        </div>
        <div class="text-center">
          <button @click="closeModal" class="btn btn-primary w-30">
            날짜 선택 완료
          </button>
        </div>
      </section>
    </div>
  </div>
</template>

<script setup>
import Header from "@/components/Header.vue";
import { onMounted, ref } from "vue";
import { initializeCalendar } from "@/lib/calender.js";

onMounted(() => {
  initializeCalendar();
});

let isDropDown = ref(false);
function onClickDate() {
  isDropDown.value = !isDropDown.value;
  console.log(isDropDown.value);
}
</script>

<style>
a {
  text-decoration: none;
}

li {
  list-style: none;
}

.calendar-section {
  margin-bottom: 20px;
}

.calendar,
.calendar_weekdays,
.calendar_content {
  max-width: 585px;
}

.calendar {
  /*float: left;*/
  margin: auto;
  //min-width: 320px;
  font-weight: 400;
  background: #fff;
  padding: 10px;
  -webkit-box-shadow: 0px 4px 26px -22px rgba(0, 0, 0, 0.14);
  -moz-box-shadow: 0px 4px 26px -22px rgba(0, 0, 0, 0.14);
  box-shadow: 0px 4px 26px -22px rgba(0, 0, 0, 0.14);
}

.calendar_weekdays {
  color: #aaa;
  font-weight: 300;
}

.calendar_weekdays div {
  display: inline-block;
  vertical-align: top;
  color: #000;
  font-weight: 700;
}

.calendar_content,
.calendar_weekdays,
.calendar_header {
  position: relative;
}

.calendar_content:after,
.calendar_weekdays:after,
.calendar_header:after {
  content: " ";
  display: table;
  clear: both;
}

.calendar_weekdays div,
.calendar_content div {
  /*border: 1px dotted #999;*/
  width: 14.28571%;
  height: 68px;
  line-height: 69px;
  overflow: hidden;
  text-align: center;
  background-color: transparent;
}

.calendar_content div {
  float: left;
  margin-left: -1px;
  margin-top: -1px;
  border: 1px solid transparent;
  border-radius: 5px;
}

.calendar_content div:hover {
  border: 1px solid #ededed;
  /*line-height: 68px;*/
  cursor: pointer;
}

.calendar_content div.blank:hover {
  cursor: default;
  border: none;
}

.calendar_content div.past-date {
  cursor: initial;
  color: #d5d5d5;
}

.calendar_content div.today {
  font-weight: 700;
  font-size: 18px;
  color: #fff;
  background-color: #bd83ce;
}

.calendar_content div.selected {
  background-color: rgba(153, 153, 161, 0.2);
  /*rgba(170, 170, 176, .5) #aaaab0*/
  border: 1px solid white;
}

.calendar_header {
  width: 100%;
  text-align: center;
  position: relative;
  height: 55px;
  display: table;
  vertical-align: middle;
}

.calendar_header h2 {
  margin-top: 15px;
  padding: 0;
  font-weight: 700;
  font-size: 18px;
  color: #000 !important;
  text-transform: uppercase;
}

button.switch-month {
  background-color: transparent;
  padding: 0;
  outline: none;
  border: none;
  height: 40px;
  width: 40px;
  position: absolute;
  top: 50%;
  -webkit-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
  color: #cccccc;
  -webkit-transition: color 0.2s;
  -o-transition: color 0.2s;
  transition: color 0.2s;
}
button.switch-month.switch-left {
  left: 0;
}
button.switch-month.switch-right {
  right: 0;
}

button.switch-month:hover {
  color: #bd83ce;
}

button.switch-month:active {
  background-color: transparent;
}
</style>
