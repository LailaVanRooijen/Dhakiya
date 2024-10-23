package com.lvr.Dhakiya_backend.environment;

import com.lvr.Dhakiya_backend.flashcards.flashcardcollection.FlashcardCollection;
import com.lvr.Dhakiya_backend.notes.notecollection.NoteCollection;
import com.lvr.Dhakiya_backend.quiz.QuizSet;
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
public class Environment {
    @GeneratedValue @Id Long id;

    private String title;

    @OneToOne
    private NoteCollection noteCollection;

    @OneToMany
    private List<FlashcardCollection> flashcardCollections = new ArrayList<>();

    @OneToMany
    private List<QuizSet> quizSets = new ArrayList<>();

    public Environment(String title) {
        this.title = title;
    }
}
