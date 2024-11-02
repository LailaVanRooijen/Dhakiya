package com.lvr.Dhakiya_backend.entities.flashcard;

import com.lvr.Dhakiya_backend.entities.flashcardset.FlashcardSet;
import com.lvr.Dhakiya_backend.entities.flashcardset.FlashcardSetRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagHelperMethods;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardService {
  private final FlashcardRepository flashcardRepository;
  private final FlashcardSetRepository flashcardSetRepository;
  private final TagHelperMethods tagHelper;

  public Flashcard create(FlashcardDto dto) {
    Flashcard flashcard = FlashcardDto.to(dto);
    if (!dto.tagIds().isEmpty()) {
      Set<Tag> tagList = tagHelper.convertToTags(dto.tagIds());
      flashcard.addTags(tagList);
    }
    FlashcardSet flashcardSet =
        flashcardSetRepository
            .findById(dto.flashcardSetId())
            .orElseThrow(() -> new BadRequestException("flashcardSet set does not exist"));
    flashcardSet.addFlashcard(flashcard);
    return flashcardRepository.save(flashcard);
  }

  public List<Flashcard> getAll() {
    return flashcardRepository.findAll();
  }

  public Flashcard getById(Long id) {
    return flashcardRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public void delete(Long id) {
    Flashcard flashcard = flashcardRepository.findById(id).orElseThrow(NotFoundException::new);
    flashcardRepository.deleteById(id);
  }

  public Flashcard update(Long id, FlashcardPatchDto patch) {
    if (patch.flashcardSetId() != null) {
      throw new BadRequestException("Changing flashcard set id is not allowed");
    }
    Flashcard flashcard = flashcardRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.frontContent() != null) {
      flashcard.setFrontContent(patch.frontContent());
    }
    if (patch.backContent() != null) {
      flashcard.setBackContent(patch.backContent());
    }
    if (patch.addTags() != null) {
      Set<Tag> tags = tagHelper.convertToTags(patch.addTags());
      flashcard.addTags(tags);
    }
    if (patch.deleteTags() != null) {
      Set<Tag> tags = tagHelper.convertToTags(patch.deleteTags());
      flashcard.deleteTags(tags);
    }
    return flashcardRepository.save(flashcard);
  }
}
