package com.lvr.Dhakiya_backend.entities.notes;

import com.lvr.Dhakiya_backend.entities.tag.Tag;
import java.util.List;

public record NoteDto(Long noteSetId, String title, String content, List<Long> tagIds) {
  public static NoteDto from(Note note, Long noteSetId) {
    List<Long> tagIdList = note.getTags().stream().map(Tag::getId).toList();
    return new NoteDto(noteSetId, note.getTitle(), note.getContent(), tagIdList);
  }

  public static Note to(NoteDto dto) {
    return new Note(dto.title(), dto.content);
  }
}
