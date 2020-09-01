import React, {Component} from 'react';
import {HashRouter as Router, Route} from 'react-router-dom'

import Home from './pages/Home'
import Profile from './pages/Profile'
import User from './pages/User'

class App extends Component{
    render() {
        return (
            <Router>
                <div>
                    <Route path='/' exact={true} component={Home}></Route>
                    <Route path='/profile' component={Profile}></Route>
                    <Route path='/user' component={User}></Route>
                </div>
            </Router>
        );
    }
}

/*function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h2>欢迎来到菜鸟教程</h2>
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}*/

export default App;
