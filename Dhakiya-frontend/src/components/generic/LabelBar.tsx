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
    color: "var(--text-color)",
    padding: "18px",
    borderRadius: "3px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    boxShadow: "var(--shadow-multi-depth)",
};
