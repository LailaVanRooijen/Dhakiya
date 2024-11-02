package com.lvr.Dhakiya_backend.entities.flashcard;


import java.util.List;

public record FlashcardPatchDto(
        Long flashcardSetId, String frontContent, String backContent, List<Long> addTags, List<Long>deleteTags) {
    public static Flashcard to(FlashcardPatchDto dto) {
        return new Flashcard(dto.frontContent, dto.backContent);
    }
}