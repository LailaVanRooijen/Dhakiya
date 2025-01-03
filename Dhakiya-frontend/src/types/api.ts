/* eslint-disable prettier/prettier */

/* This file holds all the interfaces related to the backends http requests responses and bodies */

/* Api Enums */
enum STATUS {
  WEAK,
  MEDIOCRE,
  GOOD,
  STRONG,
  VERY_STRONG,
  NO_DATA,
}

enum FLAG {
  CORRECT,
  INCORRECT,
  FLAGGED_EASY,
  FLAGGED_DIFFICULT,
}

/* Api Response and RequestBodies */

/* ~~~ Environment ~~~ */
interface EnvironmentBase {
  title: string;
}

export interface GetEnvironmentResponse extends EnvironmentBase {
  id: number;
}

export interface GetFullEnvironmentResponse extends GetEnvironmentResponse {
  noteCollectionId: number;
  flashcardDecks: GetFlashcardDeckResponse[];
  quizCollections: GetQuizCollectionResponse[];
  progressReport: GetProgressReportResponse;
  tags: GetTagResponse[];
}

export interface PostEnvironmentRequest {
  title: string;
}

export interface PatchEnvironmentRequest {
  title?: string;
}

/* ~~~ Note Collection ~~~ */
export interface getNoteCollectionResponse {
  id: number;
  environment: EnvironmentBase;
  notes: GetNoteResponse[];
}

/* ~~~ Note ~~~ */
interface NoteBase {
  title: string;
  content: string;
}
export interface GetNoteResponse extends NoteBase {
  id: number;
  noteCollectionId: number;
  environmentId: number;
  tag: GetTagResponseBasic;
}

export interface PostNoteRequest extends NoteBase {
  noteCollectionId: number;
  tagId: number;
}

export interface PatchNoteRequest extends NoteBase {
  tagId?: number;
}

/* ~~~ Flashcard Deck ~~~ */
interface FlashcardDeckBase {
  title: string;
}

export interface GetFlashcardDeckResponse extends FlashcardDeckBase {
  environment: GetEnvironmentResponse;
  id: number;
}

export interface PostFlashcardDeckRequest extends FlashcardBase {
  environmentId: number;
}

export interface PatchFlashcardDeckRequest {
  title?: string;
}

/* ~~~ Flashcard ~~~ */
interface FlashcardBase {
  title: string;
  content: string;
}

export interface GetFlashcardResponse extends FlashcardBase {
  id: number;
  tag?: TagBase;
  status: STATUS;
}

export interface PostFlashcardRequest extends FlashcardBase {
  flashcardDeckId: number;
  tagId: number;
  minimumDisplays: number;
}

export interface PatchFlashcardRequest extends FlashcardBase {
  flag?: FLAG;
  tagId?: number;
}

/* ~~~ Quiz Collection ~~~ */
export interface GetQuizCollectionResponse {
  id: number;
  environmentId: number;
  title: string;
}

/* ~~~ Quiz ~~~ */
interface QuizBase {
  id: number;
  title: string;
}

export interface GetQuizResponse extends QuizBase {
  quizCollectionId: number;
}

export interface PostQuizRequest {
  quizCollectionId: number;
  title: string;
  size: number;
}

export interface PatchQuizRequest {
  title?: string;
  isFinal?: boolean;
}

/* ~~~ Question ~~~ */
interface QuestionBase {
  id: number;
  environmentId?: number;
  questions: string;
  answers?: GetAnswerResponse[];
}

export interface GetQuestionResponse extends QuestionBase {
  quizId: number;
  tag: TagBase;
}

export interface PostQuestionRequest {
  environmentId: number;
  title: string;
}

export interface PatchQuestionRequest {
  question?: string;
  answerCount?: number;
  answers?: GetAnswerResponse[];
  tagId?: number;
}

/* ~~~ Answer ~~~ */
interface AnswerBase {
  answer: string;
  isCorrect: boolean;
}

export interface GetAnswerResponse extends AnswerBase {
  id: number;
}

export interface PatchAnswerRequest {
  answer?: string;
  isCorrect?: boolean;
}

/* ~~~ Quiz Result ~~~ */
export interface GetQuizResultResponse {
  id: number;
  answeredQuestions: QuestionBase[];
}

export interface PostQuizResultRequest {
  quizId: number;
}

export interface SubmitQuizResultResponse {
  id: number;
  score: number;
}

export interface SubmitQuizResultAnswer {
  questionId: number;
  answerIds: number[];
}

export interface PostQuizCollectionRequest {
  environmentId: number;
  title: string;
}

export interface PatchQuizCollectionRequest {
  title?: string;
}

/* ~~~ Progress Report ~~~ */
export interface GetProgressReportResponse {
  id: number;
  environmentId: number;
  tags: detailedTag[];
  strength: detailedTag;
  weakness: detailedTag;
}

/* ~~~ Tag ~~~ */
interface TagBase {
  title: string;
}

export interface GetTagResponseBasic extends TagBase {
  id: number;
}

export interface GetTagResponse extends TagBase {
  id: number;
  seenCount: number;
  flaggedPositiveCount: number;
  environment: GetEnvironmentResponse;
  status: STATUS;
  percentage: number;
}
interface detailedTag {
  id: number;
  percentage: number;
  status: STATUS;
  title: string;
}

export interface PostTagRequest extends TagBase {
  environmentId: number;
}

export interface PatchTagRequest {
  title?: string;
  isFlaggedPositive?: boolean;
  reset?: boolean;
}
