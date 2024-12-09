package com.lvr.Dhakiya_backend.entities.environment.flashcard;

import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Flashcard {
  @GeneratedValue @Id private Long id;

  @Setter private String title;
  @Setter private String content;
  @Setter private Integer seenCount;
  @Setter private Integer correctCount;
  @Setter private Integer incorrectCount;
  @Setter private Integer flaggedEasyCount;
  @Setter private Integer flaggedDifficultCount;
  @Setter private LocalDate lastSeen;
  private final LocalDate createdOn = LocalDate.now();

  @Setter @ManyToOne Tag tag;

  @Setter @ManyToOne FlashcardDeck flashcardDeck;

  public Flashcard(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
