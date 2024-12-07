package com.lvr.Dhakiya_backend.entities.environment.dto;

import com.lvr.Dhakiya_backend.entities.environment.Environment;

public record CreateEnvironment(String title) {
  public static Environment to(CreateEnvironment dto) {
    return new Environment(dto.title);
  }
}
