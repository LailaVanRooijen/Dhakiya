package com.lvr.Dhakiya_backend.entities.flashcardset;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentRepository;
import com.lvr.Dhakiya_backend.entities.flashcard.Flashcard;
import com.lvr.Dhakiya_backend.entities.flashcard.FlashcardHelperService;
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
  private final FlashcardHelperService flashcardHelper;

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
    flashcardSetRepository.save(flashcardSet);
    environmentRepository.save(environment);
    return flashcardSet;
  }

  public List<FlashcardSet> getAll() {
    return flashcardSetRepository.findAll();
  }

  public FlashcardSet getById(Long id) {
    return flashcardSetRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public FlashcardSet update(Long id, FlashcardSetPatch patch) {
    FlashcardSet flashcardSet =
        flashcardSetRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.name() != null) {
      flashcardSet.setName(patch.name());
    }
    if (patch.addFlashcards() != null) {
      List<Flashcard> flashcards = flashcardHelper.convertToFlashcardList(patch.addFlashcards());
      flashcardSet.addAllFlashcards(flashcards);
    }
    if (patch.deleteFlashcards() != null) {
      List<Flashcard> flashcards = flashcardHelper.convertToFlashcardList(patch.deleteFlashcards());
      flashcardSet.removeAllFlashcards(flashcards);
    }
    return flashcardSetRepository.save(flashcardSet);
  }

  public void delete(Long id) {
    flashcardSetRepository.findById(id).orElseThrow(NotFoundException::new);
    flashcardSetRepository.deleteById(id);
  }
}
