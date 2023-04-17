import { Component } from './pages/Component';
import { Component1 } from './pages/Component1';
import './App.css';
import { Component2 } from './pages/Component2';
import { Component3 } from './pages/Component3';

function App() {
  return (
    <table>
    <tr>
      <td><Component /></td>
      <td><Component1/></td>
    </tr>
    <tr>
      <td><Component2 /></td>
      <td><Component3 /></td>
    </tr>
  </table>
  );
}

export default App;
