package com.lvr.Dhakiya_backend.noteSet;

import static com.lvr.Dhakiya_backend.appConfig.Routes.NOTE_COLLECTIONS;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping(NOTE_COLLECTIONS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class NoteSetController {
  private final NoteSetService noteSetService;

  @GetMapping("/{id}")
  public NoteSet getById(@PathVariable Long id) {
    return noteSetService.getById(id);
  }

  // TODO delete me later, for debug purpose
  @GetMapping()
  public List<NoteSet> getById() {
    return noteSetService.getAll();
  }
}
