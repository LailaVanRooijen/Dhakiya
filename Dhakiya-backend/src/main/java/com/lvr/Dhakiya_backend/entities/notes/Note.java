package com.lvr.Dhakiya_backend.entities.notes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lvr.Dhakiya_backend.entities.noteSet.NoteSet;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Note {
  @GeneratedValue @Id private Long id;

  @Setter private String title;
  @Setter private String content;
  @Setter @ManyToMany private Set<Tag> tags = new HashSet<>();
  @JsonIgnore @ManyToOne private NoteSet noteSet;

  public Note(String title, String content, Set<Tag> tags) {
    this.title = title;
    this.content = content;
    this.tags.addAll(tags);
  }

  public Note(String title, String content, NoteSet noteSet) {
    this.title = title;
    this.content = content;
    this.noteSet = noteSet;
  }
}
