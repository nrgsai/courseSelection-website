import {useState} from "react";
import AuthService from "../../services/AuthService";
import "../../styles/Auth.css";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await AuthService.login(username, password);
        } catch (error) {
            console.error("Login error", error);
        }
    };

    return (
        <div className="auth-container">
            <form onSubmit={handleSubmit}>
                <h2>welcome to course selection system</h2>
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
