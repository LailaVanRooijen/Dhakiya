package com.lvr.Dhakiya_backend.entities.notes;

import com.lvr.Dhakiya_backend.entities.noteSet.NoteSetService;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagHelperMethods;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;
  private final NoteSetService noteSetService;
  private final TagHelperMethods tagHelper;
  private final NoteHelperService noteHelper;

  public Note create(NoteDto dto) {
    Note note = NoteDto.to(dto);
    if (dto.tagIds() != null) {
      Set<Tag> tags = tagHelper.convertToTags(dto.tagIds());
      if (tags.size() < dto.tagIds().size()) {
        throw new BadRequestException("duplicate tag id's");
      }
      note.addAllTags(tags);
    }
    noteRepository.save(note);
    noteSetService.addNote(dto.noteSetId(), note);
    return note;
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

  public Note update(Long id, NotePatch patch) {
    if (patch.noteSetId() != null) {
      throw new BadRequestException("Not allowed to set note to another note set");
    }
    Note note = noteRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.title() != null) note.setTitle(patch.title());
    if (patch.content() != null) note.setContent(patch.content());
    if (patch.addTags() != null) {
      Set<Tag> tags = tagHelper.convertToTags(patch.addTags());
      if (tags.size() < patch.addTags().size()) {
        throw new BadRequestException("Duplicate tags provided");
      }
      note.addAllTags(tags);
    }
    if (patch.deleteTags() != null) {
      Set<Tag> tags = tagHelper.convertToTags(patch.deleteTags());
      if (tags.size() < patch.deleteTags().size()) {
        throw new BadRequestException("Duplicate tags tags provided");
      }
      note.removeAllTags(tags);
    }
    return noteRepository.save(note);
  }
}
