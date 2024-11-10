import "./button.css";
import React from "react";

export const Button: React.FC<ButtonProps> = ({ content, handleClick }) => {
    return (
        <button className={`button`} onClick={handleClick}>
            {content}
        </button>
    );
};

interface ButtonProps {
    content: string;
    handleClick: () => void;
}
