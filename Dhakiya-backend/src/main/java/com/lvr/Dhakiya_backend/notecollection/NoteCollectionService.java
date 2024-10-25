package com.lvr.Dhakiya_backend.notecollection;

import com.lvr.Dhakiya_backend.notecollection.notes.Note;
import com.lvr.Dhakiya_backend.notecollection.notes.NoteRepository;
import com.lvr.Dhakiya_backend.quiz.Action;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteCollectionService {
  private final NoteCollectionRepository noteCollectionRepository;
  private final NoteRepository noteRepository;

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
    noteCollectionRepository.delete(noteCollection);
  }

  public NoteCollection patchNoteCollection(Long noteCollectionId, Long noteId, Action action) {
    Note note = noteRepository.findById(noteId).orElseThrow();
    NoteCollection noteCollection =
        noteCollectionRepository.findById(noteCollectionId).orElseThrow();
    if (action == Action.ADD) {
      noteCollection.addNote(note);
    } else if (action == Action.DELETE) {
      noteCollection.deleteNote(note);
    }
    return noteCollection;
  }
}
