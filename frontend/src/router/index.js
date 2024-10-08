import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/example",
      name: "example",
      component: () => import("../views/ExampleView.vue"),
    },
    {
      path: "/map",
      name: "map",
      component: () => import("../views/MapView.vue"),
    },
    {
      path: "/broker-auction",
      name: "broker-acution",
      component: () => import("../views/BrokerAuctionView.vue"),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/Login.vue"),
    },
    {
      path: "/create_account",
      name: "create_account",
      component: () => import("../views/Create_account.vue"),
    },
    {
      path: '/chatting', // 채팅 화면 경로
      name: 'chatting',
      component: () => import('../views/ChattingView.vue'), 
    },
    {
      path: '/chat/input', // 입력 페이지 경로
      name: 'chatInput', // 이름 설정
      component: () => import('../views/ChattingInputView.vue'),
    }
  ],
});

export default router;
