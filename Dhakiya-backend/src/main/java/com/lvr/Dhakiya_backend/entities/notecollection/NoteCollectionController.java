package com.lvr.Dhakiya_backend.entities.notecollection;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.NOTE_COLLECTIONS)
@RequiredArgsConstructor
public class NoteCollectionController {
  private final NoteCollectionService noteCollectionService;

  // TODO delete me later, testing purposes
  @GetMapping
  public ResponseEntity<List<NoteCollection>> getAll() {
    List<NoteCollection> noteCollections = noteCollectionService.getAll();
    return ResponseEntity.ok(noteCollections);
  }

  @GetMapping("/{id}")
  public ResponseEntity<NoteCollection> getById(@PathVariable Long id) {
    NoteCollection noteCollection = noteCollectionService.getById(id);
    return ResponseEntity.ok(noteCollection);
  }
}
