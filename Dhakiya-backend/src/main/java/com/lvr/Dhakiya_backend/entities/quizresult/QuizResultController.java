package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.GetQuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.PostQuizResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(Routes.QUIZ_RESULTS)
public class QuizResultController {
    private final QuizResultService quizResultService;

    @PostMapping
    public ResponseEntity<GetQuizResult> create(@RequestBody PostQuizResult dto){
        GetQuizResult createdQuizResult = quizResultService.create(dto);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdQuizResult.id())
                        .toUri();
        return ResponseEntity.created(location).body(createdQuizResult);
    }
}
