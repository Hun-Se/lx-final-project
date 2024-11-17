<script setup>
import { ref, reactive, onMounted, onUnmounted } from "vue";
import PdfViewer from "@/components/PdfViewer.vue";
import PublicLedgerModal from "@/components/PublicLedgerModal.vue";
import { usePublicLedgerModalStore } from "@/stores/modal.js";
import { storeToRefs } from "pinia";
// Reactive 상태 선언
const audioPlayer = ref(null);
const isPlaying = ref(false);
const audioSource = ref(
  "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
);
const currentTime = ref("00:00");
const duration = ref("00:00");
const progress = ref(0);

// 메서드 정의
const fetchInfo = () => {
  // 정보 조회 함수 로직
};

const deleteInfo = () => {
  // 정보 삭제 함수 로직
};

const togglePlay = () => {
  if (audioPlayer.value) {
    if (isPlaying.value) {
      audioPlayer.value.pause();
    } else {
      audioPlayer.value.play();
    }
    isPlaying.value = !isPlaying.value;
  }
};

const setDuration = () => {
  if (audioPlayer.value) {
    npm;
    duration.value = formatTime(audioPlayer.value.duration);
  }
};

const updateProgress = () => {
  if (audioPlayer.value) {
    currentTime.value = formatTime(audioPlayer.value.currentTime);
    progress.value =
      (audioPlayer.value.currentTime / audioPlayer.value.duration) * 100 || 0;
  }
};

const seekAudio = () => {
  if (audioPlayer.value) {
    audioPlayer.value.currentTime =
      (progress.value / 100) * audioPlayer.value.duration;
  }
};

const formatTime = (seconds) => {
  const minutes = Math.floor(seconds / 60)
    .toString()
    .padStart(2, "0");
  const secs = Math.floor(seconds % 60)
    .toString()
    .padStart(2, "0");
  return `${minutes}:${secs}`;
};

const onAudioEnded = () => {
  isPlaying.value = false;
  progress.value = 0;
  currentTime.value = "00:00";
};

// 오디오 이벤트 등록 및 해제
onMounted(() => {
  if (audioPlayer.value) {
    audioPlayer.value.addEventListener("loadedmetadata", setDuration);
    audioPlayer.value.addEventListener("timeupdate", updateProgress);
    audioPlayer.value.addEventListener("ended", onAudioEnded);
  }
});

onUnmounted(() => {
  if (audioPlayer.value) {
    audioPlayer.value.removeEventListener("loadedmetadata", setDuration);
    audioPlayer.value.removeEventListener("timeupdate", updateProgress);
    audioPlayer.value.removeEventListener("ended", onAudioEnded);
  }
});

let map;
onMounted(() => {
  const script = document.createElement("script");
  script.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yi1l80sw0i&submodules=geocoder`;
  script.onload = () => {
    initMap();
  };
  document.head.appendChild(script);
});

async function initMap() {
  map = new naver.maps.Map("map", {
    center: new naver.maps.LatLng(37.516042, 127.034881), // 데이터로 변경 필요
    zoom: 17,
    zoomControl: false,
    mapTypeControl: true,
  });

  var marker = new naver.maps.Marker({
    position: new naver.maps.LatLng(37.516042, 127.034881), // 데이터로 변경필요
    map: map,
  });
}

function zoomIn() {
  if (map) {
    map.setZoom(map.getZoom() + 1);
  }
}

function zoomOut() {
  if (map) {
    map.setZoom(map.getZoom() - 1);
  }
}

function resetMap() {
  if (map) {
    // 초기 위치와 줌 레벨로 지도를 리셋
    map.setCenter(
      new naver.maps.LatLng(this.initialCenter.lat, this.initialCenter.lng),
    );
    map.setZoom(this.initialZoom);
  }
}

// 모달 관련 함수
const publicLedgerStore = usePublicLedgerModalStore();
const { docNameRef } = storeToRefs(publicLedgerStore);
function onClickOpenPublicLedgerModal(docName) {
  docNameRef.value = docName;
  publicLedgerStore.openModal();
}
</script>

<template>
  <nav>
    <div>
      <div class="logo"></div>
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
  <div class="p-10" style="margin-top: 5rem; margin-left: 300px">
    <div class="row">
      <div class="col-5">
        <div class="d-flex flex-column justify-content-between h-100">
          <div style="flex: 1; margin-bottom: 20px" class="card">
            <div class="card-body pt-9">
              <div class="">
                <div class="d-flex align-items-center mb-2">
                  <a href="#" class="tag fs-1"> 신고접수번호 C51743 </a>
                  <span class="arrow"></span>
                </div>
                <div class="d-flex flex-column container-left">
                  <div class="mt-5">
                    <div class="d-flex text-gray-800">
                      <div class="fw-semibold pe-5">신고일자:</div>
                      <div class="text-end fw-norma">2024-11-01</div>
                      <!--end::Label-->
                    </div>
                    <!--end::Item-->
                    <!--begin::Item-->
                    <div class="d-flex text-gray-800 mt-5 mb-3 container-badge">
                      <!--begin::Accountnumber-->
                      <div class="fw-semibold pe-5">신고사유:</div>
                      <!--end::Accountnumber-->
                      <!--begin::Number-->
                      <span
                        class="badge signal badge-light-danger fw-bold me-2 px-4 py-3"
                        >명시의무 위반</span
                      >
                      <span
                        class="badge signal badge-light-warning fw-bold me-2 px-4 py-3"
                        >중개사무소</span
                      >
                      <span
                        class="badge signal badge-light-success fw-bold me-2 px-4 py-3"
                        >상호명시 위반</span
                      >
                      <!--end::Number-->
                    </div>
                    <div class="d-flex text-gray-800 mt-5">
                      <div class="fw-semibold pe-5" style="white-space: nowrap">
                        증빙자료:
                      </div>
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
                          <tr class="text-center tr-table-list">
                            <td></td>
                            <td></td>
                            <td>
                              <i
                                class="bi bi-check-circle-fill text-success"
                              ></i>
                            </td>
                            <td></td>
                          </tr>
                          <!-- and so on... -->
                        </tbody>
                      </table>
                      <!--end::Label-->
                    </div>
                    <div class="d-flex text-gray-800 mt-5">
                      <div clss="fw-semibold pe-5" style="white-space: nowrap">
                        첨부파일:
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div style="flex: 1" class="card">
            <div class="card-body pt-9">
              <h4>공적장부 열람</h4>
              <div class="btn-group-public">
                <button
                  type="button"
                  class="btn btn-light"
                  @click="onClickOpenPublicLedgerModal('토지대장')"
                >
                  토지대장
                </button>
                <button type="button" class="btn btn-light">등기부등본</button>
                <button type="button" class="btn btn-light">등기부등본</button>
                <button type="button" class="btn btn-light">등기부등본</button>
              </div>
            </div>
            <div>
              <h4></h4>
            </div>
          </div>
        </div>
      </div>

      <div class="col-7">
        <div class="card">
          <div class="card-body pt-9">
            <div class="row h-100">
              <div class="col-6">
                <div class="">
                  <!--begin::User-->
                  <div class="d-flex flex-column">
                    <div class="d-flex">
                      <div class="form-check">
                        <input
                          class="form-check-input"
                          type="radio"
                          name="flexRadioDefault"
                          id="flexRadioDefault1"
                          checked
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
                  </div>
                </div>
                <div>
                  <div class="mt-10 pb-5 content-list">
                    <!--begin::Details item-->
                    <div class="fw-bold mt-5 mb-3">
                      중개사 번호: <span class="text-gray-600 ms-1">12345</span>
                    </div>
                    <div class="fw-bold mt-5 mb-3">
                      이메일:
                      <span class="ms-1 text-gray-600">
                        <a href="#" class="text-gray-600 text-hover-primary"
                          >kim72840@gmail.com</a
                        >
                      </span>
                    </div>

                    <!--begin::Details item-->
                    <!--begin::Details item-->
                    <div class="fw-bold mt-5 mb-3">
                      중개사무소 소재지:
                      <span class="text-gray-600 ms-1">강남구 언주로 703</span>
                    </div>

                    <!--begin::Details item-->
                    <!--begin::Details item-->
                    <div class="fw-bold mt-5 mb-3">
                      중개사무소명:
                      <span class="text-gray-600 ms-1">한마음공인중개사</span>
                    </div>

                    <!--begin::Details item-->
                    <!--begin::Details item-->
                    <div class="fw-bold mt-5 mb-3">
                      중개사무소 연락처:
                      <span class="ms-1 text-gray-600">010-1234-1234</span>
                    </div>
                    <div class="fw-bold mt-5 mb-3">
                      대표중개사명:
                      <span class="ms-1 text-gray-600">김세훈</span>
                    </div>
                    <!--begin::Details item-->
                  </div>
                </div>
                <!--end::Title-->
                <!--begin::Stats-->

                <!--end::Stats-->
              </div>
              <!--    지도    -->
              <div class="container-map col-6">
                <div class="map-container">
                  <div id="map"></div>
                  <div class="zoom-controls">
                    <button @click="zoomIn" class="zoom-button">+</button>
                    <button @click="zoomOut" class="zoom-button">-</button>
                  </div>
                </div>
              </div>
              <!-- 지도 끝 -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-7" style="height: 600px">
        <div class="card h-100" id="kt_chat_messenger">
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
          <div class="card-body" id="kt_chat_messenger_body">
            <div
              class="scroll-y me-n5 pe-5 h-100 h-lg-auto"
              data-kt-element="messages"
              data-kt-scroll="true"
              data-kt-scroll-activate="{default: false, lg: true}"
              data-kt-scroll-dependencies="#kt_header, #kt_app_header, #kt_app_toolbar, #kt_toolbar, #kt_footer, #kt_app_footer, #kt_chat_messenger_header, #kt_chat_messenger_footer"
              data-kt-scroll-wrappers="#kt_content, #kt_app_content, #kt_chat_messenger_body"
              data-kt-scroll-offset="5px"
            >
              <div class="d-flex justify-content-end mb-10">
                <div class="d-flex flex-column align-items-end">
                  <div class="d-flex align-items-center mb-2">
                    <div class="me-3">
                      <span class="me-3">2024-11-12 13:30:54</span>
                      <a
                          href="#"
                          class="fs-5 fw-bold text-gray-900 text-hover-primary ms-1"
                      >신고자</a
                      >
                    </div>
                  </div>
                  <div
                      class="p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end"
                      data-kt-element="message-text"
                  >
                    안녕하세요. OO아파트 매물 확인하고 싶어서 연락드렸습니다. 이 매물 아직 볼 수 있을까요?
                  </div>
                </div>
              </div>
              <div class="d-flex justify-content-start mb-10 p-1">
                <div class="d-flex flex-column align-items-start">
                  <div class="d-flex align-items-center mb-2">
                    <div class="ms-3">
                      <a
                        href="#"
                        class="fs-5 fw-bold text-gray-900 text-hover-primary me-1"
                        >중개인</a
                      >
                      <span class="me-3">2024-11-12 13:30:58</span>
                    </div>
                  </div>
                  <div
                    class="p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start"
                    data-kt-element="message-text"
                  >
                    안녕하세요! 아쉽게도 그 매물은 현재 계약이 완료된 상태입니다. 대신 OO아파트 근처에 비슷한 가격대의 좋은 매물이 많이 있습니다. 바로 안내드릴 수 있는데, 관심 있으신가요?
                  </div>
                </div>
              </div>
              <div class="d-flex justify-content-end mb-10">
                <div class="d-flex flex-column align-items-end">
                  <div class="d-flex align-items-center mb-2">
                    <div class="me-3">
                      <span class="me-3">2024-11-12 13:31:02</span>
                      <a
                          href="#"
                          class="fs-5 fw-bold text-gray-900 text-hover-primary ms-1"
                      >신고자</a
                      >
                    </div>
                  </div>
                  <div
                      class="p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end"
                      data-kt-element="message-text"
                  >
                    아… 저는 그 아파트 매물에 관심이 있어서요. 같은 매물이 올라오면 알려주실 수 있나요?
                  </div>
                </div>
              </div>
              <div class="d-flex justify-content-start mb-10 p-1">
                <div class="d-flex flex-column align-items-start">
                  <div class="d-flex align-items-center mb-2">
                    <div class="ms-3">
                      <a
                          href="#"
                          class="fs-5 fw-bold text-gray-900 text-hover-primary me-1"
                      >중개인</a
                      >
                      <span class="me-3">2024-11-12 13:31:14</span>
                    </div>
                  </div>
                  <div
                      class="p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start"
                      data-kt-element="message-text"
                  >
                    그 매물은 당분간 나오기 어려울 것 같네요. 대신 비슷한 조건의 XX아파트나 XX빌라 매물을 추천드립니다. 지금 둘러보시면 좋은 매물도 발견하실 수 있을 거예요!
                  </div>
                </div>
              </div>

            </div>
          </div>
          <!-- 오디오 재생/정지 툴바 -->
          <div class="audio-toolbar p-5">
            <audio
              ref="audioPlayer"
              :src="audioSource"
              @timeupdate="updateProgress"
              @loadedmetadata="setDuration"
              @ended="onAudioEnded"
            ></audio>

            <!-- 재생/정지 버튼 -->
            <button @click="togglePlay" class="play-button">
              {{ isPlaying ? "정지" : "재생" }}
            </button>

            <!-- 진행 바 및 시간 표시 -->
            <div class="progress-container">
              <span class="time">{{ currentTime }}</span>
              <input
                type="range"
                class="progress-bar"
                min="0"
                max="100"
                v-model="progress"
                @input="seekAudio"
              />
              <span class="time">{{ duration }}</span>
            </div>
          </div>
          <!--end::Card footer-->
        </div>
      </div>
      <div class="col-5">
        <div class="card h-xl-100" dir="ltr">
          <div class="card-header" id="kt_chat_messenger_header">
            <!--begin::Title-->
            <div class="card-title">
              <!--begin::Users-->
              <div class="symbol-group symbol-hover"></div>
              <h3>의견서</h3>
            </div>
            <!--end::Title--><!--begin::Card toolbar-->
            <div class="card-toolbar">
              <!--begin::Menu-->
              <div class="me-n3">
                <button
                  class="btn btn-sm btn-icon btn-active-light-primary"
                  data-kt-menu-trigger="click"
                  data-kt-menu-placement="bottom-end"
                >
                  <i class="ki-duotone ki-dots-square fs-2"
                    ><span class="path1"></span><span class="path2"></span
                    ><span class="path3"></span><span class="path4"></span
                  ></i></button
                ><!--begin::Menu 3-->
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
                  <!--end::Heading--><!--begin::Menu item-->
                  <div class="menu-item px-3">
                    <a
                      href="#"
                      class="menu-link px-3"
                      data-bs-toggle="modal"
                      data-bs-target="#kt_modal_users_search"
                      >Add Contact</a
                    >
                  </div>
                  <!--end::Menu item--><!--begin::Menu item-->
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
                        ><i class="ki-duotone ki-information fs-7"
                          ><span class="path1"></span><span class="path2"></span
                          ><span class="path3"></span></i></span
                    ></a>
                  </div>
                  <!--end::Menu item--><!--begin::Menu item-->
                  <div
                    class="menu-item px-3"
                    data-kt-menu-trigger="hover"
                    data-kt-menu-placement="right-start"
                  >
                    <a href="#" class="menu-link px-3"
                      ><span class="menu-title">Groups</span
                      ><span class="menu-arrow"></span></a
                    ><!--begin::Menu sub-->
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
                      <!--end::Menu item--><!--begin::Menu item-->
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
                      <!--end::Menu item--><!--begin::Menu item-->
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
                  <!--end::Menu item--><!--begin::Menu item-->
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
                </div>
              </div>
            </div>
          </div>
          <div class="card-body d-flex flex-column">
            <div class="" style="width: 100%; height: 100%">
              <div class="fw-bold fs-3 mb-3">
                AI 의견 내용:
                <span class="text-gray-600 fs-3 ms-1 badge badge-light-primary"
                  >부합</span
                >
              </div>
              <div class="fw-bold fs-3 mt-3 mb-3">
                부동산 광고시장 감시센터 공문 <span></span>
              </div>
              <PdfViewer></PdfViewer>
            </div>
          </div>
        </div>
      </div>
    </div>
    <PublicLedgerModal></PublicLedgerModal>
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
  width: 240px;
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
  width: 100%;
  border-collapse: collapse;
  font-size: 0.9em;
}

.styled-table thead tr {
  text-align: center;
  background-color: var(--color-bg-blue1);
  color: #ffffff;
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

.tr-table-list {
  border-left: 1px solid #dddddd;
  border-right: 1px solid #dddddd;
}

.container-map {
  display: flex;
  justify-content: center;
  height: auto;
}

.container-map .map-container {
  width: 500px;
  height: 100%;
  display: block;
  position: initial;
}

#map {
  width: 100%;
  height: 100%;
}

.zoom-controls {
  position: absolute;
  bottom: 20px;
  right: 20px;
  display: flex;
  flex-direction: column;
}

.zoom-button {
  width: 40px;
  height: 40px;
  font-size: 20px;
  margin-top: 5px;
  background-color: rgb(255, 255, 255);
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
}

.zoom-button:hover {
  background-color: #f0f0f0;
}

.content-list div {
  font-size: 1.2rem;
}

.btn-group-public {
  display: flex;
  overflow-x: scroll;
  overflow-y: hidden;
  white-space: no-wrap;
  gap: 1rem;
  margin-top: 16px;
}

.btn-group-public button {
  width: 120px;
  height: 50px;
  flex: 0 0 auto;
}

.content-list div {
  padding: 1rem;
  border-bottom: 1px solid rgb(245, 245, 245);
}

.container-left {
  font-size: 1rem;
}

.badge.signal {
  font-size: 1rem !important;
}

.container-badge {
  overflow: auto;
  white-space: nowrap;
}

/* 오디오 재생/정지 툴바 */
.audio-toolbar {
  grid-column: span 2;
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px;
  border-top: 1px solid var(--bs-card-border-color);
}

.play-button {
  padding: 5px 15px;
  font-size: 14px;
  cursor: pointer;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
}

.play-button:hover {
  background-color: #0056b3;
}

.progress-container {
  display: flex;
  align-items: center;
  gap: 10px;
  width: 100%;
}

.time {
  font-size: 12px;
  color: #555;
}

.progress-bar {
  flex: 1;
  cursor: pointer;
}
</style>
