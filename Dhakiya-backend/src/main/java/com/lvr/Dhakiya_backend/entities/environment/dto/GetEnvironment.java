package com.lvr.Dhakiya_backend.entities.environment.dto;

import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.GetFlashcardDeck;
import com.lvr.Dhakiya_backend.entities.notecollection.dto.GetNoteCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.GetQuizCollection;

public record GetEnvironment(
    Long id,
    String title,
    GetNoteCollection noteCollection,
    GetFlashcardDeck flashcardDeck,
    GetQuizCollection quizCollection) {}
