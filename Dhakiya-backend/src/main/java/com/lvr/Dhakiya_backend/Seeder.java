package com.lvr.Dhakiya_backend;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentService;
import com.lvr.Dhakiya_backend.entities.environment.environmentDto.CreateEnvironment;
import com.lvr.Dhakiya_backend.entities.progressreport.ProgressReportService;
import com.lvr.Dhakiya_backend.entities.tag.TagService;
import com.lvr.Dhakiya_backend.entities.tag.tagDto.CreateTag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
  private final EnvironmentService environmentService;
  private final TagService tagService;
  private final ProgressReportService progressReportService;

  @Override
  public void run(String... args) throws Exception {
    seedEnvironments();
    seedTags();
  }

  public void seedEnvironments() {
    environmentService.create(new CreateEnvironment("OCA 21"));
    environmentService.create(new CreateEnvironment("History"));
    environmentService.create(new CreateEnvironment("Lyricology"));
  }

  private void seedTags() {
    List<Environment> environments = environmentService.getAll();
    if (environments.isEmpty()) return;

    tagService.createTag(new CreateTag(environments.get(0).getId(), "Primitive Datatypes"));
    tagService.createTag(new CreateTag(environments.get(1).getId(), "Ancient Egypt"));
    tagService.createTag(new CreateTag(environments.get(2).getId(), "Punchlines"));
  }
}
