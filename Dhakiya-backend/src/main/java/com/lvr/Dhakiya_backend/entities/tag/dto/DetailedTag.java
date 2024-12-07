package com.lvr.Dhakiya_backend.entities.tag.dto;

import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagStatus;

public record DetailedTag(Long id, String tag, Double percentage, TagStatus status) {
  public static DetailedTag from(Tag tag) {
    return new DetailedTag(tag.getId(), tag.getTag(), tag.getPercentage(), tag.getStatus());
  }
}
