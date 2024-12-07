package com.lvr.Dhakiya_backend.entities.note;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.note.dto.PatchNote;
import com.lvr.Dhakiya_backend.entities.note.dto.PostNote;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Routes.NOTES)
@RequiredArgsConstructor
public class NoteController {
  private final NoteService noteService;

  @GetMapping
  public ResponseEntity<List<Note>> getAll() {
    List<Note> notes = noteService.getAll();
    if (notes.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(notes);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Note> getById(@PathVariable Long id) {
    return ResponseEntity.ok(noteService.getById(id));
  }

  @PostMapping
  public ResponseEntity<Note> create(@RequestBody PostNote note) {
    Note savedNote = noteService.create(note);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedNote.getId())
            .toUri();

    return ResponseEntity.created(location).body(savedNote);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Note> patch(@PathVariable Long id, @RequestBody PatchNote patch) {
    return ResponseEntity.ok(noteService.patch(id, patch));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Note> delete(@PathVariable Long id) {
    noteService.delete(id);
    return ResponseEntity.ok().build();
  }
}
