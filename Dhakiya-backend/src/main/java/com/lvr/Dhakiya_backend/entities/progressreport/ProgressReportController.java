package com.lvr.Dhakiya_backend.entities.progressreport;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.progressreport.progressReportDto.GetProgressReport;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.PROGRESS_REPORTS)
@RequiredArgsConstructor
public class ProgressReportController {
  private final ProgressReportService progressReportService;

  // TODO temporary for testing, delete me later!
  @GetMapping
  public ResponseEntity<List<GetProgressReport>> getAll() {
    List<GetProgressReport> progressReports = progressReportService.getAll();
    if (progressReports.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(progressReports);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetProgressReport> getById(@PathVariable Long id) {
    GetProgressReport progressReport = progressReportService.getById(id);
    return ResponseEntity.ok(progressReport);
  }
}
