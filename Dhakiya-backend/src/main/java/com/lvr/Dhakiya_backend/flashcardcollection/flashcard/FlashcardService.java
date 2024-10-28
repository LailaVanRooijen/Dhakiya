package com.lvr.Dhakiya_backend.flashcardcollection.flashcard;

import com.lvr.Dhakiya_backend.flashcardcollection.FlashcardCollectionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardService {
  private final FlashcardRepository flashcardRepository;
  private final FlashcardCollectionRepository flashcardCollectionRepository;

  public Flashcard create(FlashcardDto flashcard) {
    return flashcardRepository.save(FlashcardDto.to(flashcard));
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
