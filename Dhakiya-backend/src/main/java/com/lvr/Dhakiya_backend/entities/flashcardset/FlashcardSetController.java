package com.lvr.Dhakiya_backend.entities.flashcardset;

import static com.lvr.Dhakiya_backend.appConfig.Routes.FLASHCARD_SETS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(FLASHCARD_SETS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class FlashcardSetController {
  private final FlashcardSetService flashcardSetService;

  @PostMapping
  public ResponseEntity<FlashcardSet> create(@RequestBody FlashcardSetDto dto) {
    FlashcardSet savedFlashcardSet = flashcardSetService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedFlashcardSet.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedFlashcardSet);
  }

  @GetMapping
  public ResponseEntity<List<FlashcardSet>> getAll() {
    List<FlashcardSet> flashcardSets = flashcardSetService.getAll();
    if (flashcardSets.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(flashcardSets);
    }
  }

  @GetMapping("/{id}")
  public FlashcardSet getById(@PathVariable Long id) {
    return flashcardSetService.getById(id);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<FlashcardSet> update(
      @PathVariable Long id, @RequestBody FlashcardSetPatchDto patch) {
    FlashcardSet flashcardSet = flashcardSetService.update(id, patch);
    return ResponseEntity.ok(flashcardSet);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<FlashcardSet> delete(@PathVariable Long id) {
    flashcardSetService.delete(id);
    return ResponseEntity.ok().build();
  }
}
