import React from "react";
import { createRoot } from "react-dom/client";
import { HashRouter as Router, Routes, Route, Link } from "react-router-dom";
import { Environment } from "./pages/environment/Environment";
import { Page } from "./components/pageLayout/Page";
import "./basestyles/base.css";
import { Notes } from "./pages/notes/Notes";
import { Flashcards } from "./pages/flashcards/Flashcards";
import { Tests } from "./pages/tests/Tests";
import { EnvironmentView } from "./pages/environment/EnvironmentView";
import { NoteSet } from "./pages/notes/NoteSet";
import { FlashCardSet } from "./pages/flashcards/FlashCardSet";
import { FlashcardView } from "./pages/flashcards/FlashcardView";

const App: React.FC = () => {
    return (
        <Router>
            <Page>
                <Routes>
                    <Route path="/" element={<Environment />} />
                    <Route
                        path="/environment/:id"
                        element={<EnvironmentView />}
                    />
                    <Route path="/notes" element={<Notes />} />
                    <Route path="/note-set/:id" element={<NoteSet />} />
                    <Route path="/note/:id" element={<NoteSet />} />
                    <Route path="/flashcards" element={<Flashcards />} />
                    <Route
                        path="/flashcard-set/:id"
                        element={<FlashCardSet />}
                    />
                    <Route path="/flashcard/:id" element={<FlashcardView />} />
                    <Route path="/tests" element={<Tests />} />
                </Routes>
            </Page>
        </Router>
    );
};

const container = document.getElementById("root");
const root = createRoot(container!);
root.render(<App />);
