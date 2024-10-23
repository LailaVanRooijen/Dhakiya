package com.lvr.Dhakiya_backend.flashcardcollection;

import static com.lvr.Dhakiya_backend.appConfig.Routes.FLASHCARD_COLLECTIONS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(FLASHCARD_COLLECTIONS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class FlashcardCollectionController {
    private final FlashcardCollectionService flashcardCollectionService;

    @PostMapping()
    public ResponseEntity<FlashcardCollection> create(@RequestBody FlashcardCollectionDto flashcardCollectionDto){
        FlashcardCollection savedFlashcardCollection = flashcardCollectionService.create(flashcardCollectionDto);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(savedFlashcardCollection.getId())
                        .toUri();
        return ResponseEntity.created(location).body(savedFlashcardCollection);
    }

    @GetMapping()
    public List<FlashcardCollection> getAll(){
        return flashcardCollectionService.getAll();
    }

    @GetMapping("/{id}")
    public FlashcardCollection getById(@PathVariable Long id){
        return flashcardCollectionService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        flashcardCollectionService.delete(id);
    }
}
