package com.lvr.Dhakiya_backend.entities.flashcarddeck;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.PostFlashCardDeck;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Routes.FLASHCARD_DECKS)
@RequiredArgsConstructor
public class FlashcardDeckController {
  private final FlashcardDeckService flashcardDeckService;

  @GetMapping
  public ResponseEntity<List<FlashcardDeck>> getAll() {
    List<FlashcardDeck> flashcardDecks = flashcardDeckService.getAll();
    if (flashcardDecks.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(flashcardDecks);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<FlashcardDeck> getById(@PathVariable Long id) {
    return ResponseEntity.ok(flashcardDeckService.getById(id));
  }

  @PostMapping
  public ResponseEntity<FlashcardDeck> create(@RequestBody PostFlashCardDeck flashcardDeck) {
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand().toUri();
    FlashcardDeck createdFlashcardDeck = flashcardDeckService.create(flashcardDeck);
    return ResponseEntity.created(location).body(createdFlashcardDeck);
  }
}