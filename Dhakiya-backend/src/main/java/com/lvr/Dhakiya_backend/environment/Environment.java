package com.lvr.Dhakiya_backend.environment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Environment {
    @GeneratedValue @Id Long id;

    private String title;
}
