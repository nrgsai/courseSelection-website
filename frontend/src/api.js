import axios from 'axios';

const API_URL = 'http://localhost:8089/api'; // Replace with your backend URL

const axiosInstance = axios.create({
  baseURL: API_URL,
});

export default axiosInstance;
