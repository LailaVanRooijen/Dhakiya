package com.lvr.Dhakiya_backend.entities.quiz.dto;

import com.lvr.Dhakiya_backend.entities.quiz.Quiz;

public record GetQuiz(Long id, Long QuizCollectionId, String title) {
  public static GetQuiz from(Quiz quiz) {
    return new GetQuiz(quiz.getId(), quiz.getQuizCollection().getId(), quiz.getTitle());
  }
}