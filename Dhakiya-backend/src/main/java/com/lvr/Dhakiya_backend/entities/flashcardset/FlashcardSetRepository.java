package com.lvr.Dhakiya_backend.entities.flashcardset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardSetRepository extends JpaRepository<FlashcardSet, Long> {}
