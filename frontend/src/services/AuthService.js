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
                localStorage.setItem('user', JSON.stringify(response.data));
            }
            return response.data;
        });
};

const logout = () => {
    localStorage.removeItem('user');
};
const getCurrentUser = () => {
    const user = localStorage.getItem('user');
    if (user){
        return JSON.parse(user);

    }
}
const AuthService = {
    register,
    login,
    logout,
    getCurrentUser
};

export default AuthService;
