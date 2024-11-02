package com.lvr.Dhakiya_backend.entities.flashcard;

import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardHelperService {
  private final FlashcardRepository flashcardRepository;

  public List<Flashcard> convertToFlashcardList(List<Long> flashcardsIds) {
    return flashcardsIds.stream()
        .map(id -> flashcardRepository.findById(id).orElseThrow(NotFoundException::new))
        .toList();
  }

  public Set<Flashcard> convertToFlashcardSet(List<Long> flashcardIds) {
    return flashcardIds.stream()
        .map(id -> flashcardRepository.findById(id).orElseThrow(NotFoundException::new))
        .collect(Collectors.toSet());
  }
}
