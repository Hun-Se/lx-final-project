import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/map',
      name: 'map',
      component: () => import('../views/MapView.vue')
    }
  ]
})

export default router

document.addEventListener('DOMContentLoaded', function() {
    const toggleButton = document.getElementById('kt_app_sidebar_toggle');
    const sidebar = document.getElementById('kt_app_sidebar');

    toggleButton.addEventListener('click', function() {
        sidebar.classList.toggle('collapsed'); // 'collapsed' 클래스 사용
        toggleButton.classList.toggle('active'); // 선택적: active 클래스를 추가하여 아이콘 상태 변경
    });
});

// const express = require('express');
// const mysql = require('mysql');
// const bodyParser = require('body-parser');
// const cors = require('cors');

// const app = express();
// const port = 3000;

// app.use(cors());
// app.use(bodyParser.json()); // JSON 요청 본문을 파싱

// // MySQL 연결 설정
// const connection = mysql.createConnection({
//     host: 'localhost',
//     port: 3306,
//     user: 'root',
//     password: 'rootroot',
//     database: 'sales'
// });

// // 연결 확인
// connection.connect(err => {
//     if (err) {
//         console.error('mysql connection error');
//         throw err;
//     }
// });

// // API
// app.post('/regist', (req, res) => {
//     const user = {
//         salesimg: req.body.salesimg,
//         salesname: req.body.salesname,
//         salescontent: req.body.salescontent
//     };
//     const query = connection.query('INSERT INTO users SET ?', user, (err, result) => {
//         if (err) {
//             console.error(err);
//             throw err;
//         }
//         res.status(200).send('success');
//     });
// });

// // 서버 시작
// app.listen(port, () => {
//     console.log(`Server running at http://localhost:${port}`);
// });


// document.addEventListener('DOMContentLoaded', function() {
//   const toggleButton = document.getElementById('kt_app_sidebar_toggle');
//   const sidebar = document.getElementById('kt_app_sidebar');
//   const menuItems = sidebar.querySelectorAll('.menu');

//   toggleButton.addEventListener('click', function() {
//       sidebar.classList.toggle('collapsed'); // 'collapsed' 클래스 사용
//       toggleButton.classList.toggle('active'); // 선택적: active 클래스를 추가하여 아이콘 상태 변경

//       // 메뉴 아이템의 표시 여부 조정
//       menuItems.forEach(menu => {
//           if (sidebar.classList.contains('collapsed')) {
//               menu.style.display = 'none'; // 접혔을 때 메뉴 숨김
//           } else {
//               menu.style.display = 'block'; // 펼쳤을 때 메뉴 보이기
//           }
//       });
//   });
// });
// ;

