import { useParams } from "react-router-dom";

export const FlashCardSet = () => {
    const { id } = useParams<{ id: string }>();

    return <div>FlashCardSet</div>;
};
