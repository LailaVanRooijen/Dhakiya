package com.lvr.Dhakiya_backend.flashcardcollection;

public record FlashcardCollectionDto(String title) {
  public static FlashcardCollectionDto from(FlashcardCollection flashcardCollection) {
    return new FlashcardCollectionDto(flashcardCollection.getTitle());
  }

  public static FlashcardCollection to(FlashcardCollectionDto flashcardCollectionDto) {
    return new FlashcardCollection(flashcardCollectionDto.title());
  }
}
