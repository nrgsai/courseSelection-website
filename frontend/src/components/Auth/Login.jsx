import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../../services/AuthService";
import "../../styles/Auth.css";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const user = await AuthService.login(username, password);
            if (user) {
                console.log("Logged in user:", user);
                if (user.role === "ADMIN") {
                    navigate("/admin");
                } else if (user.role === "STUDENT") {
                    navigate("/student");
                } else {
                    console.error("Unknown user role:", user.role);
                }
            }
        } catch (error) {
            console.error("Login error", error);
        }
    };

    return (
        <div className="auth-container">
            <form onSubmit={handleSubmit}>
                <h2>Welcome to Course Selection System</h2>
                <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    placeholder="Username"
                />
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Password"
                />
                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default Login;
