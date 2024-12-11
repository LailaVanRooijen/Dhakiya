package com.lvr.Dhakiya_backend.entities.questions.dto;

import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import java.util.List;

public record PostQuestion(
    Long quizId,
    String question,
    Integer answerCount,
    Integer validAnswerCount,
    List<String> answers,
    Long tagId) {
  public static Question to(PostQuestion dto) {
    if (dto.validAnswerCount >= dto.validAnswerCount) {
      throw new BadRequestException("At least 1 answer must be incorrect");
    }
    // todo als er minder antwoorden zijn dan answerCount throw bad request
    /*if (dto.answers.size() != dto.answerCount()) {
      throw new BadRequestException("Answers exceed limit");
    }*/
    return new Question(dto.question, dto.answerCount, dto.validAnswerCount);
  }
}
