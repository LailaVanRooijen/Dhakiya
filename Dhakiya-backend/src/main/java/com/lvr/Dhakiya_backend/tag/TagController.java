package com.lvr.Dhakiya_backend.tag;

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
    Tag savedTag = tagService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(savedTag.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedTag);
  }

  @GetMapping
    public List<Tag> getAll(){
      return tagService.getAll();
  }
}