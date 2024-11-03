package com.lvr.Dhakiya_backend.entities.quiz;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
  private final QuizRepository quizRepository;
  private final EnvironmentRepository environmentRepository;

  public Quiz create(QuizDto dto) {
    Quiz quiz = QuizDto.to(dto);
    Environment environment =
        environmentRepository
            .findById(dto.environmentId())
            .orElseThrow(() -> new BadRequestException("environment does not exist"));
    environment.addQuiz(quiz);
    quizRepository.save(quiz);
    environmentRepository.save(environment);
    return quiz;
  }

  public List<Quiz> getAll() {
    return quizRepository.findAll();
  }

  public Quiz getById(Long id) {
    return quizRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public void delete(Long id) {
    quizRepository.findById(id).orElseThrow(NotFoundException::new);
    quizRepository.deleteById(id);
  }

  public Quiz update(Long id, QuizPatch patch) {
    Quiz quiz = quizRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.name() != null) {
      quiz.setName(patch.name());
    }
    return quizRepository.save(quiz);
  }
}
