import React from "react";
import { createRoot } from "react-dom/client";
import Home from "./pages/home/Home";
import { HashRouter as Router, Routes, Route, Link } from "react-router-dom";
import { Environment } from "./pages/environment/Environment";
import { NavBar } from "./components/navbar/NavBar";

const App: React.FC = () => {
    return (
        <Router>
            <NavBar />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/environment" element={<Environment />} />
            </Routes>
        </Router>
    );
};

const root = createRoot(document.body);
root.render(<App />);
