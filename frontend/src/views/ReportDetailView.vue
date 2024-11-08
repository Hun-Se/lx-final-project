<script setup></script>

<template>
  <nav>
    <div>
      <div class="logo">
        <span class="logo-name">믿음집</span>
      </div>
      <div class="sidebar">
        <div class="logo">
          <i class="bx bx-menu menu-icon"></i>
          <span class="logo-name">믿음집</span>
        </div>
        <div class="sidebar-content">
          <ul class="lists">
            <li class="list">
              <a href="#" class="nav-link">
                <i class="bx bx-home-alt icon"></i>
                <span class="link">대시보드</span>
              </a>
            </li>
            <li class="list">
              <a href="#" class="nav-link">
                <i class="bx bx-bar-chart-alt-2 icon"></i>
                <span class="link">신고접수내역</span>
              </a>
            </li>
            <li class="list">
              <a href="#" class="nav-link">
                <i class="bx bx-message-rounded icon"></i>
                <span class="link">메시지전송내역</span>
              </a>
            </li>
            <li class="list">
              <a href="#" class="nav-link">
                <i class="bx bx-pie-chart-alt-2 icon"></i>
                <span class="link">신고처리현황</span>
              </a>
            </li>
            <li class="list">
              <a href="#" class="nav-link">
                <i class="bx bx-heart icon"></i>
                <span class="link">업무처리</span>
              </a>
            </li>
            <li class="list">
              <a href="#" class="nav-link">
                <i class="bx bx-folder-open icon"></i>
                <span class="link">파일보관함</span>
              </a>
            </li>
          </ul>

          <div class="bottom-cotent">
            <li class="list">
              <a href="#" class="nav-link">
                <i class="bx bx-bell icon"></i>
                <span class="link">알림</span>
              </a>
            </li>
            <li class="list">
              <a href="#" class="nav-link">
                <i class="bx bx-cog icon"></i>
                <span class="link">설정</span>
              </a>
            </li>
            <li class="list">
              <a href="#" class="nav-link">
                <i class="bx bx-log-out icon"></i>
                <span class="link">로그아웃</span>
              </a>
            </li>
          </div>
        </div>
      </div>
    </div>
  </nav>
  <section class="overlay"></section>
  <div class="p-10" style="margin-top: 5rem; margin-left: 300px">
    <div class="row">
      <div class="col-7">
        <div class="card mb-5 mb-xl-10">
          <div class="card-body pt-9">
            <!--begin::Details-->
            <div class="d-flex flex-wrap flex-sm-nowrap">
              <!--begin: Pic-->
              <!--end::Pic-->
              <!--begin::Info-->
              <div class="flex-grow-1">
                <!--begin::Title-->
                <div
                  class="d-flex justify-content-between align-items-start flex-wrap mb-2"
                >
                  <!--begin::User-->
                  <div class="d-flex flex-column" style="height: 385px">
                    <!--begin::Name-->
                    <div class="d-flex align-items-center mb-2">
                      <span
                        class="badge p-5 rounded-pill text-bg-primary fs-2 fw-bold me-1 text-white"
                        >신고접수번호 C51743</span
                      >
                    </div>
                    <!--end::Name-->
                    <!--begin::Info-->
                    <div class="d-flex flex-column mt-5">
                      <!--begin::Label-->

                      <!--end::Label-->
                      <!--begin::Item-->
                      <div class="d-flex text-gray-800 mb-3 fs-6">
                        <!--begin::Accountname-->
                        <div class="fw-semibold pe-5">신고일자:</div>
                        <!--end::Accountname-->
                        <!--begin::Label-->
                        <div class="text-end fw-norma">2024-11-01</div>
                        <!--end::Label-->
                      </div>
                      <!--end::Item-->
                      <!--begin::Item-->
                      <div class="d-flex text-gray-800 mb-3 fs-6">
                        <!--begin::Accountnumber-->
                        <div class="fw-semibold pe-5">신고사유:</div>
                        <!--end::Accountnumber-->
                        <!--begin::Number-->
                        <span
                          class="badge badge-light-danger fw-bold me-2 px-4 py-3"
                          >명시의무 위반</span
                        >
                        <span
                          class="badge badge-light-warning fw-bold me-2 px-4 py-3"
                          >중개사무소</span
                        >
                        <span
                          class="badge badge-light-success fw-bold me-2 px-4 py-3"
                          >상호명시 위반</span
                        >
                        <!--end::Number-->
                      </div>
                      <!--end::Item-->
                      <!--begin::Item-->
                      <div class="d-flex text-gray-800 fs-6">
                        <!--begin::Code-->
                        <div class="fw-semibold pe-5">증빙자료:</div>
                        <!--end::Code-->
                        <!--begin::Label-->
                        <table class="styled-table">
                          <thead>
                            <tr>
                              <th>광고화면</th>
                              <th>녹취</th>
                              <th>문자내용</th>
                              <th>기타자료</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <td>Dom</td>
                              <td>6000</td>
                            </tr>
                            <tr class="active-row">
                              <td>Melissa</td>
                              <td>5150</td>
                            </tr>
                            <!-- and so on... -->
                          </tbody>
                        </table>
                        <!--end::Label-->
                      </div>
                      <!--end::Item-->
                    </div>
                    <!--end::Info-->
                  </div>
                  <!--end::User-->
                  <!--begin::Actions-->
                  <div class="d-flex my-4 mty">
                    <a
                      href="#"
                      class="btn btn-sm btn-light me-2"
                      id="kt_user_follow_button"
                    >
                      <i class="ki-duotone ki-check fs-3 d-none"></i>
                      <!--begin::Indicator label-->
                      <span class="indicator-label">수정</span>
                      <!--end::Indicator label-->
                      <!--begin::Indicator progress-->
                      <!--end::Indicator progress-->
                    </a>
                    <!--begin::Menu-->
                    <div class="me-0">
                      <button
                        class="btn btn-sm btn-icon btn-bg-light btn-active-color-primary"
                        data-kt-menu-trigger="click"
                        data-kt-menu-placement="bottom-end"
                      >
                        <i class="ki-solid ki-dots-horizontal fs-2x"></i>
                      </button>
                      <!--begin::Menu 3-->
                      <div
                        class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-800 menu-state-bg-light-primary fw-semibold w-200px py-3"
                        data-kt-menu="true"
                      >
                        <!--begin::Heading-->
                        <div class="menu-item px-3">
                          <div
                            class="menu-content text-muted pb-2 px-3 fs-7 text-uppercase"
                          >
                            Payments
                          </div>
                        </div>
                        <!--end::Heading-->
                        <!--begin::Menu item-->
                        <div class="menu-item px-3">
                          <a href="#" class="menu-link px-3">Create Invoice</a>
                        </div>
                        <!--end::Menu item-->
                        <!--begin::Menu item-->
                        <div class="menu-item px-3">
                          <a href="#" class="menu-link flex-stack px-3"
                            >Create Payment
                            <span
                              class="ms-2"
                              data-bs-toggle="tooltip"
                              aria-label="Specify a target name for future usage and reference"
                              data-bs-original-title="Specify a target name for future usage and reference"
                              data-kt-initialized="1"
                            >
                              <i class="ki-duotone ki-information fs-6">
                                <span class="path1"></span>
                                <span class="path2"></span>
                                <span class="path3"></span>
                              </i> </span
                          ></a>
                        </div>
                        <!--end::Menu item-->
                        <!--begin::Menu item-->
                        <div class="menu-item px-3">
                          <a href="#" class="menu-link px-3">Generate Bill</a>
                        </div>
                        <!--end::Menu item-->
                        <!--begin::Menu item-->
                        <div
                          class="menu-item px-3"
                          data-kt-menu-trigger="hover"
                          data-kt-menu-placement="right-end"
                        >
                          <a href="#" class="menu-link px-3">
                            <span class="menu-title">Subscription</span>
                            <span class="menu-arrow"></span>
                          </a>
                          <!--begin::Menu sub-->
                          <div class="menu-sub menu-sub-dropdown w-175px py-4">
                            <!--begin::Menu item-->
                            <div class="menu-item px-3">
                              <a href="#" class="menu-link px-3">Plans</a>
                            </div>
                            <!--end::Menu item-->
                            <!--begin::Menu item-->
                            <div class="menu-item px-3">
                              <a href="#" class="menu-link px-3">Billing</a>
                            </div>
                            <!--end::Menu item-->
                            <!--begin::Menu item-->
                            <div class="menu-item px-3">
                              <a href="#" class="menu-link px-3">Statements</a>
                            </div>
                            <!--end::Menu item-->
                            <!--begin::Menu separator-->
                            <div class="separator my-2"></div>
                            <!--end::Menu separator-->
                            <!--begin::Menu item-->
                            <div class="menu-item px-3">
                              <div class="menu-content px-3">
                                <!--begin::Switch-->
                                <label
                                  class="form-check form-switch form-check-custom form-check-solid"
                                >
                                  <!--begin::Input-->
                                  <input
                                    class="form-check-input w-30px h-20px"
                                    type="checkbox"
                                    value="1"
                                    checked="checked"
                                    name="notifications"
                                  />
                                  <!--end::Input-->
                                  <!--end::Label-->
                                  <span class="form-check-label text-muted fs-6"
                                    >Recuring</span
                                  >
                                  <!--end::Label-->
                                </label>
                                <!--end::Switch-->
                              </div>
                            </div>
                            <!--end::Menu item-->
                          </div>
                          <!--end::Menu sub-->
                        </div>
                        <!--end::Menu item-->
                        <!--begin::Menu item-->
                        <div class="menu-item px-3 my-1">
                          <a href="#" class="menu-link px-3">Settings</a>
                        </div>
                        <!--end::Menu item-->
                      </div>
                      <!--end::Menu 3-->
                    </div>
                    <!--end::Menu-->
                  </div>
                  <!--end::Actions-->
                </div>
                <!--end::Title-->
                <!--begin::Stats-->
                <!--end::Stats-->
              </div>
              <!--end::Info-->
            </div>
            <!--end::Details-->
          </div>
        </div>
      </div>
      <div class="col-5">
        <div class="card mb-5 mb-xl-10">
          <div class="card-body pt-9">
            <!--begin::Details-->
            <div class="d-flex flex-wrap flex-sm-nowrap h-100">
              <!--begin: Pic-->
              <div class="me-7 mb-4">
                <div
                  class="symbol symbol-100px symbol-lg-160px symbol-fixed position-relative"
                >
                  <img src="assets/media/avatars/300-1.jpg" alt="image" />
                  <div
                    class="position-absolute translate-middle bottom-0 start-100 mb-6 bg-success rounded-circle border border-4 border-body h-20px w-20px"
                  ></div>
                </div>
              </div>
              <!--end::Pic-->
              <!--begin::Info-->
              <div class="flex-grow-1">
                <!--begin::Title-->
                <div
                  class="d-flex justify-content-between align-items-start flex-wrap mb-2"
                >
                  <!--begin::User-->
                  <div class="d-flex flex-column">
                    <div class="d-flex">
                      <div class="form-check">
                        <input
                          class="form-check-input"
                          type="radio"
                          name="flexRadioDefault"
                          id="flexRadioDefault1"
                        />
                        <label
                          class="form-check-label"
                          for="flexRadioDefault1"
                          style="line-height: 20px"
                        >
                          피신고 대상자 정보 조회
                        </label>
                      </div>
                      <div class="form-check ms-3">
                        <input
                          class="form-check-input"
                          type="radio"
                          name="flexRadioDefault"
                          id="flexRadioDefault2"
                          checked
                        />
                        <label
                          class="form-check-label"
                          for="flexRadioDefault2"
                          style="line-height: 20px"
                        >
                          허위매물 정보 조회
                        </label>
                      </div>
                    </div>
                    <!--begin::Name-->
                    <div class="d-flex align-items-center mt-3 mb-2">
                      <a
                        href="#"
                        class="text-gray-900 text-hover-primary fs-2 fw-bold me-1"
                        >김중개</a
                      >
                      <a href="#">
                        <i class="ki-duotone ki-verify fs-1 text-primary">
                          <span class="path1"></span>
                          <span class="path2"></span>
                        </i>
                      </a>
                    </div>
                    <!--end::Name-->
                    <!--begin::Info-->
                    <div class="d-flex flex-wrap fw-semibold fs-6 mb-4 pe-2">
                      <a
                        href="#"
                        class="d-flex align-items-center text-gray-500 text-hover-primary me-5 mb-2"
                      >
                        <i class="ki-duotone ki-profile-circle fs-4 me-1">
                          <span class="path1"></span>
                          <span class="path2"></span>
                          <span class="path3"></span> </i
                        >한마음공인중개사</a
                      >
                      <a
                        href="#"
                        class="d-flex align-items-center text-gray-500 text-hover-primary me-5 mb-2"
                      >
                        <i class="ki-duotone ki-geolocation fs-4 me-1">
                          <span class="path1"></span>
                          <span class="path2"></span> </i
                        >강남구 언주로 703</a
                      >
                      <a
                        href="#"
                        class="d-flex align-items-center text-gray-500 text-hover-primary mb-2"
                      >
                        <i class="ki-duotone ki-sms fs-4">
                          <span class="path1"></span>
                          <span class="path2"></span> </i
                        >kim72840@gmail.com</a
                      >
                    </div>
                    <!--end::Info-->
                  </div>
                  <!--end::User-->
                  <!--begin::Actions-->
                  <div class="d-flex my-4">
                    <div class="me-0">
                      <button
                        class="btn btn-sm btn-icon btn-bg-light btn-active-color-primary"
                        data-kt-menu-trigger="click"
                        data-kt-menu-placement="bottom-end"
                      >
                        <i class="ki-solid ki-dots-horizontal fs-2x"></i>
                      </button>
                      <!--begin::Menu 3-->
                      <div
                        class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-800 menu-state-bg-light-primary fw-semibold w-200px py-3"
                        data-kt-menu="true"
                      >
                        <!--begin::Heading-->
                        <div class="menu-item px-3">
                          <div
                            class="menu-content text-muted pb-2 px-3 fs-7 text-uppercase"
                          >
                            Payments
                          </div>
                        </div>
                        <!--end::Heading-->
                        <!--begin::Menu item-->
                        <div class="menu-item px-3">
                          <a href="#" class="menu-link px-3">Create Invoice</a>
                        </div>
                        <!--end::Menu item-->
                        <!--begin::Menu item-->
                        <div class="menu-item px-3">
                          <a href="#" class="menu-link flex-stack px-3"
                            >Create Payment
                            <span
                              class="ms-2"
                              data-bs-toggle="tooltip"
                              aria-label="Specify a target name for future usage and reference"
                              data-bs-original-title="Specify a target name for future usage and reference"
                              data-kt-initialized="1"
                            >
                              <i class="ki-duotone ki-information fs-6">
                                <span class="path1"></span>
                                <span class="path2"></span>
                                <span class="path3"></span>
                              </i> </span
                          ></a>
                        </div>
                        <!--end::Menu item-->
                        <!--begin::Menu item-->
                        <div class="menu-item px-3">
                          <a href="#" class="menu-link px-3">Generate Bill</a>
                        </div>
                        <!--end::Menu item-->
                        <!--begin::Menu item-->
                        <div
                          class="menu-item px-3"
                          data-kt-menu-trigger="hover"
                          data-kt-menu-placement="right-end"
                        >
                          <a href="#" class="menu-link px-3">
                            <span class="menu-title">Subscription</span>
                            <span class="menu-arrow"></span>
                          </a>
                          <!--begin::Menu sub-->
                          <div class="menu-sub menu-sub-dropdown w-175px py-4">
                            <!--begin::Menu item-->
                            <div class="menu-item px-3">
                              <a href="#" class="menu-link px-3">Plans</a>
                            </div>
                            <!--end::Menu item-->
                            <!--begin::Menu item-->
                            <div class="menu-item px-3">
                              <a href="#" class="menu-link px-3">Billing</a>
                            </div>
                            <!--end::Menu item-->
                            <!--begin::Menu item-->
                            <div class="menu-item px-3">
                              <a href="#" class="menu-link px-3">Statements</a>
                            </div>
                            <!--end::Menu item-->
                            <!--begin::Menu separator-->
                            <div class="separator my-2"></div>
                            <!--end::Menu separator-->
                            <!--begin::Menu item-->
                            <div class="menu-item px-3">
                              <div class="menu-content px-3">
                                <!--begin::Switch-->
                                <label
                                  class="form-check form-switch form-check-custom form-check-solid"
                                >
                                  <!--begin::Input-->
                                  <input
                                    class="form-check-input w-30px h-20px"
                                    type="checkbox"
                                    value="1"
                                    checked="checked"
                                    name="notifications"
                                  />
                                  <!--end::Input-->
                                  <!--end::Label-->
                                  <span class="form-check-label text-muted fs-6"
                                    >Recuring</span
                                  >
                                  <!--end::Label-->
                                </label>
                                <!--end::Switch-->
                              </div>
                            </div>
                            <!--end::Menu item-->
                          </div>
                          <!--end::Menu sub-->
                        </div>
                        <!--end::Menu item-->
                        <!--begin::Menu item-->
                        <div class="menu-item px-3 my-1">
                          <a href="#" class="menu-link px-3">Settings</a>
                        </div>
                        <!--end::Menu item-->
                      </div>
                      <!--end::Menu 3-->
                    </div>
                    <!--end::Menu-->
                  </div>
                  <!--end::Actions-->
                </div>
                <div>
                  <div>
                    <span class="fs-3"
                      ><span
                        class="p-3 fs-3 badge rounded-pill text-bg-primary text-white"
                        >중개사번호</span
                      >
                      12345</span
                    >
                    <span class="ms-5 badge badge-light-info fw-bold px-4 py-3">
                      신고회수 17</span
                    >
                  </div>
                  <div class="mt-5">
                    <span class="fs-3"
                      ><span
                        class="p-3 fs-3 badge rounded-pill text-bg-primary text-white"
                        >중개사무소 주소</span
                      >
                      강남구 언주로 703</span
                    >
                  </div>
                  <div class="mt-5">
                    <span class="fs-3"
                      ><span
                        class="p-3 fs-3 badge rounded-pill text-bg-primary text-white"
                        >중개사무소명</span
                      >
                      한마음 공인중개사
                    </span>
                  </div>
                  <div class="mt-5">
                    <span class="fs-3"
                      ><span
                        class="p-3 fs-3 badge rounded-pill text-bg-primary text-white"
                        >중개사무소 연락처</span
                      >
                      010-5004-8626</span
                    >
                  </div>
                </div>
                <!--end::Title-->
                <!--begin::Stats-->

                <!--end::Stats-->
              </div>
              <!--end::Info-->
            </div>
            <!--end::Details-->
            <!--begin::Navs-->

            <!--begin::Navs-->
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-7" style="height: 600px">
        <div class="card h-100" id="kt_chat_messenger">
          <!--begin::Card header-->
          <div class="card-header" id="kt_chat_messenger_header">
            <!--begin::Title-->
            <div class="card-title">
              <!--begin::Users-->
              <div class="symbol-group symbol-hover"></div>
              <h3>녹취록 스크립트</h3>
            </div>
            <!--end::Title-->
            <!--begin::Card toolbar-->
            <div class="card-toolbar">
              <!--begin::Menu-->
              <div class="me-n3">
                <button
                  class="btn btn-sm btn-icon btn-active-light-primary"
                  data-kt-menu-trigger="click"
                  data-kt-menu-placement="bottom-end"
                >
                  <i class="ki-duotone ki-dots-square fs-2">
                    <span class="path1"></span>
                    <span class="path2"></span>
                    <span class="path3"></span>
                    <span class="path4"></span>
                  </i>
                </button>
                <!--begin::Menu 3-->
                <div
                  class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-800 menu-state-bg-light-primary fw-semibold w-200px py-3"
                  data-kt-menu="true"
                >
                  <!--begin::Heading-->
                  <div class="menu-item px-3">
                    <div
                      class="menu-content text-muted pb-2 px-3 fs-7 text-uppercase"
                    >
                      Contacts
                    </div>
                  </div>
                  <!--end::Heading-->
                  <!--begin::Menu item-->
                  <div class="menu-item px-3">
                    <a
                      href="#"
                      class="menu-link px-3"
                      data-bs-toggle="modal"
                      data-bs-target="#kt_modal_users_search"
                      >Add Contact</a
                    >
                  </div>
                  <!--end::Menu item-->
                  <!--begin::Menu item-->
                  <div class="menu-item px-3">
                    <a
                      href="#"
                      class="menu-link flex-stack px-3"
                      data-bs-toggle="modal"
                      data-bs-target="#kt_modal_invite_friends"
                      >Invite Contacts
                      <span
                        class="ms-2"
                        data-bs-toggle="tooltip"
                        aria-label="Specify a contact email to send an invitation"
                        data-bs-original-title="Specify a contact email to send an invitation"
                        data-kt-initialized="1"
                      >
                        <i class="ki-duotone ki-information fs-7">
                          <span class="path1"></span>
                          <span class="path2"></span>
                          <span class="path3"></span>
                        </i> </span
                    ></a>
                  </div>
                  <!--end::Menu item-->
                  <!--begin::Menu item-->
                  <div
                    class="menu-item px-3"
                    data-kt-menu-trigger="hover"
                    data-kt-menu-placement="right-start"
                  >
                    <a href="#" class="menu-link px-3">
                      <span class="menu-title">Groups</span>
                      <span class="menu-arrow"></span>
                    </a>
                    <!--begin::Menu sub-->
                    <div class="menu-sub menu-sub-dropdown w-175px py-4">
                      <!--begin::Menu item-->
                      <div class="menu-item px-3">
                        <a
                          href="#"
                          class="menu-link px-3"
                          data-bs-toggle="tooltip"
                          data-bs-original-title="Coming soon"
                          data-kt-initialized="1"
                          >Create Group</a
                        >
                      </div>
                      <!--end::Menu item-->
                      <!--begin::Menu item-->
                      <div class="menu-item px-3">
                        <a
                          href="#"
                          class="menu-link px-3"
                          data-bs-toggle="tooltip"
                          data-bs-original-title="Coming soon"
                          data-kt-initialized="1"
                          >Invite Members</a
                        >
                      </div>
                      <!--end::Menu item-->
                      <!--begin::Menu item-->
                      <div class="menu-item px-3">
                        <a
                          href="#"
                          class="menu-link px-3"
                          data-bs-toggle="tooltip"
                          data-bs-original-title="Coming soon"
                          data-kt-initialized="1"
                          >Settings</a
                        >
                      </div>
                      <!--end::Menu item-->
                    </div>
                    <!--end::Menu sub-->
                  </div>
                  <!--end::Menu item-->
                  <!--begin::Menu item-->
                  <div class="menu-item px-3 my-1">
                    <a
                      href="#"
                      class="menu-link px-3"
                      data-bs-toggle="tooltip"
                      data-bs-original-title="Coming soon"
                      data-kt-initialized="1"
                      >Settings</a
                    >
                  </div>
                  <!--end::Menu item-->
                </div>
                <!--end::Menu 3-->
              </div>
              <!--end::Menu-->
            </div>
            <!--end::Card toolbar-->
          </div>
          <!--end::Card header-->
          <!--begin::Card body-->
          <div class="card-body" id="kt_chat_messenger_body">
            <!--begin::Messages-->
            <div
              class="scroll-y me-n5 pe-5 h-100 h-lg-auto"
              data-kt-element="messages"
              data-kt-scroll="true"
              data-kt-scroll-activate="{default: false, lg: true}"
              data-kt-scroll-dependencies="#kt_header, #kt_app_header, #kt_app_toolbar, #kt_toolbar, #kt_footer, #kt_app_footer, #kt_chat_messenger_header, #kt_chat_messenger_footer"
              data-kt-scroll-wrappers="#kt_content, #kt_app_content, #kt_chat_messenger_body"
              data-kt-scroll-offset="5px"
            >
              <!--begin::Message(in)-->
              <div class="d-flex justify-content-start mb-10">
                <!--begin::Wrapper-->
                <div class="d-flex flex-column align-items-start">
                  <!--begin::User-->
                  <div class="d-flex align-items-center mb-2">
                    <!--begin::Avatar-->

                    <!--end::Avatar-->
                    <!--begin::Details-->
                    <div class="ms-3">
                      <a
                        href="#"
                        class="fs-5 fw-bold text-gray-900 text-hover-primary me-1"
                        >중개인</a
                      >
                    </div>
                    <!--end::Details-->
                  </div>
                  <!--end::User-->
                  <!--begin::Text-->
                  <div
                    class="p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start"
                    data-kt-element="message-text"
                  >
                    문의 주신 매물은 없고요, 제가 다른 매물 보여드릴께요 일단
                    한번 오시겠어요??
                  </div>
                  <!--end::Text-->
                </div>
                <!--end::Wrapper-->
              </div>

              <div class="d-flex justify-content-end mb-10">
                <!--begin::Wrapper-->
                <div class="d-flex flex-column align-items-end">
                  <!--begin::User-->
                  <div class="d-flex align-items-center mb-2">
                    <!--begin::Details-->
                    <div class="me-3">
                      <span class="text-muted fs-7 mb-1">2 Hours</span>
                      <a
                        href="#"
                        class="fs-5 fw-bold text-gray-900 text-hover-primary ms-1"
                        >신고자</a
                      >
                    </div>
                    <!--end::Details-->
                    <!--begin::Avatar-->

                    <!--end::Avatar-->
                  </div>
                  <!--end::User-->
                  <!--begin::Text-->
                  <div
                    class="p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end"
                    data-kt-element="message-text"
                  >
                    아니요 허위 매물 신고 하겠습니다.
                  </div>
                  <!--end::Text-->
                </div>
                <!--end::Wrapper-->
              </div>
            </div>
            <!--end::Messages-->
          </div>
          <!--end::Card body-->
          <!--begin::Card footer-->
          <div class="card-footer pt-4" id="kt_chat_messenger_footer">
            <!--begin::Input-->
            <textarea
              class="form-control form-control-flush mb-3"
              rows="1"
              data-kt-element="input"
              placeholder="Type a message"
            ></textarea>
            <!--end::Input-->
            <!--begin:Toolbar-->
            <div class="d-flex flex-stack">
              <!--begin::Actions-->
              <div class="d-flex align-items-center me-2">
                <button
                  class="btn btn-sm btn-icon btn-active-light-primary me-1"
                  type="button"
                  data-bs-toggle="tooltip"
                  aria-label="Coming soon"
                  data-bs-original-title="Coming soon"
                  data-kt-initialized="1"
                >
                  <i class="ki-duotone ki-paper-clip fs-3"></i>
                </button>
                <button
                  class="btn btn-sm btn-icon btn-active-light-primary me-1"
                  type="button"
                  data-bs-toggle="tooltip"
                  aria-label="Coming soon"
                  data-bs-original-title="Coming soon"
                  data-kt-initialized="1"
                >
                  <i class="ki-duotone ki-exit-up fs-3">
                    <span class="path1"></span>
                    <span class="path2"></span>
                  </i>
                </button>
              </div>
              <!--end::Actions-->
              <!--begin::Send-->
              <button
                class="btn btn-primary"
                type="button"
                data-kt-element="send"
              >
                Send
              </button>
              <!--end::Send-->
            </div>
            <!--end::Toolbar-->
          </div>
          <!--end::Card footer-->
        </div>
      </div>
      <div class="col-5">
        <div class="card h-xl-100" dir="ltr">
          <!--begin::Body-->
          <div class="card-body d-flex flex-column flex-center">
            <!--begin::Heading-->
            <div class="mb-2">
              <!--begin::Title-->
              <h1 class="fw-semibold text-gray-800 text-center lh-lg">
                Have you tried <br />new
                <span class="fw-bolder">Mobile Application ?</span>
              </h1>
              <!--end::Title-->
              <!--begin::Illustration-->
              <div class="py-10 text-center">
                <img
                  src="assets/media/svg/illustrations/easy/1.svg"
                  class="theme-light-show w-200px"
                  alt=""
                />
                <img
                  src="assets/media/svg/illustrations/easy/1-dark.svg"
                  class="theme-dark-show w-200px"
                  alt=""
                />
              </div>
              <!--end::Illustration-->
            </div>
            <!--end::Heading-->
            <!--begin::Links-->
            <div class="text-center mb-1">
              <!--begin::Link-->
              <a
                class="btn btn-sm btn-primary me-2"
                data-bs-target="#kt_modal_new_card"
                data-bs-toggle="modal"
                >접수하기</a
              >
            </div>
            <!--end::Links-->
          </div>
          <!--end::Body-->
        </div>
      </div>
    </div>
  </div>
</template>

<style>
/* Google Fonts - Poppins */
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}
nav {
  position: fixed;
  top: 0;
  left: 0;
  height: 70px;
  width: 100%;
  display: flex;
  align-items: center;
  background: #4070f4;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.1);
}
nav .logo {
  display: flex;
  align-items: center;
  margin: 0 24px;
}
.logo .menu-icon {
  color: #333;
  font-size: 24px;
  margin-right: 14px;
  cursor: pointer;
}
.logo .logo-name {
  color: #333;
  font-size: 22px;
  font-weight: 500;
}
nav .sidebar {
  position: fixed;
  top: 0;
  /* left: 100%; */
  height: 100%;
  width: 260px;
  padding: 20px 0;
  background-color: #fff;
  box-shadow: 0 5px 1px rgba(0, 0, 0, 0.1);
  transition: all 0.4s ease;
}
nav.open .sidebar {
  left: 0;
}
.sidebar .sidebar-content {
  display: flex;
  height: 100%;
  flex-direction: column;
  justify-content: space-between;
  padding: 30px 16px;
}
.sidebar-content .list {
  list-style: none;
}
.list .nav-link {
  display: flex;
  align-items: center;
  margin: 8px 0;
  padding: 14px 12px;
  border-radius: 8px;
  text-decoration: none;
}
.lists .nav-link:hover {
  background-color: #4070f4;
}
.nav-link .icon {
  margin-right: 14px;
  font-size: 20px;
  color: #707070;
}
.nav-link .link {
  font-size: 16px;
  color: #707070;
  font-weight: 400;
}
.lists .nav-link:hover .icon,
.lists .nav-link:hover .link {
  color: #fff;
}
.overlay {
  position: fixed;
  top: 0;
  left: -100%;
  height: 1000vh;
  width: 200%;
  opacity: 0;
  pointer-events: none;
  transition: all 0.4s ease;
  background: rgba(0, 0, 0, 0.3);
}
nav.open ~ .overlay {
  opacity: 1;
  left: 260px;
  pointer-events: auto;
}

.styled-table {
  border-collapse: collapse;
  font-size: 0.9em;
  font-family: sans-serif;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
  background-color: var(--color-bg-blue3);
  color: #ffffff;
  text-align: left;
}

.styled-table th,
.styled-table td {
  padding: 12px 15px;
}

.styled-table tbody tr {
  border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
  background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
  border-bottom: 2px solid #009879;
}

.styled-table tbody tr.active-row {
  font-weight: bold;
  color: #009879;
}
</style>
