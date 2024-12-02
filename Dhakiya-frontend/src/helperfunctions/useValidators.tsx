import { I_Note } from "types/api";

export const usePostNoteValidator = (note: I_Note) => {
    if (note.title === "" || note.content === "" || note.noteSetId === null) {
        throw "Illegal argument";
    }
    return note;
};

export const usePatchNoteValidator = (note: I_Note) => {
    if (note.id === null || note.noteSetId === null) {
        throw "Illegal argument";
    }
    return note;
};
