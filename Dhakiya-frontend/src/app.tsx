import { createRoot } from "react-dom/client";
import { Route, HashRouter as Router, Routes } from "react-router-dom";
import "./basestyles/base.css";
import { BasicPage } from "./components/layouts/basicpage/BasicPage";
import { EnvironmentProvider } from "./context/EnvironmentContext";
import { ThemeProvider } from "./context/ThemeContext";
import { Environment } from "./pages/environment/Environment";
import { HomePage } from "./pages/homepage/HomePage";
import { NoteCollection } from "./pages/notecollection/NoteCollection";

const App: React.FC = () => {
  return (
    <EnvironmentProvider>
      <ThemeProvider>
        <Router>
          <BasicPage>
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/environment/:id" element={<Environment />} />
              <Route path="/note-collection/:id" element={<NoteCollection />} />
            </Routes>
          </BasicPage>
        </Router>
      </ThemeProvider>
    </EnvironmentProvider>
  );
};

const container = document.getElementById("root");
const root = createRoot(container!);
root.render(<App />);
