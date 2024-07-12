import axiosInstance from './api';

const API_URL = 'http://localhost:8089';

const register = (username, password) => {
    return axiosInstance.post(API_URL + '/api/users/create', {
        username,
        password,
    });
};

const login = (username, password) => {
    return axiosInstance
        .post(API_URL + '/jwt/token', {
            username,
            password,
        })
        .then((response) => {
            if (response.data.accessToken) {
                localStorage.setItem('accessToken', response.data.accessToken);
            }
            return response.data;
        });
};

const logout = () => {
    localStorage.removeItem('user');
};
const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"))
}
const AuthService = {
    register,
    login,
    logout,
    getCurrentUser
};

export default AuthService;
