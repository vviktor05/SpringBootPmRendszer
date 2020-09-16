import axios from 'axios';
import { url } from '../util/BackendURL';
import Cookies from 'js-cookie';

export const SESSION_STORAGE_LOGGED_IN_EMPLOYEE = 'authenticatedEmployee';

class AuthService {

    login(email, password) {
        return axios.post(url("api/auth/signin"), {
                email,
                password
            })
            .then(response => {
                if (response.data.token) {
                    sessionStorage.setItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE, JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE);
        if (user === null)
            return false;

        return true;
    }

    getLoggedInUserEmail() {
        let user = sessionStorage.getItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE);
        if (user === null)
            return '';

        return JSON.parse(user).email;
    }

    getLoggedInUsername() {
        let user = sessionStorage.getItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE);
        if (user === null)
            return '';

        return JSON.parse(user).name;
    }

    getLoggedInUser() {
        let user = sessionStorage.getItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE);
        if (user === null)
            return '';

        return JSON.parse(user);
    }

    logout() {
        sessionStorage.removeItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE);
        Cookies.remove("JSESSIONID");
        axios.get(url("logout"), { withCredentials: true });
    }
}

export default new AuthService()