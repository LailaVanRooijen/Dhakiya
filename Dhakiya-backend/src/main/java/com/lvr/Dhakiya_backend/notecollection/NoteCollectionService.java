package com.lvr.Dhakiya_backend.notecollection;

import com.lvr.Dhakiya_backend.notecollection.notes.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteCollectionService {
  private final NoteCollectionRepository noteCollectionRepository;
  private final NoteRepository noteRepository;

  public NoteCollection getById(Long id) {
    return noteCollectionRepository.findById(id).orElseThrow();
  }

  public NoteCollection create() {
    return noteCollectionRepository.save(new NoteCollection());
  }
}
