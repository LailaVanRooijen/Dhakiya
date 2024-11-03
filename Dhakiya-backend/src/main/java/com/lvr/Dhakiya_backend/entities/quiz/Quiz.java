package com.lvr.Dhakiya_backend.entities.quiz;

import com.lvr.Dhakiya_backend.entities.question.Question;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Quiz {
  @GeneratedValue @Id private Long id;

  @Setter private String name;

  @OneToMany(fetch = FetchType.EAGER)
  private List<Question> questions = new ArrayList<>();

  public Quiz(String name) {
    this.name = name;
  }

  public void addQuestions(List<Question> questions) {
    this.questions.addAll(questions);
  }

  // overloaded
  public void addQuestions(Question question) {
    this.questions.add(question);
  }

  public void removeQuestions(List<Question> questions) {
    this.questions.removeAll(questions);
  }

  // overloaded
  public void removeQuestions(Question question) {
    this.questions.remove(question);
  }
}
