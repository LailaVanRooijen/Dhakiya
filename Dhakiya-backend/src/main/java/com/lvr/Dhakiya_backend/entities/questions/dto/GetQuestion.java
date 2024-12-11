package com.lvr.Dhakiya_backend.entities.questions.dto;

import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.entities.tag.dto.GetTag;

public record GetQuestion(Long id, Long quizId, String question, Boolean isCompleted, GetTag tag) {
  public static GetQuestion from(Question question) {
    GetTag tag = null;
    if (question.getTag() != null) {
      tag = GetTag.from(question.getTag());
    }

    return new GetQuestion(
        question.getId(),
        question.getQuiz().getId(),
        question.getQuestion(),
        question.getIsCompleted(),
        tag);
  }
}
