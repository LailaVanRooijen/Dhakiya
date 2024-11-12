import "./note.css";
import { useFetch } from "../../hooks/useApi";
import { useEffect } from "react";
import { useParams } from "react-router-dom";
import { Note } from "./Note";

export const NoteView = () => {
    const { id } = useParams<{ id: string }>();
    const { data: note, error: noteError } = useFetch(`notes/${id}`);

    useEffect(() => {
        console.log(note);
    }, [note]);

    if (noteError) return <div>noteError</div>;
    if (note == null) return <div>No content here</div>;

    return (
        <>
            <Note note={note} />
        </>
    );
};
