package com.lvr.Dhakiya_backend.entities.answer;

public record AnswerDto(String answer, Boolean isValid) {
  public static Answer to(AnswerDto dto) {
    return new Answer(dto.answer(), dto.isValid);
  }

  public static AnswerDto from(Answer answer) {
    return new AnswerDto(answer.getAnswer(), answer.getIsValid());
  }
}
