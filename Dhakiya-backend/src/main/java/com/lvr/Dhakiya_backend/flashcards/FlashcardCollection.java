package com.lvr.Dhakiya_backend.flashcards;

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

    @OneToMany
    private List<Flashcard> flashcard = new ArrayList<>();
}
