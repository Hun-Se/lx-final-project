<template>
  <div>
    <div class="container">
      <div>피니아 사용한 데이터</div>
      <ul>
        <li v-for="item in data" :key="item.id">
          {{ item.name }} - {{ item.description }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useExampleStore } from "@/stores/example";
import { storeToRefs } from "pinia";
onMounted(() => {
  init();
});
const store = useExampleStore();
async function init() {
  console.log("초기화!");
  store.fetchGetExamples();
}
const { data } = storeToRefs(store);
</script>

<style scoped>
li {
  color: var(--color-bg-blue1);
}
</style>
