import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

axios.defaults.baseURL = import.meta.env.VITE_API_URL

// 간편하게 axios를 사용하기 위해 전역 설정하는데, 서버와 통신할 때 환경 변수의 URL을 사용
const app = createApp(App)
app.config.globalProperties.axios = axios

app.use(createPinia())
app.use(router)

app.mount('#app')