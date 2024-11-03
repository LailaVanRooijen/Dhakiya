package com.lvr.Dhakiya_backend.entities.environment;

import com.lvr.Dhakiya_backend.entities.flashcardset.FlashcardSet;
import com.lvr.Dhakiya_backend.entities.noteSet.NoteSet;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Environment {
  @GeneratedValue @Id private Long id;

  @Setter private String title;

  @Setter @OneToOne private NoteSet noteSet = new NoteSet();

  @Setter
  @OneToMany(fetch = FetchType.EAGER)
  private List<FlashcardSet> flashcardSets = new ArrayList<>();

  @Setter
  @OneToMany(fetch = FetchType.EAGER)
  private List<Quiz> quizSets = new ArrayList<>();

  public Environment(String title) {
    this.title = title;
  }

  public void addFlashcardSets(List<FlashcardSet> flashcardSets) {
    this.flashcardSets.addAll(flashcardSets);
  }

  // overloaded
  public void addFlashcardSets(FlashcardSet flashcardSet) {
    this.flashcardSets.add(flashcardSet);
  }

  public void removeFlashcardSets(List<FlashcardSet> flashcardSets) {
    this.flashcardSets.removeAll(flashcardSets);
  }

  // overloaded
  public void removeFlashcardSets(FlashcardSet flashcardSet) {
    this.flashcardSets.remove(flashcardSet);
  }

  public void addQuizSets(List<Quiz> quizSets) {
    this.quizSets.addAll(quizSets);
  }

  // overloaded
  public void addQuizSets(Quiz quiz) {
    this.quizSets.add(quiz);
  }

  public void removeQuizSets(List<Quiz> quizSets) {
    this.quizSets.removeAll(quizSets);
  }

  // overloaded
  public void removeQuizSets(Quiz quiz) {
    this.quizSets.remove(quiz);
  }
}
