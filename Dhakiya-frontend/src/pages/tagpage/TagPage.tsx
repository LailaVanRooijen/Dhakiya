import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { GetTagResponse } from "types/api";
import { Button, BUTTON_STYLE } from "../../components/button/Button";
import { HeaderBar } from "../../components/headerbar/HeaderBar";
import { AxiosClient } from "../../services/AxiosClient";
import { ListItem } from "./components/ListItem";
import "./TagPage.css";

export const TagPage = () => {
  const { environmentId } = useParams<{ environmentId: string }>();
  const [tags, setTags] = useState<GetTagResponse[] | null>(null);

  useEffect(() => {
    AxiosClient.get("tags", { environmentId: environmentId })
      .then((response: GetTagResponse[]) => {
        setTags(response);
        console.log(response);
      })
      .catch((error) => console.log(error));
  }, []);

  const deleteTag = (id: number) => {
    AxiosClient.delete("tags", id)
      .then((response) => console.log(response))
      .catch((error) => console.error(error));
  };

  return (
    <div>
      <HeaderBar
        label={"Tag Panel"}
        option1={"TODO filter"}
        option2={"TODO sort"}
        option3={<Button btnStyle={BUTTON_STYLE.ENCOURAGE} content={"+ new"} />}
      />
      <ul className="tag-panel-list">
        {tags &&
          tags.map((tag: GetTagResponse) => (
            <ListItem
              key={tag.id}
              title={tag.title}
              option1={`Status : ${tag.status
                .toString()
                .replace("_", " ")
                .toLowerCase()}`}
              option2={<Button content={"reset"} />}
              option3={
                <Button content={"✏️"} btnStyle={BUTTON_STYLE.ENCOURAGE} />
              }
              option4={
                <Button
                  content={"X"}
                  btnStyle={BUTTON_STYLE.ALERT}
                  handleClick={() => deleteTag(tag.id)}
                />
              }
            />
          ))}
      </ul>
    </div>
  );
};
