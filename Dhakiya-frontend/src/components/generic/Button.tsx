import React from "react";

export const Button: React.FC<ButtonProps> = ({ content, handleClick }) => {
    return (
        <button style={style} onClick={handleClick}>
            {content}
        </button>
    );
};

/* interfaces */
interface ButtonProps {
    content: string;
    handleClick: () => void;
}

/* css */
const style: React.CSSProperties = {
    padding: "9px 12px",
    border: "none",
    borderRadius: "3px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "var(--cta-color)",
    color: "var(--text-light)",
    letterSpacing: "1px",
    fontWeight: "400",
    cursor: "pointer",
    boxShadow: "var(--shadow-soft-fade)",
};
