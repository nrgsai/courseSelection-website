import axiosInstance from './api';

const API_URL = 'http://localhost:8089/api/courses/';

const getCourses = () => {
    return axiosInstance.get(API_URL).then((response) => response.data);
};

const addCourse = (course) => {
    return axiosInstance.post(API_URL, course);
};

const getSelectedCourses = () => {
    return axiosInstance.get(API_URL + 'selectedCourses') 
        .then((response) => response.data);
};

const searchCourses = (query) => {
    return axiosInstance.get(API_URL + 'search', {params: {q: query}}).then((response) => response.data);
};

const CourseService = {
    getCourses,
    addCourse,
    getSelectedCourses,
    searchCourses,
};

export default CourseService;
