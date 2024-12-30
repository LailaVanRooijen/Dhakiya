import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  GetFullEnvironmentResponse,
  GetNoteResponse,
  GetTagResponse,
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
  validateNoteRequestBody,
} from "../../../../utils/useFormValidators";
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
  const [environmentTags, setEnvironmentTags] = useState<GetTagResponse[] | []>(
    []
  );
  const [showModal, setShowModal] = useState<boolean>(false);

  useEffect(() => {
    fetchTags();
    fetchNote(noteId);
  }, [noteId]);

  const fetchTags = () => {
    AxiosClient.get(`environments/${environmentId}`)
      .then((response: GetFullEnvironmentResponse) =>
        setEnvironmentTags(response.tags)
      )
      .catch((error) => console.error(error));
  };

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
      isValid: validateNoteRequestBody(formValues),
    });
  };

  const submitNote = ({ requestBody, isPatch, isValid }: SubmitNoteProps) => {
    if (!isValid) return;
    const method = isPatch ? "patch" : "post";
    const url = isPatch ? `notes/${noteId}` : `notes`;
    AxiosClient[method](url, requestBody)
      .then((response) => console.log(response))
      .catch((error) => console.error(error));
  };

  const handleTagSelect = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedTag = e.target.value;
    if (selectedTag === "new-tag") {
      console.log("Make a new tag!");
    } else {
      const newTag = environmentTags.find(
        (tag: GetTagResponseBasic) => tag.id == Number(selectedTag)
      );
      setTag(newTag);
    }
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
      {noteId && (
        <Button
          content={"X"}
          btnStyle={BUTTON_STYLE.ALERT}
          handleClick={() => {
            setShowModal(true);
          }}
        />
      )}
      <form className="note-manager-form" onSubmit={handleSubmit}>
        <input
          name="title"
          type="text"
          value={title}
          placeholder="Title"
          onChange={(e) => {
            setTitle(e.target.value);
          }}
        />
        <textarea
          name="content"
          value={content}
          placeholder="Write your note here..."
          onChange={(e) => setContent(e.target.value)}
        />
        {environmentTags && (
          <select
            name="tagId"
            onChange={(e) => handleTagSelect(e)}
            value={tag ? tag.id : "default"}
          >
            <option value="default" disabled>
              {tag ? tag.title : "Select a tag"}
            </option>
            {environmentTags.length != 0 ? (
              environmentTags.map((environmentTag: GetTagResponse) => (
                <option key={environmentTag.id} value={environmentTag.id}>
                  {environmentTag.title}
                </option>
              ))
            ) : (
              <option value={"new-tag"}>Create new Tag...</option>
            )}
          </select>
        )}
        <input
          type="hidden"
          name={"noteCollectionId"}
          value={noteCollectionId}
        />
        <Button content={"save"} btnStyle={BUTTON_STYLE.ENCOURAGE} />
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
