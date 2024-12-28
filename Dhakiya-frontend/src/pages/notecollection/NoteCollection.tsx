import { useParams } from "react-router-dom";

export const NoteCollection = () => {
  const { id } = useParams<{ id: string }>();
  return <div>NoteCollection id: {id}</div>;
};
