import axios from "axios";

const BASE_URL = "http://localhost:8080/api/v1/";
const HEADERS = { "Content-Type": "application/json" };

export class AxiosClient {
    static async get<T>(url: string): Promise<T> {
        try {
            const response = await axios.get(`${BASE_URL}${url}`, {
                headers: HEADERS,
            });
            return response.data;
        } catch (error) {
            return error;
        }
    }

    static async post<T>(url: string, body: any): Promise<T> {
        try {
            const response = await axios.post(`${BASE_URL}${url}`, body, {
                headers: HEADERS,
            });
            return response.data;
        } catch (error) {
            return error.detail;
        }
    }
}
