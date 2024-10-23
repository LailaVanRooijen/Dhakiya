package com.lvr.Dhakiya_backend.environment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
    private final EnvironmentRepository environmentRepository;
    public Environment create(CreateEnvironmentDto environment) {
    return environmentRepository.save(CreateEnvironmentDto.to(environment));
    }

    public List<Environment> getAll() {
        return environmentRepository.findAll();
    }

    public Environment getById(Long id) {
        return environmentRepository.findById(id).orElseThrow();
    }

    public void delete(Long id){
        environmentRepository.findById(id).orElseThrow();
        environmentRepository.deleteById(id);
    }
}
