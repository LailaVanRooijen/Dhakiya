package com.lvr.Dhakiya_backend.entities.tag;

public record TagDto(String name) {
  public static Tag to(TagDto dto) {
    return new Tag(dto.name);
  }
}
