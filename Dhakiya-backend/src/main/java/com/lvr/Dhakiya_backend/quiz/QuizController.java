package com.lvr.Dhakiya_backend.quiz;

import static com.lvr.Dhakiya_backend.appConfig.Routes.QUIZZES;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(QUIZZES)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class QuizController {
  private final QuizService quizService;

  @PostMapping()
  public ResponseEntity<Quiz> create(@RequestBody QuizDto quizDto) {
    Quiz savedQuiz = quizService.create(quizDto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(savedQuiz.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedQuiz);
  }

  @GetMapping()
  public List<Quiz> getAll() {
    return quizService.getAll();
  }

  @GetMapping("/{id}")
  public Quiz getById(@PathVariable Long id) {
    return quizService.getById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    quizService.delete(id);
  }

  @PatchMapping("/{quizId}")
  public Quiz editQuiz(
      @PathVariable Long quizId, @RequestParam Long questionId, @RequestParam Action action) {
    return quizService.patchQuiz(quizId, questionId, action);
  }
}
