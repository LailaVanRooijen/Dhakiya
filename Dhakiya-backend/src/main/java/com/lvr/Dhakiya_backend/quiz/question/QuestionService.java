package com.lvr.Dhakiya_backend.quiz.question;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  public Question create(QuestionDto questionDto) {
    return questionRepository.save(QuestionDto.to(questionDto));
  }

  public List<Question> getAll() {
    return questionRepository.findAll();
  }

  public Question getById(Long id) {
    return questionRepository.findById(id).orElseThrow();
  }

  public void delete(Long id) {
    questionRepository.findById(id).orElseThrow();
    questionRepository.deleteById(id);
  }
}
