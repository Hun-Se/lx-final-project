import { fileURLToPath, URL } from "node:url";
import { defineConfig, loadEnv } from "vite";
import vue from "@vitejs/plugin-vue";
import VitePluginHtmlEnv from 'vite-plugin-html-env';
import { quasar, transformAssetUrls } from '@quasar/vite-plugin';

export default ({ mode }) => {
  // 현재 작업 디렉터리의 `mode`를 기반으로 env 파일을 불러옴
  // 세 번째 매개변수를 ''로 설정하면 `VITE_` 접두사에 관계없이 모든 환경 변수를 불러옴
  const env = loadEnv(mode, process.cwd());

  return defineConfig({
    plugins: [
      vue({
        template: { transformAssetUrls }
      }),
      quasar({
        sassVariables: 'src/assets/quasar/quasar-variables.sass'
      }),
    ],
    resolve: {
      alias: {
        "@": fileURLToPath(new URL("./src", import.meta.url)),
      },
    },
    build: {
      outDir: "../backend/src/main/resources/static",
    },
    server: {
      host: 'localhost',
      port: 9000,
      proxy: {
        "/ws/chat": {
          target: env.VITE_API_URL, // .env에서 불러온 API URL
          changeOrigin: true,
          secure: false,
          ws: true, // WebSocket 프록시 활성화
        },
        "/api": {
          target: env.VITE_API_URL, // .env에서 불러온 API URL
          changeOrigin: true,
          secure: false,
          ws: false, // 일반 HTTP API
        },
      },
    },
    define: {
      'process.env.VITE_APP_OPENAI_API_KEY': JSON.stringify(env.VITE_APP_OPENAI_API_KEY), // env에서 불러온 OPENAI_API_KEY 정의
    },
  });
};
