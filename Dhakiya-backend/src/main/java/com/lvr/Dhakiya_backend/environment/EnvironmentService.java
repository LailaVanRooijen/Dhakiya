package com.lvr.Dhakiya_backend.environment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
    private final EnvironmentRepository environmentRepository;
    public Environment create(CreateEnvironmentDto environment) {
    return environmentRepository.save(new Environment(environment.title()));
    }

    public List<Environment> getAll() {
        return environmentRepository.findAll();
    }
}
