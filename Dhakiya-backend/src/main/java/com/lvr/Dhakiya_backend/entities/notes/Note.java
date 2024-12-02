package com.lvr.Dhakiya_backend.entities.notes;

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
  private Long noteSetId;

  public Note(String title, String content, Set<Tag> tags) {
    this.title = title;
    this.content = content;
    this.tags.addAll(tags);
  }

  public Note(String title, String content, Long noteSetId) {
    this.title = title;
    this.content = content;
    this.noteSetId = noteSetId;
  }
}
