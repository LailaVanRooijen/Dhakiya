export interface I_Environment {
    id: number;
    title: string;
    noteCollection?: I_Note[];
    flashCardSet?: I_Flashcard[];
    quizSet?: I_Quiz[];
}

export interface I_Note {
    id: number;
    title: String;
    content: String;
    tags: I_Tag[];
}

export interface I_Flashcard {
    id?: number;
    noteSet: number;
    title: string;
    content: string;
    tagIds?: number[];
    addTags?: number[];
    deleteTags?: number[];
}

export interface I_Quiz {
    id?: number;
    environmentId: number;
    name: string;
}

export interface I_Tag {
    id: number;
    name: string;
}
