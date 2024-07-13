import { useState } from 'react';
import CourseService from '../../services/CourseService';
import '../../styles/Student.css';

const CourseSearch = () => {
    const [query, setQuery] = useState('');
    const [results, setResults] = useState([]);
    const [error, setError] = useState('');

    const handleSearch = async (e) => {
        e.preventDefault();
        setError('');
        try {
            const data = await CourseService.searchCourses(query);
            console.log("Search Results:", data); // Debugging log
            if (Array.isArray(data)) {
                setResults(data);
            } else {
                setError('Unexpected response format');
                console.error('Unexpected response format:', data);
            }
        } catch (error) {
            setError('Error searching courses');
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
            {error && <p className="error-message">{error}</p>}
            <ul>
                {results.map((course) => (
                    <li key={course.id}>{course.name} - {course.instructor}</li>
                ))}
            </ul>
        </div>
    );
};

export default CourseSearch;
