import "./note.css";
import { useEffect, useState } from "react";
import { I_Note, I_Tag } from "types/api";
import { ColorOption } from "../../types/enums";
import { Tag } from "../../components/tag/Tag";
import { Button } from "../../components/button/Button";
import { useFetch } from "../../hooks/useApi";
import { Dropdown } from "../../components/dropdown/Dropdown";
import { useDropdownReformatter } from "../../hooks/useListReformatter";
import { AxiosClient } from "../../services/AxiosClient";

export const Note: React.FC<NoteProps> = ({ note }) => {
    const { data: tags, error: tagError } = useFetch(`tags`);
    const formattedTags = useDropdownReformatter(tags, "name");

    const [body, setBody] = useState<I_Note | null>(null);
    const [noteId, setNoteId] = useState<number | null>(null);
    const [noteTitle, setNoteTitle] = useState<string>("");
    const [noteContent, setNoteContent] = useState<string>("");

    const handleSave = () => {
        if (note) {
            console.log("patch existing");
        } else {
            console.log("from funkytown ", body);
            AxiosClient.post("notes", {
                noteSetId: 1,
                title: noteTitle,
                content: noteContent,
                tagIds: [1, 2, 3],
            });
        }
    };

    const handleAddTag = () => {
        console.log("Adding tag");
    };

    useEffect(() => {
        console.log(note);
        console.log(tags);
        console.log(ColorOption.PRIMARY_BG);
        if (note) {
            setNoteTitle(note.title);
            setNoteContent(note.content);
        }
    }, [note]);

    return (
        <>
            <div className="note-view">
                <input
                    className="note-view-title"
                    value={noteTitle}
                    onChange={(e) => setNoteTitle(e.target.value)}
                />
                <textarea
                    className="note-view-content"
                    value={noteContent}
                    onChange={(e) => setNoteContent(e.target.value)}
                />
                {note && (
                    <div className="note-view-tag-list">
                        <Dropdown items={formattedTags} label={"Tags"} />
                        {note.tags?.map((tag: I_Tag) => (
                            <Tag tag={tag} />
                        ))}
                    </div>
                )}
            </div>
            <div className={`note-view-btn-box`}>
                <Button content="save" handleClick={handleSave} />
                <Button
                    content="+ Tag"
                    handleClick={handleAddTag}
                    color={ColorOption.SECONDARY_BG}
                />
            </div>
        </>
    );
};

interface NoteProps {
    note?: I_Note;
}
