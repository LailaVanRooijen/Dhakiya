package com.lvr.Dhakiya_backend.entities.quiz;

import static com.lvr.Dhakiya_backend.appConfig.Routes.QUIZ_SETS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(QUIZ_SETS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class QuizController {
  private final QuizService quizService;

  @PostMapping()
  public ResponseEntity<Quiz> create(@RequestBody QuizDto dto) {
    Quiz savedQuiz = quizService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedQuiz.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedQuiz);
  }

  @GetMapping
  public ResponseEntity<List<Quiz>> getAll() {
    List<Quiz> quizSets = quizService.getAll();
    if (quizSets.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(quizSets);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Quiz> getById(@PathVariable Long id) {
    Quiz quiz = quizService.getById(id);
    return ResponseEntity.ok(quiz);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Quiz> delete(@PathVariable Long id) {
    quizService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Quiz> patch(@PathVariable Long id, @RequestBody QuizPatch patch) {
    Quiz quiz = quizService.update(id, patch);
    return ResponseEntity.ok(quiz);
  }
}
