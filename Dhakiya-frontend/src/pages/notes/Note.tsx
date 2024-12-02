import "./note.css";
import { useEffect, useState } from "react";
import { I_Note, I_Tag } from "types/api";
import { Button } from "../../components/button/Button";
import { useTagIdExtractor } from "../../helperfunctions/useListReformatter";
import { AxiosClient } from "../../services/AxiosClient";
import { useNavigate } from "react-router-dom";
import {usePatchNoteValidator,usePostNoteValidator,} from "../../helperfunctions/useValidators";
import { AddTags } from "../../components/addTags/AddTags";
import { useEnvironmentCtx } from "../../context/EnvironmentContext";
import { ColorOption } from "../../types/enums";

export const Note: React.FC<NoteProps> = ({ note }) => {
    const navigate = useNavigate();
    const [noteTitle, setNoteTitle] = useState<string>("");
    const [noteContent, setNoteContent] = useState<string>("");
    const [noteTags, setNoteTags] = useState<I_Tag[]>([]);
    const { noteSet } = useEnvironmentCtx();

    const handleSave = () => {
        if (note) {
            console.log("the note: ", note);
            try {
                const body = usePatchNoteValidator({
                    title: noteTitle,
                    content: noteContent,
                    tagIds: useTagIdExtractor(noteTags), //TODO gaat niet goed!
                });
                AxiosClient.patch(`notes/${note.id}`, body).then(
                    (response: I_Note) => console.log(response)
                );
            } catch (error) {
                console.error(error);
            }
        }
        if (!note) {
            if (noteSet === null) {
                console.log("ns", noteSet);
                console.log("no notesetid do something else");
            }
            try {
                const body = usePostNoteValidator({
                    noteSetId: noteSet,
                    title: noteTitle,
                    content: noteContent,
                    tagIds: useTagIdExtractor(noteTags), //TODO gaat niet goed!
                });
                AxiosClient.post("notes", body)
                    .then((response: I_Note) => {
                        navigate(`/note/${response.id}`);
                    })
                    .catch((err) => console.log(err));
            } catch (error) {
                console.log(error);
            }
        }
    };

    const handleAddTag = (tags: I_Tag[]) => {
        const prevTags = tags.concat(noteTags);
        setNoteTags([...new Set(prevTags)]);
    };

    const handleDeleteNote = ()=>{
        console.log(note.id)
        AxiosClient.delete("notes",note.id)
        .then(response=> console.log(response))
        .catch(error=>console.error(error));
    }

    useEffect(() => {
        if (note) {
            console.log("note: ",note)
            setNoteTitle(note.title);
            setNoteContent(note.content);
            setNoteTags(note.tags);
        }
    }, [note]);

    return (
        <>
            <div className="note-view">
                <input
                    className="note-view-title"
                    value={noteTitle}
                    onChange={(e) => setNoteTitle(e.target.value)}
                />
                <textarea
                    className="note-view-content"
                    value={noteContent}
                    onChange={(e) => setNoteContent(e.target.value)}
                />
                <div className="note-view-tag-list">
                    {note && (
                        <AddTags
                            onSelect={handleAddTag}
                            selectedTagIds={note?.tagIds}
                        />
                    )}
                    {!note && (
                        <AddTags onSelect={handleAddTag} />
                    )}
                </div>
            </div>
            <div className={`note-view-btn-box`}>
                <Button content="save" color={ColorOption.ENCOURAGE_BG} handleClick={handleSave} />
                <Button content="delete" color={ColorOption.WARNING_BG} handleClick={handleDeleteNote} />
            </div>
        </>
    );
};

interface NoteProps {
    note?: I_Note;
}
