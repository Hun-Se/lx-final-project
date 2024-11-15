<script setup>
import { onMounted } from "vue";
import { RouterView } from "vue-router";

onMounted(() => {
  // Bootstrap CSS 추가
  const link = document.createElement("link");
  link.rel = "stylesheet";
  link.href = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css";
  document.head.appendChild(link);

  // Bootstrap JS 추가
  const script = document.createElement("script");
  script.src = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js";
  document.body.appendChild(script);
});
</script>

<template>
  <RouterView />
</template>

<style scoped></style>
