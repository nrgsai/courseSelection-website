import axios from 'axios';

const API_URL = 'https://738d-86-55-248-175.ngrok-free.app';

const axiosInstance = axios.create({
    baseURL: API_URL,
});

axiosInstance.interceptors.request.use(
    (config) => {
        const accessToken = localStorage.getItem('accessToken');
        if (accessToken != null) {
            config.headers['Authorization'] = accessToken;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default axiosInstance;
