import "./FormTextField.css";

export const FormTextField: React.FC<FormTextFieldProps> = ({
  label,
  type,
}) => {
  return (
    <div className="text-field-wrapper">
      <label htmlFor={label}>{label}</label>
      <input type={type || "text"} id={label} name={label} />
    </div>
  );
};

interface FormTextFieldProps {
  label: string;
  type?: string;
}
