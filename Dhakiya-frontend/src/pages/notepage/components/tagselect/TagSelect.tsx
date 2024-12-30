import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  GetFullEnvironmentResponse,
  GetTagResponse,
  GetTagResponseBasic,
} from "types/api";
import { AxiosClient } from "../../../../services/AxiosClient";
import { createTagPagePath } from "../../../../utils/Routes";
import "./TagSelect.css";

export const TagSelect: React.FC<TagSelectProps> = ({
  environmentId,
  isEnabled,
  initialValue,
  getTag,
}) => {
  const navigate = useNavigate();
  const [environmentTags, setEnvironmentTags] = useState<GetTagResponse[] | []>(
    []
  );
  const [tag, setTag] = useState<GetTagResponseBasic>(null);
  useEffect(() => {
    setTag(initialValue);
    fetchTags(environmentId);
  }, [initialValue]);

  const fetchTags = (environmentId: number | string) => {
    AxiosClient.get(`environments/${environmentId}`)
      .then((response: GetFullEnvironmentResponse) =>
        setEnvironmentTags(response.tags)
      )
      .catch((error) => console.error(error));
  };

  const handleTagSelect = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedTag = e.target.value;
    if (selectedTag === "new-tag") {
      navigate(createTagPagePath(environmentId));
    } else {
      const newTag = environmentTags.find(
        (tag: GetTagResponseBasic) => tag.id == Number(selectedTag)
      );
      setTag(newTag);
      getTag(newTag);
    }
  };
  return (
    <div>
      <select
        className="tag-select"
        name="tagId"
        onChange={(e) => handleTagSelect(e)}
        value={tag ? tag.id : "default"}
        disabled={!isEnabled}
      >
        <option value="default" disabled>
          {tag ? tag.title : "Select a tag"}
        </option>
        {environmentTags.length != 0 ? (
          environmentTags.map((environmentTag: GetTagResponse) => (
            <option key={environmentTag.id} value={environmentTag.id}>
              {environmentTag.title}
            </option>
          ))
        ) : (
          <option value={"new-tag"}>Create new Tag...</option>
        )}
      </select>
    </div>
  );
};

interface TagSelectProps {
  environmentId: number | string;
  getTag: (tag: GetTagResponse) => void;
  isEnabled: boolean;
  initialValue?: GetTagResponseBasic;
}
