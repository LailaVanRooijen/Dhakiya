package com.lvr.Dhakiya_backend.entities.questions.dto;

public record PatchQuestion(
    String question,
    Integer answerCount,
    Integer validAnswerCount,
    Boolean isCompleted,
    Long tagId) {}
