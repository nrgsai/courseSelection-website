import {useEffect, useState} from 'react';
import CourseService from '../../services/CourseService';
import '../../styles/Student.css';

const SelectedCourses = () => {
    const [selectedCourses, setSelectedCourses] = useState([]);

    useEffect(() => {
        fetchSelectedCourses();
    }, []);

    const fetchSelectedCourses = async () => {
        const data = await CourseService.getSelectedCourses();
        setSelectedCourses(data);
    };

    return (
        <div>
            <h2>Selected Courses</h2>
            <ul>
                {selectedCourses.map((course) => (
                    <li key={course._id}>{course.name} - {course.instructor}</li>
                ))}
            </ul>
        </div>
    );
};

export default SelectedCourses;
