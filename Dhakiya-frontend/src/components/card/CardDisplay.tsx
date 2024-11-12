import { I_Card } from "../../types/styleTypes";
import { Card } from "./Card";
import { ColorOption } from "../../types/enums";
import { Button } from "../../components/button/Button";
import { useNavigate } from "react-router-dom";

export const CardDisplay: React.FC<I_CardDisplayProps> = ({
    cardList,
    color,
    linkTo,
    label,
    creationEndpoint,
}) => {
    const navigate = useNavigate();
    const redirect = () => {
        navigate(`/${creationEndpoint}`);
    };

    return (
        <div className="card-display-container">
            {label && <h2 className="card-display-label">{label}</h2>}
            <div className={`card-display`}>
                {cardList &&
                    cardList.map((card) => (
                        <Card
                            content={card}
                            key={card.id}
                            color={color}
                            linkTo={linkTo}
                            itemId={card.id}
                        />
                    ))}
            </div>

            {creationEndpoint && (
                <div className="card-display-btn-box">
                    <Button content="+ new" handleClick={redirect} />
                </div>
            )}
        </div>
    );
};

interface I_CardDisplayProps {
    cardList: I_Card[];
    color?: ColorOption;
    linkTo?: string;
    label?: String;
    creationEndpoint?: string;
}
