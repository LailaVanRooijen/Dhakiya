import "./toolbar.css";
import { useStyle } from "../../context/ThemeContext";
import { MdLightMode } from "react-icons/md";
import { MdDarkMode } from "react-icons/md";

export const ThemeSwitch = () => {
    const { theme, toggleTheme } = useStyle();

    const handleClick = () => {
        toggleTheme();
    };

    if (theme.name == "dark") {
        return (
            <li className={`theme-switch`}>
                <MdLightMode onClick={handleClick} className={`nav-link`} />
            </li>
        );
    }

    if (theme.name == "light") {
        return (
            <li className={`theme-switch`}>
                <MdDarkMode onClick={handleClick} className={`nav-link`} />
            </li>
        );
    }
};
