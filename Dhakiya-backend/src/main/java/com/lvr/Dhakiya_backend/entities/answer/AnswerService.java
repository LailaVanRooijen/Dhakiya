package com.lvr.Dhakiya_backend.entities.answer;

import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
  private final AnswerRepository answerRepository;

  public Answer create(AnswerDto dto) {
    Answer answer = AnswerDto.to(dto);
    answerRepository.save(answer);
    return answer;
  }

  public Answer getById(Long id) {
    return answerRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public void delete(Long id) {
    answerRepository.findById(id).orElseThrow(NotFoundException::new);
    answerRepository.deleteById(id);
  }

  public Answer update(Long id, AnswerPatch patch) {
    Answer answer = answerRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.answer() != null) {
      answer.setAnswer(patch.answer());
    }
    if (patch.isValid() != null) {
      answer.setIsValid(patch.isValid());
    }
    return answerRepository.save(answer);
  }
}
