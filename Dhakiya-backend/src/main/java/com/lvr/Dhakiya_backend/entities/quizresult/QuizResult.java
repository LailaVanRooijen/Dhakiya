package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quizresult.quizResultQuestion.QuizResultQuestion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class QuizResult {
    @GeneratedValue @Id private Long id;

    @Setter private String title;
    @Setter private LocalDate createdOn;
    @Setter private Boolean isCompleted;

    @OneToMany List<QuizResultQuestion> questions = new ArrayList<>();

    @Setter @ManyToOne Quiz quiz;

    public void add(List<QuizResultQuestion> questions){
        this.questions.addAll(questions);
    }

    // TODO delete comments
    // wat is het plan? als een quizResult word aangemaakt dan:
        // word er een quizResultQuestion aangemaakt (maybe todo betere naam voor deze)
        // dan kan per vraag een patch request gedaan worden om the selectedAnswers door te geven.
        // dan kan er een patch gedaan worden om de quizResult te submitten.
        // als de quizResult word gesubmit, dan komt er een total score uit, die word gedisplayed in de GetQuizResultDto
        // Pas bij de submit moeten de tags die aan deze questions gekoppeld zijn worden geflagged.
        // en eigenlijk mag de entity dan niet meer worden aangepast. dus een isSubmited, als die true is exception throwen in patch ofzo?
        // als de quiz word gedelete, moeten de flags van tag er weer afgetrokken worden!
    // TODO check of alle endpoints nog werken. + loop alle entities na, methods insert noemen als 1 item word toegevoegd aan list, en add voor meerdere!

}
