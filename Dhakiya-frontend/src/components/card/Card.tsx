import "./card.css";
import { ColorOption } from "../../types/enums";

export const Card: React.FC<I_CardProps> = ({ content, color }) => {
    return (
        <div className={`card ${color ? color : "card-color-default"}`}>
            {content.main}
        </div>
    );
};

interface I_CardProps {
    color?: ColorOption;
    content: {
        id: number;
        main: string;
        header?: string;
        footer?: string;
    };
}
