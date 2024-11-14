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
          <img
            class="ms-10"
            src="/assets/img/logo_white.png"
            style="width: 70px"
          />
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

  <div class="p-10" style="margin-left: 300px">
    <div class="row gy-5 gx-xl-10">
      <!--begin::Col-->
      <div class="col-sm-6 col-xl-2 mb-xl-10">
        <!--begin::Card widget 2-->
        <div class="card h-lg-80">
          <!--begin::Body-->
          <div
            class="card-body d-flex justify-content-between align-items-start flex-column"
          >
            <!--begin::Icon-->
            <div class="m-0">
              <i class="ki-duotone ki-abstract-39 fs-2hx text-danger">
                <span class="path1"></span>
                <span class="path2"></span>
              </i>
            </div>
            <!--end::Icon-->
            <!--begin::Section-->
            <div class="d-flex flex-column my-7">
              <!--begin::Number-->
              <span class="fw-semibold fs-3x text-gray-800 lh-1 ls-n2"
                >327</span
              >
              <!--end::Number-->
              <!--begin::Follower-->
              <div class="m-0">
                <span class="fw-semibold fs-6 text-gray-500">접수내역</span>
              </div>
              <!--end::Follower-->
            </div>
            <!--end::Section-->
            <!--begin::Badge-->
            <!--end::Badge-->
          </div>
          <!--end::Body-->
        </div>
        <!--end::Card widget 2-->
      </div>
      <!--end::Col-->
      <!--begin::Col-->
      <div class="col-sm-6 col-xl-2 mb-xl-10">
        <!--begin::Card widget 2-->
        <div class="card h-lg-80">
          <!--begin::Body-->
          <div
            class="card-body d-flex justify-content-between align-items-start flex-column"
          >
            <!--begin::Icon-->
            <div class="m-0">
              <i class="ki-duotone ki-abstract-39 fs-2hx text-warning">
                <span class="path1"></span>
                <span class="path2"></span>
                <span class="path3"></span>
                <span class="path4"></span>
              </i>
            </div>
            <!--end::Icon-->
            <!--begin::Section-->
            <div class="d-flex flex-column my-7">
              <!--begin::Number-->
              <span class="fw-semibold fs-3x text-gray-800 lh-1 ls-n2"
                >121</span
              >
              <!--end::Number-->
              <!--begin::Follower-->
              <div class="m-0">
                <span class="fw-semibold fs-6 text-gray-500">처리 진행중</span>
              </div>
              <!--end::Follower-->
            </div>
            <!--end::Section-->
            <!--begin::Badge-->

            <!--end::Badge-->
          </div>
          <!--end::Body-->
        </div>
        <!--end::Card widget 2-->
      </div>
      <!--end::Col-->
      <!--begin::Col-->
      <div class="col-sm-6 col-xl-2 mb-xl-10">
        <!--begin::Card widget 2-->
        <div class="card h-lg-80">
          <!--begin::Body-->
          <div
            class="card-body d-flex justify-content-between align-items-start flex-column"
          >
            <!--begin::Icon-->
            <div class="m-0">
              <i class="ki-duotone ki-abstract-39 fs-2hx text-primary">
                <span class="path1"></span>
                <span class="path2"></span>
              </i>
            </div>
            <!--end::Icon-->
            <!--begin::Section-->
            <div class="d-flex flex-column my-7">
              <!--begin::Number-->
              <span class="fw-semibold fs-3x text-gray-800 lh-1 ls-n2">89</span>
              <!--end::Number-->
              <!--begin::Follower-->
              <div class="m-0">
                <span class="fw-semibold fs-6 text-gray-500">결제 대기중</span>
              </div>
              <!--end::Follower-->
            </div>
            <!--end::Section-->
            <!--begin::Badge-->

            <!--end::Badge-->
          </div>
          <!--end::Body-->
        </div>
        <!--end::Card widget 2-->
      </div>
      <div class="col-sm-6 col-xl-2 mb-xl-10">
        <!--begin::Card widget 2-->
        <div class="card h-lg-80">
          <!--begin::Body-->
          <div
            class="card-body d-flex justify-content-between align-items-start flex-column"
          >
            <!--begin::Icon-->
            <div class="m-0">
              <i class="ki-duotone ki-abstract-39 fs-2hx text-info"
                ><span class="path1"></span><span class="path2"></span
              ></i>
            </div>
            <!--end::Icon--><!--begin::Section-->
            <div class="d-flex flex-column my-7">
              <!--begin::Number--><span
                class="fw-semibold fs-3x text-gray-800 lh-1 ls-n2"
                >89</span
              ><!--end::Number--><!--begin::Follower-->
              <div class="m-0">
                <span class="fw-semibold fs-6 text-gray-500">반려 처리</span>
              </div>
              <!--end::Follower-->
            </div>
            <!--end::Section--><!--begin::Badge--><!--end::Badge-->
          </div>
          <!--end::Body-->
        </div>
        <!--end::Card widget 2-->
      </div>
      <div class="col-sm-6 col-xl-2 mb-xl-10">
        <!--begin::Card widget 2-->
        <div class="card h-lg-80">
          <!--begin::Body-->
          <div
            class="card-body d-flex justify-content-between align-items-start flex-column"
          >
            <!--begin::Icon-->
            <div class="m-0">
              <i class="ki-duotone ki-abstract-39 fs-2hx text-success"
                ><span class="path1"></span><span class="path2"></span
              ></i>
            </div>
            <!--end::Icon--><!--begin::Section-->
            <div class="d-flex flex-column my-7">
              <!--begin::Number--><span
                class="fw-semibold fs-3x text-gray-800 lh-1 ls-n2"
                >89</span
              ><!--end::Number--><!--begin::Follower-->
              <div class="m-0">
                <span class="fw-semibold fs-6 text-gray-500">처리완료</span>
              </div>
              <!--end::Follower-->
            </div>
            <!--end::Section--><!--begin::Badge--><!--end::Badge-->
          </div>
          <!--end::Body-->
        </div>
        <!--end::Card widget 2-->
      </div>
    </div>

    <div class="">
      <div class="card card-flush">
        <!--begin::Card header-->
        <div class="card-header">
          <!--begin::Card title-->
          <div class="card-title flex-column">
            <h3 class="fw-bold mb-1">허위매물 신고내역</h3>
          </div>
          <!--begin::Card title-->
          <!--begin::Card toolbar-->
          <div class="card-toolbar my-1">
            <!--begin::Select-->
            <div class="me-6 my-1">
              <select
                id="kt_filter_year"
                name="year"
                data-control="select2"
                data-hide-search="true"
                class="w-125px form-select form-select-solid form-select-sm select2-hidden-accessible"
                data-select2-id="select2-data-kt_filter_year"
                tabindex="-1"
                aria-hidden="true"
                data-kt-initialized="1"
              >
                <option
                  value="All"
                  selected="selected"
                  data-select2-id="select2-data-34-bdcq"
                >
                  All time
                </option>
                <option value="thisyear">This year</option>
                <option value="thismonth">This month</option>
                <option value="lastmonth">Last month</option>
                <option value="last90days">Last 90 days</option></select
              ><span
                class="select2 select2-container select2-container--bootstrap5"
                dir="ltr"
                data-select2-id="select2-data-33-5bcf"
                style="width: 100%"
                ><span class="selection"
                  ><span
                    class="select2-selection select2-selection--single w-125px form-select form-select-solid form-select-sm"
                    role="combobox"
                    aria-haspopup="true"
                    aria-expanded="false"
                    tabindex="0"
                    aria-disabled="false"
                    aria-labelledby="select2-kt_filter_year-container"
                    aria-controls="select2-kt_filter_year-container"
                    ><span
                      class="select2-selection__rendered"
                      id="select2-kt_filter_year-container"
                      role="textbox"
                      aria-readonly="true"
                      title="All time"
                      >All time</span
                    ><span class="select2-selection__arrow" role="presentation"
                      ><b role="presentation"></b></span></span></span
                ><span class="dropdown-wrapper" aria-hidden="true"></span
              ></span>
            </div>
            <!--end::Select-->
            <!--begin::Select-->
            <div class="me-4 my-1">
              <select
                id="kt_filter_orders"
                name="orders"
                data-control="select2"
                data-hide-search="true"
                class="w-125px form-select form-select-solid form-select-sm select2-hidden-accessible"
                data-select2-id="select2-data-kt_filter_orders"
                tabindex="-1"
                aria-hidden="true"
                data-kt-initialized="1"
              >
                <option
                  value="All"
                  selected="selected"
                  data-select2-id="select2-data-36-l53r"
                >
                  All Orders
                </option>
                <option value="Approved">Approved</option>
                <option value="Declined">Declined</option>
                <option value="In Progress">In Progress</option>
                <option value="In Transit">In Transit</option></select
              ><span
                class="select2 select2-container select2-container--bootstrap5"
                dir="ltr"
                data-select2-id="select2-data-35-2e8k"
                style="width: 100%"
                ><span class="selection"
                  ><span
                    class="select2-selection select2-selection--single w-125px form-select form-select-solid form-select-sm"
                    role="combobox"
                    aria-haspopup="true"
                    aria-expanded="false"
                    tabindex="0"
                    aria-disabled="false"
                    aria-labelledby="select2-kt_filter_orders-container"
                    aria-controls="select2-kt_filter_orders-container"
                    ><span
                      class="select2-selection__rendered"
                      id="select2-kt_filter_orders-container"
                      role="textbox"
                      aria-readonly="true"
                      title="All Orders"
                      >All Orders</span
                    ><span class="select2-selection__arrow" role="presentation"
                      ><b role="presentation"></b></span></span></span
                ><span class="dropdown-wrapper" aria-hidden="true"></span
              ></span>
            </div>
            <!--end::Select-->
            <!--begin::Search-->
            <div class="d-flex align-items-center position-relative my-1">
              <i class="ki-duotone ki-magnifier fs-3 position-absolute ms-3">
                <span class="path1"></span>
                <span class="path2"></span>
              </i>
              <input
                type="text"
                id="kt_filter_search"
                class="form-control form-control-solid form-select-sm w-150px ps-9"
                placeholder="Search Order"
              />
            </div>
            <!--end::Search-->
          </div>
          <!--begin::Card toolbar-->
        </div>
        <!--end::Card header-->
        <!--begin::Card body-->
        <div class="card-body pt-0">
          <!--begin::Table container-->
          <div class="table-responsive">
            <!--begin::Table-->
            <div
              id="kt_profile_overview_table_wrapper"
              class="dt-container dt-bootstrap5 dt-empty-footer"
            >
              <div id="" class="table-responsive">
                <table
                  id="kt_profile_overview_table"
                  class="table table-row-bordered table-row-dashed gy-4 align-middle fw-bold dataTable"
                  style="width: 1039.5px"
                >
                  <colgroup>
                    <col data-dt-column="0" style="width: 392.25px" />
                    <col data-dt-column="1" style="width: 235.352px" />
                    <col data-dt-column="2" style="width: 141.211px" />
                    <col data-dt-column="3" style="width: 165.703px" />
                    <col data-dt-column="4" style="width: 104.984px" />
                  </colgroup>
                  <thead class="fs-7 text-gray-500 text-uppercase">
                    <tr role="row">
                      <th class="col-1">
                        <span class="dt-column-title mb-5" role="button"
                          >순번</span
                        ><span class="dt-column-order"></span>
                      </th>
                      <th class="col-2">
                        <span class="dt-column-title" role="button"
                          >신고일자</span
                        ><span class="dt-column-order"></span>
                      </th>
                      <th class="col-2 me-3">
                        <span class="dt-column-title" role="button"
                          >신고접수번호</span
                        ><span class="dt-column-order"></span>
                      </th>
                      <th class="col-3 me-3">
                        <span class="dt-column-title" role="button"
                          >신고사유</span
                        ><span class="dt-column-order"></span>
                        <div class="row mt-5">
                          <div class="dropdown col-4">
                            <button
                              class="btn btn-secondary btn-sm dropdown-toggle"
                              type="button"
                              data-bs-toggle="dropdown"
                              aria-expanded="false"
                            >
                              대분류
                            </button>
                            <ul class="dropdown-menu">
                              <li>
                                <a class="dropdown-item" href="#">Action</a>
                              </li>
                              <li>
                                <a class="dropdown-item" href="#"
                                  >Another action</a
                                >
                              </li>
                              <li>
                                <a class="dropdown-item" href="#"
                                  >Something else here</a
                                >
                              </li>
                            </ul>
                          </div>
                          <div class="dropdown col-4">
                            <button
                              class="btn btn-secondary btn-sm dropdown-toggle"
                              type="button"
                              data-bs-toggle="dropdown"
                              aria-expanded="false"
                            >
                              중분류
                            </button>
                            <ul class="dropdown-menu">
                              <li>
                                <a class="dropdown-item" href="#">Action</a>
                              </li>
                              <li>
                                <a class="dropdown-item" href="#"
                                  >Another action</a
                                >
                              </li>
                              <li>
                                <a class="dropdown-item" href="#"
                                  >Something else here</a
                                >
                              </li>
                            </ul>
                          </div>
                          <div class="dropdown col-4">
                            <button
                              class="btn btn-secondary dropdown-toggle btn-sm"
                              type="button"
                              data-bs-toggle="dropdown"
                              aria-expanded="false"
                            >
                              소분류
                            </button>
                            <ul class="dropdown-menu">
                              <li>
                                <a class="dropdown-item" href="#">Action</a>
                              </li>
                              <li>
                                <a class="dropdown-item" href="#"
                                  >Another action</a
                                >
                              </li>
                              <li>
                                <a class="dropdown-item" href="#"
                                  >Something else here</a
                                >
                              </li>
                            </ul>
                          </div>
                        </div>
                      </th>
                      <th class="col-3">
                        <span class="dt-column-title mb-5" role="button"
                          >증빙자료 항목</span
                        ><span class="dt-column-order"></span>
                        <div class="row mt-5">
                          <div class="col-3 p-3">광고화면</div>
                          <div class="col-3 p-3">녹취</div>
                          <div class="col-3 p-3">문자내용</div>
                          <div class="col-3 p-3">기타자료</div>
                        </div>
                      </th>
                      <th class="col-1">
                        <span class="dt-column-title" role="button">상태</span
                        ><span class="dt-column-order"></span>
                      </th>
                    </tr>
                  </thead>
                  <tbody class="fs-6">
                    <tr>
                      <td>2416</td>
                      <td data-order="2024-09-22T00:00:00+09:00">2024-11-01</td>
                      <td>
                        <!--begin::User-->
                        <span class="badge fw-bold px-4 py-3">A1234B</span>
                        <!--end::User-->
                      </td>
                      <td class="dt-type-numeric">
                        <div class="row">
                          <div class="col-4">
                            <span
                              class="badge badge-light-danger fw-bold px-4 py-3"
                              >명시의무 위반</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-warning fw-bold px-4 py-3"
                              >중개사무소</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-success fw-bold px-4 py-3"
                              >상호명시 위반</span
                            >
                          </div>
                        </div>
                      </td>
                      <td>
                        <div class="row">
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                        </div>
                      </td>
                      <td class="">
                        <span>신규</span>
                      </td>
                    </tr>
                    <tr>
                      <td>2411</td>
                      <td data-order="2024-09-22T00:00:00+09:00">2024-11-01</td>
                      <td>
                        <!--begin::User-->
                        <span class="badge fw-bold px-4 py-3">A1234B</span>
                        <!--end::User-->
                      </td>
                      <td class="dt-type-numeric">
                        <div class="row">
                          <div class="col-4">
                            <span
                              class="badge badge-light-danger fw-bold px-4 py-3"
                              >명시의무 위반</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-warning fw-bold px-4 py-3"
                              >중개사무소</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-success fw-bold px-4 py-3"
                              >상호명시 위반</span
                            >
                          </div>
                        </div>
                      </td>
                      <td>
                        <div class="row">
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                        </div>
                      </td>
                      <td class="">처리완료</td>
                    </tr>
                    <tr>
                      <td>2412</td>
                      <td data-order="2024-09-22T00:00:00+09:00">2024-11-01</td>
                      <td>
                        <!--begin::User-->
                        <span class="badge fw-bold px-4 py-3">A1234B</span>
                        <!--end::User-->
                      </td>
                      <td class="dt-type-numeric">
                        <div class="row">
                          <div class="col-4">
                            <span
                              class="badge badge-light-danger fw-bold px-4 py-3"
                              >명시의무 위반</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-warning fw-bold px-4 py-3"
                              >중개사무소</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-success fw-bold px-4 py-3"
                              >상호명시 위반</span
                            >
                          </div>
                        </div>
                      </td>
                      <td>
                        <div class="row">
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                        </div>
                      </td>
                      <td class="">처리완료</td>
                    </tr>
                    <tr>
                      <td>2413</td>
                      <td data-order="2024-09-22T00:00:00+09:00">2024-11-01</td>
                      <td>
                        <!--begin::User-->
                        <span class="badge fw-bold px-4 py-3">A1234B</span>
                        <!--end::User-->
                      </td>
                      <td class="dt-type-numeric">
                        <div class="row">
                          <div class="col-4">
                            <span
                              class="badge badge-light-danger fw-bold px-4 py-3"
                              >명시의무 위반</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-warning fw-bold px-4 py-3"
                              >중개사무소</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-success fw-bold px-4 py-3"
                              >상호명시 위반</span
                            >
                          </div>
                        </div>
                      </td>
                      <td>
                        <div class="row">
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                        </div>
                      </td>
                      <td class="">처리완료</td>
                    </tr>
                    <tr>
                      <td>2414</td>
                      <td data-order="2024-09-22T00:00:00+09:00">2024-11-01</td>
                      <td>
                        <!--begin::User-->
                        <span class="badge fw-bold px-4 py-3">A1234B</span>
                        <!--end::User-->
                      </td>
                      <td class="dt-type-numeric">
                        <div class="row">
                          <div class="col-4">
                            <span
                              class="badge badge-light-danger fw-bold px-4 py-3"
                              >명시의무 위반</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-warning fw-bold px-4 py-3"
                              >중개사무소</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-success fw-bold px-4 py-3"
                              >상호명시 위반</span
                            >
                          </div>
                        </div>
                      </td>
                      <td>
                        <div class="row">
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                        </div>
                      </td>
                      <td class="">처리완료</td>
                    </tr>
                    <tr>
                      <td>2415</td>
                      <td data-order="2024-09-22T00:00:00+09:00">2024-11-01</td>
                      <td>
                        <!--begin::User-->
                        <span class="badge fw-bold px-4 py-3">A1234B</span>
                        <!--end::User-->
                      </td>
                      <td class="dt-type-numeric">
                        <div class="row">
                          <div class="col-4">
                            <span
                              class="badge badge-light-danger fw-bold px-4 py-3"
                              >명시의무 위반</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-warning fw-bold px-4 py-3"
                              >중개사무소</span
                            >
                          </div>
                          <div class="col-4">
                            <span
                              class="badge badge-light-success fw-bold px-4 py-3"
                              >상호명시 위반</span
                            >
                          </div>
                        </div>
                      </td>
                      <td>
                        <div class="row">
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                          <div class="col-3 p-5 ps-7">
                            <i class="bi bi-check-circle-fill text-success"></i>
                          </div>
                        </div>
                      </td>
                      <td class="">처리완료</td>
                    </tr>
                  </tbody>
                  <tfoot></tfoot>
                </table>
              </div>
              <div id="" class="row">
                <div
                  id=""
                  class="col-sm-12 col-md-5 d-flex align-items-center justify-content-center justify-content-md-start dt-toolbar"
                >
                  <div>
                    <select
                      name="kt_profile_overview_table_length"
                      aria-controls="kt_profile_overview_table"
                      class="form-select form-select-solid form-select-sm"
                      id="dt-length-0"
                    >
                      <option value="10">10</option>
                      <option value="25">25</option>
                      <option value="50">50</option>
                      <option value="100">100</option></select
                    ><label for="dt-length-0"></label>
                  </div>
                </div>
                <div
                  id=""
                  class="col-sm-12 col-md-7 d-flex align-items-center justify-content-center justify-content-md-end"
                >
                  <div class="dt-paging paging_simple_numbers">
                    <ul class="pagination">
                      <li class="dt-paging-button page-item disabled">
                        <a
                          class="page-link previous"
                          aria-controls="kt_profile_overview_table"
                          aria-disabled="true"
                          aria-label="Previous"
                          data-dt-idx="previous"
                          tabindex="-1"
                          ><i class="previous"></i
                        ></a>
                      </li>
                      <li class="dt-paging-button page-item active">
                        <a
                          href="#"
                          class="page-link"
                          aria-controls="kt_profile_overview_table"
                          aria-current="page"
                          data-dt-idx="0"
                          tabindex="0"
                          >1</a
                        >
                      </li>
                      <li class="dt-paging-button page-item">
                        <a
                          href="#"
                          class="page-link"
                          aria-controls="kt_profile_overview_table"
                          data-dt-idx="1"
                          tabindex="0"
                          >2</a
                        >
                      </li>
                      <li class="dt-paging-button page-item">
                        <a
                          href="#"
                          class="page-link"
                          aria-controls="kt_profile_overview_table"
                          data-dt-idx="2"
                          tabindex="0"
                          >3</a
                        >
                      </li>
                      <li class="dt-paging-button page-item">
                        <a
                          href="#"
                          class="page-link next"
                          aria-controls="kt_profile_overview_table"
                          aria-label="Next"
                          data-dt-idx="next"
                          tabindex="0"
                          ><i class="next"></i
                        ></a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
            <!--end::Table-->
          </div>
          <!--end::Table container-->
        </div>
        <!--end::Card body-->
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
  background: var(--color-bg-blue1);
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

.additional-items {
  display: flex;
  justify-content: space-between;
  padding: 20px;
}
</style>
