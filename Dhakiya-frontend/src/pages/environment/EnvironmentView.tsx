import { useParams } from "react-router-dom";
import { LabelBar } from "../../components/generic/LabelBar";
import { useFetch } from "../../hooks/useApi";
import { useEffect } from "react";

export const EnvironmentView = () => {
    const { id } = useParams<{ id: string }>();
    const { data: environment, error } = useFetch(`environments/${id}`);

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
