import { LabelBar } from "../../components/labelbar/LabelBar";
import { useNavigate } from "react-router-dom";

export const Flashcards = () => {
    const navigate = useNavigate();

    return (
        <div>
            <LabelBar label={"Flashcards"} />
        </div>
    );
};
