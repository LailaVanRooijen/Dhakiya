import React, { useEffect } from "react";
import { LabelBar } from "../../components/generic/LabelBar";
import { useNavigate } from "react-router-dom";
import { useFetch } from "../../context/ContextApi";

export const Flashcards = () => {
    const navigate = useNavigate();
    const { data: environments, error, fetchData } = useFetch();

    useEffect(() => {
        console.log("fething environments");
        fetchData("environments");
    }, []);

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div>
            <LabelBar label={"Flashcards"} />
        </div>
    );
};
