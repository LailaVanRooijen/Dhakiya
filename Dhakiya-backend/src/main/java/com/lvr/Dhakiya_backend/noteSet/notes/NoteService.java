package com.lvr.Dhakiya_backend.noteSet.notes;

import com.lvr.Dhakiya_backend.noteSet.NoteSetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;
  private final NoteSetService noteSetService;

  public Note create(NoteDto dto) {
    Note note = noteRepository.save(NoteDto.to(dto));
    noteSetService.addNote(dto.noteSetId(), note);
    return note;
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

  public Note update(Long id, NotePatch patch) {
    Note note = noteRepository.findById(id).orElseThrow();
    if (patch.title() != null) note.setTitle(patch.title());
    if (patch.content() != null) note.setContent(patch.content());
    if (patch.label() != null) note.setLabel(patch.label());
    return noteRepository.save(note);
  }
}
