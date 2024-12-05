package com.lvr.Dhakiya_backend.entities.progressreport;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressReportRepository extends JpaRepository<ProgressReport, Long> {
  ProgressReport findByEnvironment(Environment environment);
}
