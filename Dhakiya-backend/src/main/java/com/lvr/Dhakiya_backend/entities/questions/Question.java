package com.lvr.Dhakiya_backend.entities.questions;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import jakarta.persistence.*;
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

  @Setter @ManyToOne Quiz quiz;
  @Setter private String question;
  @OneToMany List<Answer> answers = new ArrayList<>();
  @Setter private Integer answerCount;
  @Setter private Integer validAnswerCount;
  @Setter private Boolean isCompleted;
  @Setter @ManyToOne private Tag tag;

  public Question(String question, Integer answerCount, Integer validAnswerCount) {
    this.question = question;
    this.answerCount = answerCount;
    this.validAnswerCount = validAnswerCount;
  }

  public void addAnswers(List<Answer> answers){
    this.answers.addAll(answers);
  }
}
