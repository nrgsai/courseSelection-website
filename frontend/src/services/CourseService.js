import axiosInstance from './api';

const API_URL = 'http://localhost:8089/api'; // Ensure this matches your backend base URL

const getCourses = () => {
    return axiosInstance.get(`${API_URL}/courses`).then((response) => response.data);
};

const addCourse = (course) => {
    return axiosInstance.post(`${API_URL}/courses`, course);
};

const getSelectedCourses = () => {
    return axiosInstance.get(`${API_URL}/courses/selected`).then((response) => response.data);
};

const searchCourses = (query) => {
    return axiosInstance.get(`${API_URL}/courses/search`, { params: { q: query } })
        .then((response) => response.data);
};

const CourseService = {
    getCourses,
    addCourse,
    getSelectedCourses,
    searchCourses,
};

export default CourseService;
