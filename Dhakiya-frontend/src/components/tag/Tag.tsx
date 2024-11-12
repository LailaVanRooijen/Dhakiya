import "./tag.css";
import React from "react";
import { I_Tag } from "types/api";
import { TiDelete } from "react-icons/ti";

export const Tag: React.FC<TagProps> = ({ tag }) => {
    const handleClick = () => {
        console.log("Delete this tag!"); // TODO !
    };
    return (
        <div className={"note-view-tag-box"} key={tag.id}>
            <div className={`note-view-tag`}>{tag.name}</div>
            <TiDelete className={`tag-delete-btn`} onClick={handleClick} />
        </div>
    );
};

interface TagProps {
    tag: I_Tag;
}
