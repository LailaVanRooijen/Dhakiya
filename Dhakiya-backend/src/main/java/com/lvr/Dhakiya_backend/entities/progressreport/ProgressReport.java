package com.lvr.Dhakiya_backend.entities.progressreport;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class ProgressReport {
  @GeneratedValue @Id Long id;
  @OneToOne @Setter private Environment environment;
}
