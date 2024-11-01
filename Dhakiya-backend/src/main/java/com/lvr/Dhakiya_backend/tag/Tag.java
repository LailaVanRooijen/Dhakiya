package com.lvr.Dhakiya_backend.tag;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
