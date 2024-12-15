package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class QuizResult {
    @GeneratedValue @Id private Long id;

    private String title;

    @ManyToOne Quiz quiz;

}
