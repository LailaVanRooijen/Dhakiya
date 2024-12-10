package com.lvr.Dhakiya_backend.entities.quizcollection;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class QuizCollection {
  @GeneratedValue @Id Long id;

  @Setter private String title;

  @Setter @ManyToOne private Environment environment;

  public QuizCollection(String title) {
    this.title = title;
  }
}
