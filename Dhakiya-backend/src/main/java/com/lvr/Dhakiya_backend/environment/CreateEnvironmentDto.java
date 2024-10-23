package com.lvr.Dhakiya_backend.environment;

public record CreateEnvironmentDto(String title) {
    public static CreateEnvironmentDto from(Environment environment){
        return new CreateEnvironmentDto(environment.getTitle());
    }

    public static Environment to(CreateEnvironmentDto environmentDto){
        return new Environment(environmentDto.title);
    }
}
