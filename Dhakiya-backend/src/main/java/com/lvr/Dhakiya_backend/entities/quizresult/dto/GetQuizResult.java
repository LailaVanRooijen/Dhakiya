package com.lvr.Dhakiya_backend.entities.quizresult.dto;

import com.lvr.Dhakiya_backend.entities.quizresult.QuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.AnsweredQuestion;

import java.util.List;

public record GetQuizResult(Long id, List<AnsweredQuestion> questions, Double score) {
  public static GetQuizResult from(QuizResult entity) {
    return new GetQuizResult(entity.getId(), entity.getQuestions(), entity.getScore());
  }
}
