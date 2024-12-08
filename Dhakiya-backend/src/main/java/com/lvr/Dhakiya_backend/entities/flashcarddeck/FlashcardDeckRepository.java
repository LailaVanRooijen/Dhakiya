package com.lvr.Dhakiya_backend.entities.flashcarddeck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardDeckRepository extends JpaRepository<FlashcardDeck, Long> {}
