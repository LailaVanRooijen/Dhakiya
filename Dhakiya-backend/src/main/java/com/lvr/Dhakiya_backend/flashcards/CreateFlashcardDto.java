package com.lvr.Dhakiya_backend.flashcards;

import java.util.List;

public record CreateFlashcardDto(String frontContent, String backContent, List<String> tags) {
    public static CreateFlashcardDto from (Flashcard flashcard){
        return new CreateFlashcardDto(flashcard.getFrontContent(), flashcard.getBackContent(), flashcard.getTags());
    }

    public static Flashcard to (CreateFlashcardDto flashcardDto){
    Flashcard createdFlashcard =  new Flashcard(flashcardDto.frontContent, flashcardDto.backContent);
    createdFlashcard.getTags().addAll(flashcardDto.tags);
    return createdFlashcard;
    }
}
