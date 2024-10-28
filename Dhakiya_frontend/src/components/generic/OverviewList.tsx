import React from "react";
import { useNavigate } from "react-router-dom";

export const OverviewList: React.FC<OverviewListProps> = ({ list, linkTo }) => {
    const navigate = useNavigate();
    if (!Array.isArray(list)) {
        return <div>Nothing to see here</div>;
    }
    return (
        <ul style={listStyle}>
            {list.map((list) => (
                <li
                    style={listItem}
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

/* interfaces */
interface OverviewListProps {
    list: [{ id: number; title: string }];
    linkTo: string;
}

/* css */
const listStyle = {
    listStyleType: "none",
};

const listItem = {
    cursor: "pointer",
    padding: "9px 0 6px 12px",
    color: "var(--text-light)",
    letterSpacing: "1px",
    textTransform: "capitalize" as "capitalize", // typecast cause i know better then typescript
    boxShadow: "var(--shadow-soft-fade)",
    borderRadius: "3px",
    marginTop: "6px",
};
