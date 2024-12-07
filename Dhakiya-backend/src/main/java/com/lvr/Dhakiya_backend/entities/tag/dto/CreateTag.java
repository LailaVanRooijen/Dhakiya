package com.lvr.Dhakiya_backend.entities.tag.dto;

import com.lvr.Dhakiya_backend.entities.tag.Tag;

public record CreateTag(Long environmentId, String tag) {
  public static Tag to(CreateTag dto) {
    return new Tag(dto.tag());
  }
}
