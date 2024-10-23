package com.lvr.Dhakiya_backend.flashcardcollection;

import com.lvr.Dhakiya_backend.flashcardcollection.flashcard.Flashcard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class FlashcardCollection {
    @GeneratedValue @Id Long id;

    private String title;

    @OneToMany
    private List<Flashcard> flashcard = new ArrayList<>();

    public FlashcardCollection(String title) {
        this.title = title;
    }
}
