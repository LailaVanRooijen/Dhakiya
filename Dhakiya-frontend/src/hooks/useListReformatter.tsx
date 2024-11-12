export const useDropdownReformatter = (objArray: any, keyName: string) => {
    return objArray.map((obj: any) => ({ id: obj.id, value: obj[keyName] }));
};
