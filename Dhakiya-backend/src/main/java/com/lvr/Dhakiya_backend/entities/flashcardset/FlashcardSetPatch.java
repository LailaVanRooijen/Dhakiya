package com.lvr.Dhakiya_backend.entities.flashcardset;

import java.util.List;

public record FlashcardSetPatch(
    String name, List<Long> addFlashcards, List<Long> deleteFlashcards) {}
