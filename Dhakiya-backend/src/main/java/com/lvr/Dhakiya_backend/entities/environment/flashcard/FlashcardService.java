package com.lvr.Dhakiya_backend.entities.environment.flashcard;

import com.lvr.Dhakiya_backend.entities.environment.flashcard.dto.PostFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeckRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
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
}
