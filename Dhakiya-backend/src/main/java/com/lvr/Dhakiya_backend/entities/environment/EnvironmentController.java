package com.lvr.Dhakiya_backend.entities.environment;

import static com.lvr.Dhakiya_backend.appConfig.Routes.ENVIRONMENTS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(ENVIRONMENTS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class EnvironmentController {
  private final EnvironmentService environmentService;

  @PostMapping()
  public ResponseEntity<Environment> create(@RequestBody EnvironmentDto dto) {
    Environment savedEnvironment = environmentService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedEnvironment.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedEnvironment);
  }

  @GetMapping()
  public ResponseEntity<List<Environment>> getAll() {
    List<Environment> environments = environmentService.getAll();
    if (environments.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(environments);
    }
  }

  @GetMapping("/{id}")
  public Environment getById(@PathVariable Long id) {
    return environmentService.getById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Environment> delete(@PathVariable Long id) {
    environmentService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Environment> update(
      @PathVariable Long id, @RequestBody EnvironmentDto patch) {
    Environment patchedEnvironment = environmentService.update(id, patch);
    return ResponseEntity.ok(patchedEnvironment);
  }
}
