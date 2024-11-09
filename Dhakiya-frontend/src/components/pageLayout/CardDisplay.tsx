import { I_Card } from "../../types/styleTypes";
import { Card } from "./Card";
import { ColorOption } from "../../types/enums";

export const CardDisplay: React.FC<I_CardDisplayProps> = ({
    cardList,
    color,
}) => {
    return (
        <div style={cardDisplay}>
            {cardList &&
                cardList.map((card) => (
                    <Card content={card} key={card.id} color={color} />
                ))}
        </div>
    );
};

interface I_CardDisplayProps {
    cardList: I_Card[];
    color?: ColorOption;
}

const cardDisplay: React.CSSProperties = {
    minHeight: "200px",
    borderBottom: "2px solid var(--text-color)",
    borderTop: "2px solid var(--text-color)",
    display: "flex",
    flexDirection: "row",
    alignItems: "center",
    gap: "9px",
    justifyContent: "center",
    padding: "4px",
};
