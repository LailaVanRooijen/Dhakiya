package com.lvr.Dhakiya_backend.environment;

import com.lvr.Dhakiya_backend.notecollection.NoteCollection;
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

  @OneToOne private NoteCollection noteCollection = new NoteCollection();

  public Environment(String title) {
    this.title = title;
  }

  public Environment(String title, NoteCollection noteCollections) {
    this.title = title;
    this.noteCollection = noteCollections;
  }
}
