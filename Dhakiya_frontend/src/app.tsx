import React from "react";
import { createRoot } from "react-dom/client";
import { HashRouter as Router, Routes, Route, Link } from "react-router-dom";
import { Environment } from "./pages/environment/Environment";
import { Page } from "./components/pageLayout/Page";
import "./basestyles/base.css";
import { Notes } from "./pages/notes/Notes";
import { Flashcards } from "./pages/flashcards/Flashcards";
import { Tests } from "./pages/tests/Tests";
import { FetchProvider } from "./context/ContextApi";

const App: React.FC = () => {
    return (
        <FetchProvider>
            <Router>
                <Page>
                    <Routes>
                        <Route path="/" element={<Environment />} />
                        <Route path="/notes" element={<Notes />} />
                        <Route path="/flashcards" element={<Flashcards />} />
                        <Route path="/tests" element={<Tests />} />
                    </Routes>
                </Page>
            </Router>
        </FetchProvider>
    );
};

const container = document.getElementById("root");
const root = createRoot(container!);
root.render(<App />);
