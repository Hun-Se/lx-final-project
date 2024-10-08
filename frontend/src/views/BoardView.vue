<template>
    <component :is="renderHeader()" />

</template>
<script setup>
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

</script>