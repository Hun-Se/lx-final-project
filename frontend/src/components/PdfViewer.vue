<script setup>
import { ref, onMounted } from "vue";
import * as pdfjsLib from "pdfjs-dist";

// PDF 렌더링 컨테이너를 참조하기 위한 ref
const viewerContainer = ref(null);

// 파일 변경 시 호출되는 함수
const onChange = async (e) => {
  const input = e.target;
  const files = input.files;
  if (!files || files.length === 0) return;
  const file = files[0];
  const reader = new FileReader();

  reader.onload = async () => {
    const arrayBuffer = reader.result;
    pdfjsLib.GlobalWorkerOptions.workerSrc =
      "https://cdnjs.cloudflare.com/ajax/libs/pdf.js/3.7.107/pdf.worker.min.js";

    const loadingTask = pdfjsLib.getDocument(arrayBuffer);
    const pdf = await loadingTask.promise;

    if (viewerContainer.value) {
      viewerContainer.value.innerHTML = ""; // 기존 내용 초기화
      const totalPage = pdf.numPages;

      for (let pageNum = 1; pageNum <= totalPage; pageNum++) {
        const page = await pdf.getPage(pageNum);
        const scale = 1.5;
        const viewport = page.getViewport({ scale });
        const canvas = document.createElement("canvas");

        viewerContainer.value.appendChild(canvas);
        const canvasContext = canvas.getContext("2d");
        if (!canvasContext) return;
        canvas.height = viewport.height;
        canvas.width = viewport.width;

        await page.render({
          canvasContext,
          viewport,
        }).promise;
      }
    }
  };

  reader.readAsArrayBuffer(file);
};

// 컴포넌트 마운트 시 동작
onMounted(() => {
  console.log("pdf viewer mounted");
});
</script>

<template>
  <div class="container">
    PdfViewer PDF VIEWER
    <div class="fileInput">
      <input type="file" @change="onChange" />
    </div>
    <div id="pdf-viewer" ref="viewerContainer"></div>
  </div>
</template>

<style scoped></style>
