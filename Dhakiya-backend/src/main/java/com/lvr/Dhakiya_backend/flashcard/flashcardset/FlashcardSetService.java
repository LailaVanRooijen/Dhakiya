package com.lvr.Dhakiya_backend.flashcard.flashcardset;

import com.lvr.Dhakiya_backend.environment.Environment;
import com.lvr.Dhakiya_backend.environment.EnvironmentRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardSetService {
  private final FlashcardSetRepository flashcardSetRepository;
  private final EnvironmentRepository environmentRepository;

  public FlashcardSet create(FlashcardSetDto dto) {
    FlashcardSet flashcardSet = FlashcardSetDto.to(dto);
    if (dto.environmentId() == null) {
      throw new BadRequestException("environment id required");
    }
    Environment environment =
        environmentRepository
            .findById(dto.environmentId())
            .orElseThrow(() -> new BadRequestException("Environment does not exist"));
    environment.addFlashcardSet(flashcardSet);
    return flashcardSetRepository.save(flashcardSet);
  }

  public List<FlashcardSet> getAll() {
    return flashcardSetRepository.findAll();
  }

  public FlashcardSet getById(Long id) {
    return flashcardSetRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public FlashcardSet update(Long id, FlashcardSetDto patch) {
    FlashcardSet flashcardSet =
        flashcardSetRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.name() != null) {
      flashcardSet.setName(patch.name());
    }
    return flashcardSetRepository.save(flashcardSet);
  }

  public void delete(Long id) {
    flashcardSetRepository.findById(id).orElseThrow(NotFoundException::new);
    flashcardSetRepository.deleteById(id);
  }
}
