package com.lvr.Dhakiya_backend.flashcardcollection;

import com.lvr.Dhakiya_backend.environment.Environment;

public record FlashcardCollectionDto(String title, Long environmentId) {
  public static FlashcardCollectionDto from(FlashcardCollection flashcardCollection) {
    return new FlashcardCollectionDto(
        flashcardCollection.getTitle(), flashcardCollection.getEnvironment().getId());
  }

  public static FlashcardCollection to(FlashcardCollectionDto dto, Environment environment) {
    return new FlashcardCollection(dto.title(), environment);
  }
}
