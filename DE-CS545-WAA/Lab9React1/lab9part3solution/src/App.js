import React from 'react';
import Counterone from './pages/Counterone';
import Counterthree from './pages/Counterthree';
import Counterfive from './pages/Counterfive';
import Countereight from './pages/Countereight';

function App() {
  return (
    <table>
      <tr>
        <td><Counterone/></td>
        <td><Counterthree/></td>
      </tr>
      <tr>
        <td><Counterfive/></td>
        <td><Countereight/></td>
      </tr>
    </table>
  );
};

export default App;