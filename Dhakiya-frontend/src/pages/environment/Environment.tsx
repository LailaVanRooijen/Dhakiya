import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, BUTTON_STYLE } from "../../components/button/Button";
import { LabelBar } from "../../components/labelbar/LabelBar";
import { Modal } from "../../components/modal/Modal";
import { AxiosClient } from "../../services/AxiosClient";
import { GetFullEnvironmentResponse } from "../../types/api";
import {
  createNoteCollectionPath,
  createTagPagePath,
} from "../../utils/Routes";
import { CardWithList } from "./components/cardwithlist/CardWithList";
import { NavigationSection } from "./components/navigationsection/NavigationSection";
import { ProgressReportSection } from "./components/progressreportsection/ProgressReportSection";
import "./Environment.css";

export const Environment = () => {
  const navigate = useNavigate();
  const { environmentId } = useParams<{
    environmentId: string;
  }>();
  const [environment, setEnvironment] =
    useState<GetFullEnvironmentResponse | null>(null);
  const [isModalVisible, setIsModalVisible] = useState<boolean>(false);

  useEffect(() => {
    AxiosClient.get(`environments/${environmentId}`)
      .then((response: GetFullEnvironmentResponse) => {
        setEnvironment(response);
        console.log(response);
      })
      .catch((error) => console.error(error));
  }, []);

  const deleteEnvironment = () => {
    AxiosClient.delete(`environments`, environment.id)
      .then(() => {
        navigate("/");
      })
      .catch((error) => console.error(error));
  };

  const comfirmDelete = (answer: boolean) => {
    setIsModalVisible(false);
    if (answer) deleteEnvironment();
  };

  if (environment) {
    return (
      <div className="environment-wrapper">
        {isModalVisible && (
          <Modal
            question={"Are you sure you want to delete?"}
            getAnswer={(answer: boolean) => comfirmDelete(answer)}
          />
        )}

        <div className="environment-header">
          <LabelBar label={environment.title} />
        </div>
        <div className="environment-wrapper-btn-container">
          <Button
            content={"delete"}
            btnStyle={BUTTON_STYLE.ALERT}
            handleClick={() => setIsModalVisible(true)}
          />
          <Button content={"edit"} btnStyle={BUTTON_STYLE.ENCOURAGE} />{" "}
        </div>
        <div className="environment-note-container">
          <NavigationSection
            label={"Notes"}
            content={"View, create and edit notes"}
            navToLink={createNoteCollectionPath({
              environmentId: environmentId,
              noteCollectionId: environment.noteCollectionId,
            })}
          />
        </div>
        <div className="environment-tag-panel">
          <NavigationSection
            label={"Tag Panel"}
            content={"View, create and edit tags"}
            navToLink={createTagPagePath(environmentId)}
          />
        </div>
        <div className="environment-progress-report">
          <ProgressReportSection progressReport={environment.progressReport} />
        </div>
        <div className="environment-flashcard-decks">
          <CardWithList
            label={"Flashcard Decks"}
            list={environment.flashcardDecks}
          />
        </div>
        <div className="environment-quiz-collections">
          <CardWithList
            label={"Quiz collections"}
            list={environment.quizCollections}
          />
        </div>
      </div>
    );
  }
};
