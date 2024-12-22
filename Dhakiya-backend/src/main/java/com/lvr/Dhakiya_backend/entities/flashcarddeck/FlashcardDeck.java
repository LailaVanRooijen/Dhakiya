package com.lvr.Dhakiya_backend.entities.flashcarddeck;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class FlashcardDeck {
  @GeneratedValue @Id private Long id;

  @Setter @ManyToOne private Environment environment;

  @Setter private String title;

  public FlashcardDeck(String title) {
    this.title = title;
  }
}
