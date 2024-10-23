package com.lvr.Dhakiya_backend.notecollection.notes;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;

  public Note create(NoteDto note) {
    return noteRepository.save(NoteDto.to(note));
  }

  public List<Note> getAll() {
    return noteRepository.findAll();
  }

  public Note getById(Long id) {
    return noteRepository.findById(id).orElseThrow();
  }

  public void delete(Long id) {
    noteRepository.findById(id).orElseThrow();
    noteRepository.deleteById(id);
  }
}
