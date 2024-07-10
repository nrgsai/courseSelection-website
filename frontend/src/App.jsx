import {BrowserRouter, Route, Routes} from "react-router-dom";
import "./App.css";
import AuthService from "./services/AuthService";
import Login from "./components/Auth/Login";
import Register from "./components/Auth/Register";
import AdminDashboard from "./components/Admin/AdminDashboard";
import StudentDashboard from "./components/Student/StudentDashboard";

const App = () => {
    const currentUser = AuthService?.getCurrentUser();
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                    {currentUser && currentUser.role === "admin" && (
                        <Route path="/admin" component={AdminDashboard}/>
                    )}
                    {currentUser && currentUser.role === "student" && (
                        <Route path="/student" component={StudentDashboard}/>
                    )}
                </Routes>
            </BrowserRouter>
        </>
    );
};

export default App;
