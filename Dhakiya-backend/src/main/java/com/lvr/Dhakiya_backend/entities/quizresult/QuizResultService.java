package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.answer.AnswerRepository;
import com.lvr.Dhakiya_backend.entities.questions.Question;
import com.lvr.Dhakiya_backend.entities.questions.QuestionRepository;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quiz.QuizRepository;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.GetQuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.PostQuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.quizResultQuestion.QuizResultQuestion;
import com.lvr.Dhakiya_backend.entities.quizresult.quizResultQuestion.QuizResultQuestionRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizResultService {
    private final QuizResultRepository quizResultRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final QuizResultQuestionRepository quizResultQuestionRepository;

    public GetQuizResult create(PostQuizResult dto) {
        QuizResult quizResult = new QuizResult();

        Quiz quiz = quizRepository.findById(dto.quizId()).orElseThrow(NotFoundException::new);
        quizResult.setQuiz(quiz);

        List<QuizResultQuestion> questions = questionRepository.findQuestionByQuiz(quiz).stream().map(question -> copyQuestion(question)).toList();
        quizResult.add(questions);

        quizResultRepository.save(quizResult);

        return GetQuizResult.from(quizResult);
    }

    public QuizResultQuestion copyQuestion(Question question){
        QuizResultQuestion copiedQuestion = new QuizResultQuestion(question);
        return quizResultQuestionRepository.save(copiedQuestion);
    }
}
