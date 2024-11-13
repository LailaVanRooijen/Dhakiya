import { useParams } from "react-router-dom";

export const FlashcardView = () => {
    const { id } = useParams<{ id: string }>();

    return <div>{}</div>;
};
