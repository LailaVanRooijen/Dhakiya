package com.lvr.Dhakiya_backend.entities.question;

import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quiz.QuizRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;
  private final QuizRepository quizRepository;

  public Question create(QuestionDto dto) {
    Question question = QuestionDto.to(dto);
    Quiz quiz =
        quizRepository
            .findById(dto.quizId())
            .orElseThrow(() -> new BadRequestException("Quiz does not exist"));
    questionRepository.save(question);
    quiz.addQuestions(question);
    quizRepository.save(quiz);
    return question;
  }

  public List<Question> getAll() {
    return questionRepository.findAll();
  }

  public Question getById(Long id) {
    return questionRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public void delete(Long id) {
    questionRepository.findById(id).orElseThrow(NotFoundException::new);
    questionRepository.deleteById(id);
  }

  public Question update(Long id, QuestionPatch patch) {
    Question question = questionRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.question() != null) {
      question.setQuestion(patch.question());
    }
    if (patch.answerAmount() != null) {
      if (patch.answerAmount() == 4 || patch.answerAmount() == 6 || patch.answerAmount() == 8) {
        question.setAnswerAmount(patch.answerAmount());
      } else {
        throw new BadRequestException("Amount of answer can be 4,6 or 8");
      }
    }
    return questionRepository.save(question);
  }
}
