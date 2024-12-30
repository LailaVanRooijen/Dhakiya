import { useParams } from "react-router-dom";

export const TagPage = () => {
  const { environmentId } = useParams<{ environmentId: string }>();
  return <div>TagPage / environment id: {environmentId}</div>;
};
