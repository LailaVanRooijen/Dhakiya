package com.lvr.Dhakiya_backend.entities.environment;

public record EnvironmentDto(String title) {
  public static EnvironmentDto from(Environment environment) {
    return new EnvironmentDto(environment.getTitle());
  }

  public static Environment to(EnvironmentDto environmentDto) {
    return new Environment(environmentDto.title);
  }
}
