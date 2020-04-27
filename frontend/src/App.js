import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Table from './Table'

class App extends Component {
    state = {};

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <div className="App-intro">
                        <Table></Table>
                    </div>
                </header>
            </div>
        );
    }

}
export default App;