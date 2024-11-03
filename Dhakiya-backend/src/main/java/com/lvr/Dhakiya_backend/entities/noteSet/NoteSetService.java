package com.lvr.Dhakiya_backend.entities.noteSet;

import com.lvr.Dhakiya_backend.entities.notes.Note;
import com.lvr.Dhakiya_backend.entities.notes.NoteHelperService;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteSetService {
  private final NoteSetRepository noteSetRepository;
  private final NoteHelperService noteHelper;

  public NoteSet getById(Long id) {
    return noteSetRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public NoteSet create() {
    return noteSetRepository.save(new NoteSet());
  }

  public void addNote(Long id, Note note) {
    NoteSet noteSet = noteSetRepository.findById(id).orElseThrow(NotFoundException::new);
    noteSet.addNote(note);
    noteSetRepository.save(noteSet);
  }

  public List<NoteSet> getAll() {
    return noteSetRepository.findAll();
  }

  public NoteSet update(Long id, NoteSetPatch patch) {
    NoteSet noteSet = noteSetRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.removeNotes() != null) {
      List<Note> notes = noteHelper.convertToNoteList(patch.removeNotes());
      noteSet.removeAllNotes(notes);
    }
    noteSetRepository.save(noteSet);
    return noteSet;
  }
}
