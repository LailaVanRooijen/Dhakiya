package com.lvr.Dhakiya_backend.notecollection;

import static com.lvr.Dhakiya_backend.appConfig.Routes.NOTE_COLLECTIONS;

import com.lvr.Dhakiya_backend.quiz.Action;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping(NOTE_COLLECTIONS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class NoteCollectionController {
  private final NoteCollectionService noteCollectionService;

  @GetMapping("/{id}")
  public NoteCollection getById(@PathVariable Long id) {
    return noteCollectionService.getById(id);
  }

  @PatchMapping("/{noteCollectionId}")
  public NoteCollection editNoteCollection(
      @PathVariable Long noteCollectionId, @RequestParam Long noteId, @RequestParam Action action) {
    return noteCollectionService.patchNoteCollection(noteCollectionId, noteId, action);
  }
}
