package com.lvr.Dhakiya_backend.entities.environment.flashcard;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.environment.flashcard.dto.PatchFlashcard;
import com.lvr.Dhakiya_backend.entities.environment.flashcard.dto.PostFlashcard;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Routes.FLASHCARDS)
@RequiredArgsConstructor
public class FlashcardController {
  private final FlashcardService flashcardService;

  @PostMapping
  public ResponseEntity<Flashcard> create(@RequestBody PostFlashcard flashcard) {
    Flashcard createdFlashcard = flashcardService.create(flashcard);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdFlashcard.getId())
            .toUri();

    return ResponseEntity.created(location).body(createdFlashcard);
  }

  @GetMapping
  public ResponseEntity<List<Flashcard>> getAll() {
    List<Flashcard> flashcards = flashcardService.getAll();
    if (flashcards.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(flashcards);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Flashcard> getById(@PathVariable Long id) {
    return ResponseEntity.ok(flashcardService.getById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Flashcard> delete(@PathVariable Long id) {
    flashcardService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Flashcard> patch(@PathVariable Long id, @RequestBody PatchFlashcard patch) {
    return ResponseEntity.ok(flashcardService.patch(id, patch));
  }
}
