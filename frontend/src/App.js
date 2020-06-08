import React from 'react';
import './App.css';
import { Router } from 'react-router';
import Routes from './Routes';
import { createBrowserHistory } from "history"

const history = createBrowserHistory();

function App() {
  return (
    <div className="App">
      <Router history={history}>
        <Routes/>
      </Router>
    </div>
  );
}

export default App;
