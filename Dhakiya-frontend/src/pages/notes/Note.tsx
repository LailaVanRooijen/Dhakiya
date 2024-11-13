import "./note.css";
import { useEffect, useState } from "react";
import { I_Note, I_Tag } from "types/api";
import { ColorOption } from "../../types/enums";
import { Tag } from "../../components/tag/Tag";
import { Button } from "../../components/button/Button";
import { Dropdown } from "../../components/dropdown/Dropdown";
import { useDropdownReformatter } from "../../hooks/useListReformatter";
import { AxiosClient } from "../../services/AxiosClient";
import { useNavigate } from "react-router-dom";
import { usePostNoteValidator } from "../../hooks/useValidators";

export const Note: React.FC<NoteProps> = ({ note, noteSetId }) => {
    const navigate = useNavigate();
    const [tags, setTags] = useState<I_Tag[]>([]);
    const formattedTags = useDropdownReformatter(tags, "name");
    const [noteTitle, setNoteTitle] = useState<string>("");
    const [noteContent, setNoteContent] = useState<string>("");

    const [noteId, setNoteId] = useState<number | null>(null);

    const handleSave = () => {
        if (note) {
            console.log("patch existing");
        } else {
            if (!noteSetId) {
                console.log("No noteSetId found");
                return;
            }
            try {
                const body = usePostNoteValidator({
                    noteSetId: noteSetId,
                    title: noteTitle,
                    content: noteContent,
                    tagIds: [1], // TODO get tagIds
                });
                AxiosClient.post("notes", body)
                    .then((response: I_Note) => {
                        navigate(`/note/${response.id}`);
                    })
                    .catch((err) => console.log(err));
            } catch (error) {
                console.log(error);
            }
        }
    };

    const handleAddTag = () => {
        console.log("Adding tag");
    };

    useEffect(() => {
        AxiosClient.get("tags")
            .then((response: I_Tag[]) => setTags(response))
            .catch((error) => console.error(error));
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
                <div className="note-view-tag-list">
                    <Dropdown items={formattedTags} label={"+ Tags"} />
                    {note && note.tags?.map((tag: I_Tag) => <Tag tag={tag} />)}
                </div>
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
    noteSetId?: number;
}
