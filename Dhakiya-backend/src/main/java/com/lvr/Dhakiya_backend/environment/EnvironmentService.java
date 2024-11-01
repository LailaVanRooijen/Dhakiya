package com.lvr.Dhakiya_backend.environment;

import com.lvr.Dhakiya_backend.noteSet.NoteSet;
import com.lvr.Dhakiya_backend.noteSet.NoteSetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
  private final EnvironmentRepository environmentRepository;

  private final NoteSetService noteSetService;

  public Environment create(EnvironmentDto dto) {
    Environment environment = EnvironmentDto.to(dto);
    NoteSet noteSet = noteSetService.create();
    environment.setNoteSet(noteSet);
    return environmentRepository.save(environment);
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
