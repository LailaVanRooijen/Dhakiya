package com.lvr.Dhakiya_backend.entities.quiz;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.quiz.dto.GetQuiz;
import com.lvr.Dhakiya_backend.entities.quiz.dto.PatchQuiz;
import com.lvr.Dhakiya_backend.entities.quiz.dto.PostQuiz;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping(Routes.QUIZZES)
public class QuizController {
  private final QuizService quizService;

  @PostMapping
  public ResponseEntity<GetQuiz> create(@RequestBody PostQuiz quiz) {
    GetQuiz createdQuiz = quizService.create(quiz);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdQuiz.id())
            .toUri();

    return ResponseEntity.created(location).body(createdQuiz);
  }

  @GetMapping
  public ResponseEntity<List<GetQuiz>> getAll() {
    List<GetQuiz> quizzes = quizService.getAll();
    if (quizzes.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(quizzes);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetQuiz> getById(@PathVariable Long id) {
    return ResponseEntity.ok(quizService.getById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GetQuiz> delete(@PathVariable Long id) {
    quizService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetQuiz> update(@PathVariable Long id, @RequestBody PatchQuiz patch) {
    GetQuiz quiz = quizService.update(id, patch);
    return ResponseEntity.ok(quiz);
  }
}