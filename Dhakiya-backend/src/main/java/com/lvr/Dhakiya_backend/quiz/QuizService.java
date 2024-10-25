package com.lvr.Dhakiya_backend.quiz;

import com.lvr.Dhakiya_backend.quiz.question.Question;
import com.lvr.Dhakiya_backend.quiz.question.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
  private final QuizRepository quizRepository;
  private final QuestionRepository questionRepository;

  public Quiz patchQuiz(Long id, Long questionId, Action action) {
    Quiz quiz = quizRepository.findById(id).orElseThrow();
    Question question = questionRepository.findById(questionId).orElseThrow();
    if (action == Action.ADD) {
      quiz.addQuestion(question);
    } else if (action == Action.DELETE) {
      quiz.deleteQuestion(question);
    }
    // todo ff dubbelchecken of dit werkt en er geen bugs zijn!
    return quiz;
  }

  public Quiz create(QuizDto quizDto) {
    return quizRepository.save(QuizDto.to(quizDto));
  }

  public List<Quiz> getAll() {
    return quizRepository.findAll();
  }

  public Quiz getById(Long id) {
    return quizRepository.findById(id).orElseThrow();
  }

  public void delete(Long id) {
    quizRepository.findById(id);
    quizRepository.deleteById(id);
  }
}
