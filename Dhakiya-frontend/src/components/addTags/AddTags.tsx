import "./addTags.css";
import { Button } from "../../components/button/Button";
import { I_Tag } from "types/api";
import { useState } from "react";
import { ColorOption, widthOption } from "../../types/enums";

export const AddTags: React.FC<AddTagsProps> = ({
    onSelect,
    tagList,
    selectedTags,
}) => {
    const [selected, setSelected] = useState<I_Tag[]>(selectedTags || []);

    const handleCheckboxChange = (tag: I_Tag) => {
        const isSelected = selected.some(
            (selectedTag) => selectedTag.id === tag.id
        );
        const updatedSelectedTags = isSelected
            ? selected.filter((selectedTag) => selectedTag.id !== tag.id)
            : [...selected, tag];

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
                {tagList &&
                    show &&
                    tagList.map((tag: I_Tag) => (
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
    tagList: I_Tag[];
    selectedTags?: I_Tag[];
}
