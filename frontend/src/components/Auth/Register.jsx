import {useState} from "react";
import { useNavigate } from "react-router-dom";

import AuthService from "../../services/AuthService";
import "../../styles/Auth.css";

function Register() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await AuthService.register(username, password);
            navigate("/login");
        } catch (error) {
            console.error("Register error", error);
        }
    };

    return (
        <div className="register-container">
            <form onSubmit={handleSubmit}>
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
                <button type="submit">Register</button>
            </form>
        </div>
    );
}

export default Register;
