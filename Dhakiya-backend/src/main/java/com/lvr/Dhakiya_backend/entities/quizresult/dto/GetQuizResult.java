package com.lvr.Dhakiya_backend.entities.quizresult.dto;

import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.entities.quizresult.QuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.quizResultQuestion.QuizResultQuestion;

import java.util.List;

public record GetQuizResult(Long id, List<QuizResultQuestion> questions) {
    public static GetQuizResult from(QuizResult entity){
        return new GetQuizResult(entity.getId(),entity.getQuestions());
    }
}
