import axios from 'axios';
import { requestConfig } from '../../app.config';
  
///
/// 서버에 POST 방식으로 데이터 요청
///
export const requestPost = (url, data, callback) => {
	axios({
		method: 'post',
		baseURL: requestConfig.baseUrl,  
		url: url,
		data: data,
		timeout: requestConfig.timeout,
		headers: {
			'X-User-Id': requestConfig.headers['X-User-Id']
		},
		responseType: 'json'
	}).then((response) => {
		callback(null, response);
	}).catch((err) => {
		callback(err, null);
	});
};

///
/// 서버에 POST 방식으로 데이터 요청 (Promise로 반환)
///
export const requestPostAsync = (url, data) => {
    return new Promise((resolve, reject) => {
        try {
            const response = axios({
                method: 'post',
                baseURL: requestConfig.baseUrl,  
                url: url,
                data: data,
                timeout: requestConfig.timeout,
                headers: {
                    'X-User-Id': requestConfig.headers['X-User-Id']
                },
                responseType: 'json'
            });

            resolve(response);
        } catch (error) {
            reject(error);
        }
    });
};

///
/// Axios 초기화
///
export const initAxios = () => {
    // 요청 인터셉터
    axios.interceptors.request.use(request => {
        console.log(`request intercepted : ${JSON.stringify(request)}`);

        return request;
    }, error => {
        return Promise.reject(error);
    });
                                        
    // 응답 인터셉터
    axios.interceptors.response.use((response) => {
        console.log(`response intercepted : ${JSON.stringify(response)}`);

        return response;
    }, error => {
        return Promise.reject(error);
    });
}

