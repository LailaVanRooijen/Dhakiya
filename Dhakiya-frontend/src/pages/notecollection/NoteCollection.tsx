import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getNoteCollectionResponse } from "types/api";
import { Note } from "../../components/note/Note";
import { AxiosClient } from "../../services/AxiosClient";
import "./NoteCollection.css";

export const NoteCollection = () => {
  const { id } = useParams<{ id: string }>();
  const [noteCollection, setNoteCollection] =
    useState<getNoteCollectionResponse>(null);

  useEffect(() => {
    AxiosClient.get(`note-collections/${id}`)
      .then((response: getNoteCollectionResponse) =>
        setNoteCollection(response)
      )
      .catch((error) => console.error(error));
  }, []);

  if (noteCollection)
    return (
      <div className="note-collection-wrapper">
        <div>HEADER collection {id}</div>

        <ul>
          {noteCollection.notes.map((note) => (
            <li key={note.id}>
              <Note note={note} />
            </li>
          ))}
        </ul>
      </div>
    );
};
