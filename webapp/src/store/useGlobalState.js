import { useReducer } from 'react';
import authService from '../services/AuthService';

const reducer = (state, action) => {
    switch (action.type) {
        case "LOGIN":
            return {
                ...state,
                loggedInUser: action.payload
            };

        case "LOGOUT":
            return {
                ...state,
                loggedInUser: null
            };

        case "SHOW_SIDEBAR":
            return {
                ...state,
                isShowSidebar: true
            };

        case "HIDE_SIDEBAR":
            return {
                ...state,
                isShowSidebar: false
            };

        default: {
            return state;
        }
    }
}

const useGlobalState = () => {
    const loggedInUser = authService.getLoggedInUser();
    const isUserLoggedIn = authService.isUserLoggedIn();

    const [globalState, globalDispatch] = useReducer(reducer, {
        loggedInUser: loggedInUser,
        isShowSidebar: isUserLoggedIn
    });

    return { globalState, globalDispatch };
}

export default useGlobalState;