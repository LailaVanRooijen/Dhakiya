import "./toolbar.css";
import { useStyle } from "../../context/ThemeContext";
import { MdLightMode } from "react-icons/md";
import { MdDarkMode } from "react-icons/md";

export const ThemeSwitch = () => {
    const { theme, toggleTheme } = useStyle();

    if (theme.name == "dark") {
        return (
            <li className={`theme-switch`}>
                <MdLightMode onClick={toggleTheme} className={`nav-link`} />
            </li>
        );
    }

    if (theme.name == "light") {
        return (
            <li className={`theme-switch`}>
                <MdDarkMode onClick={toggleTheme} className={`nav-link`} />
            </li>
        );
    }
};
