import React from 'react';
import './App.css';
import { Router } from 'react-router';
import Routes from './Routes';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes/>
      </Router>
    </div>
  );
}

export default App;
