package com.lvr.Dhakiya_backend.entities;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class ProgressReport {
  @GeneratedValue @Id Long id;

  @Setter @OneToMany List<Tag> tags;
  @Setter @OneToOne Environment environment;
}
