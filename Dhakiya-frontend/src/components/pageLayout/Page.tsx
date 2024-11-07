import { ToolBar } from "../toolbar/ToolBar";
import { useStyle } from "../../context/ThemeContext";

export const Page: React.FC<PageProps> = ({ children }) => {
    const { theme } = useStyle();

    return (
        <div style={pageLayout}>
            <ToolBar />
            <div style={contentBox}>{children}</div>
        </div>
    );
};

/* interfaces */
interface PageProps {
    children: React.ReactNode;
}

/* css */
const pageLayout: React.CSSProperties = {
    minHeight: "100vh",
    minWidth: "100vw",
    display: "flex",
    position: "relative",
};

const contentBox: React.CSSProperties = {
    padding: "27px",
    width: "100%",
    backgroundColor: "var(--primary-bg-color)",
};
