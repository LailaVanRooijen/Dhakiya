import "./FormInputField.css";

export const FormInputField: React.FC<FormInputFieldProps> = ({
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

interface FormInputFieldProps {
  label: string;
  type?: string;
}
