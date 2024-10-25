import React from "react";
import { createRoot } from "react-dom/client";
import Home from "./pages/home/Home";
import { HashRouter as Router, Routes, Route, Link } from "react-router-dom";
import { Environment } from "./pages/environment/Environment";
import { NavBar } from "./components/navbar/NavBar";
import { Page } from "./components/pageLayout/Page";

const App: React.FC = () => {
    return (
        <Router>
            <Page>
                <NavBar />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/environment" element={<Environment />} />
                </Routes>
            </Page>
        </Router>
    );
};

const root = createRoot(document.body);
root.render(<App />);
