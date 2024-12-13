package com.lvr.Dhakiya_backend.entities.questions.dto;

import com.lvr.Dhakiya_backend.entities.answer.dto.PatchAnswer;
import java.util.List;

public record PatchQuestion(
    String question,
    Integer answerCount,
    Boolean isCompleted,
    List<PatchAnswer> answers,
    Long tagId) {}
