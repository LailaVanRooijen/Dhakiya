package com.lvr.Dhakiya_backend.notes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Note {
    @GeneratedValue @Id Long id;

    private String title;
    private String content;
    private String label;

    @ManyToOne
    private NoteCollection noteCollection;
}
