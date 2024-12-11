package com.lvr.Dhakiya_backend.entities.questions;

import com.lvr.Dhakiya_backend.entities.questions.dto.GetQuestion;
import com.lvr.Dhakiya_backend.entities.questions.dto.PostQuestion;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quiz.QuizRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
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
}
