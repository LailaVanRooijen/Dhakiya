package com.lvr.Dhakiya_backend.quiz.question;

import java.util.List;

public record QuestionDto(String question, String correctAnswer, List<String> incorrectAnswers) {
  public static QuestionDto from(Question question) {
    return new QuestionDto(
        question.getQuestion(), question.getCorrectAnswer(), question.getIncorrectAnswers());
  }

  public static Question to(QuestionDto questionDto) {
    Question newQuestion = new Question(questionDto.question, questionDto.correctAnswer);
    newQuestion.addIncorrectAnswers(questionDto.incorrectAnswers);
    return newQuestion;
  }
}
