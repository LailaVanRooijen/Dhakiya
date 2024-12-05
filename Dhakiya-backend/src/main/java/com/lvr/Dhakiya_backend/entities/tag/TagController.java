package com.lvr.Dhakiya_backend.entities.tag;

import static com.lvr.Dhakiya_backend.appConfig.Routes.TAGS;

import com.lvr.Dhakiya_backend.entities.tag.tagDto.CreateTag;
import com.lvr.Dhakiya_backend.entities.tag.tagDto.PatchTag;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(TAGS)
@RequiredArgsConstructor
public class TagController {
  private final TagService tagService;

  @PostMapping
  public ResponseEntity<Tag> createTag(@RequestBody CreateTag dto) {
    Tag savedTag = tagService.createTag(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedTag.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedTag);
  }

  @GetMapping
  public ResponseEntity<List<Tag>> getAllTags() {
    List<Tag> tags = tagService.getAllTags();
    if (tags.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(tags);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
    return ResponseEntity.ok(tagService.getById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Tag> patchTag(@PathVariable Long id, @RequestBody PatchTag patch) {
    Tag patchedTag = tagService.patchTag(id, patch);
    return ResponseEntity.ok(patchedTag);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Tag> deleteTag(@PathVariable Long id) {
    tagService.deleteTag(id);
    return ResponseEntity.ok().build();
  }
}
