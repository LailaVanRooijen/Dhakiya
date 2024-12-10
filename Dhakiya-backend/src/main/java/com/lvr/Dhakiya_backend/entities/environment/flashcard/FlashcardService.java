package com.lvr.Dhakiya_backend.entities.environment.flashcard;

import com.lvr.Dhakiya_backend.entities.environment.flashcard.dto.PatchFlashcard;
import com.lvr.Dhakiya_backend.entities.environment.flashcard.dto.PostFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeckRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardService {
  private final FlashcardDeckRepository flashcardDeckRepository;
  private final FlashcardRepository flashcardRepository;
  private final TagRepository tagRepository;

  public Flashcard create(PostFlashcard dto) {
    Flashcard createdFlashcard = PostFlashcard.to(dto);

    if (dto.tagId() != null) {
      Tag tag = tagRepository.findById(dto.tagId()).orElseThrow(NotFoundException::new);
      createdFlashcard.setTag(tag);
    }

    FlashcardDeck deck =
        flashcardDeckRepository.findById(dto.flashcardDeckId()).orElseThrow(NotFoundException::new);
    createdFlashcard.setFlashcardDeck(deck);

    return flashcardRepository.save(createdFlashcard);
  }

  public List<Flashcard> getAll() {
    return flashcardRepository.findAll();
  }

  public Flashcard getById(Long id) {
    Flashcard flashcard = flashcardRepository.findById(id).orElseThrow(NotFoundException::new);
    return flashcard;
  }

  public void delete(Long id) {
    flashcardRepository.findById(id).orElseThrow(NotFoundException::new);
    flashcardRepository.deleteById(id);
  }

  public Flashcard patch(Long id, PatchFlashcard patch) {
    Flashcard flashcard = flashcardRepository.findById(id).orElseThrow(NotFoundException::new);

    if (patch.title() != null) {
      flashcard.setTitle(patch.title());
    }
    if (patch.content() != null) {
      flashcard.setContent(patch.content());
    }

    if (patch.flag() != null) {
      FlashcardFlags flag = FlashcardFlags.valueOf(patch.flag().toUpperCase());
      flashcard.setSeenCount(flashcard.getSeenCount() + 1);
      flashcard.setLastSeen(LocalDate.now());

      if (flag == FlashcardFlags.CORRECT) {
        flashcard.setCorrectCount(flashcard.getCorrectCount() + 1);
      }
      if (flag == FlashcardFlags.INCORRECT) {
        flashcard.setIncorrectCount(flashcard.getIncorrectCount() + 1);
      }
      if (flag == FlashcardFlags.FLAGGED_EASY) {
        flashcard.setFlaggedEasyCount(flashcard.getFlaggedEasyCount() + 1);
      }
      if (flag == FlashcardFlags.FLAGGED_DIFFICULT) {
        flashcard.setFlaggedDifficultCount(flashcard.getFlaggedDifficultCount() + 1);
      }
    }

    if (patch.tagId() != null) {
      Tag tag = tagRepository.findById(patch.tagId()).orElseThrow(NotFoundException::new);
      flashcard.setTag(tag);
    }

    flashcardRepository.save(flashcard);

    return flashcard;
  }
}
