package com.lvr.Dhakiya_backend.flashcard.flashcardset;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class FlashcardSet {
  @GeneratedValue @Id private Long id;

  @Setter private String name;

  public FlashcardSet(String name) {
    this.name = name;
  }
}
