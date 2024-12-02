import "./addTags.css";
import { Button } from "../../components/button/Button";
import { I_Tag } from "types/api";
import { useEffect, useState } from "react";
import { ColorOption, widthOption } from "../../types/enums";
import { AxiosClient } from "../../services/AxiosClient";

export const AddTags: React.FC<AddTagsProps> = ({
    onSelect,
    selectedTagIds,
}) => {
    const [selected, setSelected] = useState<I_Tag[]>([]);
    const [tags,setTags] = useState<I_Tag[]>([]);

    useEffect(()=>{
        AxiosClient.get("tags")
            .then((response: I_Tag[]) => setTags(response))
            .catch((error) => console.error(error))
    },[])

    useEffect(()=>{
        if(tags){
            if(tags.length === 0)return;
             setSelected(tags.filter((tag)=>selectedTagIds.includes(tag.id)))
         }
    },[tags])  

    const handleCheckboxChange = (tag: I_Tag) => {
        console.log("initial: ",selected)
        const isSelected = selected.some(
            (selectedTag) => selectedTag.id === tag.id
        );
        const updatedSelectedTags = isSelected
            ? selected.filter((selectedTag) => selectedTag.id !== tag.id)
            : [...selected, tag];

            console.log(updatedSelectedTags)
        setSelected(updatedSelectedTags);

        onSelect(updatedSelectedTags);
    };

    const [show, setShow] = useState<boolean>(false);
    const toggleSelect = () => {
        setShow(!show);
    };

    return (
        <div className="add-tags-main-wrapper">
            <Button
                content={show ? "Hide tags" : "Show tags"}
                handleClick={toggleSelect}
                color={ColorOption.SECONDARY_BG}
                width={widthOption.MEDIUM}
            />
            <div className={`add-tags-tag-list`}>
                {tags &&
                    show &&
                    tags.map((tag: I_Tag) => (
                        <div className={`add-tags-tag-list-item`} key={tag.id}>
                            <input
                                type="checkbox"
                                name={tag.name}
                                value={tag.name}
                                checked={selected.some(
                                    (selectedTag) => selectedTag.id === tag.id
                                )}
                                onChange={() => handleCheckboxChange(tag)}
                            />
                            <label
                                className={`add-tags-list-item-label`}
                                htmlFor={tag.name}
                            >
                                {tag.name}
                            </label>
                        </div>
                    ))}
            </div>
        </div>
    );
};

interface AddTagsProps {
    onSelect: (tags: I_Tag[]) => void;
    selectedTagIds?: number[];
}
