package com.lvr.Dhakiya_backend.flashcards;

import com.lvr.Dhakiya_backend.flashcards.flashcardcollection.FlashcardCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Flashcard {
    @Id @GeneratedValue Long id;

    private String frontContent;
    private String backContent;
    private List<String> tags = new ArrayList<>();

    @ManyToOne
    FlashcardCollection flashcardCollection;

    public Flashcard(String frontContent, String backContent) {
        this.frontContent = frontContent;
        this.backContent = backContent;
    }
}
