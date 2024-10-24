package com.lvr.Dhakiya_backend.quiz;

import com.lvr.Dhakiya_backend.quiz.question.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Quiz {
  @GeneratedValue @Id Long id;

  private String title;

  @OneToMany private List<Question> questions = new ArrayList<>();

  public Quiz(String title) {
    this.title = title;
  }
}
