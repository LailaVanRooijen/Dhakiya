package com.lvr.Dhakiya_backend.entities.tag.dto;

import com.lvr.Dhakiya_backend.entities.enums.Status;
import com.lvr.Dhakiya_backend.entities.tag.Tag;

public record DetailedTag(Long id, String tag, Double percentage, Status status) {
  public static DetailedTag from(Tag tag) {
    return new DetailedTag(tag.getId(), tag.getTitle(), tag.getPercentage(), tag.getStatus());
  }
}
