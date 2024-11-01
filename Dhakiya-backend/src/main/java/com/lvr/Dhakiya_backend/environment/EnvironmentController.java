package com.lvr.Dhakiya_backend.environment;

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
  public ResponseEntity<Environment> create(@RequestBody EnvironmentDto environment) {
    Environment savedEnvironment = environmentService.create(environment);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(savedEnvironment.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedEnvironment);
  }

  @GetMapping()
  public List<Environment> getAll() {
    return environmentService.getAll();
  }

  @GetMapping("/{id}")
  public Environment getById(@PathVariable Long id) {
    return environmentService.getById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    environmentService.delete(id);
  }

  @PatchMapping("/{id}")
  public Environment update(@PathVariable Long id, @RequestBody EnvironmentDto patch) {
    return environmentService.update(id, patch);
  }
}
