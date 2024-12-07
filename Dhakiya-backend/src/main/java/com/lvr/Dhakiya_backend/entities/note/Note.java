package com.lvr.Dhakiya_backend.entities.note;

import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Note {
  @GeneratedValue @Id Long id;

  @Setter private String title;
  // TODO set die field zodat die groter kan zijn dan x aantal chars
  @Setter private String content;

  @Setter @ManyToOne private NoteCollection noteCollection;

  @Setter @ManyToOne private Tag tag;

  public Note(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
