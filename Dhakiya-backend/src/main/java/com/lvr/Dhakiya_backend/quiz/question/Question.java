package com.lvr.Dhakiya_backend.quiz.question;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
  @GeneratedValue @Id Long id;

  private String question;

  private String correctAnswer;
  private List<String> incorrectAnswers = new ArrayList<>();

  public Question(String correctAnswer, String question) {
    this.correctAnswer = correctAnswer;
    this.question = question;
  }

  public void addIncorrectAnswers(List<String> incorrectAnswers) {
    this.incorrectAnswers.addAll(incorrectAnswers);
  }

  public void overwriteIncorrectAnswers(List<String> incorrectAnswers) {
    this.incorrectAnswers = incorrectAnswers;
  }
}
