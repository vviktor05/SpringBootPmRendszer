// const BACKEND_URL_DEV = 'http://localhost:8080';
const BACKEND_URL_PROD = 'https://pmrendszer.herokuapp.com';

export const url = (path: string): string => `${BACKEND_URL_PROD}/${path}`;