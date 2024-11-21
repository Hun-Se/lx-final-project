<template>
  <div class="container-bottom-nav">
    <nav class="nav">
      <ul class="nav-content">
        <li class="nav-list" v-for="(item, index) in menuItems" :key="index">
          <a href="#" class="link-item select_menu" @click="onClickNavigate(index)">
            <i :class="item.iconClass + ' link-icon'"></i>
            <span class="link-text">{{ item.text }}</span>
          </a>
        </li>
        <span class="indicator"></span>
      </ul>
    </nav>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const menuItems = ref([
  { text: '홈', iconClass: 'bi bi-house-door-fill' },
  { text: '지도', iconClass: 'bi bi-map' },
  { text: 'AI가격', iconClass: 'bi bi-graph-up' },
  { text: '관리', iconClass: 'bi bi-card-checklist' },
  { text: '마이페이지', iconClass: 'bi bi-person-fill' }
])

onMounted(() => {
  const linkItems = document.querySelectorAll(".link-item");
  linkItems.forEach((linkItem, index) => {
    linkItem.addEventListener("click", () => {
      document.querySelector(".select_menu").classList.remove("select_menu");
      linkItem.classList.add("select_menu");
      const indicator = document.querySelector(".indicator");
      indicator.style.left = `${index * 64 + 32}px`;
    });
  });
});

function onClickNavigate(index) {
  if (index === 0) {
    router.push({ path: "/mobile_home" });
  } else if(index === 1) {
    router.push({ path: "/mobile_map" });
  } else if(index === 2) {
    router.push({ path: "/mobile_ai_real_estate" });
  } else if(index === 3) {
    window.open("http://localhost:5173/report");
  }  else if(index === 4) {
    router.push({ path: "mobile_my_page"})
  }
}
</script>

<style scoped>
.container-bottom-nav {
  position: fixed;
  bottom: 0;
  width: 100%;
  //height: 50px;
  background-color: var(--color-bg-white);
  z-index: 9999;
}

.container-bottom-nav {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: var(--color-bg-white);
}

nav {
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
  background: var(--color-bg-blue1);
  padding: 0 20px;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
  height: 60px;
}

.nav-content {
  display: flex;
  height: 80px; /* nav와 같은 높이로 조정 */
  align-items: center;
  list-style: none;
  position: relative;
}

.link-item {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 50px;
  width: 64px;
  text-decoration: none;
  transform: translateY(-8px);
  transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}
.link-item.select_menu {
  transform: translateY(-10px); /* 줄어든 높이에 맞춰 이동 거리 조정 */
}

.link-icon {
  font-size: 20px; /* 기존 30px에서 줄임 */
  color: #fff;
  transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}
.link-item.select_menu .link-icon {
  color: #fff;
}

.link-text {
  position: absolute;
  font-size: 10px; /* 기존 12px에서 줄임 */
  font-weight: 500;
  color: #fff;
  opacity: 1;
  transform: translateY(20px); /* 높이에 맞게 조정 */
  transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}
.link-item.select_menu .link-text {
  opacity: 1;
}

.indicator {
  position: absolute;
  top: -12px;
  left: 34px;
  height: 24px;
  width: 24px;
  background-color: var(--color-bg-third);
  border: 6px solid #fff;
  border-radius: 50%;
  transform: translateX(-50%);
  transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.indicator::before,
.indicator::after {
  content: "";
  position: absolute;
  bottom: -14px; /* 위치 조정 */
  height: 20px; /* 비례 줄임 */
  width: 16px; /* 비례 줄임 */
}

.indicator::before {
  left: -18px; /* 비례 조정 */
  background-color: var(--color-bg-blue1);
  border-top-right-radius: 20px; /* 비례 조정 */
}

.indicator::after {
  right: -18px; /* 비례 조정 */
  background-color: var(--color-bg-blue1);
  border-top-left-radius: 20px; /* 비례 조정 */
}
</style>
