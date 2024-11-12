import axios from "axios";

const BASE_URL = "http://localhost:8080/api/v1/";
const HEADERS = { "Content-Type": "application/json" };

//TODO finish service class, refactor all current useApi hooks to make use of this class
// TODO deny empty bodies to posted from front as well as back end.

export class AxiosClient {
    static get<T>(url: string) {
        axios
            .get(`${BASE_URL}${url}`, {
                headers: HEADERS,
            })
            .then((response) => {
                return response.data;
            })
            .catch((error) => {
                return error.detail;
            });
    }

    static post<T>(url: string, body: any) {
        console.log("from AxiosClient ", body);
        axios
            .post(`${BASE_URL}${url}`, body, {
                headers: HEADERS,
            })
            .then((response) => {
                return response.data;
            })
            .catch((error) => {
                return error.detail;
            });
    }
}
