package com.lvr.Dhakiya_backend.entities.tag;

import static com.lvr.Dhakiya_backend.appConfig.Routes.TAGS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(TAGS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class TagController {
  private final TagService tagService;

  @PostMapping()
  public ResponseEntity<Tag> create(@RequestBody TagDto dto) {
    System.out.println("calling post mapping"); // todo delete me
    Tag savedTag = tagService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedTag.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedTag);
  }

  @GetMapping
  public List<Tag> getAll() {
    return tagService.getAll();
  }

  @GetMapping("/{id}")
  public Tag getById(@PathVariable Long id) {
    return tagService.getById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Tag> delete(@PathVariable Long id) {
    tagService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Tag> update(@PathVariable Long id, @RequestBody TagDto patch) {
    Tag updatedTag = tagService.update(id, patch);
    return ResponseEntity.ok(updatedTag);
  }
}
