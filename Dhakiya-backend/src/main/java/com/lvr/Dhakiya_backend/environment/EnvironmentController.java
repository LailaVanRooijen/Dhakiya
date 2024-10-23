package com.lvr.Dhakiya_backend.environment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.lvr.Dhakiya_backend.appConfig.Routes.ENVIRONMENTS;

@RequestMapping(ENVIRONMENTS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
public class EnvironmentController {
    private final EnvironmentService environmentService;

    @PostMapping()
    public ResponseEntity<Environment> create(@RequestBody CreateEnvironmentDto environment){
        Environment savedEnvironment = environmentService.create(environment);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(savedEnvironment.getId())
                        .toUri();
        return ResponseEntity.created(location).body(savedEnvironment);
    }

    @GetMapping()
    public List<Environment> getAll(){
        return environmentService.getAll();
    }

    @GetMapping("/{id}")
    public Environment getById(@PathVariable Long id){
        return environmentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        environmentService.delete(id);
    }
}
