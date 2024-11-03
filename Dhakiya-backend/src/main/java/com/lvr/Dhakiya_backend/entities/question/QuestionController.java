package com.lvr.Dhakiya_backend.entities.question;

import static com.lvr.Dhakiya_backend.appConfig.Routes.QUESTIONS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(QUESTIONS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
@RestController
public class QuestionController {
  private final QuestionService questionService;

  @PostMapping
  public ResponseEntity<Question> create(@RequestBody QuestionDto dto) {
    Question savedQuestion = questionService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedQuestion.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedQuestion);
  }

  @GetMapping
  public ResponseEntity<List<Question>> getAll() {
    List<Question> questions = questionService.getAll();
    if (questions.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(questions);
    }
  }

  @GetMapping("/{id}")
  public Question getById(@PathVariable Long id) {
    return questionService.getById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Question> delete(@PathVariable Long id) {
    questionService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody QuestionPatch patch) {
    Question question = questionService.update(id, patch);
    return ResponseEntity.ok(question);
  }
}
