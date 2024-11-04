package com.lvr.Dhakiya_backend.entities.question;

import com.lvr.Dhakiya_backend.entities.answer.AnswerDto;
import java.util.List;

public record QuestionDto(
    Long quizId, String question, Integer answerAmount, List<AnswerDto> answers) {
  public static Question to(QuestionDto dto) {
    return new Question(dto.question, dto.answerAmount());
  }

  public static QuestionDto from(Long quizId, Question question, List<AnswerDto> answers) {
    return new QuestionDto(quizId, question.getQuestion(), question.getAnswerAmount(), answers);
  }
}
