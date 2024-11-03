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
  @GeneratedValue @Id private Long id;
  @Setter private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  List<Flashcard> flashcards = new ArrayList<>();

  public FlashcardSet(String name) {
    this.name = name;
  }

  public void addFlashcards(List<Flashcard> flashcards) {
    this.flashcards.addAll(flashcards);
  }

  // overloaded
  public void addFlashcards(Flashcard flashcard) {
    this.flashcards.add(flashcard);
  }

  public void removeFlashcards(List<Flashcard> flashcards) {
    this.flashcards.removeAll(flashcards);
  }

  // overloaded
  public void removeFlashcards(Flashcard flashcard) {
    this.flashcards.remove(flashcard);
  }
}
