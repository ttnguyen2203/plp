import React from 'react';
import './App.css';
import Translate from './Translate.js';

class App extends React.Component {
  constructor(props) {
    super(props);
  }
  
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <p>
            Pig Latin Project
          </p>
        </header>
        <div className="Text"> 
          <Translate/>
        </div>
      </div>
    );
  }

}

export default App;
