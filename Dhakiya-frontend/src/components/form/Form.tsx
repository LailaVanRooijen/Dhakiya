import { Button, BUTTON_STYLE } from ".././../components/button/Button";
import "./Form.css";

export const Form: React.FC<FormProps> = ({ children, handleSubmit }) => {
  const getFormValues = (e: React.FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget as HTMLFormElement);
    const formValues = Object.fromEntries(formData);
    handleSubmit(formValues);
  };
  return (
    <div className="form-wrapper">
      <form
        onSubmit={(e) => {
          getFormValues(e);
        }}
      >
        {children}
        <Button content={"submit"} btnStyle={BUTTON_STYLE.ENCOURAGE} />
      </form>
    </div>
  );
};

interface FormProps {
  children: React.ReactNode;
  handleSubmit: (body: any) => void;
}
