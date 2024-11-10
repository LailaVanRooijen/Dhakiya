import { I_Card } from "../../types/styleTypes";
import { Card } from "./Card";
import { ColorOption } from "../../types/enums";

export const CardDisplay: React.FC<I_CardDisplayProps> = ({
    cardList,
    color,
}) => {
    return (
        <div className={`card-display`}>
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
