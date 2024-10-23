import React from "react";
import { Link } from "react-router-dom";

export const NavBarLink: React.FC<NavBarLinkProps> = ({ path, label }) => {
    return (
        <li>
            <Link to={path}>{label}</Link>
        </li>
    );
};

interface NavBarLinkProps {
    path: string;
    label: string;
}
