package com.lvr.Dhakiya_backend.noteSet;

import com.lvr.Dhakiya_backend.noteSet.notes.Note;
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
}
