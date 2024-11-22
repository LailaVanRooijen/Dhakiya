import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { AxiosClient } from "../../services/AxiosClient";
import { I_Flashcard } from "types/api";
import "./flashcard.css";
import { Button } from "../../components/button/Button";

export const FlashcardView = () => {
    const { id } = useParams<{ id: string }>();
    const [flashcard, setFlashcard] = useState<I_Flashcard | null>(null);
    const [showAnser, setShowAnser] = useState<boolean>(false);

    useEffect(() => {
        AxiosClient.get(`flashcards/${id}`)
            .then((response) => setFlashcard(response))
            .catch((error) => console.error(error));
    }, []);

    useEffect(() => {
        if (flashcard) console.log(flashcard);
    }, [flashcard]);

    if (!flashcard) return <div>No flashcard found</div>;
    return (
        <div className={`flashcard-view-container`}>
            <div className="flashcard-options">
                <Button
                    content={`show shortcuts`}
                    handleClick={() => {
                        console.log("show shortcuts");
                    }}
                />
            </div>
            <div className={`flashcard-view-front-content`}>
                {flashcard.frontContent}
            </div>
            {showAnser && (
                <div className={`flashcard-view-back-content`}>
                    {flashcard.backContent}
                </div>
            )}
            {!showAnser && (
                <div className={"flashcard-view-back-content"}></div>
            )}
            {!showAnser && (
                <div className={`flashcard-view-moves`}>
                    <Button
                        content={`Show`}
                        handleClick={() => setShowAnser(!showAnser)}
                    />
                </div>
            )}
            {showAnser && (
                <div className={`flashcard-view-moves`}>
                    <Button
                        content={`easy`}
                        handleClick={() => {
                            console.log("do sum");
                        }}
                    />
                    <Button
                        content={`correct`}
                        handleClick={() => {
                            console.log("do sum");
                        }}
                    />
                    <Button
                        content={`incorrect`}
                        handleClick={() => {
                            console.log("do sum");
                        }}
                    />
                    <Button
                        content={`difficult`}
                        handleClick={() => {
                            console.log("do sum");
                        }}
                    />
                    <Button
                        content={`delete card`}
                        handleClick={() => {
                            console.log("do sum");
                        }}
                    />
                </div>
            )}
        </div>
    );
};
