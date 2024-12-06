package com.lvr.Dhakiya_backend.entities.noteset;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.NOTE_SETS)
@RequiredArgsConstructor
public class NoteSetController {
  private final NoteSetService noteSetService;

  // TODO delete me later, testing purposes
  @GetMapping
  public ResponseEntity<List<NoteSet>> get() {
    List<NoteSet> notesets = noteSetService.getAll();
    return ResponseEntity.ok(notesets);
  }

  @GetMapping("/{id}")
  public ResponseEntity<NoteSet> getById(@PathVariable Long id) {
    NoteSet noteSet = noteSetService.getById(id);
    return ResponseEntity.ok(noteSet);
  }
}
