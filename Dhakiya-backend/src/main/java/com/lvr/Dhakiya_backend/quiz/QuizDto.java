package com.lvr.Dhakiya_backend.quiz;

import com.lvr.Dhakiya_backend.quiz.question.Question;
import java.util.List;

public record QuizDto(String title, List<Question> questions) {
  public static QuizDto from(Quiz quiz) {
    return new QuizDto(quiz.getTitle(), quiz.getQuestions());
  }

  public static Quiz to(QuizDto quizDto) {
    Quiz newQuiz = new Quiz(quizDto.title);
    return newQuiz;
  }
}
