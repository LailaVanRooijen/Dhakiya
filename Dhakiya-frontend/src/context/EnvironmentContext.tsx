import { createContext, useContext, useEffect, useMemo, useState } from "react";


const EnvironmentContext = createContext({
    noteSet: null,
    updateNoteSet: (id: number) => {},
    resetEnvironmentData:()=>{}
});

export const EnvironmentProvider: React.FC<Props> = ({ children }) => {
    const [noteSet, setNoteSet] = useState<number | null>(null);

    useEffect(()=>{
        console.log("noteset change: ",noteSet)
    },[noteSet])

    const updateNoteSet = (id: number) => {
        console.log("environmentData from ctx: ", noteSet)
        setNoteSet(id); 
    };

    const resetEnvironmentData = ()=>{
        setNoteSet(null);
    }
    

    const value = useMemo(
        () => ({ noteSet, updateNoteSet,resetEnvironmentData }),
        [noteSet]
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
