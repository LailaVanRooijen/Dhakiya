package com.lvr.Dhakiya_backend.entities.notecollection;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.NOTE_COLLECTIONS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
public class NoteCollectionController {
  private final NoteCollectionService noteCollectionService;

  @GetMapping("/{id}")
  public ResponseEntity<NoteCollection> getById(@PathVariable Long id) {
    NoteCollection noteCollection = noteCollectionService.getById(id);
    return ResponseEntity.ok(noteCollection);
  }
}
