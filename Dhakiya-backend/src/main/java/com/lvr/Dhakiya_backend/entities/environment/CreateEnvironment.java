package com.lvr.Dhakiya_backend.entities.environment;

public record CreateEnvironment(String title) {
  public static Environment to(CreateEnvironment dto) {
    return new Environment(dto.title);
  }
}
