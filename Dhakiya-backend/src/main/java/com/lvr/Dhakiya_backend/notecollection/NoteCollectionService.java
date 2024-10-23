package com.lvr.Dhakiya_backend.notecollection;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteCollectionService {
  private final NoteCollectionRepository noteCollectionRepository;

  public NoteCollection create(NoteCollectionDto note) {
    return noteCollectionRepository.save(NoteCollectionDto.to(note));
  }

  public List<NoteCollection> getAll() {
    return noteCollectionRepository.findAll();
  }

  public NoteCollection getById(Long id) {
    return noteCollectionRepository.findById(id).orElseThrow();
  }

  public void delete(Long id) {
    NoteCollection noteCollection = noteCollectionRepository.findById(id).orElseThrow();
    noteCollectionRepository.deleteById(id);
  }
}
