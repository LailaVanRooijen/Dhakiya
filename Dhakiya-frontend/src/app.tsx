import { createRoot } from "react-dom/client";
import { Route, HashRouter as Router, Routes } from "react-router-dom";
import "./basestyles/base.css";
import { ThemeProvider } from "./context/ThemeContext";
import { BasicPage } from "./layouts/basicpage/BasicPage";
import { Environment } from "./pages/environment/Environment";
import { HomePage } from "./pages/homepage/HomePage";
import { NoteCollection } from "./pages/notecollection/NoteCollection";
import { NotePage } from "./pages/notepage/NotePage";

const App: React.FC = () => {
  return (
    <ThemeProvider>
      <Router>
        <BasicPage>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route
              path="/environments/:environmentId"
              element={<Environment />}
            />
            <Route
              path="/environments/:environmentId/note-collections/:noteCollectionId"
              element={<NoteCollection />}
            />
            <Route
              path="/environments/:environmentId/note-collections/:noteCollectionId/notes/:noteId"
              element={<NotePage />}
            />
            <Route
              path="/environments/:environmentId/note-collections/:noteCollectionId/notes"
              element={<NotePage />}
            />
          </Routes>
        </BasicPage>
      </Router>
    </ThemeProvider>
  );
};

const container = document.getElementById("root");
const root = createRoot(container!);
root.render(<App />);
