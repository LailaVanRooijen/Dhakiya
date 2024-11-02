package com.lvr.Dhakiya_backend.entities.environment;

import com.lvr.Dhakiya_backend.entities.flashcardset.FlashcardSet;
import com.lvr.Dhakiya_backend.entities.noteSet.NoteSet;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Environment {
  @GeneratedValue @Id private Long id;

  private String title;

  @OneToOne private NoteSet noteSet = new NoteSet();

  @OneToMany(fetch = FetchType.EAGER)
  private List<FlashcardSet> flashcardSets = new ArrayList<>();

  public Environment(String title) {
    this.title = title;
  }

  public void addFlashcardSet(FlashcardSet flashcardSet) {
    this.flashcardSets.add(flashcardSet);
  }
}
