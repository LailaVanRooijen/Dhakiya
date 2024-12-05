package com.lvr.Dhakiya_backend;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
  private final EnvironmentRepository environmentRepository;
  private final TagRepository tagRepository;

  @Override
  public void run(String... args) throws Exception {
    seedEnvironments();
    seedTags();
  }

  public void seedEnvironments() {
    environmentRepository.save(new Environment("OCA 21"));
    environmentRepository.save(new Environment("History"));
    environmentRepository.save(new Environment("Philosophy"));
  }

  private void seedTags() {
    List<Environment> environments = environmentRepository.findAll();
    if (environments.isEmpty()) return;

    Tag tag1 = new Tag("Primitive Datatypes");
    tag1.setEnvironment(environments.get(0));
    tagRepository.save(tag1);

    Tag tag2 = new Tag("Ancient Egypt");
    tag2.setEnvironment(environments.get(1));
    tagRepository.save(tag2);

    Tag tag3 = new Tag("Al-Kindi");
    tag3.setEnvironment(environments.get(2));
    tagRepository.save(tag3);
  }
}
