package com.lvr.Dhakiya_backend.entities.tag;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Tag {
  @GeneratedValue @Id private Long id;

  @Setter private String name;

  public Tag(String name) {
    this.name = name;
  }
}
