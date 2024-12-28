import { useNavigate } from "react-router-dom";
import "./NavigationSection.css";

export const NavigationSection: React.FC<NavigationSectionProps> = ({
  label,
  content,
  navToLink,
}) => {
  const navigate = useNavigate();
  return (
    <div
      className={"navigation-section-container"}
      onClick={() => navigate(navToLink)}
    >
      <h3>{label}</h3>
      {content && <div>{content}</div>}
    </div>
  );
};

interface NavigationSectionProps {
  label: string;
  content?: string;
  navToLink: string;
}
