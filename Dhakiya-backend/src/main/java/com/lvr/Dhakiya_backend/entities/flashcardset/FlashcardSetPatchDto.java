package com.lvr.Dhakiya_backend.entities.flashcardset;

import java.util.List;

public record FlashcardSetPatchDto(
    String name, List<Long> addFlashcards, List<Long> deleteFlashcards) {}
