package com.lvr.Dhakiya_backend.entities.quiz;

public record QuizDto(Long environmentId, String name) {
  public static QuizDto from(Long environmentId, Quiz quiz) {
    return new QuizDto(environmentId, quiz.getName());
  }

  public static Quiz to(QuizDto dto) {
    return new Quiz(dto.name);
  }
}
