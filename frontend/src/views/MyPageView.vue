<template>
  <component :is="renderHeader()" />
  <div class="p-3">
    <ul class="container-sub-menu d-flex fs-4 p-3">
      <li v-for="(menu, index) in menuList" :key="index" :class="['p-2', { selected: selectPageNum === index }]"
        @click="onClickSelectPage(index)">
        {{ menu }}
      </li>
    </ul>
    <!--0: 내매물  -->
    <div v-if="selectPageNum === 0">
      <div class="d-flex mt-3">
        <div class="property-container p-5 flex-grow-1">
          <div class="d-flex w-100 justify-content-between">
            <form data-v-c44d4e75="" class="form-inline">
              <div data-v-c44d4e75="" class="input-group" style="width: 220px">
                <input data-v-c44d4e75="" class="form-control" type="search" placeholder="매물을 검색하세요."
                  aria-label="Search" /><button data-v-c44d4e75="" class="btn btn-outline-primary" type="submit">
                  <i data-v-c44d4e75="" class="bi bi-search"></i>
                </button>
              </div>
            </form>
            <div>
              <button @click="onClickOpenRegisterPrp" class="btn btn-primary me-2">
                매물등록
              </button>
              <button class="btn btn-danger">매물삭제</button>
            </div>
          </div>
          <div class="card-container mt-5">
            <CardProperty :properties="saleListByAgent"></CardProperty>
          </div>
        </div>
        <div class="flex-grow-1 util-container">
          <div class="chat-container w-100 ps-5 pe-5">
            <div class="flex-column flex-lg-row-auto w-100 mb-10 mb-lg-0">
              <!--begin::Contacts-->
              <div class="card card-flush">
                <!--begin::Card header-->
                <div class="card-header pt-7" id="kt_chat_contacts_header">
                  <!--begin::Form-->
                  <form class="w-100 position-relative" autocomplete="off">
                    <!--begin::Icon-->
                    <i
                      class="ki-duotone ki-magnifier fs-3 text-gray-500 position-absolute top-50 ms-5 translate-middle-y">
                      <span class="path1"></span>
                      <span class="path2"></span>
                    </i>
                    <!--end::Icon-->
                    <!--begin::Input-->
                    <input type="text" class="form-control form-control-solid px-13" name="search" value=""
                      placeholder="Search by username or email..." />
                    <!--end::Input-->
                  </form>
                  <!--end::Form-->
                </div>
                <!--end::Card header-->
                <!--begin::Card body-->
                <div class="card-body pt-5 h-50" id="kt_chat_contacts_body">
                  <!--begin::List-->
                  <div class="scroll-y me-n5 pe-5 h-100" data-kt-scroll="true"
                    data-kt-scroll-activate="{default: false, lg: true}" data-kt-scroll-max-height="auto"
                    data-kt-scroll-dependencies="#kt_header, #kt_app_header, #kt_toolbar, #kt_app_toolbar, #kt_footer, #kt_app_footer, #kt_chat_contacts_header"
                    data-kt-scroll-wrappers="#kt_content, #kt_app_content, #kt_chat_contacts_body"
                    data-kt-scroll-offset="5px">
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-danger text-danger fs-6 fw-bolder">M</span>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Melody Macy</a>
                          <div class="fw-semibold text-muted">
                            melody@altbox.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">1 week</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Max Smith</a>
                          <div class="fw-semibold text-muted">max@kt.com</div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                        <span class="badge badge-sm badge-circle badge-light-success">6</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                          <div class="symbol-badge bg-success start-100 top-100 border-4 h-8px w-8px ms-n2 mt-n2"></div>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Sean Bean</a>
                          <div class="fw-semibold text-muted">
                            sean@dellito.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                        <span class="badge badge-sm badge-circle badge-light-success">2</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Brian Cox</a>
                          <div class="fw-semibold text-muted">
                            brian@exchange.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">1 day</span>
                        <span class="badge badge-sm badge-circle badge-light-success">2</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-warning text-warning fs-6 fw-bolder">C</span>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Mikaela Collins</a>
                          <div class="fw-semibold text-muted">mik@pex.com</div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Francis Mitcham</a>
                          <div class="fw-semibold text-muted">
                            f.mit@kpmg.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">3 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-danger text-danger fs-6 fw-bolder">O</span>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Olivia Wild</a>
                          <div class="fw-semibold text-muted">
                            olivia@corpmail.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-primary text-primary fs-6 fw-bolder">N</span>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Neil Owen</a>
                          <div class="fw-semibold text-muted">
                            owen.neil@gmail.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">3 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Dan Wilson</a>
                          <div class="fw-semibold text-muted">
                            dam@consilting.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">1 day</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-danger text-danger fs-6 fw-bolder">E</span>
                          <div class="symbol-badge bg-success start-100 top-100 border-4 h-8px w-8px ms-n2 mt-n2"></div>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Emma Bold</a>
                          <div class="fw-semibold text-muted">
                            emma@intenso.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">20 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                  </div>
                  <!--end::List-->
                </div>
                <!--end::Card body-->
              </div>
              <!--end::Contacts-->
            </div>
          </div>
        </div>
      </div>
      <RegisterPrpModal></RegisterPrpModal>
    </div>
    <!--1: 관심매물   -->
    <div v-else-if="selectPageNum === 1">
      <div class="d-flex mt-3">
        <div class="property-container p-5 flex-grow-1">
          <div class="d-flex w-100 justify-content-between">
            <div data-v-c44d4e75="" class="input-group" style="width: 220px">
              <input data-v-c44d4e75="" class="form-control" type="search" placeholder="매물을 검색하세요."
                aria-label="Search" /><button data-v-c44d4e75="" class="btn btn-outline-primary" type="submit">
                <i data-v-c44d4e75="" class="bi bi-search"></i>
              </button>
            </div>

            <div class="d-flex">
              <div class="card-toolbar me-10" style="position: relative">
                <!--begin::Daterangepicker(defined in src/js/layout/app.js)-->
                <div data-kt-daterangepicker="true" data-kt-daterangepicker-opens="left"
                  class="btn btn-flex btn-sm btn-light d-flex align-items-center px-4 w-100" data-kt-initialized="1"
                  @click="onClickCalender">
                  <!--begin::Display range-->
                  <div class="text-gray-600 fw-bold fs-2">2024년 9월 1일</div>
                  <!--end::Display range-->
                  <i class="ki-duotone ki-calendar-8 text-gray-500 lh-0 fs-2 ms-2 me-0">
                    <span class="path1"></span>
                    <span class="path2"></span>
                    <span class="path3"></span>
                    <span class="path4"></span>
                    <span class="path5"></span>
                    <span class="path6"></span>
                  </i>
                </div>
                <!--end::Daterangepicker-->
                <div class="flatpickr-calendar rangeMode animate arrowTop arrowLeft" :class="{ open: isCalenderDrop }"
                  tabindex="-1" style="top: 4rem; right: 10px">
                  <Calender></Calender>
                </div>
              </div>
              <div class="card-toolbar me-10" style="position: relative">
                <!--begin::Daterangepicker(defined in src/js/layout/app.js)-->
                <div data-kt-daterangepicker="true" data-kt-daterangepicker-opens="left"
                  class="btn btn-flex btn-sm btn-light d-flex align-items-center px-4 w-100" data-kt-initialized="1"
                  @click="onClickDateDrop">
                  <!--begin::Display range-->
                  <div class="text-gray-600 fw-bold fs-2">-- : --</div>
                  <!--end::Display range-->
                  <i class="ms-4 bi bi-clock-fill text-gray-600"></i>
                </div>
                <!--end::Daterangepicker-->
                <div class="flatpickr-calendar rangeMode animate arrowTop arrowLeft datepicker"
                  :class="{ open: isDateDrop }" tabindex="-1" style="top: 4rem; right: -18px">
                  <div class="d-flex">
                    <ul class="timeList">
                      <li v-for="(time, index) in times" :key="index">
                        {{ time }}
                      </li>
                    </ul>
                    <div class="d-flex flex-column ms-4">
                      <button class="btn">AM</button>
                      <button class="btn btn-light-primary">PM</button>
                    </div>
                  </div>
                </div>
              </div>
              <button class="btn btn-primary">매물보기 신청</button>
            </div>
          </div>
          <div class="card-container mt-5">
            <CardProperty :properties="selectPageNum === 0 ? saleListByAgent : interestPrpList
              "></CardProperty>
          </div>
        </div>
        <div class="flex-grow-1 util-container">
          <div class="chat-container w-100 ps-5 pe-5">
            <div class="flex-column flex-lg-row-auto w-100 mb-10 mb-lg-0">
              <!--begin::Contacts-->
              <div class="card card-flush">
                <!--begin::Card header-->
                <div class="card-header pt-7" id="kt_chat_contacts_header">
                  <!--begin::Form-->
                  <form class="w-100 position-relative" autocomplete="off">
                    <!--begin::Icon-->
                    <i
                      class="ki-duotone ki-magnifier fs-3 text-gray-500 position-absolute top-50 ms-5 translate-middle-y">
                      <span class="path1"></span>
                      <span class="path2"></span>
                    </i>
                    <!--end::Icon-->
                    <!--begin::Input-->
                    <input type="text" class="form-control form-control-solid px-13" name="search" value=""
                      placeholder="Search by username or email..." />
                    <!--end::Input-->
                  </form>
                  <!--end::Form-->
                </div>
                <!--end::Card header-->
                <!--begin::Card body-->
                <div class="card-body pt-5 h-50" id="kt_chat_contacts_body">
                  <!--begin::List-->
                  <div class="scroll-y me-n5 pe-5 h-100" data-kt-scroll="true"
                    data-kt-scroll-activate="{default: false, lg: true}" data-kt-scroll-max-height="auto"
                    data-kt-scroll-dependencies="#kt_header, #kt_app_header, #kt_toolbar, #kt_app_toolbar, #kt_footer, #kt_app_footer, #kt_chat_contacts_header"
                    data-kt-scroll-wrappers="#kt_content, #kt_app_content, #kt_chat_contacts_body"
                    data-kt-scroll-offset="5px">
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-danger text-danger fs-6 fw-bolder">M</span>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Melody Macy</a>
                          <div class="fw-semibold text-muted">
                            melody@altbox.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">1 week</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Max Smith</a>
                          <div class="fw-semibold text-muted">max@kt.com</div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                        <span class="badge badge-sm badge-circle badge-light-success">6</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                          <div class="symbol-badge bg-success start-100 top-100 border-4 h-8px w-8px ms-n2 mt-n2"></div>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Sean Bean</a>
                          <div class="fw-semibold text-muted">
                            sean@dellito.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                        <span class="badge badge-sm badge-circle badge-light-success">2</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Brian Cox</a>
                          <div class="fw-semibold text-muted">
                            brian@exchange.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">1 day</span>
                        <span class="badge badge-sm badge-circle badge-light-success">2</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-warning text-warning fs-6 fw-bolder">C</span>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Mikaela Collins</a>
                          <div class="fw-semibold text-muted">mik@pex.com</div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Francis Mitcham</a>
                          <div class="fw-semibold text-muted">
                            f.mit@kpmg.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">3 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-danger text-danger fs-6 fw-bolder">O</span>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Olivia Wild</a>
                          <div class="fw-semibold text-muted">
                            olivia@corpmail.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-primary text-primary fs-6 fw-bolder">N</span>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Neil Owen</a>
                          <div class="fw-semibold text-muted">
                            owen.neil@gmail.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">3 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <img alt="Pic" src="/assets/img/user01.png" />
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Dan Wilson</a>
                          <div class="fw-semibold text-muted">
                            dam@consilting.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">1 day</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                    <!--begin::Separator-->
                    <div class="separator separator-dashed d-none"></div>
                    <!--end::Separator-->
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span class="symbol-label bg-light-danger text-danger fs-6 fw-bolder">E</span>
                          <div class="symbol-badge bg-success start-100 top-100 border-4 h-8px w-8px ms-n2 mt-n2"></div>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a href="#" class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Emma Bold</a>
                          <div class="fw-semibold text-muted">
                            emma@intenso.com
                          </div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">20 hrs</span>
                      </div>
                      <!--end::Lat seen-->
                    </div>
                    <!--end::User-->
                  </div>
                  <!--end::List-->
                </div>
                <!--end::Card body-->
              </div>
              <!--end::Contacts-->
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--2: 매물보기 현황    -->
    <div v-else-if="selectPageNum === 2">
      <div class="card card-flush h-lg-100">
        <!--begin::Header-->
        <div class="card-header pt-7">
          <!--begin::Title-->
          <h3 class="card-title align-items-start flex-column">
            <span class="card-label fw-bold text-gray-800">신고내역</span>
          </h3>
          <!--end::Title-->
          <!--begin::Toolbar-->
          <div class="card-toolbar">
            <!--begin::Daterangepicker(defined in src/js/layout/app.js)-->

            <!--end::Daterangepicker-->
          </div>
          <!--end::Toolbar-->
        </div>
        <!--end::Header-->
        <!--begin::Body-->
        <div class="card-body pt-6">
          <!--begin::Table container-->
          <div class="table-responsive">
            <!--begin::Table-->
            <table class="table table-row-dashed align-middle gs-0 gy-3 my-0">
              <!--begin::Table head-->
              <thead>
                <tr class="fs-7 fw-bold text-gray-500 border-bottom-0">
                  <th class="p-0 text-start">매물정보</th>
                  <th class="p-0 text-end">주소</th>
                  <th class="p-0 text-end">가격</th>
                  <th class="p-0 text-end pe-12">작성자</th>
                  <th class="p-0 pb-3 w-125px text-end pe-7">신고날짜</th>
                  <th class="p-0 pb-3 w-50px text-end">삭제</th>
                </tr>
              </thead>
              <!--end::Table head-->
              <!--begin::Table body-->
              <tbody>
                <tr>
                  <td>
                    <div class="d-flex align-items-center">
                      <div class="symbol symbol-50px me-3">
                        <img src="assets/img/home.jpg" class="" alt="" />
                      </div>
                      <div class="d-flex justify-content-start flex-column">
                        <a href="apps/projects/users.html"
                          class="text-gray-800 fw-bold text-hover-primary mb-1 fs-6">풀옵션, 2호선 도보1분 살기좋은집</a>
                      </div>
                    </div>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">도림로 47길 25</span>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">1000/50</span>
                  </td>
                  <td class="text-end">
                    <!--begin::Label-->
                    <span>따뜻한공인중개사</span>
                  </td>
                  <td class="text-end">
                    <span>2024.10.27</span>
                  </td>
                  <td class="text-end">
                    <a href="#" class="btn btn-sm btn-icon btn-bg-light btn-active-color-primary w-30px h-30px">
                      <i class="bi bi-trash"></i>
                    </a>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="d-flex align-items-center">
                      <div class="symbol symbol-50px me-3">
                        <img src="assets/img/home.jpg" class="" alt="" />
                      </div>
                      <div class="d-flex justify-content-start flex-column">
                        <a href="apps/projects/users.html"
                          class="text-gray-800 fw-bold text-hover-primary mb-1 fs-6">풀옵션, 2호선 도보1분 살기좋은집</a>
                      </div>
                    </div>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">도림로 47길 25</span>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">1000/50</span>
                  </td>
                  <td class="text-end">
                    <!--begin::Label-->
                    <span>따뜻한공인중개사</span>
                  </td>
                  <td class="text-end">
                    <span>2024.10.27</span>
                  </td>
                  <td class="text-end">
                    <a href="#" class="btn btn-sm btn-icon btn-bg-light btn-active-color-primary w-30px h-30px">
                      <i class="bi bi-trash"></i>
                    </a>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="d-flex align-items-center">
                      <div class="symbol symbol-50px me-3">
                        <img src="assets/img/home.jpg" class="" alt="" />
                      </div>
                      <div class="d-flex justify-content-start flex-column">
                        <a href="apps/projects/users.html"
                          class="text-gray-800 fw-bold text-hover-primary mb-1 fs-6">풀옵션, 2호선 도보1분 살기좋은집</a>
                      </div>
                    </div>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">도림로 47길 25</span>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">1000/50</span>
                  </td>
                  <td class="text-end">
                    <!--begin::Label-->
                    <span>따뜻한공인중개사</span>
                  </td>
                  <td class="text-end">
                    <span>2024.10.27</span>
                  </td>
                  <td class="text-end">
                    <a href="#" class="btn btn-sm btn-icon btn-bg-light btn-active-color-primary w-30px h-30px">
                      <i class="bi bi-trash"></i>
                    </a>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="d-flex align-items-center">
                      <div class="symbol symbol-50px me-3">
                        <img src="assets/img/home.jpg" class="" alt="" />
                      </div>
                      <div class="d-flex justify-content-start flex-column">
                        <a href="apps/projects/users.html"
                          class="text-gray-800 fw-bold text-hover-primary mb-1 fs-6">풀옵션, 2호선 도보1분 살기좋은집</a>
                      </div>
                    </div>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">도림로 47길 25</span>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">1000/50</span>
                  </td>
                  <td class="text-end">
                    <!--begin::Label-->
                    <span>따뜻한공인중개사</span>
                  </td>
                  <td class="text-end">
                    <span>2024.10.27</span>
                  </td>
                  <td class="text-end">
                    <a href="#" class="btn btn-sm btn-icon btn-bg-light btn-active-color-primary w-30px h-30px">
                      <i class="bi bi-trash"></i>
                    </a>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="d-flex align-items-center">
                      <div class="symbol symbol-50px me-3">
                        <img src="assets/img/home.jpg" class="" alt="" />
                      </div>
                      <div class="d-flex justify-content-start flex-column">
                        <a href="apps/projects/users.html"
                          class="text-gray-800 fw-bold text-hover-primary mb-1 fs-6">풀옵션, 2호선 도보1분 살기좋은집</a>
                      </div>
                    </div>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">도림로 47길 25</span>
                  </td>
                  <td class="text-end pe-0">
                    <span class="text-gray-600 fw-bold fs-6">1000/50</span>
                  </td>
                  <td class="text-end">
                    <!--begin::Label-->
                    <span>따뜻한공인중개사</span>
                  </td>
                  <td class="text-end">
                    <span>2024.10.27</span>
                  </td>
                  <td class="text-end">
                    <a href="#" class="btn btn-sm btn-icon btn-bg-light btn-active-color-primary w-30px h-30px">
                      <i class="bi bi-trash"></i>
                    </a>
                  </td>
                </tr>
              </tbody>
              <!--end::Table body-->
            </table>
          </div>
          <!--end::Table-->
        </div>
        <!--end: Card Body-->
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import CardProperty from "@/components/CardProperty.vue";
import RegisterPrpModal from "@/components/RegisterPrpModal.vue";

import { useMobileStore } from "@/stores/mobile";
import { useMyPageStore } from "@/stores/myPage.js";
import { useSaleStore } from "@/stores/property.js";
import { useRegisterPrpModalStore } from "@/stores/modal";

import { onMounted } from "vue";
import { createProperty } from "@/api/property.js";
import Calender from "@/components/Calender.vue";

const mobileStore = useMobileStore();
const { isMobile } = storeToRefs(mobileStore);

const changeMypageStore = useMyPageStore();
const { selectPageNum } = storeToRefs(changeMypageStore);
const menuList = changeMypageStore.menuList;

const saleStore = useSaleStore();
const { saleListByAgent, interestPrpList } = storeToRefs(saleStore);

const userPk = sessionStorage.getItem("userPk");

onMounted(() => {
  if (selectPageNum.value === 0) {
    saleStore.fetchSaleListByAgent(userPk);
  } else if (selectPageNum.value === 1) {
    saleStore.fetchInterestPrp(userPk);
  }
});

const onClickSelectPage = async (num) => {
  selectPageNum.value = num;
  if (num === 1) {
    await saleStore.fetchInterestPrp(userPk);
  }
};

// 모달
const storeModal = useRegisterPrpModalStore();
const onClickOpenRegisterPrp = () => {
  storeModal.openModal();
};

const onClickRegisterPrp = async () => {
  await createProperty(userPk);
  await saleStore.fetchSaleListByAgent(userPk);
};

const renderHeader = () => {
  if (isMobile.value) {
    return MobileHeader;
  } else {
    return Header;
  }
};

const times = ref([]);

// 초기 시간을 설정합니다.
let hour = 9;
let minute = 0;

// 30분 단위로 시간을 증가시키며 `times` 배열에 추가합니다.
for (let i = 0; i < 48; i++) {
  // 하루에 48번 반복
  // 시간을 포맷팅하여 배열에 추가합니다.
  const formattedTime = `${String(hour).padStart(2, "0")}:${String(minute).padStart(2, "0")}`;
  times.value.push(formattedTime);

  // 30분씩 증가
  minute += 30;
  if (minute >= 60) {
    minute = 0;
    hour += 1;
  }

  // 시간이 24시를 넘어가면 0시로 초기화
  if (hour >= 24) {
    hour = 0;
  }
}

const isCalenderDrop = ref(false);
const isDateDrop = ref(false);

function onClickCalender() {
  isCalenderDrop.value = !isCalenderDrop.value;
}

function onClickDateDrop() {
  isDateDrop.value = !isDateDrop.value;
}
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

.property-container {
  flex-basis: 70%;
}

.util-container {
  flex-basis: 30%;
}

.chat-container {
  height: 80%;
  overflow: scroll;
}

.flatpickr-calendar {
  min-width: 400px;
}

.datepicker {
  padding: 10px;
  min-width: 0;
  width: auto !important;
  font-family: inherit;
  border: 0;
  border-radius: 0;
  box-shadow: var(--bs-dropdown-box-shadow);
  background-color: var(--bs-body-bg);
  border-radius: 0.475rem;
}

.flatpickr-calendar {
  overflow: scroll;
}

.datepicker.open {
  max-height: 300px;
}

.timeList li {
  padding: 4px;
  cursor: pointer;
}
</style>
npm
