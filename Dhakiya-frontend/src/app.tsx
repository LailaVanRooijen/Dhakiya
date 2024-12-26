import { createRoot } from "react-dom/client";
import { Route, HashRouter as Router, Routes } from "react-router-dom";
import { EnvironmentProvider } from "../src/context/EnvironmentContext";
import "./basestyles/base.css";
import { BasicPage } from "./components/layouts/basicpage/BasicPage";
import { ThemeProvider } from "./context/ThemeContext";
import { Environment } from "./pages/environment/Environment";
import { HomePage } from "./pages/homepage/HomePage";

const App: React.FC = () => {
  return (
    <EnvironmentProvider>
      <ThemeProvider>
        <Router>
          <BasicPage>
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/environment/:id" element={<Environment />} />
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
