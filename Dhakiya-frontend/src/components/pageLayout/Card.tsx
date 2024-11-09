import { ColorOption } from "../../types/enums";

export const Card: React.FC<I_CardProps> = ({ content, color }) => {
    const card: React.CSSProperties = {
        borderRadius: "6px",
        padding: "20px",
        backgroundColor: color ? color : "#ffff88",
        color: "black",
        height: "4rem",
        cursor: "pointer",
        fontFamily: "var(--main-text-font)",
    };

    return <div className={``}>{content.main}</div>;
};

interface I_CardProps {
    color?: ColorOption;
    content: {
        id: number;
        main: string;
        header?: string;
        footer?: string;
    };
}
