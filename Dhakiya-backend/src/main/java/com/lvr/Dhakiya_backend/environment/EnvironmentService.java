package com.lvr.Dhakiya_backend.environment;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
  private final EnvironmentRepository environmentRepository;

  public Environment create(EnvironmentDto environment) {
    return environmentRepository.save(EnvironmentDto.to(environment));
  }

  public List<Environment> getAll() {
    return environmentRepository.findAll();
  }

  public Environment getById(Long id) {
    return environmentRepository.findById(id).orElseThrow();
  }

  public void delete(Long id) {
    Environment environment = environmentRepository.findById(id).orElseThrow();
    environmentRepository.delete(environment);
  }
}
