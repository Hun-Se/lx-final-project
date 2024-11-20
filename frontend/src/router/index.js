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
      path: "/mobile_map",
      name: "mobile_map",
      component: () => import("../views/MapView_mobile.vue"),
    },
    {
      path: "/broker_auction",
      name: "broker_acution",
      component: () => import("../views/BrokerAuctionView.vue"),
    },
    {
      path: "/user_auction",
      name: "user_acution",
      component: () => import("../views/UserAuctionView.vue"),
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
      path: "/chatting", // 채팅 화면 경로
      name: "chatting",
      component: () => import("../views/ChattingView.vue"),
    },
    {
      path: "/chat/input", // 입력 페이지 경로
      name: "chatInput", // 이름 설정
      component: () => import("../views/ChattingInputView.vue"),
    },
    {
      path: "/ai_real_estate",
      name: "ai_real_estate",
      component: () => import("../views/AI_real_estateView.vue"),
    },
    {
      path: "/board",
      name: "board",
      component: () => import("../views/BoardView.vue"),
    },
    {
      path: "/my_page",
      name: "my_page",
      component: () => import("../views/MyPageView.vue"),
    },
    {
      path: "/report",
      name: "report",
      component: () => import("../views/ReportView.vue"),
    },
    {
      path: "/mobile_home",
      name: "mobile_home",
      component: () => import("../views/MobileHomeView.vue"),
    },
    {
      path: "/mobile_my_page",
      name: "mobile_my_page",
      component: () => import("../views/MobileMyPageView.vue"),
    },
    {path: "/mobile_ai_real_estate",
      name: "mobile_ai_real_estate",
      component: () => import("../views/MobileAI_real_estateView.vue")
    },
    {path: "/mobile_ai_real_estate",
      name: "mobile_ai_real_estate",
      component: () => import("../views/MobileAI_real_estateView.vue")
    },
    {
      path: "/report_detail",
      name: "report_detail",
      component: () => import("../views/ReportDetailView.vue"),
    },
    {
      path: "/report_document",
      name: "report_document",
      component: () => import("../views/CreateReportDocumentView.vue"),
    },
    {
      path: "/flr",
      name: "flr",
      component: () => import("../views/AI_Flr.vue"),
    },
    {
      path: "/mobile_chat",
      name: "mobile_chat",
      component: () => import("../views/MobileChatView.vue"),
    },
    {
      path: "/mobile_prp_detail",
      name: "mobile_prp_detail",
      component: () => import("../views/MobilePrpDetailView.vue"),
    },
    {
      path: "/mobile_prp_report",
      name: "mobile_prp_report",
      component: () => import("../views/MobilePrpReportView.vue"),
    },
  ],
});

export default router;
