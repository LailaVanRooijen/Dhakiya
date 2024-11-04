package com.lvr.Dhakiya_backend.entities.question;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Question {
  @GeneratedValue @Id private Long id;

  @Setter private String question;

  @Setter private Integer answerAmount;

  @OneToMany private List<Answer> answers = new ArrayList<>();

  public Question(String question, Integer answerAmount) {
    this.question = question;
    this.answerAmount = answerAmount;
  }

  public Question(String question, Integer answerAmount, List<Answer> answers) {
    this.question = question;
    this.answerAmount = answerAmount;
    if (answers.size() != answerAmount) {
      throw new BadRequestException("Passed answers must match answer amount");
    }
    this.answers = answers;
  }

  public void addAnswers(List<Answer> answers) {
    this.answers.addAll(answers);
  }

  // overloaded
  public void addAnswers(Answer answer) {
    this.answers.add(answer);
  }

  public void removeAnswers(List<Answer> answers) {
    this.answers.removeAll(answers);
  }

  // overloaded
  public void removeAnswers(Answer answer) {
    this.answers.remove(answer);
  }
}
