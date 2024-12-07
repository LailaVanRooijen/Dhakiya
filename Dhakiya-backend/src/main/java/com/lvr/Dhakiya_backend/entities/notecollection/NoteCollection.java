package com.lvr.Dhakiya_backend.entities.notecollection;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class NoteCollection {
  @GeneratedValue @Id private Long id;

  @Setter @OneToOne private Environment environment;
}
