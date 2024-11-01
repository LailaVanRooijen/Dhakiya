package com.lvr.Dhakiya_backend.environment;

import com.lvr.Dhakiya_backend.noteSet.NoteSet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Environment {
  @GeneratedValue @Id Long id;

  private String title;

  @OneToOne(cascade = CascadeType.REMOVE)
  private NoteSet noteSet = new NoteSet();

  public Environment(String title) {
    this.title = title;
  }
}
