import React from "react";
import { createRoot } from "react-dom/client";
import Home from "./pages/home/Home";
import { HashRouter as Router, Routes, Route, Link } from "react-router-dom";

const App: React.FC = () => {
    return (
        <Router>
            <h1>Hoi</h1>
            <nav>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                </ul>
            </nav>
            <Routes>
                <Route path="/" element={<Home />} />
            </Routes>
        </Router>
    );
};

const root = createRoot(document.body);
root.render(<App />);
