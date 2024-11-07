package com.lvr.Dhakiya_backend.entities.environment;

public record EnvironmentMinimalDto(Long id, String title) {
  public static EnvironmentMinimalDto from(Environment environment) {
    return new EnvironmentMinimalDto(environment.getId(), environment.getTitle());
  }
}
