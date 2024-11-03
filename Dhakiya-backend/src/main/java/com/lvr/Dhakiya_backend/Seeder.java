package com.lvr.Dhakiya_backend;

import com.lvr.Dhakiya_backend.entities.environment.EnvironmentDto;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentService;
import com.lvr.Dhakiya_backend.entities.flashcard.FlashcardDto;
import com.lvr.Dhakiya_backend.entities.flashcard.FlashcardService;
import com.lvr.Dhakiya_backend.entities.flashcardset.FlashcardSetDto;
import com.lvr.Dhakiya_backend.entities.flashcardset.FlashcardSetService;
import com.lvr.Dhakiya_backend.entities.notes.NoteDto;
import com.lvr.Dhakiya_backend.entities.notes.NoteService;
import com.lvr.Dhakiya_backend.entities.quiz.QuizDto;
import com.lvr.Dhakiya_backend.entities.quiz.QuizService;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
  private final EnvironmentService environmentService;
  private final TagRepository tagRepository;
  private final NoteService noteService;
  private final FlashcardSetService flashcardSetService;
  private final FlashcardService flashcardService;
  private final QuizService quizService;

  @Override
  public void run(String... args) throws Exception {
    seedEnvironments();
    seedTags();
    seedNotes();
    seedFlashcardSets();
    seedFlashcards();
    seedQuizSets();
  }

  private void seedQuizSets() {
    if (!quizService.getAll().isEmpty()) return;
    Long environmentId = environmentService.getAll().get(0).getId();
    quizService.create(new QuizDto(environmentId, "My first quiz"));
    quizService.create(new QuizDto(2L, "My second quiz"));
    quizService.create(new QuizDto(3L, "Actual Quiz master"));
  }

  private void seedFlashcards() {
    if (!flashcardService.getAll().isEmpty()) return;

    flashcardService.create(
        new FlashcardDto(1L, "Is there a?", "Yes", new ArrayList<>(List.of(1L, 2L))));
    flashcardService.create(
        new FlashcardDto(2L, "Is a?", "Maybe", new ArrayList<>(List.of(3L, 4L))));
  }

  private void seedFlashcardSets() {
    if (!flashcardSetService.getAll().isEmpty()) return;

    flashcardSetService.create(new FlashcardSetDto(1L, "My first set"));
    flashcardSetService.create(new FlashcardSetDto(2L, "My second set"));
    flashcardSetService.create(new FlashcardSetDto(3L, "Set creation Master!"));
  }

  private void seedEnvironments() {
    if (!environmentService.getAll().isEmpty()) return;

    List<String> subjects =
        new ArrayList<>(List.of("Math", "History", "Geography", "Computer Science", "OCA 21"));
    subjects.forEach(subject -> environmentService.create(new EnvironmentDto(subject)));
  }

  private void seedTags() {
    if (!tagRepository.findAll().isEmpty()) return;

    List<String> tagNames =
        new ArrayList<>(List.of("Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4", "Chapter 5"));
    tagNames.forEach(name -> tagRepository.save(new Tag(name)));
  }

  private void seedNotes() {
    if (!noteService.getAll().isEmpty()) return;

    List<String> noteContentList =
        new ArrayList<>(List.of("i am a note", "i am also a note", "i am a imposter note"));
    noteContentList.forEach(
        content -> {
          noteService.create(
              new NoteDto(
                  1L,
                  (content + " on the front"),
                  (content + " on the back"),
                  new ArrayList<>(List.of(1L))));
        });
  }
}
