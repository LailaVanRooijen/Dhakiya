package com.lvr.Dhakiya_backend.entities.noteSet;

import static com.lvr.Dhakiya_backend.appConfig.Routes.NOTE_SETS;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(NOTE_SETS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class NoteSetController {
  private final NoteSetService noteSetService;

  @GetMapping("/{id}")
  public NoteSet getById(@PathVariable Long id) {
    return noteSetService.getById(id);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<NoteSet> update(@PathVariable Long id, @RequestBody NoteSetPatchDto patch) {
    return ResponseEntity.ok(noteSetService.update(id, patch));
  }
}
