import { SESSION_STORAGE_LOGGED_IN_EMPLOYEE } from '../services/AuthService';

export default function authHeader() {
    const user = JSON.parse(sessionStorage.getItem(SESSION_STORAGE_LOGGED_IN_EMPLOYEE));
  
    if (user && user.token) {
      return { Authorization: 'Bearer ' + user.token };
    } else {
      return {};
    }
  }