package com.lvr.Dhakiya_backend.entities.flashcard;

import static com.lvr.Dhakiya_backend.appConfig.Routes.FLASHCARDS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(FLASHCARDS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class FlashcardController {
  private final FlashcardService flashcardService;

  @PostMapping
  public ResponseEntity<Flashcard> create(@RequestBody FlashcardDto dto) {
    Flashcard savedFlashcard = flashcardService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedFlashcard)
            .toUri();
    return ResponseEntity.created(location).body(savedFlashcard);
  }

  @GetMapping()
  public ResponseEntity<List<Flashcard>> getAll() {
    List<Flashcard> flashcards = flashcardService.getAll();
    if (flashcards.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(flashcards);
    }
  }

  @GetMapping("/{id}")
  public Flashcard getById(@PathVariable Long id) {
    return flashcardService.getById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Flashcard> delete(@PathVariable Long id) {
    flashcardService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Flashcard> update(
      @PathVariable Long id, @RequestBody FlashcardPatch patch) {
    Flashcard flashcard = flashcardService.update(id, patch);
    return ResponseEntity.ok(flashcard);
  }
}
