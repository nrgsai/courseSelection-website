import ManageCourses from './ManageCourses';
import '../../styles/Admin.css';

const AdminDashboard = () => {
    return (
        <div className="admin-dashboard">
            <h1>Admin Dashboard</h1>
            <ManageCourses/>
        </div>
    );
};

export default AdminDashboard;
