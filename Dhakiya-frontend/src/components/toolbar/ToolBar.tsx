import "./toolbar.css";
import { ThemeSwitch } from "./ThemeSwitch";
import { ToolBarLink } from "./ToolBarLink";
import { FaHome } from "react-icons/fa";

export const ToolBar = () => {
    return (
        <nav className={`nav-box`}>
            <ul className={`nav-bar`}>
                <ThemeSwitch />
                <ToolBarLink path="/" label={<FaHome />} />
            </ul>
        </nav>
    );
};
