import { NavBarLink } from "./NavBarLink";
import "./navbar.css";

import { FaHome } from "react-icons/fa";
import { VscServerEnvironment } from "react-icons/vsc";
import { FaNoteSticky } from "react-icons/fa6";
import { PiCardsThreeFill } from "react-icons/pi";
import { MdQuiz } from "react-icons/md";

export const NavBar = () => {
    return (
        <nav className="navbar">
            <ul>
                <NavBarLink path="/" label={<FaHome />} />
                <NavBarLink
                    path="/environment"
                    label={<VscServerEnvironment />}
                />
                <NavBarLink path="/notes" label={<FaNoteSticky />} />
                <NavBarLink path="/flashcards" label={<PiCardsThreeFill />} />
                <NavBarLink path="/tests" label={<MdQuiz />} />
            </ul>
        </nav>
    );
};
