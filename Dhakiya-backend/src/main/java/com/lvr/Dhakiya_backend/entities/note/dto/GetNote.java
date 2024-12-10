package com.lvr.Dhakiya_backend.entities.note.dto;

import com.lvr.Dhakiya_backend.entities.note.Note;
import com.lvr.Dhakiya_backend.entities.tag.dto.GetTag;

public record GetNote(
    Long id,Long noteCollectionId,Long environmentId, String title, String content, GetTag tag) {
  public static GetNote from(Note note) {
    return new GetNote(
        note.getId(),
        note.getNoteCollection().getId(),
        note.getNoteCollection().getEnvironment().getId(),
        note.getTitle(),
        note.getContent(),
        GetTag.from(note.getTag()));
  }
}
