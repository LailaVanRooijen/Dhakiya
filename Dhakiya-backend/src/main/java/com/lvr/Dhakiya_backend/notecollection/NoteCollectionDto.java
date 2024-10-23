package com.lvr.Dhakiya_backend.notecollection;

import com.lvr.Dhakiya_backend.notecollection.notes.Note;

public record NoteCollectionDto(String title) {
  public static NoteCollectionDto from(Note note) {
    return new NoteCollectionDto(note.getTitle());
  }

  public static NoteCollection to(NoteCollectionDto noteCollectionDto) {
    return new NoteCollection(noteCollectionDto.title());
  }
}
