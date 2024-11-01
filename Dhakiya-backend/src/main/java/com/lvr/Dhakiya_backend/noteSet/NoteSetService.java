package com.lvr.Dhakiya_backend.noteSet;

import com.lvr.Dhakiya_backend.noteSet.notes.Note;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteSetService {
  private final NoteSetRepository noteSetRepository;

  public NoteSet getById(Long id) {
    return noteSetRepository.findById(id).orElseThrow();
  }

  public NoteSet create() {
    return noteSetRepository.save(new NoteSet());
  }

  @Transactional
  public void addNote(Long id, Note note) {
    NoteSet noteSet = noteSetRepository.findById(id).orElseThrow();
    noteSet.addNote(note);
    noteSetRepository.save(noteSet);
  }

  public List<NoteSet> getAll() {
    return noteSetRepository.findAll();
  }
}
