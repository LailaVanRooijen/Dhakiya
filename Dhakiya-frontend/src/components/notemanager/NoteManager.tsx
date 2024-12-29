import { useEffect, useState } from "react";
import { GetNoteResponse } from "types/api";
import { AxiosClient } from "../../services/AxiosClient";
import "./NoteManager.css";

export const NoteManager: React.FC<NoteEditorProps> = ({ noteId }) => {
  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");

  useEffect(() => {
    if (noteId) {
      AxiosClient.get(`notes/${noteId}`)
        .then((response: GetNoteResponse) => {
          console.log(response);
          setTitle(response.title);
          setContent(response.content);
        })
        .catch((error) => console.error(error));
    }
  }, []);
  //TODO get tags
  // patch or post

  return (
    <div className="note-manager-wrapper">
      <input
        type="text"
        value={title}
        placeholder="Title"
        onChange={(e) => {
          setTitle(e.target.value);
        }}
      />
      <textarea
        value={content}
        placeholder="Write your note here..."
        onChange={(e) => setContent(e.target.value)}
      />
      <select name="tags" id="tags">
        <option value="tag">TAGS GO HERE</option>
      </select>
    </div>
  );
};

interface NoteEditorProps {
  noteId?: string;
}
