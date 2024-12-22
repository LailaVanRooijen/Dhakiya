package com.lvr.Dhakiya_backend.entities.questions;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.answer.AnswerRepository;
import com.lvr.Dhakiya_backend.entities.answer.dto.PatchAnswer;
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
      throw new BadRequestException("Not all answers are provided");
    }

    List<Answer> answers = dto.answers().stream().map(answer -> PostAnswer.to(answer)).toList();
    answers.forEach(answer -> answerRepository.save(answer));
    question.add(answers);

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

  public GetQuestion patch(Long id, PatchQuestion patch) {
    Question question = questionRepository.findById(id).orElseThrow(NotFoundException::new);

    if (patch.question() != null) {
      question.setQuestion(patch.question());
    }

    if (patch.answerCount() != null) {
      if (question.getAnswers().size() > patch.answerCount()) {
        throw new BadRequestException("answer count set lower then current answers");
      }
      question.setAnswerCount(patch.answerCount());
    }

    if (patch.tagId() != null) {
      Tag tag = tagRepository.findById(patch.tagId()).orElseThrow(NotFoundException::new);
      question.setTag(tag);
    }

    if (patch.answers() != null && !patch.answers().isEmpty()) {
      patch.answers().forEach(answer -> patchAnswer(answer));
    }

    questionRepository.save(question);
    return GetQuestion.from(question);
  }

  private Answer patchAnswer(PatchAnswer dto) {
    Answer answer = answerRepository.findById(dto.id()).orElseThrow(NotFoundException::new);
    if (dto.answer() != null) {
      answer.setAnswer(dto.answer());
    }
    if (dto.isCorrect() != null) {
      answer.setIsCorrect(dto.isCorrect());
    }
    return answerRepository.save(answer);
  }

  public void delete(Long id) {
    questionRepository.findById(id).orElseThrow(NotFoundException::new);
    questionRepository.deleteById(id);
  }
}
