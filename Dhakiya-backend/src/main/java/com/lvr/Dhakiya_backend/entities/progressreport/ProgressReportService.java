package com.lvr.Dhakiya_backend.entities.progressreport;

import com.lvr.Dhakiya_backend.entities.progressreport.progressReportDto.GetProgressReport;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressReportService {
  private final ProgressReportRepository progressReportRepository;
  private final TagRepository tagRepository;

  public List<GetProgressReport> getAll() {
    List<ProgressReport> progressReports = progressReportRepository.findAll();
    progressReports.forEach(progressReport -> syncProgressReport(progressReport));
    return progressReports.stream()
        .map(progressReport -> GetProgressReport.from(progressReport))
        .toList();
  }

  public void syncProgressReport(ProgressReport progressReport) {
    List<Tag> tags = tagRepository.findByEnvironment(progressReport.getEnvironment());
    progressReport.addTags(tags);
    progressReportRepository.save(progressReport);
  }

  public GetProgressReport getById(Long id) {
    ProgressReport progressReport =
        progressReportRepository.findById(id).orElseThrow(NotFoundException::new);
    syncProgressReport(progressReport);
    return GetProgressReport.from(progressReport);
  }
}
