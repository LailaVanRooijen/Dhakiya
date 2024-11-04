package com.lvr.Dhakiya_backend.entities.question;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.answer.AnswerDto;
import com.lvr.Dhakiya_backend.entities.answer.AnswerRepository;
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
  private final AnswerRepository answerRepository;

  public Question create(QuestionDto dto) {
    if (dto.answerAmount() < 2 || dto.answerAmount() > 8) {
      throw new BadRequestException("Answer amount must be between 2 and 8");
    } else if (dto.answerAmount() != dto.answers().size()) {
      throw new BadRequestException(
          "Answer limit: " + dto.answerAmount() + " Provided answers: " + dto.answers().size());
    }
    Question question = QuestionDto.to(dto);
    for (int i = 0; i < dto.answerAmount(); i++) {
      Answer answer = AnswerDto.to(dto.answers().get(i));
      answerRepository.save(answer);
      question.addAnswers(answer);
    }
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
      if (patch.answerAmount() < 4 || patch.answerAmount() > 8) {
        throw new BadRequestException("Amount of answers must be between 2 and 6");
      } else {
        question.setAnswerAmount(patch.answerAmount());
      }
    }
    return questionRepository.save(question);
  }
}
