import {BrowserRouter as Router, Route} from 'react-router-dom';
import {Pageone, Pagetwo, Pagethree, Pagefour} from './pages';
import './App.css';

function App() {
  return (
    <div>
      <Router>
        <Route exact path={["/", "/pageone"]} component={Pageone} />
        <Route path="/pagetwo" component={Pagetwo} />
        <Route path="/pagethree" component={Pagethree} />
        <Route path="/pagefour" component={Pagefour} />
      </Router>       
    </div>
  );
}

export default App;