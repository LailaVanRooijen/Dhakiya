import { useEffect, useState } from "react";
import "./PopUpMessage.css";

export const PopUpMessage: React.FC<PopUpMessageProps> = ({ message }) => {
  const [isVisible, setIsVisible] = useState<boolean>(false);

  useEffect(() => {
    if (message) {
      setIsVisible(true);
      setTimeout(() => setIsVisible(false), 1000);
    }
  }, [message]);

  return (
    <>
      {isVisible && (
        <div className="pop-up-message-backdrop">
          <div className="pop-up-message">{message}</div>
        </div>
      )}
    </>
  );
};

interface PopUpMessageProps {
  message: string;
}
