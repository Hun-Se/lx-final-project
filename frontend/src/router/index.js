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
      path: "/broker_auction",
      name: "broker_acution",
      component: () => import("../views/BrokerAuctionView.vue"),
    },
    {
      path: "/user_auction",
      name: "user_acution",
      component: () => import("../views/userAuctionView.vue"),
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
    },
    {
      path: "/ai_real_estate",
      name: "ai_real_estate",
      component: () => import("../views/AI_real_estate.vue"),
    },
    {
      path: "/board",
      name: "board",
      component: () => import("../views/BoardView.vue"),
    },
    {
      path: "/viewing_prp",
      name: "viewingPrp",
      component: () => import("../views/ViewingPrpView.vue")
    }
  ],
});

export default router;
