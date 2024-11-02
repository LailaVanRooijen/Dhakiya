package com.lvr.Dhakiya_backend.entities.flashcard;

import com.lvr.Dhakiya_backend.entities.tag.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Flashcard {
  @GeneratedValue @Id private Long id;

  @Setter private String frontContent;
  @Setter private String backContent;

  @Setter @ManyToMany private Set<Tag> flashcardTags = new HashSet<>();

  public Flashcard(String frontContent, String backContent, Set<Tag> flashcardTags) {
    this.frontContent = frontContent;
    this.backContent = backContent;
    this.flashcardTags = flashcardTags;
  }

  public Flashcard(String frontContent, String backContent) {
    this.frontContent = frontContent;
    this.backContent = backContent;
  }

  public void addTags(Set<Tag> tags) {
    this.flashcardTags.addAll(tags);
  }

  public void deleteTags(Set<Tag> tags) {
    this.flashcardTags.removeAll(tags);
  }
}
