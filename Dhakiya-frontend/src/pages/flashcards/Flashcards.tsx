import { LabelBar } from "../../components/generic/LabelBar";
import { useNavigate } from "react-router-dom";
import { useFetch } from "../../hooks/useApi";

export const Flashcards = () => {
    const navigate = useNavigate();
    const { data: environments, error } = useFetch("environments");

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div>
            <LabelBar label={"Flashcards"} />
        </div>
    );
};
