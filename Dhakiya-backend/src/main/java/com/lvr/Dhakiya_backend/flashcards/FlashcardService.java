package com.lvr.Dhakiya_backend.flashcards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;

    public Flashcard create(CreateFlashcardDto flashcard) {
        return flashcardRepository.save(CreateFlashcardDto.to(flashcard));
    }

    public List<Flashcard> getAll() {
        return flashcardRepository.findAll();
    }

    public Flashcard getById(Long id) {
        return flashcardRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        flashcardRepository.findById(id).orElseThrow();
        flashcardRepository.deleteById(id);
    }
}
