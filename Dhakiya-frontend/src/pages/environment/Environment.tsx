import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Button, BUTTON_STYLE } from "../../components/button/Button";
import { LabelBar } from "../../components/labelbar/LabelBar";
import { CardWithList } from "../../components/pagesections/cardwithlist/CardWithList";
import { NavigationSection } from "../../components/pagesections/navigationsection/NavigationSection";
import { ProgressReportSection } from "../../components/pagesections/progressreportsection/ProgressReportSection";
import { AxiosClient } from "../../services/AxiosClient";
import { GetFullEnvironmentResponse } from "../../types/api";
import "./Environment.css";

export const Environment = () => {
  const { id } = useParams<{ id: string }>();
  const [environment, setEnvironment] =
    useState<GetFullEnvironmentResponse | null>(null);

  useEffect(() => {
    AxiosClient.get(`environments/${id}`)
      .then((response: GetFullEnvironmentResponse) => {
        setEnvironment(response);
        console.log(response);
      })
      .catch((error) => console.error(error));
  }, []);

  if (environment) {
    return (
      <div className="environment-wrapper">
        <div className="environment-header">
          <LabelBar label={environment.title} />
        </div>
        <div className="environment-wrapper-btn-container">
          <Button content={"delete"} btnStyle={BUTTON_STYLE.ALERT} />{" "}
          <Button content={"edit"} btnStyle={BUTTON_STYLE.ENCOURAGE} />{" "}
        </div>
        <div className="environment-note-container">
          <NavigationSection
            label={"Notes"}
            content={"View, create and edit notes"}
            navToLink={`/note-collection/${environment.noteCollectionId}`}
          />
        </div>
        <div className="environment-tag-panel">
          <NavigationSection
            label={"Tag Panel"}
            content={"View, create and edit tags"}
            navToLink={`/tag-panel/${environment.id}`} //TODO moet ff dubbel checken of dit klopt. hoe krijg ik alle tags van een environment ook alweer?
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
