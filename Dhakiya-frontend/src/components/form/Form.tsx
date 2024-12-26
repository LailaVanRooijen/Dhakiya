import "./Form.css";

export const Form: React.FC<FormProps> = ({ children }) => {
  return (
    <div className="form-wrapper">
      <form>{children}</form>
      <button type="submit">submit</button>
    </div>
  );
};

interface FormProps {
  children: React.ReactNode;
}
