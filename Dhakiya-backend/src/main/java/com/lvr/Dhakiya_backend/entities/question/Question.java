package com.lvr.Dhakiya_backend.entities.question;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Question {
  @GeneratedValue @Id private Long id;

  @Setter private String question;

  @Setter private Integer answerAmount = 4;

  public Question(String question, Integer answerAmount) {
    this.question = question;
    this.answerAmount = answerAmount;
  }
}
