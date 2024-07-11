import "./App.css";
import "./styles/Admin.css"
import "./styles/Auth.css"
import "./styles/Student.css"
import { Route, BrowserRouter, Routes } from "react-router-dom";
import AuthService from "./services/AuthService";
import  Login from "./components/Auth/Login";
import  Register  from "./components/Auth/Register";
import StudentDashboard from "./components/Student/StudentDashboard";
import AdminDashboard from "./components/Admin/AdminDashboard";

const App = () => {
  const currentUser = AuthService?.getCurrentUser();
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route  path="/" element={<Login/>} />
          <Route  path="/login" element={<Login/>} />
          <Route  path="/register" element={<Register/>} />
          {currentUser && currentUser.role === "admin" && (
            <Route path="/admin" component={AdminDashboard} />
          )}
          {currentUser && currentUser.role === "student" && (
            <Route path="/student" component={StudentDashboard} />
          )}
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;