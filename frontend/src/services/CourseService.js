import axiosInstance from './api';

const API_URL = 'https://738d-86-55-248-175.ngrok-free.app'; // Assuming this is the base URL for your backend

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
    return axiosInstance.post(`${API_URL}/course/search`, {name: query})
        .then((response) => response.data);
};

const CourseService = {
    getCourses,
    addCourse,
    getSelectedCourses,
    searchCourses,
};

export default CourseService;
