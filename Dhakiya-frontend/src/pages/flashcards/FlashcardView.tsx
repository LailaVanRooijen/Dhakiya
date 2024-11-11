import { useFetch } from "../../hooks/useApi";
import { useParams } from "react-router-dom";

export const FlashcardView = () => {
    const { id } = useParams<{ id: string }>();
    const { data: flashcard, error } = useFetch(`flashcards/${id}`);

    if (flashcard == null) return <div>No flashcard in sight</div>;

    return <div>{flashcard.name}</div>;
};
