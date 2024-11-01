package com.lvr.Dhakiya_backend;

import com.lvr.Dhakiya_backend.environment.EnvironmentDto;
import com.lvr.Dhakiya_backend.environment.EnvironmentService;
import com.lvr.Dhakiya_backend.noteSet.notes.NoteDto;
import com.lvr.Dhakiya_backend.noteSet.notes.NoteService;
import com.lvr.Dhakiya_backend.tag.Tag;
import com.lvr.Dhakiya_backend.tag.TagDto;
import com.lvr.Dhakiya_backend.tag.TagService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
  private final EnvironmentService environmentService;
  private NoteService noteService;
  private TagService tagService;

  @Override
  public void run(String... args) throws Exception {
    seedEnvironments();
    seedNotes();
    seedTags();
  }

  public void seedEnvironments() {
    if (!environmentService.getAll().isEmpty()) return;
    ArrayList<String> subjects =
        new ArrayList<>(List.of("Math", "English", "History", "Psychology"));
    subjects.forEach(subject -> environmentService.create(new EnvironmentDto(subject)));
  }

  private void seedTags() {
    if (!tagService.getAll().isEmpty()) return;

    Tag tag = tagService.create(new TagDto("Chapter 1"));
    Tag tag1 = tagService.create(new TagDto("Chapter 2"));
    Tag tag2 = tagService.create(new TagDto("Chapter 3"));
  }

  public void seedNotes() {
    if (!noteService.getAll().isEmpty()) return;
    Long noteSetId = environmentService.getAll().get(0).getNoteSet().getId();
    noteService.create(
        new NoteDto(noteSetId, "Another test note", "this note also has text", null));
    noteService.create(new NoteDto(noteSetId + 1, "Test a note", "this note has text", null));
  }
}
