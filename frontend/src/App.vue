<script setup>
import { onMounted } from "vue";
import { RouterView } from "vue-router";

onMounted(() => {
  // Bootstrap CSS 추가
  const link = document.createElement("link");
  link.rel = "stylesheet";
  link.href = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css";
  link.integrity = "sha384-9ndCyUa6Y5Y8y4v99G6YzM/YiuKa/2eHpGSk0/UpsqCzIw5Az5WXls1l3pqxJcxx";
  link.crossOrigin = "anonymous";
  document.head.appendChild(link);

  // Bootstrap JS 추가
  const script = document.createElement("script");
  script.src = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js";
  script.integrity = "sha384-ENjdO4Dr2bkBIFxQp3nF26z2wzGr2DhH/xQWBvCOBrOVkYLDYIHmhpqls0Q5pLt";
  script.crossOrigin = "anonymous";
  document.body.appendChild(script);
});
</script>

<template>
  <RouterView />
</template>

<style scoped></style>
