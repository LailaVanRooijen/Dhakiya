package com.lvr.Dhakiya_backend.entities.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Quiz {
  @GeneratedValue @Id private Long id;

  @Setter private String name;

  public Quiz(String name) {
    this.name = name;
  }
}
