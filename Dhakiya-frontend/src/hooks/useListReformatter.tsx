export const useDropdownReformatter = (objArray: any, keyName: string) => {
    return objArray.map((obj: any) => ({ id: obj.id, value: obj[keyName] }));
};

export const useIdExtractor = (objArray: any) => {
    return objArray.map((obj: any) => obj.id);
};
