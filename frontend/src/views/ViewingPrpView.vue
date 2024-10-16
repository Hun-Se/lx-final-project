<template>
  <component :is="renderHeader()" />
  <div class="p-3">
    <ul class="container-sub-menu d-flex fs-4 p-3">
      <li
        v-for="(menu, index) in menuList"
        :key="index"
        :class="['p-2', { selected: selectPageNum === index }]"
        @click="onClickSelectPage(index)"
      >
        {{ menu }}
      </li>
    </ul>
    <div v-if="selectPageNum === 0">
      <div class="d-flex mt-3">
        <div class="property-container flex-grow-1">
          <div class="d-flex w-100 justify-content-between">
            <form data-v-c44d4e75="" class="form-inline">
              <div data-v-c44d4e75="" class="input-group" style="width: 220px">
                <input
                  data-v-c44d4e75=""
                  class="form-control"
                  type="search"
                  placeholder="매물을 검색하세요."
                  aria-label="Search"
                /><button
                  data-v-c44d4e75=""
                  class="btn btn-outline-primary"
                  type="submit"
                >
                  <i data-v-c44d4e75="" class="bi bi-search"></i>
                </button>
              </div>
            </form>
            <div>
              <button class="btn btn-primary me-2">매물등록</button>
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
                      class="ki-duotone ki-magnifier fs-3 text-gray-500 position-absolute top-50 ms-5 translate-middle-y"
                    >
                      <span class="path1"></span>
                      <span class="path2"></span>
                    </i>
                    <!--end::Icon-->
                    <!--begin::Input-->
                    <input
                      type="text"
                      class="form-control form-control-solid px-13"
                      name="search"
                      value=""
                      placeholder="Search by username or email..."
                    />
                    <!--end::Input-->
                  </form>
                  <!--end::Form-->
                </div>
                <!--end::Card header-->
                <!--begin::Card body-->
                <div class="card-body pt-5 h-50" id="kt_chat_contacts_body">
                  <!--begin::List-->
                  <div
                    class="scroll-y me-n5 pe-5 h-100"
                    data-kt-scroll="true"
                    data-kt-scroll-activate="{default: false, lg: true}"
                    data-kt-scroll-max-height="auto"
                    data-kt-scroll-dependencies="#kt_header, #kt_app_header, #kt_toolbar, #kt_app_toolbar, #kt_footer, #kt_app_footer, #kt_chat_contacts_header"
                    data-kt-scroll-wrappers="#kt_content, #kt_app_content, #kt_chat_contacts_body"
                    data-kt-scroll-offset="5px"
                  >
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span
                            class="symbol-label bg-light-danger text-danger fs-6 fw-bolder"
                            >M</span
                          >
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Melody Macy</a
                          >
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
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Max Smith</a
                          >
                          <div class="fw-semibold text-muted">max@kt.com</div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                        <span
                          class="badge badge-sm badge-circle badge-light-success"
                          >6</span
                        >
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
                          <div
                            class="symbol-badge bg-success start-100 top-100 border-4 h-8px w-8px ms-n2 mt-n2"
                          ></div>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Sean Bean</a
                          >
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
                        <span
                          class="badge badge-sm badge-circle badge-light-success"
                          >2</span
                        >
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
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Brian Cox</a
                          >
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
                        <span
                          class="badge badge-sm badge-circle badge-light-success"
                          >2</span
                        >
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
                          <span
                            class="symbol-label bg-light-warning text-warning fs-6 fw-bolder"
                            >C</span
                          >
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Mikaela Collins</a
                          >
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
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Francis Mitcham</a
                          >
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
                          <span
                            class="symbol-label bg-light-danger text-danger fs-6 fw-bolder"
                            >O</span
                          >
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Olivia Wild</a
                          >
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
                          <span
                            class="symbol-label bg-light-primary text-primary fs-6 fw-bolder"
                            >N</span
                          >
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Neil Owen</a
                          >
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
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Dan Wilson</a
                          >
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
                          <span
                            class="symbol-label bg-light-danger text-danger fs-6 fw-bolder"
                            >E</span
                          >
                          <div
                            class="symbol-badge bg-success start-100 top-100 border-4 h-8px w-8px ms-n2 mt-n2"
                          ></div>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Emma Bold</a
                          >
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
    <div v-else-if="selectPageNum === 1">
      <div class="d-flex mt-3">
        <div class="property-container flex-grow-1">
          <div class="d-flex w-100 justify-content-between">
            <form data-v-c44d4e75="" class="form-inline">
              <div data-v-c44d4e75="" class="input-group" style="width: 220px">
                <input
                  data-v-c44d4e75=""
                  class="form-control"
                  type="search"
                  placeholder="매물을 검색하세요."
                  aria-label="Search"
                /><button
                  data-v-c44d4e75=""
                  class="btn btn-outline-primary"
                  type="submit"
                >
                  <i data-v-c44d4e75="" class="bi bi-search"></i>
                </button>
              </div>
            </form>
          </div>
          <div class="card-container mt-5">
            <CardProperty
              :properties="
                selectPageNum === 0 ? saleListByAgent : intersetPrpList
              "
            ></CardProperty>
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
                      class="ki-duotone ki-magnifier fs-3 text-gray-500 position-absolute top-50 ms-5 translate-middle-y"
                    >
                      <span class="path1"></span>
                      <span class="path2"></span>
                    </i>
                    <!--end::Icon-->
                    <!--begin::Input-->
                    <input
                      type="text"
                      class="form-control form-control-solid px-13"
                      name="search"
                      value=""
                      placeholder="Search by username or email..."
                    />
                    <!--end::Input-->
                  </form>
                  <!--end::Form-->
                </div>
                <!--end::Card header-->
                <!--begin::Card body-->
                <div class="card-body pt-5 h-50" id="kt_chat_contacts_body">
                  <!--begin::List-->
                  <div
                    class="scroll-y me-n5 pe-5 h-100"
                    data-kt-scroll="true"
                    data-kt-scroll-activate="{default: false, lg: true}"
                    data-kt-scroll-max-height="auto"
                    data-kt-scroll-dependencies="#kt_header, #kt_app_header, #kt_toolbar, #kt_app_toolbar, #kt_footer, #kt_app_footer, #kt_chat_contacts_header"
                    data-kt-scroll-wrappers="#kt_content, #kt_app_content, #kt_chat_contacts_body"
                    data-kt-scroll-offset="5px"
                  >
                    <!--begin::User-->
                    <div class="d-flex flex-stack py-4">
                      <!--begin::Details-->
                      <div class="d-flex align-items-center">
                        <!--begin::Avatar-->
                        <div class="symbol symbol-45px symbol-circle">
                          <span
                            class="symbol-label bg-light-danger text-danger fs-6 fw-bolder"
                            >M</span
                          >
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Melody Macy</a
                          >
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
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Max Smith</a
                          >
                          <div class="fw-semibold text-muted">max@kt.com</div>
                        </div>
                        <!--end::Details-->
                      </div>
                      <!--end::Details-->
                      <!--begin::Lat seen-->
                      <div class="d-flex flex-column align-items-end ms-2">
                        <span class="text-muted fs-7 mb-1">5 hrs</span>
                        <span
                          class="badge badge-sm badge-circle badge-light-success"
                          >6</span
                        >
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
                          <div
                            class="symbol-badge bg-success start-100 top-100 border-4 h-8px w-8px ms-n2 mt-n2"
                          ></div>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Sean Bean</a
                          >
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
                        <span
                          class="badge badge-sm badge-circle badge-light-success"
                          >2</span
                        >
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
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Brian Cox</a
                          >
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
                        <span
                          class="badge badge-sm badge-circle badge-light-success"
                          >2</span
                        >
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
                          <span
                            class="symbol-label bg-light-warning text-warning fs-6 fw-bolder"
                            >C</span
                          >
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Mikaela Collins</a
                          >
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
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Francis Mitcham</a
                          >
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
                          <span
                            class="symbol-label bg-light-danger text-danger fs-6 fw-bolder"
                            >O</span
                          >
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Olivia Wild</a
                          >
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
                          <span
                            class="symbol-label bg-light-primary text-primary fs-6 fw-bolder"
                            >N</span
                          >
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Neil Owen</a
                          >
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
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Dan Wilson</a
                          >
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
                          <span
                            class="symbol-label bg-light-danger text-danger fs-6 fw-bolder"
                            >E</span
                          >
                          <div
                            class="symbol-badge bg-success start-100 top-100 border-4 h-8px w-8px ms-n2 mt-n2"
                          ></div>
                        </div>
                        <!--end::Avatar-->
                        <!--begin::Details-->
                        <div class="ms-5">
                          <a
                            href="#"
                            class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2"
                            >Emma Bold</a
                          >
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
    <div v-else="selectPageNum === 2">
      <h1>임장현황페이지입니다.</h1>
    </div>
  </div>
</template>

<script setup>
import { storeToRefs } from "pinia";
import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import CardProperty from "@/components/CardProperty.vue";

import { useMobileStore } from "@/stores/mobile";
import { useMyPageStore } from "@/stores/myPage.js";
import { useSaleStore } from "@/stores/property.js";
import { onMounted, ref } from "vue";

const mobileStore = useMobileStore();
const { isMobile } = storeToRefs(mobileStore);

const changeMypageStore = useMyPageStore();
const { selectPageNum } = storeToRefs(changeMypageStore);
const menuList = changeMypageStore.menuList;

const saleStore = useSaleStore();
const { saleListByAgent, intersetPrpList } = storeToRefs(saleStore);

// 세션스토리지에서 가져오는걸로 수정해야함
const user_pk = sessionStorage.getItem("userPk");

onMounted(() => {
  console.log(selectPageNum.value);
  if (selectPageNum.value === 0) {
    saleStore.fetchSaleListByAgent(user_pk);
  } else if (selectPageNum.value === 1) {
    saleStore.fetchIntersetPrp(user_pk);
  }
});

const onClickSelectPage = async (num) => {
  selectPageNum.value = num;
  if (num === 1) {
    const userPk = sessionStorage.getItem("userPk");
    await saleStore.fetchIntersetPrp(userPk);
  }
};

const renderHeader = () => {
  if (isMobile.value) {
    return MobileHeader;
  } else {
    return Header;
  }
};
</script>

<style scope>
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

.list-container {
  width: 220px;
}
</style>
