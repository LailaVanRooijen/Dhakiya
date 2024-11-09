import { useParams } from "react-router-dom";
import { LabelBar } from "../../components/generic/LabelBar";
import { useFetch } from "../../hooks/useApi";
import { useEffect } from "react";
import { CardDisplay } from "../../components/pageLayout/CardDisplay";
import { I_Note } from "../../types/api";
import { I_FlashcardSet } from "../../types/api";
import { I_QuizSet } from "../../types/api";
import { ColorOption } from "../../types/enums";

export const EnvironmentView = () => {
    const { id } = useParams<{ id: string }>();
    const { data: environment, error } = useFetch(`environments/${id}`);

    useEffect(() => {
        // TODO temp use effect, delete me later!
        console.log(environment);
    }, [environment]);

    if (error != null) {
        return <div>"woopsie"</div>;
    }

    return (
        <div>
            <LabelBar label={environment.title} />

            {environment.noteSet && (
                <CardDisplay
                    color={ColorOption.PRIMARY}
                    cardList={environment.noteSet.notes.map((set: I_Note) => ({
                        id: set.id,
                        main: set.title,
                    }))}
                />
            )}

            {environment.flashcardSets && (
                <CardDisplay
                    color={ColorOption.SECUNDARY}
                    cardList={environment.flashcardSets.map(
                        (set: I_FlashcardSet) => ({
                            id: set.id,
                            main: set.name,
                        })
                    )}
                />
            )}

            {environment.quizSets && (
                <CardDisplay
                    color={ColorOption.TERTIARY}
                    cardList={environment.quizSets.map((set: I_QuizSet) => ({
                        id: set.id,
                        main: set.name,
                    }))}
                />
            )}
        </div>
    );
};

// interfaces
interface I_EnvironmentViewProps {}
