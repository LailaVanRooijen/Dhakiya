import "./note.css";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Note } from "./Note";
import { AxiosClient } from "../../services/AxiosClient";
import { I_Note } from "types/api";

export const NoteView = () => {
    const { id } = useParams<{ id: string }>();
    const [note, setNote] = useState<I_Note | null>(null);

    useEffect(() => {
        AxiosClient.get(`notes/${id}`)
            .then((response: I_Note) => setNote(response))
            .catch((error) => console.error(error));
    }, []);

    if (note)
        return (
            <>
                <Note note={note} />
            </>
        );
};
