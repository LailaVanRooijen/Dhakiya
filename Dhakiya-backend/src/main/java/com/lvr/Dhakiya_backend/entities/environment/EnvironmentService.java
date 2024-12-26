package com.lvr.Dhakiya_backend.entities.environment;

import com.lvr.Dhakiya_backend.entities.environment.dto.PatchEnvironment;
import com.lvr.Dhakiya_backend.entities.environment.dto.PostEnvironment;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollectionRepository;
import com.lvr.Dhakiya_backend.entities.progressreport.ProgressReport;
import com.lvr.Dhakiya_backend.entities.progressreport.ProgressReportRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
  private final EnvironmentRepository environmentRepository;
  private final TagRepository tagRepository;
  private final ProgressReportRepository progressReportRepository;
  private final NoteCollectionRepository noteCollectionRepository;

  public Environment create(PostEnvironment dto) {
    Environment newEnvironment = PostEnvironment.to(dto);
    environmentRepository.save(newEnvironment);

    ProgressReport progressReport = new ProgressReport();
    progressReport.setEnvironment(newEnvironment);
    progressReportRepository.save(progressReport);

    NoteCollection noteCollection = new NoteCollection();
    noteCollection.setEnvironment(newEnvironment);
    noteCollectionRepository.save(noteCollection);

    return newEnvironment;
  }

  public List<Environment> getAll() {
    return environmentRepository.findAll();
  }

  public Environment getById(Long id) {
    return environmentRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public Environment patch(Long id, PatchEnvironment patch) {
    Environment patchedEnvironment =
        environmentRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.title() != null) {
      patchedEnvironment.setTitle(patch.title());
    }
    return environmentRepository.save(patchedEnvironment);
  }

  public void delete(Long id) {
    Environment environment =
        environmentRepository.findById(id).orElseThrow(NotFoundException::new);
    List<Tag> relatedTags = tagRepository.findByEnvironment(environment);
    if (!relatedTags.isEmpty()) {
      tagRepository.deleteAll(relatedTags);
    }
    environmentRepository.deleteById(id);
  }
}
