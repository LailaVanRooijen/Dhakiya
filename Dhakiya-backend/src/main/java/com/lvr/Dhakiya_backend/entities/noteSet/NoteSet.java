package com.lvr.Dhakiya_backend.entities.noteSet;

import com.lvr.Dhakiya_backend.entities.notes.Note;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class NoteSet {
  @GeneratedValue @Id private Long id;

  @OneToMany(fetch = FetchType.EAGER)
  private List<Note> notes = new ArrayList<>();

  public void addNotes(List<Note> notes) {
    this.notes.addAll(notes);
  }

  // overloaded
  public void addNotes(Note note) {
    this.notes.add(note);
  }

  public void removeNotes(List<Note> notes) {
    this.notes.removeAll(notes);
  }

  // overloaded
  public void removeNotes(Note note) {
    this.notes.remove(note);
  }
}
