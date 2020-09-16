import axios from 'axios';
import { url } from '../util/BackendURL';
import Cookies from 'js-cookie';

export const SESSION_STORAGE_LOGGED_IN_EMPLOYEE = 'authenticatedEmployee';

class AuthService {

    executeBasicAuth(email, password) {
        return axios.get(url("api/basicauth"),
            {
                headers: { Authorization: this.createBasicAuthHeader(email, password) },
                withCredentials: true
            });
    }

    createBasicAuthHeader(email, password) {
        return 'Basic ' + window.btoa(email + ":" + password);
    }

    registerSuccessfulLogin(employee) {
        sessionStorage.setItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE, employee);
        window.location.replace("/projects");
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
        window.location.replace("/login");
    }
}

export default new AuthService()