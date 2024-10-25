package com.lvr.Dhakiya_backend.flashcardcollection;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardCollectionService {
  private final FlashcardCollectionRepository flashcardCollectionRepository;

  public FlashcardCollection create(FlashcardCollectionDto flashcardCollectionDto) {
    return flashcardCollectionRepository.save(FlashcardCollectionDto.to(flashcardCollectionDto));
  }

  public List<FlashcardCollection> getAll() {
    return flashcardCollectionRepository.findAll();
  }

  public FlashcardCollection getById(Long id) {
    return flashcardCollectionRepository.findById(id).orElseThrow();
  }

  public void delete(Long id) {
    FlashcardCollection flashcardCollection =
        flashcardCollectionRepository.findById(id).orElseThrow();
    flashcardCollectionRepository.delete(flashcardCollection);
  }
}
