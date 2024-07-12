import { useState } from 'react';
import CourseService from '../../services/CourseService';
import '../../styles/Student.css';

const CourseSearch = () => {
    const [query, setQuery] = useState('');
    const [results, setResults] = useState([]);

    const handleSearch = async (e) => {
        e.preventDefault();
        try {
            const data = await CourseService.searchCourses(query);
            setResults(data);
        } catch (error) {
            console.error('Error searching courses:', error);
        }
    };

    return (
        <div className="student-dashboard">
            <h2>Search Courses</h2>
            <form onSubmit={handleSearch}>
                <input
                    type="text"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    placeholder="Search by course name or instructor"
                />
                <button type="submit">Search</button>
            </form>
            <ul>
                {results.map((course) => (
                    <li key={course._id}>
                        {course.name} - {course.instructor}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CourseSearch;
