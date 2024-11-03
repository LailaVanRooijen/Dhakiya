package com.lvr.Dhakiya_backend.entities.question;

public record QuestionDto(Long quizId, String question, Integer answerAmount) {
  public static Question to(QuestionDto dto) {
    return new Question(dto.question, dto.answerAmount());
  }

  public static QuestionDto from(Long quizId, Question question) {
    return new QuestionDto(quizId, question.getQuestion(), question.getAnswerAmount());
  }
}
