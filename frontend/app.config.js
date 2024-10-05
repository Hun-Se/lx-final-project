
// 웹서버의 주소
const host = '172.168.10.126';
const port = 8001;

export const requestConfig = {
    baseUrl: `http://${host}:${port}`,
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': `http://${host}:${port}`,  // cors origin (서버의 cors 설정이 동일해야 응답)
        'Access-Control-Allow-Creadentials': true    // 쿠키를 요청에 포함 (cors 설정도 동일해야 가능)
    },
    timeout: 5000
}

