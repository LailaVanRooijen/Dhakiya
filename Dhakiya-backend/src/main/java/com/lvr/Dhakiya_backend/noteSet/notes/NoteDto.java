package com.lvr.Dhakiya_backend.noteSet.notes;

import com.lvr.Dhakiya_backend.tag.Tag;
import java.util.List;

public record NoteDto(Long noteSetId, String title, String content, List<Tag> tags) {
  public static NoteDto from(Note note, Long noteSetId) {
    return new NoteDto(noteSetId, note.getTitle(), note.getContent(), note.getTags());
  }

  public static Note to(NoteDto noteDto) {
    if (noteDto.tags == null) {
      return new Note(noteDto.title(), noteDto.content);
    } else {
      return new Note(noteDto.title(), noteDto.content(), noteDto.tags);
    }
  }
}
