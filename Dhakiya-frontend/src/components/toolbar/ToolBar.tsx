import { ToolBarLink } from "./ToolBarLink";
import { FaHome } from "react-icons/fa";

export const ToolBar = () => {
    return (
        <nav style={navBox}>
            <ul style={navBar}>
                <ToolBarLink path="/" label={<FaHome />} />
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
