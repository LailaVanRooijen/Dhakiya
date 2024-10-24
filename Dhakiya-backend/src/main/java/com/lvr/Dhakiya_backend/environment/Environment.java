package com.lvr.Dhakiya_backend.environment;

import com.lvr.Dhakiya_backend.flashcardcollection.FlashcardCollection;
import com.lvr.Dhakiya_backend.notecollection.NoteCollection;
import com.lvr.Dhakiya_backend.quiz.Quiz;
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
  @GeneratedValue @Id Long id;

  private String title;

  @OneToOne private NoteCollection noteCollection;

  @OneToMany private List<FlashcardCollection> flashcardCollections = new ArrayList<>();

  @OneToMany private List<Quiz> quizSets = new ArrayList<>();

  public Environment(String title) {
    this.title = title;
  }
}
