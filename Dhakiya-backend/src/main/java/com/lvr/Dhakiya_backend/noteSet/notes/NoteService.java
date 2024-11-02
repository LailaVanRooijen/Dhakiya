package com.lvr.Dhakiya_backend.noteSet.notes;

import com.lvr.Dhakiya_backend.noteSet.NoteSetService;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import com.lvr.Dhakiya_backend.tag.Tag;
import com.lvr.Dhakiya_backend.tag.TagRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;
  private final NoteSetService noteSetService;
  private final TagRepository tagRepository;

  public Note create(NoteDto dto) {
    Note note = NoteDto.to(dto);
    if (dto.tagIds() != null) {
      Set<Tag> tags = convertToTags(dto.tagIds());
      if (tags.size() < dto.tagIds().size()) {
        throw new BadRequestException("duplicate tag id's");
      }
      note.addTagList(tags);
    }
    noteRepository.save(note);
    noteSetService.addNote(dto.noteSetId(), note);
    return note;
  }

  public Set<Tag> convertToTags(List<Long> ids) {
    return ids.stream()
        .map(
            id ->
                tagRepository
                    .findById(id)
                    .orElseThrow(() -> new BadRequestException("1 or more invalid tag ids")))
        .collect(Collectors.toSet());
  }

  public List<Note> getAll() {
    return noteRepository.findAll();
  }

  public Note getById(Long id) {
    return noteRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public void delete(Long id) {
    noteRepository.findById(id).orElseThrow(NotFoundException::new);
    noteRepository.deleteById(id);
  }

  public Note update(Long id, NoteDto patch) {
    Note note = noteRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.title() != null) note.setTitle(patch.title());
    if (patch.content() != null) note.setContent(patch.content());
    if (patch.tagIds() != null) {
      Set<Tag> tags = convertToTags(patch.tagIds());
      if (tags.size() < patch.tagIds().size()) {
        throw new BadRequestException("duplicate tag id's");
      }
      note.addTagList(tags);
    }
    return noteRepository.save(note);
  }
}
