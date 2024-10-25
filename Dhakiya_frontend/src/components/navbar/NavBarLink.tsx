import React from "react";
import { Link } from "react-router-dom";
import "./navbar.css";

export const NavBarLink: React.FC<NavBarLinkProps> = ({ path, label }) => {
    return (
        <li className="link-item">
            <Link className="nav-link" to={path}>
                {label}
            </Link>
        </li>
    );
};

interface NavBarLinkProps {
    path: string;
    label: React.ReactNode;
}
