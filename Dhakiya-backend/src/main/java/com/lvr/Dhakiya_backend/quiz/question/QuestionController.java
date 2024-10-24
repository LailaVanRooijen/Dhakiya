package com.lvr.Dhakiya_backend.quiz.question;

import static com.lvr.Dhakiya_backend.appConfig.Routes.QUESTIONS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(QUESTIONS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class QuestionController {
  private final QuestionService questionService;

  @PostMapping()
  public ResponseEntity<Question> create(@RequestBody QuestionDto questionDto) {
    Question savedQuestion = questionService.create(questionDto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(savedQuestion.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedQuestion);
  }

  @GetMapping()
  public List<Question> getAll() {
    return questionService.getAll();
  }

  @GetMapping("/{id}")
  public Question getById(@PathVariable Long id) {
    return questionService.getById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    questionService.delete(id);
  }
}
