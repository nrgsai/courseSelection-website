import {useEffect, useState} from 'react';
import CourseService from '../../services/CourseService';
import '../../styles/Admin.css';

const ManageCourses = () => {
    const [courses, setCourses] = useState([]);
    const [courseName, setCourseName] = useState('');
    const [instructor, setInstructor] = useState('');

    useEffect(() => {
        fetchCourses();
    }, []);

    const fetchCourses = async () => {
        const data = await CourseService.getCourses();
        setCourses(data);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await CourseService.addCourse({name: courseName, instructor});
            fetchCourses();
        } catch (error) {
            console.error('Add course error', error);
        }
    };

    return (
        <div className="admin-dashboard">
            <h2>Manage Courses</h2>
            <form  onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={courseName}
                    onChange={(e) => setCourseName(e.target.value)}
                    placeholder="Course Name"
                />
                <input
                    type="text"
                    value={instructor}
                    onChange={(e) => setInstructor(e.target.value)}
                    placeholder="Instructor"
                />
                <button type="submit">Add Course</button>

            </form>
            <ul>
                {courses.map((course) => (
                    <li key={course._id}>{course.name} - {course.instructor}</li>
                ))}
            </ul>
        </div>
    );
};

export default ManageCourses;
