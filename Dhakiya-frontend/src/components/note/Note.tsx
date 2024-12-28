import { GetNoteResponse } from "types/api";
import "./Note.css";
export const Note: React.FC<NoteProps> = ({ note }) => {
  return <div className="note-wrapper">{note.title}</div>;
};

interface NoteProps {
  note: GetNoteResponse;
}
