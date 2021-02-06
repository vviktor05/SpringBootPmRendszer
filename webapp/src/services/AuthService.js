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
        if (this.getLoggedInUser())
            return true;

        return false;
    }

    getLoggedInUserEmail() {
        let user = this.getLoggedInUser();
        if (user)
            return JSON.parse(user).email;

        return "";
    }

    getLoggedInUsername() {
        let user = this.getLoggedInUser();
        if (user)
            return JSON.parse(user).name;

        return "";
    }

    getLoggedInUser() {
        let user = sessionStorage.getItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE);
        if (user)
            return JSON.parse(user);

        return null;
    }

    logout() {
        sessionStorage.removeItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE);
        Cookies.remove("JSESSIONID");
    }
}

export default new AuthService();