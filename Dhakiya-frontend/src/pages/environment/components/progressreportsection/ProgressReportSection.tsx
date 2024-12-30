import { useNavigate } from "react-router-dom";
import { GetProgressReportResponse } from "types/api";
import "./ProgressReportSection.css";

export const ProgressReportSection: React.FC<ProgressReportSectionProps> = ({
  progressReport,
}) => {
  const navigate = useNavigate();

  return (
    <div
      className="progress-report-section-wrapper"
      onClick={() => navigate(`/progress-report/${progressReport.id}`)}
    >
      <p className="progress-report-section-header">Progress Report</p>
      <div className="progress-report-strength">
        <p>Strength</p>
        {progressReport.strength ? (
          <p>{progressReport.strength.title}</p>
        ) : (
          <p>No data</p>
        )}
      </div>

      <div className="progress-report-weakness">
        <p>Weakness</p>
        {progressReport.weakness ? (
          <p>{progressReport.weakness.title}</p>
        ) : (
          <p>No Data</p>
        )}
      </div>
    </div>
  );
};

interface ProgressReportSectionProps {
  progressReport: GetProgressReportResponse;
}
