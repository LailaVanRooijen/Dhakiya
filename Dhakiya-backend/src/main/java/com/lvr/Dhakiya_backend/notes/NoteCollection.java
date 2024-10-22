package com.lvr.Dhakiya_backend.notes;

import com.lvr.Dhakiya_backend.environment.Environment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class NoteCollection {
    @GeneratedValue @Id Long id;

    private String title;

    @OneToOne
    private Environment environment;

    @OneToMany(mappedBy = "noteCollection")
    private List<Note> notes = new ArrayList<>();
}
