import { useParams } from "react-router-dom";
import { LabelBar } from "../../components/generic/LabelBar";
import { EnivronmentLayout } from "./EnivronmentLayout";
import { useFetch } from "../../hooks/useApi";
import { useEffect } from "react";

export const EnvironmentView = () => {
    const { id } = useParams<{ id: string }>();
    const { data: environment, error } = useFetch<IEnvironment>(
        `environments/${id}`
    );

    useEffect(() => {
        // temp use effect, delete me later!
        console.log(environment);
    }, [environment]);

    if (error != null) {
        return <div>"woopsie"</div>;
    }

    return (
        <div>
            <LabelBar label={environment.title} />
            {/* <EnivronmentLayout
                notesCollection={environment.noteSet}
                flashcardsCollection={environment.flashcardSets}
                quizSets={environment.quizSets}
            /> */}
        </div>
    );
};

interface IEnvironment {
    id: string;
    title: string;
    noteSet: {
        id: number;
        notes: string[];
    }[];
    flashcardSets: {
        id: number;
        question: string;
        answer: string;
    }[];
    quizSets: {
        id: number;
        questions: string[];
    }[];
}
