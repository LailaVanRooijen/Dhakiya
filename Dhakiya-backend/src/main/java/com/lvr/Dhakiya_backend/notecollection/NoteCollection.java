package com.lvr.Dhakiya_backend.notecollection;

import com.lvr.Dhakiya_backend.environment.Environment;
import com.lvr.Dhakiya_backend.notecollection.notes.Note;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class NoteCollection {
  @GeneratedValue @Id Long id;

  private String title;

  @OneToOne private Environment environment;

  @OneToMany(mappedBy = "noteCollection")
  private List<Note> notes = new ArrayList<>();

  public NoteCollection(String title) {
    this.title = title;
  }

  public void addNote(Note note) {
    this.notes.add(note);
  }

  public void deleteNote(Note notes) {
    this.notes.remove(notes);
  }
}
