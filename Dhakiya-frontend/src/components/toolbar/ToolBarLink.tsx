import React from "react";
import { Link } from "react-router-dom";

export const ToolBarLink: React.FC<NavBarLinkProps> = ({ path, label }) => {
    return (
        <li style={linkItem}>
            <Link style={navLink} to={path}>
                {label}
            </Link>
        </li>
    );
};
/* interfaces */
interface NavBarLinkProps {
    path: string;
    label: React.ReactNode;
}

/* css */
const linkItem: React.CSSProperties = {
    minHeight: "60px",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
};

const navLink: React.CSSProperties = {
    textDecoration: "none",
    color: "var(--text-light)",
    fontSize: "xx-large",
};
