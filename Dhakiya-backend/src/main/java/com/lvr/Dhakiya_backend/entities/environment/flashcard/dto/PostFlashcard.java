package com.lvr.Dhakiya_backend.entities.environment.flashcard.dto;

import com.lvr.Dhakiya_backend.entities.environment.flashcard.Flashcard;

public record PostFlashcard(Long flashcardDeckId, Long tagId, String title, String content) {
  public static Flashcard to(PostFlashcard dto) {
    return new Flashcard(dto.title, dto.content);
  }
}
