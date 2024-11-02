package com.lvr.Dhakiya_backend.entities.flashcardset;

public record FlashcardSetDto(Long environmentId, String name) {
  public static FlashcardSetDto from(Long environmentId, FlashcardSet flashcardSet) {
    return new FlashcardSetDto(environmentId, flashcardSet.getName());
  }

  public static FlashcardSet to(FlashcardSetDto dto) {
    return new FlashcardSet(dto.name);
  }
}
