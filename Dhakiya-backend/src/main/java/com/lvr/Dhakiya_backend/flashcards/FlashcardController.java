package com.lvr.Dhakiya_backend.flashcards;

import com.lvr.Dhakiya_backend.environment.CreateEnvironmentDto;
import com.lvr.Dhakiya_backend.environment.Environment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.lvr.Dhakiya_backend.appConfig.Routes.FLASHCARDS;


@RequestMapping(FLASHCARDS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class FlashcardController {
    private final FlashcardService flashcardService;

    @PostMapping()
    public ResponseEntity<Flashcard> create(@RequestBody CreateFlashcardDto flashcard){
        Flashcard savedFlashcard = flashcardService.create(flashcard);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(savedFlashcard.getId())
                        .toUri();
        return ResponseEntity.created(location).body(savedFlashcard);
    }

    @GetMapping()
    public List<Flashcard> getAll(){
        return flashcardService.getAll();
    }

    @GetMapping("/{id}")
    public Flashcard getById(@PathVariable Long id){
        return flashcardService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        flashcardService.delete(id);
    }
}