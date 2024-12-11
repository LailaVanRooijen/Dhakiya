package com.lvr.Dhakiya_backend.entities.questions;

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

    // todo patch functionality

    questionRepository.save(question);
    return GetQuestion.from(question);
  }
}
