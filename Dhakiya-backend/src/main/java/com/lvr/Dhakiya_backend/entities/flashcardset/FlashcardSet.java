package com.lvr.Dhakiya_backend.entities.flashcardset;

import com.lvr.Dhakiya_backend.entities.flashcard.Flashcard;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class FlashcardSet {
  @ManyToMany(fetch = FetchType.EAGER)
  List<Flashcard> flashcards = new ArrayList<>();

  @GeneratedValue @Id private Long id;
  @Setter private String name;

  public FlashcardSet(String name) {
    this.name = name;
  }

  public void addFlashcard(Flashcard flashcard) {
    this.flashcards.add(flashcard);
  }

  public void addAllFlashcards(List<Flashcard> flashcards) {
    this.flashcards.addAll(flashcards);
  }

  public void removeFlashcard(Flashcard flashcard) {
    this.flashcards.remove(flashcard);
  }

  public void removeAllFlashcards(List<Flashcard> flashcards) {
    this.flashcards.removeAll(flashcards);
  }
}
