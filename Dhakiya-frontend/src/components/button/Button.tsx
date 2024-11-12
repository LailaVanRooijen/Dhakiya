import { ColorOption } from "types/enums";
import "./button.css";

export const Button: React.FC<ButtonProps> = ({
    content,
    handleClick,
    color,
}) => {
    return (
        <button
            className={`button ${color ? color : "btn-color"}`}
            onClick={handleClick}
        >
            {content}
        </button>
    );
};

interface ButtonProps {
    content: string;
    handleClick: () => void;
    color?: ColorOption;
}
