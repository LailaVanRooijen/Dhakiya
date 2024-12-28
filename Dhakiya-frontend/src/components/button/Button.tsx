import { widthOption } from "types/ThemeColors";
import "./button.css";

export const Button: React.FC<ButtonProps> = ({
  content,
  handleClick,
  btnStyle,
  width,
}) => {
  return (
    <button
      className={`button ${btnStyle ? btnStyle : "btn-default"} ${
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
  handleClick?: () => void;
  type?: string;
  btnStyle?: BUTTON_STYLE;
  width?: widthOption;
}

export enum BUTTON_STYLE {
  ALERT = "btn-alert",
  ENCOURAGE = "btn-encourage",
  ACCENT = "btn-accent",
}
