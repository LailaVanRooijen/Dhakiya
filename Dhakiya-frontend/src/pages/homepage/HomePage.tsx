import { useEffect, useState } from "react";
import { Button } from "../../components/button/Button";
import { Form } from "../../components/form/Form";
import { FormInputField } from "../../components/form/FormInputField";
import { LabelBar } from "../../components/labelbar/LabelBar";
import { SidebarLayout } from "../../components/layouts/sidebarlayout/SidebarLayout";
import { List } from "../../components/list/List";
import { ENVIRONMENT_BASE_PATH } from "../../helperfunctions/Routes";
import { ValidatePostEnvironment } from "../../helperfunctions/useFormValidators";
import { AxiosClient } from "../../services/AxiosClient";
import {
  GetEnvironmentResponse,
  PostEnvironmentRequest,
} from "../../types/api";
import "./HomePage.css";

export const HomePage = () => {
  const [showForm, setShowForm] = useState<boolean>(false);
  const [environments, setEnvironments] = useState<GetEnvironmentResponse[]>(
    []
  );

  const postEnvironment = (requestbody: PostEnvironmentRequest) => {
    console.log(requestbody);
    if (ValidatePostEnvironment(requestbody)) {
      AxiosClient.post("environments", requestbody)
        .then((response: GetEnvironmentResponse) => {
          setEnvironments((prev) => [...prev, response]);
          setShowForm(false);
        })
        .catch((error) => console.error(error));
    } else {
      console.error("form field is empty");
    }
  };

  useEffect(() => {
    AxiosClient.get("environments")
      .then((response: GetEnvironmentResponse[]) => setEnvironments(response))
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className={` environments-box`}>
      <LabelBar label={"Environments"} />
      <SidebarLayout
        leftContent={
          <>
            {environments && (
              <List list={environments} linkTo={ENVIRONMENT_BASE_PATH} />
            )}
          </>
        }
        rightContent={
          <div>
            <Button
              content={showForm ? "hide" : "+ environment"}
              handleClick={() => {
                setShowForm(!showForm);
              }}
            />
            {showForm && (
              <Form
                formLabel="Create new environment"
                handleSubmit={postEnvironment}
              >
                <FormInputField label={"title"} type="text" />
              </Form>
            )}
          </div>
        }
      />
    </div>
  );
};
