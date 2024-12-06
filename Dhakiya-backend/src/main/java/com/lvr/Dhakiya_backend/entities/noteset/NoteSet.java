package com.lvr.Dhakiya_backend.entities.noteset;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class NoteSet {
  @GeneratedValue @Id private Long id;

  @Setter @OneToOne private Environment environment;

  // @OneToMany List<Note> notes;
}
