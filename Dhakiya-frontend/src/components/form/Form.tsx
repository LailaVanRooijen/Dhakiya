import { Button, BUTTON_STYLE } from ".././../components/button/Button";
import "./Form.css";

export const Form: React.FC<FormProps> = ({
  children,
  handleSubmit,
  formLabel,
}) => {
  const getFormValues = (e: React.FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget as HTMLFormElement);
    const formValues = Object.fromEntries(formData);
    handleSubmit(formValues);
  };
  return (
    <div className="form-wrapper">
      <h2>{formLabel}</h2>
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
  formLabel: string;
  handleSubmit: (body: any) => void;
}
