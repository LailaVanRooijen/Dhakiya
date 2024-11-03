package com.lvr.Dhakiya_backend.entities.flashcard;

import java.util.List;

public record FlashcardPatch(
    Long flashcardSetId,
    String frontContent,
    String backContent,
    List<Long> addTags,
    List<Long> deleteTags) {
  public static Flashcard to(FlashcardPatch dto) {
    return new Flashcard(dto.frontContent, dto.backContent);
  }
}
