package com.lvr.Dhakiya_backend.flashcards;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@Entity
public class Deck {
    @GeneratedValue @Id Long id;

    @OneToMany
    ArrayList<Flashcard> flashcard;
}
