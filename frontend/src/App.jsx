import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import './App.css';
import AuthService from './services/AuthService';
import Login from './components/Auth/Login';
import Register from './components/Auth/Register';
import AdminDashboard from './components/Admin/AdminDashboard';
import StudentDashboard from './components/Student/StudentDashboard';

const App = () => {
    const currentUser = AuthService.getCurrentUser();
    return (
        <Router>
            <div className="App">
                <Switch>
                    <Route exact path="/" component={Login}/>
                    <Route exact path="/login" component={Login}/>
                    <Route exact path="/register" component={Register}/>
                    {currentUser && currentUser.role === 'admin' && (
                        <Route path="/admin" component={AdminDashboard}/>
                    )}
                    {currentUser && currentUser.role === 'student' && (
                        <Route path="/student" component={StudentDashboard}/>
                    )}
                </Switch>
            </div>
        </Router>
    );
};

export default App;
