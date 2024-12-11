package com.lvr.Dhakiya_backend.entities.questions.dto;

import com.lvr.Dhakiya_backend.entities.answer.dto.PostAnswer;
import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import java.util.List;

public record PostQuestion(
    Long quizId,
    String question,
    Integer answerCount,
    Integer validAnswerCount,
    List<PostAnswer> answers,
    Long tagId) {
  public static Question to(PostQuestion dto) {
    if (dto.answerCount <= dto.validAnswerCount) {
      throw new BadRequestException("At least 1 answer must be incorrect");
    }
    if (dto.answers.size() != dto.answerCount()) {
      throw new BadRequestException("Answers exceed limit");
    }
    return new Question(dto.question, dto.answerCount, dto.validAnswerCount);
  }
}
