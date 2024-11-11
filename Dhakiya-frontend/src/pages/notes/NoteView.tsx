import "./note.css";
import { useFetch } from "../../hooks/useApi";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { I_Tag } from "types/api";
import { TiDelete } from "react-icons/ti";
import { Button } from "../../components/button/Button";

export const NoteView = () => {
    const { id } = useParams<{ id: string }>();
    const { data: note, error } = useFetch(`notes/${id}`);
    const [noteTitle, setNoteTitle] = useState<string>("");
    const [noteContent, setNoteContent] = useState<string>("");

    useEffect(() => {
        console.log(note);
        if (note) {
            setNoteTitle(note.title || "");
            setNoteContent(note.content || "");
        }
    }, [note]);

    const saveNote = () => {
        console.log("he");
    };

    if (note == null) return <div>No content here</div>;

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
                    {note.tags?.map((tag: I_Tag) => (
                        <div className={"note-view-tag-box"} key={tag.id}>
                            <div className={`note-view-tag`}>{tag.name}</div>
                            <TiDelete className={`tag-delete-btn`} />
                        </div>
                    ))}
                </div>
            </div>
            <div className={`note-view-btn-box`}>
                <Button content="save" handleClick={saveNote} />
            </div>
        </>
    );
};
