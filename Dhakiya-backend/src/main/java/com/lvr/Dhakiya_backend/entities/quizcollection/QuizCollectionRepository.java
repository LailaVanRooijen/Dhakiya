package com.lvr.Dhakiya_backend.entities.quizcollection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCollectionRepository extends JpaRepository<QuizCollection, Long> {}
