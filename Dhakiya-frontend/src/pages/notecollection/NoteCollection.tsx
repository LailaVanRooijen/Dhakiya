import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getNoteCollectionResponse } from "types/api";
import { Button, BUTTON_STYLE } from "../../components/button/Button";
import { HeaderBar } from "../../components/headerbar/HeaderBar";
import { AxiosClient } from "../../services/AxiosClient";
import { createNotePath } from "../../utils/Routes";
import { Note } from "./components/note/Note";
import "./NoteCollection.css";

export const NoteCollection = () => {
  const navigate = useNavigate();
  const { environmentId, noteCollectionId } = useParams<{
    noteCollectionId: string;
    environmentId: string;
  }>();
  const [noteCollection, setNoteCollection] =
    useState<getNoteCollectionResponse>(null);

  useEffect(() => {
    AxiosClient.get(`note-collections/${noteCollectionId}`)
      .then((response: getNoteCollectionResponse) =>
        setNoteCollection(response)
      )
      .catch((error) => console.error(error));
  }, [noteCollectionId]);

  const handleNoteChange = (deletedNoteId: number) => {
    if (noteCollection) {
      const updatedNotes = noteCollection.notes.filter(
        (note) => note.id !== deletedNoteId
      );
      setNoteCollection({
        ...noteCollection,
        notes: updatedNotes,
      });
    }
  };

  if (!noteCollection) return <div>Loading</div>;

  if (noteCollection)
    return (
      <div className="note-collection-wrapper">
        <HeaderBar
          label={"Note Collection"}
          option1={"TODO Filter"}
          option2={"TODO Sort"}
          option3={
            <Button
              content={"+ note"}
              btnStyle={BUTTON_STYLE.ENCOURAGE}
              handleClick={() => {
                navigate(
                  createNotePath({
                    environmentId: environmentId,
                    noteCollectionId: noteCollectionId,
                  })
                );
              }}
            />
          }
        />

        <ul>
          {noteCollection.notes?.map((note) => (
            <li key={note.id}>
              <Note note={note} onNoteChange={(id) => handleNoteChange(id)} />
            </li>
          ))}
        </ul>
      </div>
    );
};
