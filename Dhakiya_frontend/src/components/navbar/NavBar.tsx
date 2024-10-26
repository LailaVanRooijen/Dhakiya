import { NavBarLink } from "./NavBarLink";
import { FaHome } from "react-icons/fa";
import { VscServerEnvironment } from "react-icons/vsc";
import { FaNoteSticky } from "react-icons/fa6";
import { PiCardsThreeFill } from "react-icons/pi";
import { MdQuiz } from "react-icons/md";

export const NavBar = () => {
    return (
        <nav style={navBox}>
            <ul style={navBar}>
                <NavBarLink path="/" label={<FaHome />} />
                <NavBarLink path="/notes" label={<FaNoteSticky />} />
                <NavBarLink path="/flashcards" label={<PiCardsThreeFill />} />
                <NavBarLink path="/tests" label={<MdQuiz />} />
            </ul>
        </nav>
    );
};

/* interface */

/* css */
const navBox: React.CSSProperties = {
    backgroundColor: "var(--primary-color-dark)",
    width: "60px",
    position: "sticky",
    paddingTop: "30px",
};

const navBar: React.CSSProperties = {
    padding: "0px",
    margin: "0px",
    listStyleType: "none",
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-evenly",
    alignItems: "center",
};
