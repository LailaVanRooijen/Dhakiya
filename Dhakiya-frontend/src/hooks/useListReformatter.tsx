import { I_Tag } from "types/api";

export const useDropdownReformatter = (objArray: any, keyName: string) => {
    return objArray.map((obj: any) => ({ id: obj.id, value: obj[keyName] }));
};

export const useTagIdExtractor = (tags: I_Tag[]) => {
    const tagIds = tags.map((tag) => {
        tag.id;
    });
    console.log("tagids: ", tagIds); //TODO fix me i bug
    return tagIds;
};
