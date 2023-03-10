import React from 'react';
import './App.css';
import Root from "../pages/Root";
import {BrowserRouter} from "react-router-dom";

function App() {
  return (
    <div>
        <BrowserRouter>
            <Root/>
        </BrowserRouter>
    </div>
  );
}

export default App;
