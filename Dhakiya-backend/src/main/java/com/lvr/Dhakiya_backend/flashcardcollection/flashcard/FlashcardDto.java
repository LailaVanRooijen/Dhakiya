package com.lvr.Dhakiya_backend.flashcardcollection.flashcard;

import java.util.List;

public record FlashcardDto(String frontContent, String backContent, List<String> tags) {
  public static FlashcardDto from(Flashcard flashcard) {
    return new FlashcardDto(
        flashcard.getFrontContent(), flashcard.getBackContent(), flashcard.getTags());
  }

  public static Flashcard to(FlashcardDto flashcardDto) {
    Flashcard createdFlashcard = new Flashcard(flashcardDto.frontContent, flashcardDto.backContent);
    createdFlashcard.getTags().addAll(flashcardDto.tags);
    return createdFlashcard;
  }
}
