import "./dropdown.css";

export const Dropdown: React.FC<DropdownProps> = ({
    label,
    items,
    onSelect,
}) => {
    const handleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        onSelect("snoop");
    };

    return (
        <div className="basic-dropdown">
            <label className="basic-dropdown-label" htmlFor={label}>
                {label}
            </label>
            <select
                className="basic-dropdown-select"
                name={label}
                id="items"
                onChange={handleChange}
            >
                {items.map((item: any) => (
                    <option
                        className="basic-dropdown-option"
                        key={item.id}
                        value={item}
                    >
                        {item.value}
                    </option>
                ))}
            </select>
        </div>
    );
};

interface DropdownProps {
    label: string;
    items: DropdownItem[];
    onSelect: (id: number) => void;
}

interface DropdownItem {
    id: number;
    value: string;
}
