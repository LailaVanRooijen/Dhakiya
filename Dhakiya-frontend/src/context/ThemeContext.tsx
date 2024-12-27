import { createContext, useContext, useEffect, useMemo, useState } from "react";

const themes = {
  light: {
    name: "light",
    background: "#ffffff",
    contrast: "#333333",
    text: "#333333",
    textContrast: "#ffffff",
  },
  dark: {
    name: "dark",
    background: "#333333",
    text: "#ffffff",
    contrast: "#ffffff",
    textContrast: "#333333",
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
    root.style.setProperty("--text-color", theme.text);
    root.style.setProperty("--primary-bg-contrast-color", theme.contrast);
    root.style.setProperty("--text-contrast", theme.textContrast);
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
