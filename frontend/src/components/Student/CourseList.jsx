import {useEffect, useState} from 'react';
import CourseService from '../../services/CourseService';
import '../../styles/Student.css';

const CourseList = () => {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        fetchCourses();
    }, []);

    const fetchCourses = async () => {
        const data = await CourseService.getCourses();
        setCourses(data);
    };

    return (
        <div className="student-dashboard">
            <h2>Available Courses</h2>
            <ul>
                {courses.map((course) => (
                    <li key={course._id}>{course.name} - {course.instructor}</li>
                ))}
            </ul>
        </div>
    );
};

export default CourseList;
