package com.lvr.Dhakiya_backend.entities.questions;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.answer.AnswerRepository;
import com.lvr.Dhakiya_backend.entities.answer.dto.PostAnswer;
import com.lvr.Dhakiya_backend.entities.questions.dto.GetQuestion;
import com.lvr.Dhakiya_backend.entities.questions.dto.PatchQuestion;
import com.lvr.Dhakiya_backend.entities.questions.dto.PostQuestion;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quiz.QuizRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;
  private final TagRepository tagRepository;
  private final QuizRepository quizRepository;
  private final AnswerRepository answerRepository;

  public GetQuestion create(PostQuestion dto) {
    Question question = PostQuestion.to(dto);

    if (dto.quizId() == null) {
      throw new BadRequestException("quiz id is required");
    } else {
      Quiz quiz = quizRepository.findById(dto.quizId()).orElseThrow(NotFoundException::new);
      question.setQuiz(quiz);
    }

    if (dto.tagId() != null) {
      Tag tag = tagRepository.findById(dto.tagId()).orElseThrow(NotFoundException::new);
      question.setTag(tag);
    }

    if (dto.answers() == null || dto.answers().size() != dto.answerCount()) {
      throw new BadRequestException("Answers must be provided");
    }
    List<Answer> answers = dto.answers().stream().map(answer -> PostAnswer.to(answer)).toList();
    answers.forEach(answer -> answerRepository.save(answer));
    question.addAnswers(answers);

    questionRepository.save(question);
    return GetQuestion.from(question);
  }

  public List<GetQuestion> GetAll() {
    return questionRepository.findAll().stream()
        .map(question -> GetQuestion.from(question))
        .toList();
  }

  public GetQuestion getById(Long id) {
    Question question = questionRepository.findById(id).orElseThrow(NotFoundException::new);
    return GetQuestion.from(question);
  }

  public void delete(Long id) {
    questionRepository.findById(id).orElseThrow(NotFoundException::new);
    questionRepository.deleteById(id);
  }

  public GetQuestion update(Long id, PatchQuestion patch) {
    Question question = questionRepository.findById(id).orElseThrow(NotFoundException::new);

    if (patch.question() != null) {
      question.setQuestion(patch.question());
    }

    if (patch.answerCount() != null) {
      if (patch.answerCount() <= question.getValidAnswerCount() && patch.validAnswerCount() == null
          || patch.answerCount() <= patch.validAnswerCount()) {
        throw new BadRequestException("answerCount and validAnswerCount are incompatible");
      }
      question.setAnswerCount(patch.answerCount());
    }

    if (patch.validAnswerCount() != null) {
      if (patch.validAnswerCount() >= question.getAnswerCount() && patch.answerCount() == null
          || patch.validAnswerCount() >= patch.answerCount()) {
        throw new BadRequestException("answerCount and validAnswerCount are incompatible");
      }
    }

    if (patch.isCompleted() != null) {
      question.setIsCompleted(patch.isCompleted());
    }

    if (patch.tagId() != null) {
      Tag tag = tagRepository.findById(patch.tagId()).orElseThrow(NotFoundException::new);
      question.setTag(tag);
    }

    questionRepository.save(question);
    return GetQuestion.from(question);
  }
}
