import { createContext, useContext, useState, useMemo, useEffect } from "react";

const themes = {
    light: {
        name: "light",
        background: "#ffffff",
        text: "#333333",
    },
    dark: {
        name: "dark",
        background: "#333333",
        text: "#ffffff",
    },
};

const ThemeContext = createContext({
    theme: themes.dark,
    toggleTheme: () => {},
});

export const ThemeProvider: React.FC<ThemeContextProps> = ({ children }) => {
    const [theme, setTheme] = useState(themes.dark);

    useEffect(() => {
        const root = document.documentElement;

        root.style.setProperty("--primary-bg-color", theme.background);
        root.style.setProperty("--text", theme.text);
    }, [theme]);

    const toggleTheme = () => {
        setTheme((prevTheme) =>
            prevTheme === themes.dark ? themes.light : themes.dark
        );
    };

    const value = useMemo(() => ({ theme, toggleTheme }), [theme]);

    return (
        <ThemeContext.Provider value={value}>{children}</ThemeContext.Provider>
    );
};

/* interfaces */
interface ThemeContextProps {
    children: React.ReactNode;
}
export const useStyle = () => useContext(ThemeContext);
