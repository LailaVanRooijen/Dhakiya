package com.lvr.Dhakiya_backend.entities.environment.flashcard.dto;

import com.lvr.Dhakiya_backend.entities.environment.enums.Status;
import com.lvr.Dhakiya_backend.entities.environment.flashcard.Flashcard;
import com.lvr.Dhakiya_backend.entities.tag.dto.GetTag;

public record GetFlashcard(Long id, String title, String content, GetTag tag, Status status) {
  public static GetFlashcard from(Flashcard flashcard) {
    GetTag tag = null;
    if (flashcard.getTag() != null) {
      tag = GetTag.from(flashcard.getTag());
    }
    return new GetFlashcard(
        flashcard.getId(),
        flashcard.getTitle(),
        flashcard.getContent(),
        tag,
        flashcard.getStatus());
  }
}
