import { ThemeSwitch } from "./ThemeSwitch";
import { ToolBarLink } from "./ToolBarLink";
import { FaHome } from "react-icons/fa";

export const ToolBar = () => {
    return (
        <nav style={navBox}>
            <ul style={navBar}>
                <ThemeSwitch />
                <ToolBarLink path="/" label={<FaHome />} />
            </ul>
        </nav>
    );
};

/* interface */

/* css */
const navBox: React.CSSProperties = {
    backgroundColor: "var(--primary-bg-color)",
    width: "60px",
    position: "sticky",
    paddingTop: "30px",
    boxShadow: "var(--shadow-soft-fade)",
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
