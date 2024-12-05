package com.lvr.Dhakiya_backend.entities.notecollection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class NoteCollection {
  @GeneratedValue @Id private Long id;
}
