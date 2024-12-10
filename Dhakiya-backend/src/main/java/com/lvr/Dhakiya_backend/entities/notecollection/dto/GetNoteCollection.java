package com.lvr.Dhakiya_backend.entities.notecollection.dto;

import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;

public record GetNoteCollection(Long noteCollectionId, Long environmentId) {
  public static GetNoteCollection from(NoteCollection noteCollection) {
    return new GetNoteCollection(noteCollection.getId(), noteCollection.getEnvironment().getId());
  }
}
