import { useEffect, useState } from "react";
import { Button } from "../../components/button/Button";
import { Form } from "../../components/form/Form";
import { FormTextField } from "../../components/form/FormTextField";
import { LabelBar } from "../../components/labelbar/LabelBar";
import { SidebarLayout } from "../../components/layouts/sidebarlayout/SidebarLayout";
import { useEnvironmentCtx } from "../../context/EnvironmentContext";
import { AxiosClient } from "../../services/AxiosClient";
import { GetEnvironmentResponse } from "../../types/api";
import "./HomePage.css";
import { List } from "./list/List";

export const HomePage = () => {
  const [environments, setEnvironments] = useState<GetEnvironmentResponse[]>(
    []
  );
  const { resetEnvironmentData } = useEnvironmentCtx();

  const addEnvironment = () => {
    console.log("add");
    // TODO make function to create new environment
  };

  useEffect(() => {
    AxiosClient.get("environments")
      .then((response: GetEnvironmentResponse[]) => setEnvironments(response))
      .catch((error) => {
        console.log(error);
      });
    resetEnvironmentData();
  }, []);

  return (
    <div className={` environments-box`}>
      <LabelBar label={"Environments"} />
      <SidebarLayout
        leftContent={
          <>
            {environments && (
              <List list={environments} linkTo={"environment"} />
            )}
          </>
        }
        rightContent={
          <div>
            <Button content="add +" handleClick={addEnvironment} />
            <div>
              <Form>
                <FormTextField label={"title"} type="text" />
              </Form>
            </div>
          </div>
        }
      />
    </div>
  );
};
