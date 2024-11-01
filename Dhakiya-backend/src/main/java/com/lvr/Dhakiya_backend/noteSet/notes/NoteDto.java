package com.lvr.Dhakiya_backend.noteSet.notes;

public record NoteDto(Long noteSetId, String title, String content, String label) {
  public static NoteDto from(Note note, Long noteSetId) {
    return new NoteDto(noteSetId, note.getTitle(), note.getContent(), note.getLabel());
  }

  public static Note to(NoteDto noteDto) {
    return new Note(noteDto.title(), noteDto.content(), noteDto.label);
  }
}
