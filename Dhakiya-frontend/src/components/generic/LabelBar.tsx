import React from "react";

export const LabelBar: React.FC<LabelBarProps> = ({ label }) => {
    return <h2 style={bar}>{label}</h2>;
};

/* interface */
interface LabelBarProps {
    label: string;
}

/* css */
const bar = {
    color: "var(--text-light)",
    backgroundColor: "var(--primary-color-light)",
    padding: "18px",
    borderRadius: "3px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    textShadow: "var(--text-outline)",
    boxShadow: "var(--shadow-multi-depth)",
};
