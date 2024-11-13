import { useParams } from "react-router-dom";
import { LabelBar } from "../../components/labelbar/LabelBar";
import { useEffect, useState } from "react";
import { CardDisplay } from "../../components/card/CardDisplay";
import { I_Environment, I_Note } from "../../types/api";
import { I_FlashcardSet } from "../../types/api";
import { I_QuizSet } from "../../types/api";
import { ColorOption } from "../../types/enums";
import { AxiosClient } from "../../services/AxiosClient";

export const EnvironmentView = () => {
    const { id } = useParams<{ id: string }>();
    const [environment, setEnvironment] = useState<I_Environment | null>(null);

    useEffect(() => {
        AxiosClient.get(`environments/${id}`)
            .then((response: I_Environment) => setEnvironment(response))
            .catch((error) => console.error(error));
        console.log(environment);
    }, []);

    if (environment) {
        return (
            <div>
                <LabelBar label={environment.title} />

                {environment.noteSet && (
                    <CardDisplay
                        color={ColorOption.PRIMARY_BG}
                        label={"Notes"}
                        linkTo={`note`}
                        creationEndpoint={"note"}
                        cardList={environment.noteSet.notes.map(
                            (set: I_Note) => ({
                                id: set.id,
                                main: set.title,
                            })
                        )}
                    />
                )}

                {environment.flashcardSets && (
                    <CardDisplay
                        label={"Flashcard sets"}
                        color={ColorOption.SECONDARY_BG}
                        linkTo={`flashcard-set`}
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
                        label={"quiz sets"}
                        linkTo={`quiz`}
                        color={ColorOption.TERTIARY_BG}
                        cardList={environment.quizSets.map(
                            (set: I_QuizSet) => ({
                                id: set.id,
                                main: set.name,
                            })
                        )}
                    />
                )}
            </div>
        );
    }
};

// interfaces
interface I_EnvironmentViewProps {}
