package com.lvr.Dhakiya_backend.quiz;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
  private final QuizRepository quizRepository;

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
