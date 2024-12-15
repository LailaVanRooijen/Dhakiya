package com.lvr.Dhakiya_backend.entities.quiz;

import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Quiz {
  @GeneratedValue @Id Long id;

  @Setter private String title;
  @Setter private Integer size;


  @Setter @ManyToOne private QuizCollection quizCollection;

  public Quiz(String title) {
    this.title = title;
  }
}
