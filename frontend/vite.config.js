import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
  build: {
    outDir: "../backend/src/main/resources/static",
  },
  server: {
    host: "0.0.0.0",
    proxy: {
      "/ws/chat": {
        target: "http://localhost:9000", // 이 부분을 수정
        changeOrigin: true,
        secure: false,
        ws: true, // WebSocket 프록시 활성화
      },
      "/api": {
        target: "http://localhost:9000",
        changeOrigin: true,
        secure: false,
        ws: false, // 일반 HTTP API
      },
    },
  },
});
