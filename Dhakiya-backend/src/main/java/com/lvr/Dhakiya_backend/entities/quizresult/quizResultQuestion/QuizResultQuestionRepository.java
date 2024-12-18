package com.lvr.Dhakiya_backend.entities.quizresult.quizResultQuestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultQuestionRepository extends JpaRepository<QuizResultQuestion, Long> {}
