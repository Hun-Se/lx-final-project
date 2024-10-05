import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from 'axios';

const app = createApp(App)

app.use(createPinia())
app.use(router)

// Axios를 전역에서 사용 가능하게 설정
app.config.globalProperties.$http = axios;

app.mount('#app')










