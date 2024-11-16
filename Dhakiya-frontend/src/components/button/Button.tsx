import { ColorOption, widthOption } from "types/enums";
import "./button.css";

export const Button: React.FC<ButtonProps> = ({
    content,
    handleClick,
    color,
    width,
}) => {
    return (
        <button
            className={`button ${color ? color : "btn-color"} ${
                width ? width : ""
            }`}
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
    width?: widthOption;
}
