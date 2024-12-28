import { useNavigate } from "react-router-dom";
import { GetFlashcardDeckResponse, GetQuizCollectionResponse } from "types/api";
import { LabelBar } from "../../../components/labelbar/LabelBar";
import "./CardWithList.css";

export const CardWithList: React.FC<CardWithListProps> = ({ label, list }) => {
  const navigate = useNavigate();
  return (
    <div className="card-with-list-section-wrapper">
      <LabelBar label={label} />
      <ul>
        {list.length != 0 ? (
          list.map((item) => (
            <li
              key={item.id}
              onClick={() =>
                navigate(`/${label.replace(" ", "-").toLowerCase()}/${item.id}`)
              }
            >
              {item.title}
            </li>
          ))
        ) : (
          <li>Nothing created yet</li>
        )}
      </ul>
    </div>
  );
};

interface CardWithListProps {
  label: string;
  list: GetFlashcardDeckResponse[] | GetQuizCollectionResponse[];
}
