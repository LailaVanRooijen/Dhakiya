import { GetTagResponse } from "types/api";

export const useDropdownReformatter = (objArray: any, keyName: string) => {
  return objArray.map((obj: any) => ({ id: obj.id, value: obj[keyName] }));
};

export const useTagIdExtractor = (tags: GetTagResponse[]) => {
  if (tags == undefined || tags == undefined) return [];
  const tagIds = tags.map((tag) => {
    tag;
  });
  return tagIds;
};
