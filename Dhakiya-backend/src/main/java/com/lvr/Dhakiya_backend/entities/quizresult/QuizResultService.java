package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.entities.questions.QuestionRepository;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.GetQuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.PostQuizResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizResultService {
    private final QuizResultRepository quizResultRepository;
    private final QuestionRepository questionRepository;


    public GetQuizResult create(PostQuizResult dto) {
        return null; //TODO uitvogelen hoe ik dit vorm wil geven
    }
}
