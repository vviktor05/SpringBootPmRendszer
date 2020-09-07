const BACKEND_URL = 'http://localhost:8080';

export const url = (path: string): string => `${BACKEND_URL}/${path}`;