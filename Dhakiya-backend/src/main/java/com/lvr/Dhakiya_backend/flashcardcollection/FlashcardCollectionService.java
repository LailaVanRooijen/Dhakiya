package com.lvr.Dhakiya_backend.flashcardcollection;

import com.lvr.Dhakiya_backend.environment.Environment;
import com.lvr.Dhakiya_backend.environment.EnvironmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardCollectionService {
  private final FlashcardCollectionRepository flashcardCollectionRepository;
  private final EnvironmentService environmentService;

  public FlashcardCollection create(FlashcardCollectionDto dto) {
    Environment environment = environmentService.getById(dto.environmentId());
    return flashcardCollectionRepository.save(FlashcardCollectionDto.to(dto, environment));
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
