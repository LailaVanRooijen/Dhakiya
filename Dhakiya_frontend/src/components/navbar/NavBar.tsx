import { NavBarLink } from "./NavBarLink";
import "./navbar.css";

export const NavBar = () => {
    return (
        <nav className="navbar">
            <ul>
                <NavBarLink path="/" label="Home" />
                <NavBarLink path="/environment" label="Environments" />
            </ul>
        </nav>
    );
};
