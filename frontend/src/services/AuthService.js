import axios from 'axios';

const API_URL = 'http://localhost:8089';

const register = (username, password) => {
    return axios.post(API_URL + '/api/users/create', {
        username,
        password,
    });
};

const login = (username, password) => {
    return axios
        .post(API_URL + '/jwt/token', {
            username,
            password,
        })
        .then((response) => {
            if (response.data.accessToken) {
                localStorage.setItem('users', JSON.stringify({
                    username: response.data.username,
                    role: response.data.role,
                    accessToken: response.data.accessToken,
                }));
            }
            return response.data;
        });
};

const logout = () => {
    localStorage.removeItem('users');
};

const getCurrentUser = () => {
    const userStr = localStorage.getItem('users');
    if (userStr) {
        return JSON.parse(userStr);
    }
    return null;
};

const AuthService = {
    register,
    login,
    logout,
    getCurrentUser,
};

export default AuthService;
