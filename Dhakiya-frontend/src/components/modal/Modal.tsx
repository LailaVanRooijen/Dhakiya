import { BUTTON_STYLE, Button } from "../../components/button/Button";
import "./Modal.css";
export const Modal: React.FC<ModalProps> = ({ question, getAnswer }) => {
  return (
    <div className="modal-backdrop" onClick={() => getAnswer(false)}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <p className="modal-header">{question}</p>
        <div>
          <Button
            content={"yes"}
            btnStyle={BUTTON_STYLE.ENCOURAGE}
            handleClick={() => getAnswer(true)}
          />
          <Button
            content={"No"}
            btnStyle={BUTTON_STYLE.ALERT}
            handleClick={() => getAnswer(false)}
          />
        </div>
      </div>
    </div>
  );
};

interface ModalProps {
  question: string;
  getAnswer: (answer: boolean) => void;
}
