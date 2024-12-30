import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  GetNoteResponse,
  GetTagResponseBasic,
  PatchNoteRequest,
  PostNoteRequest,
} from "types/api";
import { Button, BUTTON_STYLE } from "../../../../components/button/Button";
import { Modal } from "../../../../components/modal/Modal";
import { AxiosClient } from "../../../../services/AxiosClient";
import { createNoteCollectionPath } from "../../../../utils/Routes";
import {
  getFormValues,
  validateNote,
} from "../../../../utils/useFormValidators";
import { TagSelect } from "../tagselect/TagSelect";
import "./NoteManager.css";

export const NoteManager: React.FC<NoteEditorProps> = ({ noteId }) => {
  const { environmentId, noteCollectionId } = useParams<{
    environmentId: string;
    noteCollectionId: string;
  }>();
  const navigate = useNavigate();
  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const [tag, setTag] = useState<GetTagResponseBasic | null>(null);
  const [showModal, setShowModal] = useState<boolean>(false);
  const [editMode, setEditMode] = useState<boolean>(true);

  useEffect(() => {
    fetchNote(noteId);
    if (noteId) setEditMode(false);
  }, [noteId]);

  const fetchNote = (noteId: string | number) => {
    if (!noteId) return;
    AxiosClient.get(`notes/${noteId}`)
      .then((response: GetNoteResponse) => {
        console.log(response);
        setTitle(response.title);
        setContent(response.content);
        setTag(response.tag);
      })
      .catch((error) => console.error(error));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const formValues = getFormValues(e) as unknown as
      | PostNoteRequest
      | PatchNoteRequest;
    submitNote({
      requestBody: formValues,
      isPatch: Boolean(noteId),
      isValid: validateNote(formValues),
    });
  };

  const submitNote = ({ requestBody, isPatch, isValid }: SubmitNoteProps) => {
    if (!isValid) return;
    const method = isPatch ? "patch" : "post";
    const url = isPatch ? `notes/${noteId}` : `notes`;
    AxiosClient[method](url, requestBody)
      .then((response) => console.log(response))
      .catch((error) => console.error(error))
      .finally(() => {
        setEditMode(false);
        //TODO als een note is gesaved dan werkt de delete niet meer omdat die afhankelijk is van die noteId
      });
  };

  const deleteNote = () => {
    if (noteId) {
      AxiosClient.delete("notes", noteId).catch((error) =>
        console.error(error)
      );
    }
    navigate(
      createNoteCollectionPath({
        environmentId: environmentId,
        noteCollectionId: noteCollectionId,
      })
    );
  };

  const comfirmDelete = (answer: boolean) => {
    setShowModal(false);
    if (answer) deleteNote();
  };

  return (
    <div className="note-manager-wrapper">
      {showModal && (
        <Modal
          question={"Are you sure you want to delete?"}
          getAnswer={(answer: boolean) => comfirmDelete(answer)}
        />
      )}
      <div className="note-manager-wrapper-btn-container">
        {!editMode && (
          <Button
            content={"✏️"}
            handleClick={() => {
              setEditMode(true);
            }}
            btnStyle={BUTTON_STYLE.ENCOURAGE}
          />
        )}

        <Button
          content={"X"}
          btnStyle={BUTTON_STYLE.ALERT}
          handleClick={() => {
            setShowModal(true);
          }}
        />
      </div>
      <form className="note-manager-form" onSubmit={handleSubmit}>
        <input
          name="title"
          type="text"
          value={title}
          placeholder="Title"
          onChange={(e) => {
            setTitle(e.target.value);
          }}
          disabled={!editMode}
        />
        <textarea
          name="content"
          value={content}
          placeholder="Write your note here..."
          onChange={(e) => setContent(e.target.value)}
          disabled={!editMode}
        />

        <TagSelect
          environmentId={environmentId}
          isEnabled={editMode}
          getTag={(tag) => {
            setTag(tag);
          }}
          initialValue={tag}
        />
        <input
          type="hidden"
          name={"noteCollectionId"}
          value={noteCollectionId}
        />
        {editMode && (
          <Button content={"save"} btnStyle={BUTTON_STYLE.ENCOURAGE} />
        )}
      </form>
    </div>
  );
};

interface NoteEditorProps {
  noteId?: string;
}

interface SubmitNoteProps {
  requestBody: PostNoteRequest | PatchNoteRequest;
  isPatch: boolean;
  isValid: boolean;
}
