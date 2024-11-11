import "./card.css";
import { ColorOption } from "../../types/enums";
import { useNavigate } from "react-router-dom";

export const Card: React.FC<I_CardProps> = ({
    content,
    color,
    linkTo,
    itemId,
}) => {
    const navigate = useNavigate();
    return (
        <div
            className={`card ${color ? color : "card-color-default"}`}
            onClick={() => navigate(`/${linkTo}/${itemId}`)}
        >
            {content.main}
        </div>
    );
};

interface I_CardProps {
    color?: ColorOption;
    linkTo?: string;
    itemId: number | string;
    content: {
        id: number;
        main: string;
        header?: string;
        footer?: string;
    };
}
