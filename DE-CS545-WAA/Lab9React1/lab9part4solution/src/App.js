import React, { useState } from 'react';
import Counterone from './pages/Counterone';
import Counterthree from './pages/Counterthree';
import Counterfive from './pages/Counterfive';
import Countereight from './pages/Countereight';

function App() {

  const [counter, setCounter] = useState(14);
  const handleCounterChange = (newValue) => {
    setCounter(newValue);
  }
  return (
    <table>
      <tr>
        <td><h2>{counter}</h2></td>
      </tr>
      <tr>
        <td><Counterone counter={counter} onCounterChange={handleCounterChange}/></td>
        <td><Counterthree counter={counter} onCounterChange={handleCounterChange}/></td>
      </tr>
      <tr>
        <td><Counterfive counter={counter} onCounterChange={handleCounterChange} /></td>
        <td><Countereight counter={counter} onCounterChange={handleCounterChange}/></td>
      </tr>
    </table>
  );
};

export default App;