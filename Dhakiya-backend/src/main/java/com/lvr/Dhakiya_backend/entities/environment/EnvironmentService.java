package com.lvr.Dhakiya_backend.entities.environment;

import com.lvr.Dhakiya_backend.entities.noteSet.NoteSet;
import com.lvr.Dhakiya_backend.entities.noteSet.NoteSetRepository;
import com.lvr.Dhakiya_backend.entities.notes.NoteRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
  private final EnvironmentRepository environmentRepository;
  private final NoteSetRepository noteSetRepository;
  private final NoteRepository noteRepository;

  public Environment create(EnvironmentDto dto) {
    Environment environment = EnvironmentDto.to(dto);
    NoteSet noteSet = noteSetRepository.save(new NoteSet());
    environment.setNoteSet(noteSet);
    return environmentRepository.save(environment);
  }

  public List<EnvironmentMinimalDto> getAll() {
    return environmentRepository.findAll().stream().map(EnvironmentMinimalDto::from).toList();
  }

  public Environment getById(Long id) {
    return environmentRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public void delete(Long id) {
    Environment environment =
        environmentRepository.findById(id).orElseThrow(NotFoundException::new);
    NoteSet noteSet =
        noteSetRepository
            .findById(environment.getNoteSet().getId())
            .orElseThrow(NotFoundException::new);
    noteSet.getNotes().forEach(noteRepository::delete);
    noteSetRepository.delete(noteSet);
    environmentRepository.delete(environment);
  }

  public Environment update(Long id, EnvironmentDto patch) {
    Environment environment =
        environmentRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.title() != null) environment.setTitle(patch.title());
    return environmentRepository.save(environment);
  }
}
