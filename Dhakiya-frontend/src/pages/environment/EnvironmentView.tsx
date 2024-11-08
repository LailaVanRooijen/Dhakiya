import { useParams } from "react-router-dom";
import { LabelBar } from "../../components/generic/LabelBar";
import { useFetch } from "../../hooks/useApi";
import { useEffect } from "react";
import { I_FlashcardSet, I_Quiz } from "../../types/api";
import { I_Note } from "../../types/api";

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

            <div style={section}>
                {environment.noteSet &&
                    environment.noteSet.notes.map((note: I_Note) => (
                        <div key={note.id} style={noteStyle}>
                            {note.title}
                        </div>
                    ))}
            </div>

            <div>
                {environment.flashcardSets &&
                    environment.flashcardSets.map(
                        (flashcardSet: I_FlashcardSet) => (
                            <div key={flashcardSet.id} style={flashcard}>
                                {flashcardSet.name}
                            </div>
                        )
                    )}
            </div>

            <div>
                {environment.quizSets &&
                    environment.quizSets.map((quiz: I_Quiz) => (
                        <div key={quiz.id}>{quiz.name}</div>
                    ))}
            </div>
        </div>
    );
};

// interfaces
interface I_EnvironmentViewProps {}

// css styles
const section: React.CSSProperties = {
    minHeight: "200px",
    borderBottom: "2px solid var(--text-color)",
    borderTop: "2px solid var(--text-color)",
    display: "flex",
    flexDirection: "row",
    alignItems: "center",
    gap: "5px",
    justifyContent: "center",
    color: "var(--text-color)",
    padding: "4px",
};

const noteStyle = {
    borderRadius: "3px",
    borderTopRightRadius: "21px",
    padding: "20px",
    backgroundColor: "orange",
    height: "4rem",
};

const flashcard = {};
