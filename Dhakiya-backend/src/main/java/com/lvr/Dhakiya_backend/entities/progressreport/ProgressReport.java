package com.lvr.Dhakiya_backend.entities.progressreport;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class ProgressReport {
  @GeneratedValue @Id Long id;
  @OneToMany private List<Tag> tags = new ArrayList<>();
  @OneToOne @Setter private Environment environment;

  public void addTags(List<Tag> tags) {
    this.tags = tags;
  }
}
