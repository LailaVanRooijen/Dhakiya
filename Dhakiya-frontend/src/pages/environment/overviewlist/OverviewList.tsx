import "./overviewlist.css";
import { useNavigate } from "react-router-dom";
import { I_Environment } from "../../../types/api";

export const OverviewList: React.FC<I_OverviewListProps> = ({
    list,
    linkTo,
}) => {
    const navigate = useNavigate();
    if (!Array.isArray(list)) {
        return <div>Nothing to see here</div>;
    }
    return (
        <ul className={`overview-list-style`}>
            {list.map((list) => (
                <li
                    className={`overview-list-item`}
                    key={list.id}
                    onClick={() => {
                        navigate(`${linkTo}/${list.id}`);
                    }}
                >
                    {list.title}
                </li>
            ))}
        </ul>
    );
};

interface I_OverviewListProps {
    list: I_Environment[];
    linkTo: string;
}