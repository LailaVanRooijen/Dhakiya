package com.lvr.Dhakiya_backend.flashcards;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Flashcard {
    @Id @GeneratedValue Long id;

    private String frontContent;
    private String backContent;

    @ManyToOne Deck deck;
}
