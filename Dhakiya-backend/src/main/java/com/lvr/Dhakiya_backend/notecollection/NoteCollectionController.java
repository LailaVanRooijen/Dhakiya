package com.lvr.Dhakiya_backend.notecollection;

import static com.lvr.Dhakiya_backend.appConfig.Routes.NOTE_COLLECTIONS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(NOTE_COLLECTIONS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class NoteCollectionController {
  private final NoteCollectionService noteCollectionService;

  @PostMapping()
  public ResponseEntity<NoteCollection> create(@RequestBody NoteCollectionDto note) {
    NoteCollection savedNoteCollection = noteCollectionService.create(note);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(savedNoteCollection.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedNoteCollection);
  }

  @GetMapping()
  public List<NoteCollection> getAll() {
    return noteCollectionService.getAll();
  }

  @GetMapping("/{id}")
  public NoteCollection getById(@PathVariable Long id) {
    return noteCollectionService.getById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    noteCollectionService.delete(id);
  }
}
