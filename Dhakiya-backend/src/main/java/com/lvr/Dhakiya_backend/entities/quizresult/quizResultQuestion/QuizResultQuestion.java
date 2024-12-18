package com.lvr.Dhakiya_backend.entities.quizresult.quizResultQuestion;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quizresult.QuizResult;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class QuizResultQuestion {
    @GeneratedValue @Id private Long id;
    @ManyToOne Question question;
    @OneToMany private List<Answer> selectedAnswers = new ArrayList<>();
    @Setter private Boolean isCompleted = false;

    public QuizResultQuestion(Question question) {
        this.question = question;
    }
}
