import React from "react";

export const SidebarLayout: React.FC<SidebarLayoutProps> = ({
    leftContent,
    rightContent,
}) => {
    return (
        <div style={wrapper}>
            <div>{leftContent}</div>
            <div style={rightPanel}>{rightContent}</div>
        </div>
    );
};

/* interfaces */
interface SidebarLayoutProps {
    leftContent: React.ReactNode;
    rightContent: React.ReactNode;
}

/* css */
const wrapper: React.CSSProperties = {
    position: "relative",
    display: "grid",
    gridTemplateColumns: "4fr 1fr",
};
const rightPanel: React.CSSProperties = {
    padding: "12px 0px",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
};
