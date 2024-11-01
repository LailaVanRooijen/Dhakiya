package com.lvr.Dhakiya_backend.noteSet.notes;

import com.lvr.Dhakiya_backend.tag.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
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
  @ManyToMany private List<Tag> tags = new ArrayList<>();

  public Note(String title, String content, List<Tag> tags) {
    this.title = title;
    this.content = content;
    this.tags.addAll(tags);
  }

  public Note(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public void addTag(Tag tag) {
    this.tags.add(tag);
  }

  public void addTagList(List<Tag> tags) {
    this.tags.addAll(tags);
  }
}
