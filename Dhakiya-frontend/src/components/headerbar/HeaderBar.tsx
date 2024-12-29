import { Button, BUTTON_STYLE } from "../../components/button/Button";
import "./HeaderBar.css";

export const HeaderBar: React.FC<HeaderBarProps> = ({ label, btnLabel }) => {
  return (
    <div className="header-bar-wrapper">
      <div>{label}</div>
      {btnLabel && (
        <Button content={btnLabel} btnStyle={BUTTON_STYLE.ENCOURAGE} />
      )}
    </div>
  );
};

interface HeaderBarProps {
  label: string;
  btnLabel?: string;
}
