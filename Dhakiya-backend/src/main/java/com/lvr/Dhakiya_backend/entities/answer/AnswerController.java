package com.lvr.Dhakiya_backend.entities.answer;

import static com.lvr.Dhakiya_backend.appConfig.Routes.ANSWERS;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ANSWERS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
@RestController
public class AnswerController {
  private final AnswerService answerService;

  // No create/delete endpoints, user should not be able to manually create/delete
  // Answers should be created and deleted through create/deleting a question

  @PatchMapping("/{id}")
  public ResponseEntity<Answer> update(@PathVariable Long id, @RequestBody AnswerPatch patch) {
    return ResponseEntity.ok(answerService.update(id, patch));
  }
}
