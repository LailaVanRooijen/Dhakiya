package com.lvr.Dhakiya_backend.entities.environment;

import com.lvr.Dhakiya_backend.entities.environment.environmentDto.CreateEnvironment;
import com.lvr.Dhakiya_backend.entities.environment.environmentDto.PatchEnvironment;
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

  public Environment create(CreateEnvironment dto) {
    Environment newEnvironment = CreateEnvironment.to(dto);
    environmentRepository.save(newEnvironment);

    ProgressReport progressReport = new ProgressReport();
    progressReport.setEnvironment(newEnvironment);
    progressReportRepository.save(progressReport);

    return newEnvironment;
  }

  public List<Environment> getAll() {
    return environmentRepository.findAll();
  }

  public Environment getById(Long id) {
    return environmentRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public Environment patchEnvironment(Long id, PatchEnvironment patch) {
    Environment patchedEnvironment =
        environmentRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.title() != null) {
      patchedEnvironment.setTitle(patch.title());
    }
    return environmentRepository.save(patchedEnvironment);
  }

  public void deleteEnvironment(Long id) {
    Environment environment =
        environmentRepository.findById(id).orElseThrow(NotFoundException::new);
    List<Tag> relatedTags = tagRepository.findByEnvironment(environment);
    if (!relatedTags.isEmpty()) {
      tagRepository.deleteAll(relatedTags);
    }
    environmentRepository.deleteById(id);
  }
}
