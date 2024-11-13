import { I_Note } from "types/api";

export const usePostNoteValidator = (note: I_Note) => {
    if (note.title == "" || note.content == "") {
        throw "Note contains empty fields";
    }
    return note;
};
