import React, {
    createContext,
    useContext,
    useState,
    useCallback,
    useEffect,
} from "react";
import axios from "axios";

const BASE_URL = "http://localhost:8080/api/v1/";
const HEADERS = { "Content-Type": "application/json" };

export const useFetch = (url: string) => {
    const [data, setData] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`${BASE_URL}${url}`, {
                    headers: HEADERS,
                });
                setData(response.data);
            } catch (err) {
                setError(err.message);
            }
        };
        fetchData();
    }, [url]);
    return { data, error };
};

export const usePost = (url: string, body: {}) => {
    const [data, setData] = useState([]);
    const [error, setError] = useState(null);

    axios
        .post(`${BASE_URL}${url}`, {
            body,
            headers: HEADERS,
        })
        .then((response) => {
            setData(response.data);
        })
        .catch((err) => {
            setError(err);
        });
};
