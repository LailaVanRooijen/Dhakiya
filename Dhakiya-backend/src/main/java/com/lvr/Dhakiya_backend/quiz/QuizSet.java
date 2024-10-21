package com.lvr.Dhakiya_backend.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class QuizSet {
    @GeneratedValue @Id Long id;
    
}
