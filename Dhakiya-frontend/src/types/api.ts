import { I_Card } from "./styleTypes";

export interface I_Environment {
    id: number;
    title: string;
    noteCollection?: I_NoteSet;
    noteSet?: I_NoteSet;
    flashCardSet?: I_Flashcard[];
    flashcardSets?: I_Flashcard[];
    quizSet?: I_QuizSet;
    quizSets?: I_QuizSet[];
}

export interface I_Note {
    id?: number;
    noteSetId?: number;
    title: string;
    content: string;
    tagIds?: number[];
    tags?: I_Tag[];
    addTags?: number;
    deleteTages?: number;
}

export interface I_NoteSet {
    id?: number;
    notes: I_Note[];
}

export interface I_Flashcard {
    id?: number;
    flashcardSetId?: number;
    frontContent?: string;
    backContent?: string;
    tagIds?: number[];
    addTags?: number[];
    deleteTags?: number[];
}

export interface I_FlashcardSet {
    id?: number;
    name?: string;
    flashcards?: I_Flashcard[];
}

export interface I_Quiz {
    id?: number;
    environmentId: number;
    name: string;
}

export interface I_QuizSet {
    id?: number;
    name?: string;
    questions: I_Question[];
}

export interface I_Question {
    id?: number;
    answerAmount?: number;
    answers: I_Answer[];
    question: string;
}

export interface I_Answer {
    id?: number;
    answer?: string;
    isValid?: boolean;
}

export interface I_Tag {
    id: number;
    name: string;
}
