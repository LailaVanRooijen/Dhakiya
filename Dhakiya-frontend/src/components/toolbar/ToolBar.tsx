import { FaHome } from "react-icons/fa";
import { ThemeSwitch } from "./components/themeswitch/ThemeSwitch";
import { ToolBarLink } from "./components/toolbarlink/ToolBarLink";
import "./ToolBar.css";

export const ToolBar = () => {
  return (
    <nav className={`tool-bar-wrapper`}>
      <ul className={`tool-bar`}>
        <ThemeSwitch />
        <ToolBarLink path="/" label={<FaHome />} />
      </ul>
    </nav>
  );
};
