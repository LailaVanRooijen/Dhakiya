package com.lvr.Dhakiya_backend.entities.questions;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.questions.dto.GetQuestion;
import com.lvr.Dhakiya_backend.entities.questions.dto.PostQuestion;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Routes.QUESTIONS)
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionService questionService;

  @PostMapping
  public ResponseEntity<GetQuestion> create(@RequestBody PostQuestion question) {
    GetQuestion createdQuestion = questionService.create(question);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdQuestion.id())
            .toUri();
    return ResponseEntity.created(location).body(createdQuestion);
  }
}
