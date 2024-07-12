import axios from 'axios';

const API_URL = 'http://localhost:8089';

const register = (username, password) => {
    return axios.post(API_URL + '/users/create', {
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
                localStorage.setItem('user', JSON.stringify({
                    accessToken: response.data.accessToken,
                    username: response.data.username,
                    role: response.data.role,
                }));
            }
            return response.data;
        });
};

const logout = () => {
    localStorage.removeItem('user');
};

const getCurrentUser = () => {
    const userStr = localStorage.getItem('user');
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
