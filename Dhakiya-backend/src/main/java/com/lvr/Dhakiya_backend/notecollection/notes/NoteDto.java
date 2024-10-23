package com.lvr.Dhakiya_backend.notecollection.notes;

public record NoteDto(String title, String content, String label) {
  public static NoteDto from(Note note) {
    return new NoteDto(note.getTitle(), note.getContent(), note.getLabel());
  }

  public static Note to(NoteDto noteDto) {
    return new Note(noteDto.title(), noteDto.content(), noteDto.label);
  }
}
