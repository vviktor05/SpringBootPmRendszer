const BACKEND_URL = 'https://pmrendszer.herokuapp.com';

export const url = (path: string): string => `${BACKEND_URL}/${path}`;