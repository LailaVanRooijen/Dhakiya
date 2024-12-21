package com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnsweredQuestionRepository extends JpaRepository<AnsweredQuestion, Long> {}
