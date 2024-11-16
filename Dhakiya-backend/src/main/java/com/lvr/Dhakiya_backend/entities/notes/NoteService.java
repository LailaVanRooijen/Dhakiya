package com.lvr.Dhakiya_backend.entities.notes;

import com.lvr.Dhakiya_backend.entities.noteSet.NoteSet;
import com.lvr.Dhakiya_backend.entities.noteSet.NoteSetRepository;
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
  private final NoteSetRepository noteSetRepository;

  public Note create(NoteDto dto) {
    NoteSet noteSet =
        noteSetRepository.findById(dto.noteSetId()).orElseThrow(NotFoundException::new);
    Note note = NoteDto.to(dto, noteSet);
    if (dto.tagIds() != null) {
      note.setTags(tagHelper.convertToTags(dto.tagIds()));
    }
    noteRepository.save(note);
    noteSetService.addNote(dto.noteSetId(), note);
    return note;
  }

  public List<NoteDto> getAll() {
    return noteRepository.findAll().stream().map(NoteDto::from).toList();
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
    if (patch.tags() != null) {
      Set<Tag> tags = tagHelper.convertToTags(patch.tags());
      note.setTags(tags);
    }

    return noteRepository.save(note);
  }
}
