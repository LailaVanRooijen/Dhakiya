package com.lvr.Dhakiya_backend.noteSet.notes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
  @Setter private String label;

  // @ManyToMany private Tag tag;

  public Note(String title, String content, String label) {
    this.title = title;
    this.content = content;
    this.label = label;
  }
}
