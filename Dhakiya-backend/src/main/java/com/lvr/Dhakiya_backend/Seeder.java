package com.lvr.Dhakiya_backend;

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
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagService;
import com.lvr.Dhakiya_backend.entities.tag.dto.CreateTag;
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

  @Override
  public void run(String... args) throws Exception {
    seedEnvironments();
    seedTags();
    seedNotes();
    seedFlashcardDecks();
    seedFlashcards();
  }

  public void seedEnvironments() {
    environmentService.create(new CreateEnvironment("OCA 21"));
    environmentService.create(new CreateEnvironment("History"));
    environmentService.create(new CreateEnvironment("Lyricology"));
  }

  private void seedTags() {
    List<Environment> environments = environmentService.getAll();
    if (environments.isEmpty()) return;

    tagService.createTag(new CreateTag(environments.get(0).getId(), "Primitive Datatypes"));
    tagService.createTag(new CreateTag(environments.get(1).getId(), "Ancient Egypt"));
    tagService.createTag(new CreateTag(environments.get(2).getId(), "Punchlines"));
  }

  private void seedNotes() {
    List<NoteCollection> noteCollections = noteCollectionService.getAll();
    if (noteCollections.isEmpty()) return;

    List<Tag> tags = tagService.getAllTags();
    if (tags.isEmpty()) return;

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
    if (environments.isEmpty()) return;

    flashcardDeckService.create(
        new PostFlashCardDeck(environments.get(0).getId(), "Access Modifiers"));
    flashcardDeckService.create(new PostFlashCardDeck(environments.get(1).getId(), "World war 1"));
    flashcardDeckService.create(
        new PostFlashCardDeck(environments.get(2).getId(), "Rhyme schemes"));
  }

  private void seedFlashcards() {
    List<FlashcardDeck> decks = flashcardDeckService.getAll();
    if (decks.isEmpty()) return;

    List<Tag> tags = tagService.getAllTags();
    if (tags.isEmpty()) return;

    flashcardService.create(new PostFlashcard(decks.get(0).getId(), null, "ABC", "DEF"));
    flashcardService.create(
        new PostFlashcard(decks.get(1).getId(), tags.get(0).getId(), "ABC", "DEF"));
    flashcardService.create(
        new PostFlashcard(decks.get(2).getId(), tags.get(1).getId(), "ABC", "DEF"));
  }
}
