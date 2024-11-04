package com.lvr.Dhakiya_backend.entities.answer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Answer {
  @GeneratedValue @Id private Long id;

  @Setter private String answer;
  @Setter private Boolean isValid;

  public Answer(String answer, Boolean isValid) {
    this.answer = answer;
    this.isValid = isValid;
  }
}
