import { useNavigate, useParams } from "react-router-dom";
import { FlashcardView } from "./FlashcardView";

export const FlashCardSet = () => {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();

    return (
        <div>
            FlashCardSet
            <button onClick={() => navigate(`/flashcard/${id}`)}>
                push me
            </button>
        </div>
    );
};
