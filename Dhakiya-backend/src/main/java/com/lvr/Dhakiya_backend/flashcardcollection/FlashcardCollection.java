package com.lvr.Dhakiya_backend.flashcardcollection;

import com.lvr.Dhakiya_backend.environment.Environment;
import com.lvr.Dhakiya_backend.flashcardcollection.flashcard.Flashcard;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class FlashcardCollection {
  @GeneratedValue @Id Long id;

  private String title;

  @ManyToOne private Environment environment;

  @OneToMany private List<Flashcard> flashcard = new ArrayList<>();

  public FlashcardCollection(String title, Environment environment) {
    this.title = title;
    this.environment = environment;
  }
}
