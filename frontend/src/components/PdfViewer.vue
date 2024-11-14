<script setup>
import { ref, onMounted } from "vue";
import * as pdfjsLib from "pdfjs-dist";
import { usePublicLedgerModalStore } from "@/stores/modal";
import { storeToRefs } from "pinia";

pdfjsLib.GlobalWorkerOptions.workerSrc =
  "https://cdnjs.cloudflare.com/ajax/libs/pdf.js/3.7.107/pdf.worker.min.js";

const totalPages = ref(0); // 총 페이지 수
const viewerContainer = ref(null); // 큰 화면에 렌더링되는 Canvas
const previewContainer = ref(null); // 미리보기가 표시되는 영역
const currentPage = ref(1); // 선택된 페이지 번호

const loadPdf = async (url) => {
  pdfjsLib.GlobalWorkerOptions.workerSrc =
    "https://cdnjs.cloudflare.com/ajax/libs/pdf.js/3.7.107/pdf.worker.min.js";

  // PDF를 로드
  const loadingTask = pdfjsLib.getDocument(url);
  const pdf = await loadingTask.promise;

  totalPages.value = pdf.numPages;

  generatePreviews(pdf);
  renderPage(1, pdf);
};

const renderPage = async (pageNumber, pdf) => {
  if (!pdf || !viewerContainer.value) return;

  try {
    const page = await pdf.getPage(pageNumber);
    const scale = 1.5; // 큰 화면 배율
    const viewport = page.getViewport({ scale });

    const canvas = document.createElement("canvas");
    viewerContainer.value.innerHTML = ""; // 기존 내용 초기화
    viewerContainer.value.appendChild(canvas);

    const canvasContext = canvas.getContext("2d");
    if (!canvasContext) return;

    let customWidth;
    if (docNameRef.value) {
      customWidth = 800; // 원하는 너비
    } else {
      customWidth = 330;
    }
    // const customWidth = 330; // 원하는 너비
    const aspectRatio = viewport.height / viewport.width; //가로세로 비율
    // Canvas 크기 설정
    canvas.width = customWidth;
    canvas.height = customWidth * aspectRatio;

    console.log(canvas.width / viewport.width);

    // 페이지 렌더링
    await page.render({
      canvasContext,
      viewport: page.getViewport({
        scale: canvas.width / viewport.width + 0.2, // 크기 비율에 맞게 재조절
      }),
    }).promise;
  } catch (error) {
    console.error("Failed to render page:", error);
  }
};

const generatePreviews = async (pdf) => {
  if (!pdf || !previewContainer.value) return;

  previewContainer.value.innerHTML = ""; // 기존 미리보기 초기화

  for (let pageNum = 1; pageNum <= totalPages.value; pageNum++) {
    try {
      const page = await pdf.getPage(pageNum);
      const scale = 0.1; // 미리보기 배율
      const viewport = page.getViewport({ scale });

      const canvas = document.createElement("canvas");
      canvas.width = viewport.width;
      canvas.height = viewport.height;

      const canvasContext = canvas.getContext("2d");
      if (canvasContext) {
        await page.render({
          canvasContext,
          viewport,
        }).promise;
      }

      // 미리보기 클릭 이벤트
      canvas.addEventListener("click", () => {
        console.log(pageNum);
        currentPage.value = pageNum;
        renderPage(pageNum, pdf);
      });

      previewContainer.value.appendChild(canvas); // 미리보기 추가
    } catch (error) {
      console.error(`Failed to render preview for page ${pageNum}:`, error);
    }
  }
};

//  어떤 pdf 보여줄껀지 판단
const storeModal = usePublicLedgerModalStore();
const { docNameRef } = storeToRefs(storeModal);

// PDF 파일 URL을 사용하여 로드
const loadPdfFromPublic = () => {
  let pdfUrl;
  if (docNameRef.value === "토지대장") {
    pdfUrl = "/assets/file/land_test.pdf";
  } else {
    pdfUrl = "/assets/file/test.pdf"; // public 폴더의 파일 경로
  }
  loadPdf(pdfUrl);
};
// 컴포넌트 마운트 시 동작
onMounted(() => {
  loadPdfFromPublic();
});
</script>

<template>
  <div class="d-flex justify-content-around">
    <div
      ref="previewContainer"
      class="preview-container overflow-auto d-flex flex-column gap-3 bg-gray-200 pt-1 pb-1"
    ></div>
    <div ref="viewerContainer" class="viewer-container"></div>
  </div>
</template>

<style scoped></style>
