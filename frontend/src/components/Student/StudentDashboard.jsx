import CourseList from './CourseList';
import SelectedCourses from './SelectedCourses';
import CourseSearch from './CourseSearch';
import '../../styles/Student.css';

const StudentDashboard = () => {
    return (
        <div>
            <h1>Student Dashboard</h1>
            <CourseSearch/>
            <CourseList/>
            <SelectedCourses/>
        </div>
    );
};

export default StudentDashboard;
