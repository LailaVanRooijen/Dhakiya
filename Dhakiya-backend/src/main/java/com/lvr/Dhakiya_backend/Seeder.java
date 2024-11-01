package com.lvr.Dhakiya_backend;

import com.lvr.Dhakiya_backend.environment.EnvironmentDto;
import com.lvr.Dhakiya_backend.environment.EnvironmentService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
  private final EnvironmentService environmentService;

  @Override
  public void run(String... args) throws Exception {
    seedEnvironments();
  }

  public void seedEnvironments() {
    if (!environmentService.getAll().isEmpty()) return;
    ArrayList<String> subjects =
        new ArrayList<>(List.of("Math", "English", "History", "Psychology"));
    subjects.forEach(subject -> environmentService.create(new EnvironmentDto(subject)));
  }
}
