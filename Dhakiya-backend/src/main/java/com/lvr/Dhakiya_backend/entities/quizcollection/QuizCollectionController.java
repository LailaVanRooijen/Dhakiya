package com.lvr.Dhakiya_backend.entities.quizcollection;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.GetQuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.PatchQuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.PostQuizCollection;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Routes.QUIZ_COLLECTIONS)
@RequiredArgsConstructor
public class QuizCollectionController {
  private final QuizCollectionService quizCollectionService;

  @PostMapping
  public ResponseEntity<GetQuizCollection> create(@RequestBody PostQuizCollection quizCollection) {
    GetQuizCollection createdQuizCollection = quizCollectionService.create(quizCollection);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdQuizCollection.id())
            .toUri();
    return ResponseEntity.created(location).body(createdQuizCollection);
  }

  @GetMapping
  public ResponseEntity<List<GetQuizCollection>> getAll() {
    List<GetQuizCollection> quizCollections = quizCollectionService.getAll();
    if (quizCollections.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(quizCollections);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetQuizCollection> getById(@PathVariable Long id) {
    GetQuizCollection quizCollection = quizCollectionService.getById(id);
    return ResponseEntity.ok(quizCollection);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GetQuizCollection> delete(@PathVariable Long id) {
    quizCollectionService.delete(id);

    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetQuizCollection> update(
      @PathVariable Long id, PatchQuizCollection patch) {
    GetQuizCollection quizCollection = quizCollectionService.update(id, patch);
    return ResponseEntity.ok(quizCollection);
  }
}