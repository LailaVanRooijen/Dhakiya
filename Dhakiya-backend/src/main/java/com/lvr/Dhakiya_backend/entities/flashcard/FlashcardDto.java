package com.lvr.Dhakiya_backend.entities.flashcard;

import com.lvr.Dhakiya_backend.entities.tag.Tag;
import java.util.List;

public record FlashcardDto(
    Long flashcardSetId, String frontContent, String backContent, List<Long> tagIds) {
  public static FlashcardDto from(Long flashcardSetId, Flashcard flashcard) {
    List<Long> flashcardTagIds = flashcard.getFlashcardTags().stream().map(Tag::getId).toList();
    return new FlashcardDto(
        flashcardSetId, flashcard.getFrontContent(), flashcard.getBackContent(), flashcardTagIds);
  }

  public static Flashcard to(FlashcardDto dto) {
    return new Flashcard(dto.frontContent, dto.backContent);
  }
}
