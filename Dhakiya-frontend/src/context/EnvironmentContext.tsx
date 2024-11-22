import { createContext, useContext, useMemo } from "react";

const environmentData: any = {
    noteSet: null,
};

const EnvironmentContext = createContext({
    environmentData: environmentData,
    setNoteSet: (id: number) => {},
});

const setNoteSet = (id: number) => {
    console.log("from noteset setter: ", id);

    //TODO deze word niet aangeroepen, even uitvogelen waarom
    environmentData.noteSet = id;
};

export const EnvironmentProvider: React.FC<Props> = ({ children }) => {
    const value = useMemo(
        () => ({ environmentData, setNoteSet }),
        [environmentData]
    );

    return (
        <EnvironmentContext.Provider value={value}>
            {children}
        </EnvironmentContext.Provider>
    );
};

export const useEnvironmentCtx = () => useContext(EnvironmentContext);

interface Props {
    children: React.ReactNode;
}
