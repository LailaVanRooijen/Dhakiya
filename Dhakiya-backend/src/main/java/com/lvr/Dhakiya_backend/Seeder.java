package com.lvr.Dhakiya_backend;

import com.lvr.Dhakiya_backend.entities.answer.dto.PostAnswer;
import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentService;
import com.lvr.Dhakiya_backend.entities.environment.dto.CreateEnvironment;
import com.lvr.Dhakiya_backend.entities.environment.flashcard.FlashcardService;
import com.lvr.Dhakiya_backend.entities.environment.flashcard.dto.PostFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeckService;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.PostFlashCardDeck;
import com.lvr.Dhakiya_backend.entities.note.NoteService;
import com.lvr.Dhakiya_backend.entities.note.dto.PostNote;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollectionService;
import com.lvr.Dhakiya_backend.entities.progressreport.ProgressReportService;
import com.lvr.Dhakiya_backend.entities.questions.QuestionService;
import com.lvr.Dhakiya_backend.entities.questions.dto.PostQuestion;
import com.lvr.Dhakiya_backend.entities.quiz.QuizService;
import com.lvr.Dhakiya_backend.entities.quiz.dto.GetQuiz;
import com.lvr.Dhakiya_backend.entities.quiz.dto.PostQuiz;
import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollectionService;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.GetQuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.PostQuizCollection;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagService;
import com.lvr.Dhakiya_backend.entities.tag.dto.CreateTag;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
  private final EnvironmentService environmentService;
  private final TagService tagService;
  private final ProgressReportService progressReportService;
  private final NoteCollectionService noteCollectionService;
  private final NoteService noteService;
  private final FlashcardDeckService flashcardDeckService;
  private final FlashcardService flashcardService;
  private final QuizCollectionService quizCollectionService;
  private final QuizService quizService;
  private final QuestionService questionService;

  @Override
  public void run(String... args) throws Exception {
    seedEnvironments();
    seedTags();
    seedNotes();
    seedFlashcardDecks();
    seedFlashcards();
    seedQuizCollections();
    seedQuizzes();
    seedQuestions();
  }

  public void seedEnvironments() {
    environmentService.create(new CreateEnvironment("OCA 21"));
    environmentService.create(new CreateEnvironment("History"));
    environmentService.create(new CreateEnvironment("Lyricology"));
  }

  private void seedTags() {
    List<Environment> environments = environmentService.getAll();
    if (environments.size() < 3) return;

    tagService.createTag(new CreateTag(environments.get(0).getId(), "Primitive Datatypes"));
    tagService.createTag(new CreateTag(environments.get(1).getId(), "Ancient Egypt"));
    tagService.createTag(new CreateTag(environments.get(2).getId(), "Punchlines"));
  }

  private void seedNotes() {
    List<NoteCollection> noteCollections = noteCollectionService.getAll();
    if (noteCollections.size() < 3) return;

    List<Tag> tags = tagService.getAllTags();
    if (tags.size() < 3) return;

    noteService.create(
        new PostNote(
            noteCollections.get(0).getId(),
            "A NoteWorthy Note!",
            "a b c is bigger then 1 2 3",
            tags.get(0).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(1).getId(),
            "Information",
            "On a yellow sticky note",
            tags.get(1).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(2).getId(),
            "A sequence of characters",
            "Not in alphabetical order",
            tags.get(2).getId()));
  }

  private void seedFlashcardDecks() {
    List<Environment> environments = environmentService.getAll();
    if (environments.size() < 3) return;

    flashcardDeckService.create(
        new PostFlashCardDeck(environments.get(0).getId(), "Access Modifiers"));
    flashcardDeckService.create(new PostFlashCardDeck(environments.get(1).getId(), "World war 1"));
    flashcardDeckService.create(
        new PostFlashCardDeck(environments.get(2).getId(), "Rhyme schemes"));
  }

  private void seedFlashcards() {
    List<FlashcardDeck> decks = flashcardDeckService.getAll();
    if (decks.size() < 3) return;

    List<Tag> tags = tagService.getAllTags();
    if (tags.size() < 3) return;

    flashcardService.create(new PostFlashcard(decks.get(0).getId(), null, "ABC", "DEF", null));
    flashcardService.create(
        new PostFlashcard(decks.get(1).getId(), tags.get(0).getId(), "ABC", "DEF", 10));
    flashcardService.create(
        new PostFlashcard(decks.get(2).getId(), tags.get(1).getId(), "ABC", "DEF", 8));
  }

  private void seedQuizCollections() {
    List<Environment> environments = environmentService.getAll();
    if (environments.size() < 3) return;

    quizCollectionService.create(
        new PostQuizCollection(environments.get(0).getId(), "My First Quiz Collection"));
    quizCollectionService.create(
        new PostQuizCollection(environments.get(1).getId(), "My Second Quiz Collection"));
    quizCollectionService.create(
        new PostQuizCollection(environments.get(2).getId(), "My Third Quiz Collection"));
  }

  private void seedQuizzes() {
    List<GetQuizCollection> quizCollections = quizCollectionService.getAll();
    if (quizCollections.size() < 3) return;

    quizService.create(new PostQuiz(quizCollections.get(0).id(), "My First Quiz", null));
    quizService.create(new PostQuiz(quizCollections.get(1).id(), "My Second Quiz", 21));
    quizService.create(new PostQuiz(quizCollections.get(2).id(), "My Third Quiz", 30));
  }

  private void seedQuestions() {
    List<GetQuiz> quizzes = quizService.getAll();
    if (quizzes.size() < 3) return;
    List<PostAnswer> answers =
        new ArrayList<>(
            List.of(
                new PostAnswer("Answer a", true),
                new PostAnswer("Answer b", true),
                new PostAnswer("Answer c", true),
                new PostAnswer("Answer d", false)));

    questionService.create(new PostQuestion(quizzes.get(0).id(), "Is there a?", 4, answers, 1L));
    questionService.create(
        new PostQuestion(quizzes.get(1).id(), "Will there be a?", 4, answers, 1L));
    questionService.create(
        new PostQuestion(quizzes.get(2).id(), "Could it be so?", 4, answers, 1L));
  }
}
