import axios from 'axios';

const API_URL = 'http://localhost:8089';

const register = (username, password) => {
    return axios.post(API_URL + 'signup', {
        username,
        password,
    });
};

const login = (username, password) => {
    return axios
        .post(API_URL + '/token', {
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
    return JSON.parse(localStorage.getItem("user"))
}
const AuthService = {
    register,
    login,
    logout,
    getCurrentUser
};

export default AuthService;
