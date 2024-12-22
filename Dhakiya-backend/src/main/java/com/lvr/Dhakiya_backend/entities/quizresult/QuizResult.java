package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.AnsweredQuestion;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

  @Setter private LocalDate createdOn;
  @Setter private Boolean isCompleted = false;
  @Setter private int points = 0;

  @Setter @OneToMany List<AnsweredQuestion> answeredQuestions = new ArrayList<>();

  @Setter @ManyToOne Quiz quiz;

  public QuizResult(Quiz quiz) {
    this.quiz = quiz;
  }

  public void addPoint() {
    this.points++;
  }

  public BigDecimal getScore() {
    if (points == 0 || answeredQuestions.size() == 0)
      return new BigDecimal(0).setScale(1, RoundingMode.DOWN);
    Double score = (double) points / answeredQuestions.size() * 100;
    return new BigDecimal(score).setScale(1, RoundingMode.DOWN);
  }
}
