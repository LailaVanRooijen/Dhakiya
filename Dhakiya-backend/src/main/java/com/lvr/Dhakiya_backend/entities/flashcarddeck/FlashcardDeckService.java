package com.lvr.Dhakiya_backend.entities.flashcarddeck;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentRepository;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.PostFlashCardDeck;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardDeckService {
  private final FlashcardDeckRepository flashcardDeckRepository;
  private final EnvironmentRepository environmentRepository;

  public List<FlashcardDeck> getAll() {
    return flashcardDeckRepository.findAll();
  }

  public FlashcardDeck getById(Long id) {
    FlashcardDeck flashcardDeck =
        flashcardDeckRepository.findById(id).orElseThrow(NotFoundException::new);
    return flashcardDeck;
  }

  public FlashcardDeck create(PostFlashCardDeck dto) {
    FlashcardDeck createdFlashcardDeck = PostFlashCardDeck.to(dto);
    Environment environment =
        environmentRepository.findById(dto.environmentId()).orElseThrow(NotFoundException::new);
    createdFlashcardDeck.setEnvironment(environment);
    flashcardDeckRepository.save(createdFlashcardDeck);
    return createdFlashcardDeck;
  }
}
