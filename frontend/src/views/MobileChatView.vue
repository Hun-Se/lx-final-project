<template>
  <component :is="renderHeader()" />
  <div class="card mt-10 mb-5 mb-xl-10">
    <div class="card-body pt-9 pb-0">
      <!--begin::Details-->
      <div class="d-flex">
        <!--begin: Pic-->
        <div class="me-7 mb-4">
          <div
            class="symbol symbol-100px symbol-lg-160px symbol-fixed position-relative"
          >
            <img src="assets/img/agent_example.webp" alt="image" />
            <div
              class="position-absolute translate-middle bottom-0 start-100 bg-success rounded-circle border border-4 border-body h-20px w-20px"
            ></div>
          </div>
        </div>
        <!--end::Pic-->
        <!--begin::Info-->
        <div class="flex-grow-1">
          <!--begin::Title-->
          <div class="d-flex justify-content-between align-items-start mb-2">
            <!--begin::User-->
            <div class="d-flex flex-column">
              <!--begin::Name-->
              <div class="d-flex align-items-center mb-2">
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
                  class="d-flex align-items-center text-gray-500 text-hover-primary me-5 mb-2 fs-7"
                >
                  <i class="ki-duotone ki-profile-circle fs-4 me-1">
                    <span class="path1"></span>
                    <span class="path2"></span>
                    <span class="path3"></span> </i
                  >한마음 공인중개사무소</a
                >
                <a
                  href="#"
                  class="d-flex align-items-center text-gray-500 text-hover-primary me-5 mb-2 fs-7"
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
                  >kim72840@gamil.com</a
                >
              </div>
              <!--end::Info-->
            </div>
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
                style=""
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
          </div>
        </div>
        <!--end::Info-->
      </div>
    </div>
  </div>
  <!-- 채팅 시작  -->
  <div>
    <div class="flex-lg-row-fluid ms-lg-7 ms-xl-10">
      <!--begin::Messenger-->
      <div class="card" id="kt_chat_messenger">
        <!--begin::Card header-->
        <div class="card-header" id="kt_chat_messenger_header">
          <!--begin::Title-->
          <div class="card-title">
            <!--begin::User-->
            <div class="d-flex justify-content-center flex-column me-3">
              <a
                href="#"
                class="fs-4 fw-bold text-gray-900 text-hover-primary me-1 mb-2 lh-1"
                >대화내용</a
              >
              <!--begin::Info-->
              <div class="mb-0 lh-1">
                <span
                  class="badge badge-success badge-circle w-10px h-10px me-1"
                ></span>
                <span class="fs-7 fw-semibold text-muted">활동중</span>
              </div>
              <!--end::Info-->
            </div>
            <!--end::User-->
          </div>
          <!--end::Title-->
          <!--begin::Card toolbar-->
          <div class="card-toolbar">
            <!--begin::Menu-->
            <div class="me-n3">
              <button
                class="btn btn-sm btn-icon btn-light-primary"
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
            class="scroll-y me-n5 pe-5 h-300px h-lg-auto"
            data-kt-element="messages"
            data-kt-scroll="true"
            data-kt-scroll-activate="{default: false, lg: true}"
            data-kt-scroll-max-height="auto"
            data-kt-scroll-dependencies="#kt_header, #kt_app_header, #kt_app_toolbar, #kt_toolbar, #kt_footer, #kt_app_footer, #kt_chat_messenger_header, #kt_chat_messenger_footer"
            data-kt-scroll-wrappers="#kt_content, #kt_app_content, #kt_chat_messenger_body"
            data-kt-scroll-offset="5px"
            style="max-height: 274px"
          >
            <!--begin::Message(in)-->
            <div class="d-flex justify-content-start mb-10">
              <!--begin::Wrapper-->
              <div class="d-flex flex-column align-items-start">
                <!--begin::User-->
                <div class="d-flex align-items-center mb-2">
                  <!--begin::Avatar-->
                  <div class="symbol symbol-35px symbol-circle">
                    <img alt="Pic" src="assets/media/avatars/300-25.jpg" />
                  </div>
                  <!--end::Avatar-->
                  <!--begin::Details-->
                  <div class="ms-3">
                    <a
                      href="#"
                      class="fs-5 fw-bold text-gray-900 text-hover-primary me-1"
                      >김중개</a
                    >
                    <span class="text-muted fs-7 mb-1">2 mins</span>
                  </div>
                  <!--end::Details-->
                </div>
                <!--end::User-->
                <!--begin::Text-->
                <div
                  class="p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start"
                  data-kt-element="message-text"
                >
                  How likely are 김지은 to recommend our company to 김지은r
                  friends and family ?
                </div>
                <!--end::Text-->
              </div>
              <!--end::Wrapper-->
            </div>
            <!--end::Message(in)-->
            <!--begin::Message(out)-->
            <div class="d-flex justify-content-end mb-10">
              <!--begin::Wrapper-->
              <div class="d-flex flex-column align-items-end">
                <!--begin::User-->
                <div class="d-flex align-items-center mb-2">
                  <!--begin::Details-->
                  <div class="me-3">
                    <span class="text-muted fs-7 mb-1">5 mins</span>
                    <a
                      href="#"
                      class="fs-5 fw-bold text-gray-900 text-hover-primary ms-1"
                      >김지은</a
                    >
                  </div>
                  <!--end::Details-->
                  <!--begin::Avatar-->
                  <div class="symbol symbol-35px symbol-circle">
                    <img alt="Pic" src="assets/media/avatars/300-1.jpg" />
                  </div>
                  <!--end::Avatar-->
                </div>
                <!--end::User-->
                <!--begin::Text-->
                <div
                  class="p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end"
                  data-kt-element="message-text"
                >
                  Hey there, we’re just writing to let 김지은 know that
                  김지은’ve been subscribed to a repository on GitHub.
                </div>
                <!--end::Text-->
              </div>
              <!--end::Wrapper-->
            </div>
            <!--end::Message(out)-->
            <!--begin::Message(in)-->
            <div class="d-flex justify-content-start mb-10">
              <!--begin::Wrapper-->
              <div class="d-flex flex-column align-items-start">
                <!--begin::User-->
                <div class="d-flex align-items-center mb-2">
                  <!--begin::Avatar-->
                  <div class="symbol symbol-35px symbol-circle">
                    <img alt="Pic" src="assets/media/avatars/300-25.jpg" />
                  </div>
                  <!--end::Avatar-->
                  <!--begin::Details-->
                  <div class="ms-3">
                    <a
                      href="#"
                      class="fs-5 fw-bold text-gray-900 text-hover-primary me-1"
                      >김중개</a
                    >
                    <span class="text-muted fs-7 mb-1">1 Hour</span>
                  </div>
                  <!--end::Details-->
                </div>
                <!--end::User-->
                <!--begin::Text-->
                <div
                  class="p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start"
                  data-kt-element="message-text"
                >
                  Ok, Understood!
                </div>
                <!--end::Text-->
              </div>
              <!--end::Wrapper-->
            </div>
            <!--end::Message(in)-->
            <!--begin::Message(out)-->
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
                      >김지은</a
                    >
                  </div>
                  <!--end::Details-->
                  <!--begin::Avatar-->
                  <div class="symbol symbol-35px symbol-circle">
                    <img alt="Pic" src="assets/media/avatars/300-1.jpg" />
                  </div>
                  <!--end::Avatar-->
                </div>
                <!--end::User-->
                <!--begin::Text-->
                <div
                  class="p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end"
                  data-kt-element="message-text"
                >
                  김지은’ll receive notifications for all issues, pull requests!
                </div>
                <!--end::Text-->
              </div>
              <!--end::Wrapper-->
            </div>
            <!--end::Message(out)-->
            <!--begin::Message(in)-->
            <div class="d-flex justify-content-start mb-10">
              <!--begin::Wrapper-->
              <div class="d-flex flex-column align-items-start">
                <!--begin::User-->
                <div class="d-flex align-items-center mb-2">
                  <!--begin::Avatar-->
                  <div class="symbol symbol-35px symbol-circle">
                    <img alt="Pic" src="assets/media/avatars/300-25.jpg" />
                  </div>
                  <!--end::Avatar-->
                  <!--begin::Details-->
                  <div class="ms-3">
                    <a
                      href="#"
                      class="fs-5 fw-bold text-gray-900 text-hover-primary me-1"
                      >김중개</a
                    >
                    <span class="text-muted fs-7 mb-1">3 Hours</span>
                  </div>
                  <!--end::Details-->
                </div>
                <!--end::User-->
                <!--begin::Text-->
                <div
                  class="p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start"
                  data-kt-element="message-text"
                >
                  김지은 can unwatch this repository immediately by clicking
                  here:
                  <a href="https://keenthemes.com">Keenthemes.com</a>
                </div>
                <!--end::Text-->
              </div>
              <!--end::Wrapper-->
            </div>
            <!--end::Message(in)-->
            <!--begin::Message(out)-->
            <div class="d-flex justify-content-end mb-10">
              <!--begin::Wrapper-->
              <div class="d-flex flex-column align-items-end">
                <!--begin::User-->
                <div class="d-flex align-items-center mb-2">
                  <!--begin::Details-->
                  <div class="me-3">
                    <span class="text-muted fs-7 mb-1">4 Hours</span>
                    <a
                      href="#"
                      class="fs-5 fw-bold text-gray-900 text-hover-primary ms-1"
                      >김지은</a
                    >
                  </div>
                  <!--end::Details-->
                  <!--begin::Avatar-->
                  <div class="symbol symbol-35px symbol-circle">
                    <img alt="Pic" src="assets/media/avatars/300-1.jpg" />
                  </div>
                  <!--end::Avatar-->
                </div>
                <!--end::User-->
                <!--begin::Text-->
                <div
                  class="p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end"
                  data-kt-element="message-text"
                >
                  Most purchased Business courses during this sale!
                </div>
                <!--end::Text-->
              </div>
              <!--end::Wrapper-->
            </div>
            <!--end::Message(out)-->
            <!--begin::Message(in)-->
            <div class="d-flex justify-content-start mb-10">
              <!--begin::Wrapper-->
              <div class="d-flex flex-column align-items-start">
                <!--begin::User-->
                <div class="d-flex align-items-center mb-2">
                  <!--begin::Avatar-->
                  <div class="symbol symbol-35px symbol-circle">
                    <img alt="Pic" src="assets/media/avatars/300-25.jpg" />
                  </div>
                  <!--end::Avatar-->
                  <!--begin::Details-->
                  <div class="ms-3">
                    <a
                      href="#"
                      class="fs-5 fw-bold text-gray-900 text-hover-primary me-1"
                      >김중개</a
                    >
                    <span class="text-muted fs-7 mb-1">5 Hours</span>
                  </div>
                  <!--end::Details-->
                </div>
                <!--end::User-->
                <!--begin::Text-->
                <div
                  class="p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start"
                  data-kt-element="message-text"
                >
                  Company BBQ to celebrate the last quater achievements and
                  goals. Food and drinks provided
                </div>
                <!--end::Text-->
              </div>
              <!--end::Wrapper-->
            </div>
            <!--end::Message(in)-->
            <!--begin::Message(template for out)-->
            <div
              class="d-flex justify-content-end mb-10 d-none"
              data-kt-element="template-out"
            >
              <!--begin::Wrapper-->
              <div class="d-flex flex-column align-items-end">
                <!--begin::User-->
                <div class="d-flex align-items-center mb-2">
                  <!--begin::Details-->
                  <div class="me-3">
                    <span class="text-muted fs-7 mb-1">Just now</span>
                    <a
                      href="#"
                      class="fs-5 fw-bold text-gray-900 text-hover-primary ms-1"
                      >김지은</a
                    >
                  </div>
                  <!--end::Details-->
                  <!--begin::Avatar-->
                  <div class="symbol symbol-35px symbol-circle">
                    <img alt="Pic" src="assets/media/avatars/300-1.jpg" />
                  </div>
                  <!--end::Avatar-->
                </div>
                <!--end::User-->
                <!--begin::Text-->
                <div
                  class="p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end"
                  data-kt-element="message-text"
                ></div>
                <!--end::Text-->
              </div>
              <!--end::Wrapper-->
            </div>
            <!--end::Message(template for out)-->
            <!--begin::Message(template for in)-->
            <div
              class="d-flex justify-content-start mb-10 d-none"
              data-kt-element="template-in"
            >
              <!--begin::Wrapper-->
              <div class="d-flex flex-column align-items-start">
                <!--begin::User-->
                <div class="d-flex align-items-center mb-2">
                  <!--begin::Avatar-->
                  <div class="symbol symbol-35px symbol-circle">
                    <img alt="Pic" src="assets/media/avatars/300-25.jpg" />
                  </div>
                  <!--end::Avatar-->
                  <!--begin::Details-->
                  <div class="ms-3">
                    <a
                      href="#"
                      class="fs-5 fw-bold text-gray-900 text-hover-primary me-1"
                      >김중개</a
                    >
                    <span class="text-muted fs-7 mb-1">Just now</span>
                  </div>
                  <!--end::Details-->
                </div>
                <!--end::User-->
                <!--begin::Text-->
                <div
                  class="p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start"
                  data-kt-element="message-text"
                >
                  Right before vacation season we have the next Big Deal for
                  김지은.
                </div>
                <!--end::Text-->
              </div>
              <!--end::Wrapper-->
            </div>
            <!--end::Message(template for in)-->
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
      <!--end::Messenger-->
    </div>
  </div>
  <!-- 채팅 끝  -->
</template>

<script setup>
import { ref } from "vue";
import { useChatStore } from "@/stores/chat";
import Header from "@/components/Header.vue";
import MobileHeader from "@/components/MobileHeader.vue";
import { useMobileStore } from "@/stores/mobile";
import { storeToRefs } from "pinia";

const store = useMobileStore();
const { isMobile } = storeToRefs(store);

const renderHeader = () => {
  if (isMobile.value) {
    return MobileHeader;
  } else {
    return Header;
  }
};

const chatStore = useChatStore();
const newMessage = ref("");

// 메시지 전송 처리
const submitMessage = () => {
  if (newMessage.value.trim() === "") return; // 빈 메시지 전송 방지
  chatStore.sendMessageToChat(newMessage.value); // 메시지 전송 로직
  newMessage.value = ""; // 입력창 비우기
};
</script>

<style scoped>
#conversation {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  width: 180ex;
  margin: 0 auto;
  max-width: 100%;
}

#response {
  max-height: 300px;
  overflow-y: auto;
  margin-bottom: 10px;
}

.chat-message {
  margin-bottom: 5px;
  padding: 8px;
  border-radius: 5px;
}

.my-message {
  background-color: #e7f4fe;
  align-self: flex-end;
  /* 사용자 메시지 오른쪽 정렬 */
  text-align: right;
  /* 사용자 메시지 오른쪽 정렬 */
}

.other-message {
  background-color: #fdecf8;
  align-self: flex-start;
  /* 상대방 메시지 왼쪽 정렬 */
  text-align: left;
  /* 상대방 메시지 왼쪽 정렬 */
}

.input-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
}

input {
  flex-grow: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
