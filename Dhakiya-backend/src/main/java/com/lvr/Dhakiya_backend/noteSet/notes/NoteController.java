package com.lvr.Dhakiya_backend.noteSet.notes;

import static com.lvr.Dhakiya_backend.appConfig.Routes.NOTES;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(NOTES)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class NoteController {
  private final NoteService noteService;

  @PostMapping()
  public ResponseEntity<Note> create(@RequestBody NoteDto dto) {
    Note savedNote = noteService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(savedNote.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedNote);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody NotePatch patch) {
    Note patchedNote = noteService.update(id, patch);
    return ResponseEntity.ok(patchedNote);
  }

  // TODO delete me, only needed for debug purposes
  @GetMapping()
  public List<Note> getAll() {
    return noteService.getAll();
  }

  @GetMapping("/{id}")
  public Note getById(@PathVariable Long id) {
    return noteService.getById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    noteService.delete(id);
  }
}