import { useParams } from "react-router-dom";
import { NoteManager } from "../../components/notemanager/NoteManager";
import "./NotePage.css";

export const NotePage = () => {
  const { noteId } = useParams<{ noteId?: string }>();

  return (
    <div className="note-page-wrapper">
      {noteId ? <NoteManager noteId={noteId} /> : <NoteManager />}
    </div>
  );
};
