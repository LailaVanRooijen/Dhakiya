package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.AnsweredQuestion;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class QuizResult {
  @GeneratedValue @Id private Long id;

  @Setter private String title;
  @Setter private LocalDate createdOn;
  @Setter private Boolean isCompleted;
  @Setter private int points = 0;

  @OneToMany List<AnsweredQuestion> questions = new ArrayList<>();

  @Setter @ManyToOne Quiz quiz;

  public void add(List<AnsweredQuestion> questions) {
    this.questions.addAll(questions);
  }

  public void addPoint() {
    this.points++;
  }

  public Double getScore() {
    return (double) points / questions.size() * 100;
  }

  // TODO delete comments
  // Pas bij de submit moeten de tags die aan deze questions gekoppeld zijn worden geflagged.
  // als de quiz word gedelete, moeten de flags van tag er weer afgetrokken worden!
  // TODO check of alle endpoints nog werken. + loop alle entities na:
  // methods insert noemen als 1 item word toegevoegd aan list, en add voor meerdere!
  // update seeder met quizresults
  // check of de patch voor answers werkt

}
