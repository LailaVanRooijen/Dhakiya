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
            <li style={item}>
                <MdLightMode onClick={handleClick} style={navLink} />
            </li>
        );
    }

    if (theme.name == "light") {
        return (
            <li style={item}>
                <MdDarkMode onClick={handleClick} style={navLink} />
            </li>
        );
    }
};

/* css */
const item: React.CSSProperties = {
    minHeight: "60px",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    cursor: "pointer",
};

const navLink: React.CSSProperties = {
    textDecoration: "none",
    color: "var(--text-color)",
    fontSize: "xx-large",
};
