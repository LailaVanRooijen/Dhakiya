import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { LabelBar } from "../../components/labelbar/LabelBar";
import { AxiosClient } from "../../services/AxiosClient";
import { GetEnvironmentResponse } from "../../types/api";

export const Environment: React.FC<EnvironmentProps> = () => {
  const { id } = useParams<{ id: string }>();
  const [environment, setEnvironment] = useState<GetEnvironmentResponse | null>(
    null
  );

  useEffect(() => {
    AxiosClient.get(`environments/${id}`)
      .then((response: GetEnvironmentResponse) => {
        setEnvironment(response);
      })
      .catch((error) => console.error(error));
  }, []);

  if (environment) {
    return (
      <div>
        <LabelBar label={environment.title} />
      </div>
    );
  }
};

// interfaces
interface EnvironmentProps {}
