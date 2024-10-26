import { NavBar } from "../navbar/NavBar";

export const Page: React.FC<PageProps> = ({ children }) => {
    return (
        <div style={pageLayout}>
            <NavBar />
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
    backgroundColor: "var(--primary-color)",
};
