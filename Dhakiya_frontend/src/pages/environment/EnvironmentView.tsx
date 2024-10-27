import { useParams } from "react-router-dom";

export const EnvironmentView = () => {
    const { id } = useParams<{ id: string }>();
    return <div>EnvironmentView {id}</div>;
};
