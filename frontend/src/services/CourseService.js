import axiosInstance from './api';

const API_URL = 'http://localhost:8089'; // Assuming this is the base URL for your backend

const getCourses = () => {
    return axiosInstance.get(`${API_URL}/course/list`).then((response) => response.data);
};

const addCourse = (course) => {
    return axiosInstance.post(`${API_URL}/course/create`, course);
};

const getSelectedCourses = () => {
    return axiosInstance.get(`${API_URL}/course/selectedCourses`)
        .then((response) => response.data);
};

const searchCourses = (query) => {
    return axiosInstance.post(`${API_URL}/course/search`, { params: { name : query } })
        .then((response) => response.data);
};

const CourseService = {
    getCourses,
    addCourse,
    getSelectedCourses,
    searchCourses,
};

export default CourseService;
