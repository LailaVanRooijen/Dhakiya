package com.lvr.Dhakiya_backend.entities.questions.dto;

import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.entities.tag.dto.GetTag;

public record GetQuestion(Long id, Long quizId, String question, Boolean isCompleted, GetTag tag) {
  public static GetQuestion from(Question question) {
    return new GetQuestion(
        question.getId(),
        question.getQuiz().getId(),
        question.getQuestion(),
        question.getIsCompleted(),
        GetTag.from(question.getTag()));
  }
}
