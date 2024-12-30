import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { GetNoteResponse } from "types/api";
import { Button, BUTTON_STYLE } from "../../../../components/button/Button";
import { Modal } from "../../../../components/modal/Modal";
import { AxiosClient } from "../../../../services/AxiosClient";
import { createNotePath } from "../../../../utils/Routes";
import "./Note.css";
export const Note: React.FC<NoteProps> = ({ note, onNoteChange }) => {
  const { environmentId, noteCollectionId, noteId } = useParams<{
    environmentId: string;
    noteCollectionId: string;
    noteId: string;
  }>();
  const [showModal, setShowModal] = useState<boolean>(false);
  const navigate = useNavigate();

  useEffect(() => {
    console.log(note);
  }, []);

  const deleteNote = () => {
    AxiosClient.delete("notes", note.id)
      .then(() => {
        onNoteChange(note.id);
      })
      .catch((error) => console.error(error));
  };

  const comfirmDelete = (answer: boolean) => {
    setShowModal(false);
    if (answer) deleteNote();
  };

  const handleClick = (e) => {
    navigate(
      createNotePath({
        environmentId: environmentId,
        noteCollectionId: noteCollectionId,
        noteId: note.id,
      })
    );
  };
  return (
    <div
      className="note-wrapper"
      onClick={(e) => {
        handleClick(e);
      }}
    >
      {showModal && (
        <Modal
          question={"Are you sure you want to delete?"}
          getAnswer={(answer: boolean) => comfirmDelete(answer)}
        />
      )}
      <Button
        content={"X"}
        btnStyle={BUTTON_STYLE.ALERT}
        handleClick={(e: React.MouseEvent<HTMLButtonElement>) => {
          setShowModal(true);
          e.stopPropagation();
        }}
      />
      <div className="note-title">{note.title}</div>
      <div className="note-content">{note.content}</div>
      <div className="note-footer">{note.tag && note.tag.title}</div>
    </div>
  );
};

interface NoteProps {
  note: GetNoteResponse;
  onNoteChange: (id: number) => void;
}
