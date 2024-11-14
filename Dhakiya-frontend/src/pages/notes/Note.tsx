import "./note.css";
import { useEffect, useState } from "react";
import { I_Note, I_Tag } from "types/api";
import { ColorOption } from "../../types/enums";
import { Tag } from "../../components/tag/Tag";
import { Button } from "../../components/button/Button";
import { Dropdown } from "../../components/dropdown/Dropdown";
import {
    useDropdownReformatter,
    useIdExtractor,
} from "../../hooks/useListReformatter";
import { AxiosClient } from "../../services/AxiosClient";
import { useNavigate } from "react-router-dom";
import { usePostNoteValidator } from "../../hooks/useValidators";
import { AddTags } from "../../components/addTags/addTags";

export const Note: React.FC<NoteProps> = ({ note, noteSetId }) => {
    const navigate = useNavigate();
    const [tags, setTags] = useState<I_Tag[]>([]);
    const formattedTags = useDropdownReformatter(tags, "name");
    const [noteTitle, setNoteTitle] = useState<string>("");
    const [noteContent, setNoteContent] = useState<string>("");
    const [noteTags, setNoteTags] = useState<I_Tag[]>([]);
    const [noteId, setNoteId] = useState<number | null>(null);

    const handleSave = () => {
        if (note) {
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
                    tagIds: useIdExtractor(noteTags), // TODO get tagIds
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

    const handleAddTag = (tag: I_Tag) => {
        console.log("tag: ", tag);
        setNoteTags((prev) => [...prev, tag]);
    };

    useEffect(() => {
        AxiosClient.get("tags")
            .then((response: I_Tag[]) => setTags(response))
            .catch((error) => console.error(error));
        console.log(note);
        if (note) {
            setNoteTitle(note.title);
            setNoteContent(note.content);
            setNoteTags(note.tags);
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
                    <AddTags
                        tagList={tags}
                        onSelect={handleAddTag}
                        selectedTags={note ? note.tags : []}
                    />
                </div>
            </div>
            <div className={`note-view-btn-box`}>
                <Button content="save" handleClick={handleSave} />
            </div>
        </>
    );
};

interface NoteProps {
    note?: I_Note;
    noteSetId?: number;
}
