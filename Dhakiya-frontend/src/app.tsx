import "./basestyles/base.css";
import { createRoot } from "react-dom/client";
import { HashRouter as Router, Routes, Route } from "react-router-dom";
import { Environments } from "./pages/environment/Environments";
import { BasicPage } from "./components/layouts/basicpage/BasicPage";
import { Notes } from "./pages/notes/Notes";
import { Flashcards } from "./pages/flashcards/Flashcards";
import { EnvironmentView } from "./pages/environment/EnvironmentView";
import { NoteSet } from "./pages/notes/NoteSet";
import { FlashCardSet } from "./pages/flashcards/FlashCardSet";
import { FlashcardView } from "./pages/flashcards/FlashcardView";
import { ThemeProvider } from "./context/ThemeContext";
import { QuizSets } from "./pages/quizsets/QuizSets";
import { NoteView } from "./pages/notes/NoteView";

const App: React.FC = () => {
    return (
        <ThemeProvider>
            <Router>
                <BasicPage>
                    <Routes>
                        <Route path="/" element={<Environments />} />
                        <Route
                            path="/environment/:id"
                            element={<EnvironmentView />}
                        />
                        <Route path="/notes" element={<Notes />} />
                        <Route path="/note-set/:id" element={<NoteSet />} />
                        <Route path="/note/:id" element={<NoteView />} />
                        <Route path="/flashcards" element={<Flashcards />} />
                        <Route
                            path="/flashcard-set/:id"
                            element={<FlashCardSet />}
                        />
                        <Route
                            path="/flashcard/:id"
                            element={<FlashcardView />}
                        />
                        <Route path="/quiz-sets" element={<QuizSets />} />
                    </Routes>
                </BasicPage>
            </Router>
        </ThemeProvider>
    );
};

const container = document.getElementById("root");
const root = createRoot(container!);
root.render(<App />);
