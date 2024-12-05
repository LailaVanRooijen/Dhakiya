package com.lvr.Dhakiya_backend.entities.environment;

import static com.lvr.Dhakiya_backend.appConfig.Routes.ENVIRONMENTS;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(ENVIRONMENTS)
@RestController
@RequiredArgsConstructor
public class EnvironmentController {
  public final EnvironmentService environmentService;

  @PostMapping
  public ResponseEntity<Environment> createEnvironment(@RequestBody CreateEnvironment dto) {
    Environment savedEnvironment = environmentService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedEnvironment.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedEnvironment);
  }

  @GetMapping
  public ResponseEntity<List<Environment>> getAllEnvironments() {
    List<Environment> environments = environmentService.getAll();
    if (environments.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(environments);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Environment> getEnvironment(@PathVariable Long id) {
    return ResponseEntity.ok(environmentService.getById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Environment> deleteEnvironment(@PathVariable Long id) {
    environmentService.deleteEnvironment(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Environment> patchEnvironment(
      @PathVariable Long id, @RequestBody PatchEnvironment patch) {
    return ResponseEntity.ok(environmentService.patchEnvironment(id, patch));
  }
}
