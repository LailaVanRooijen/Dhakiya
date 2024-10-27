import React, { createContext, useContext, useState, useCallback } from "react";
import axios from "axios";

const FetchContext = createContext<FetchContextType | undefined>(undefined);
const BASE_URL = "http://localhost:8080/api/v1/";
const HEADERS = { "Content-Type": "application/json" };

export const useFetch = () => {
    return useContext(FetchContext);
};

export const FetchProvider: React.FC<FetchProviderProps> = ({ children }) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);

    const fetchData = useCallback((url: string) => {
        setError(null);
        axios
            .get(`${BASE_URL}${url}`, {
                headers: HEADERS,
            })
            .then((response) => {
                setData(response.data);
            })
            .catch((err) => {
                setError(err.message);
            });
    }, []);

    const postData = useCallback((url: string, body: {}) => {
        setError(null);
        axios
            .post(`${BASE_URL}${url}`, {
                body,
                headers: HEADERS,
            })
            .then((response) => {
                setData(response.data);
            })
            .catch((err) => {
                setError(err.message);
            });
    }, []);

    return (
        <FetchContext.Provider value={{ data, error, fetchData }}>
            {children}
        </FetchContext.Provider>
    );
};

interface FetchProviderProps {
    children: React.ReactNode;
}

interface FetchContextType {
    data: any;
    error: string | null;
    fetchData: (url: string) => void;
}
