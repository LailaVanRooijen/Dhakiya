import { createContext, useMemo, useState } from "react";

const NoteContext = createContext({
    getId: () => {},
    updateId: (id: number) => {},
});

export const NoteProvider: React.FC<NoteContextProviderProps> = ({
    children,
}) => {
    const [id, setId] = useState<number | null>(null);
    const updateId = (newId: number) => {
        setId(newId);
    };

    const getId = () => {
        return id;
    };
    const value = useMemo(() => ({ getId, updateId }), [id]);
    return (
        <NoteContext.Provider value={value}>{children}</NoteContext.Provider>
    );
};

interface NoteContextProviderProps {
    children: React.ReactNode;
}
