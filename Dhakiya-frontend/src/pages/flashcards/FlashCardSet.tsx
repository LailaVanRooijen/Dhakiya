import { useFetch } from "../../hooks/useApi";
import { useParams } from "react-router-dom";

export const FlashCardSet = () => {
    const { id } = useParams<{ id: string }>();
    const { data: flashcardSet, error } = useFetch(`flashcard-sets/${id}`);

    if (flashcardSet == null) return <div>no flashcard set in sight</div>;

    return (
        <div>
            FlashCardSet
            {flashcardSet.id}
        </div>
    );
};
