package com.lvr.Dhakiya_backend.quiz;

public record QuizDto(String title) {
  public static QuizDto from(Quiz quiz) {
    return new QuizDto(quiz.getTitle());
  }

  public static Quiz to(QuizDto quizDto) {
    return new Quiz(quizDto.title);
  }
}
