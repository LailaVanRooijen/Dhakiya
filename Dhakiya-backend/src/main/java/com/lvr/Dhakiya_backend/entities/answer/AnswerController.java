package com.lvr.Dhakiya_backend.entities.answer;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.ANSWERS)
@RequiredArgsConstructor
public class AnswerController {
  private final AnswerRepository answerRepository;

  @GetMapping
  public ResponseEntity<List<Answer>> getAll() {
    return ResponseEntity.ok(answerRepository.findAll());
  }
}
