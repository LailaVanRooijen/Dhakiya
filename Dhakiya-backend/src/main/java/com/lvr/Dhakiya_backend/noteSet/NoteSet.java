package com.lvr.Dhakiya_backend.noteSet;

import com.lvr.Dhakiya_backend.noteSet.notes.Note;
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
public class NoteSet {
  @GeneratedValue @Id Long id;

  @OneToMany private List<Note> notes = new ArrayList<>();

  public void addNote(Note note) {
    this.notes.add(note);
  }

  public void deleteNote(Note notes) {
    this.notes.remove(notes);
  }
}
