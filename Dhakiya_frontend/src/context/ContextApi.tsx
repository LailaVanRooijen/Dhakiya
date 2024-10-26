import React, { createContext, useContext, useState } from "react";
import axios from "axios";

const FetchContext = createContext();
const BASE_URL = "http://localhost:8080/api/v1/";
const HEADERS = { "Content-Type": "application/json" };

export const useFetch = () => {
    return useContext(FetchContext);
};

export const FetchProvider: React.FC<FetchProviderProps> = ({ children }) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);

    const fetchData = (url: string) => {
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
    };

    return (
        <FetchContext.Provider value={{ data, error, fetchData }}>
            {children}
        </FetchContext.Provider>
    );
};

interface FetchProviderProps {
    children: React.ReactNode;
}
