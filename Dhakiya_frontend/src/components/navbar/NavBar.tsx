import { NavBarLink } from "./NavBarLink";

export const NavBar = () => {
    return (
        <nav>
            <ul>
                <NavBarLink path="/" label="Home" />
                <NavBarLink path="/environment" label="Environments" />
            </ul>
        </nav>
    );
};
