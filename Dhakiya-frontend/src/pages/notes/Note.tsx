import "./note.css";
import { useEffect, useState } from "react";
import { I_Note, I_Tag } from "types/api";
import { Button } from "../../components/button/Button";
import { useTagIdExtractor } from "../../hooks/useListReformatter";
import { AxiosClient } from "../../services/AxiosClient";
import { useNavigate } from "react-router-dom";
import {
    usePatchNoteValidator,
    usePostNoteValidator,
} from "../../hooks/useValidators";
import { AddTags } from "../../components/addTags/AddTags";

export const Note: React.FC<NoteProps> = ({ note, noteSetId }) => {
    const navigate = useNavigate();
    const [tags, setTags] = useState<I_Tag[]>([]);
    const [noteTitle, setNoteTitle] = useState<string>("");
    const [noteContent, setNoteContent] = useState<string>("");
    const [noteTags, setNoteTags] = useState<I_Tag[]>([]);

    const handleSave = () => {
        console.log("getting ready to save!");

        if (note) {
            console.log("the note: ", note);
            try {
                const body = usePatchNoteValidator({
                    title: noteTitle,
                    content: noteContent,
                    tagIds: useTagIdExtractor(noteTags), // TODO fix me i bug
                });
                AxiosClient.patch(`notes/${note.id}`, body).then(
                    (response: I_Note) => console.log(response)
                );
            } catch (error) {
                console.error(error);
            }
        }
        if (!noteSetId) {
            console.log("No noteSetId found");
            return;
        }
        if (!note) {
            try {
                const body = usePostNoteValidator({
                    noteSetId: noteSetId,
                    title: noteTitle,
                    content: noteContent,
                    tagIds: useTagIdExtractor(noteTags),
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

    const handleAddTag = (tags: I_Tag[]) => {
        const prevTags = tags.concat(noteTags);
        setNoteTags([...new Set(prevTags)]);
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
                    {note && (
                        <AddTags
                            tagList={tags}
                            onSelect={handleAddTag}
                            selectedTags={note.tags}
                        />
                    )}
                    {!note && (
                        <AddTags tagList={tags} onSelect={handleAddTag} />
                    )}
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