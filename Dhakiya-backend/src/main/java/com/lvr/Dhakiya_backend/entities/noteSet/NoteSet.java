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

  public void addNote(Note note) {
    this.notes.add(note);
  }

  public void removeAllNotes(List<Note> notes) {
    this.notes.removeAll(notes);
  }
}
