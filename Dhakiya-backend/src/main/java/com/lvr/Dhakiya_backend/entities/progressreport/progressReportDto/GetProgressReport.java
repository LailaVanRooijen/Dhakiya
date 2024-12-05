package com.lvr.Dhakiya_backend.entities.progressreport.progressReportDto;

import com.lvr.Dhakiya_backend.entities.progressreport.ProgressReport;
import com.lvr.Dhakiya_backend.entities.tag.tagDto.DetailedTag;
import java.util.List;

public record GetProgressReport(Long id, Long environmentId, List<DetailedTag> tags) {
  public static GetProgressReport from(ProgressReport progressReport) {
    List<DetailedTag> detailedTags =
        progressReport.getTags().stream().map(tag -> DetailedTag.from(tag)).toList();
    return new GetProgressReport(
        progressReport.getId(), progressReport.getEnvironment().getId(), detailedTags);
  }
}
