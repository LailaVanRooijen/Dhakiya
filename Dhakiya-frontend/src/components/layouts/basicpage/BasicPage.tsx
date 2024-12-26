import { useStyle } from "../../../context/ThemeContext";
import { ToolBar } from "../../toolbar/ToolBar";
import "./basicPage.css";

export const BasicPage: React.FC<PageProps> = ({ children }) => {
  const { theme } = useStyle();

  return (
    <div className={"page-layout"}>
      <ToolBar />
      <div className={"page-content-box"}>{children}</div>
    </div>
  );
};

/* interfaces */
interface PageProps {
  children: React.ReactNode;
}
