import React, { Component } from 'react'
import './Table.css';

class Table extends Component {
    constructor(props) {
        super(props)
        this.state = {
            isLoading: true,
            averageVotes: ''
        }
    }

    renderTableHeader() {
        return <tr><td>id</td><td>genre</td><td>average vote</td><td>date</td><td>recalculate</td></tr>
    }

    renderTableData() {
        return this.state.averageVotes.map((averageVoteModel, index) => {
            const { id, nameGenre, averageVote, timestamp } = averageVoteModel
            return (
                <tr key={id}>
                <td>{id}</td>
                <td>{nameGenre}</td>
                <td>{averageVote}</td>
                <td>{this.parseDate(timestamp)}</td>
                <td><button onClick={(event)=>this.recalculate(id, event)}>Пересчитать</button> </td>
                </tr>
            )
        })
    }

     async recalculate(id, event) {
        event.preventDefault();
        this.setState({isLoading: true});
        let response = await fetch('/calculate/' + id);
        this.componentDidMount();
     }

     async recalculateAll(event) {
        event.preventDefault();
        this.setState({isLoading: true});
        let response = await fetch('/calculates/');
        this.componentDidMount();
     }

    parseDate(date) {
        var options = {
            year: '2-digit',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        };
        return new Date(date).toLocaleString("ru", options);
    }

    async componentDidMount() {
        let response = await fetch('/averageVotes');
        let body = await response.json();
        this.setState({averageVotes: body, isLoading: false})
    }

    render() {
        if (this.state.isLoading) {
            return <div>Loading...</div>;
        }
        return (
            <div>
                <h1 class='title'>Average Votes</h1>
                <table class='averageVote'>
                    {this.renderTableHeader()}
                    {this.renderTableData()}
                </table>
                <button class="allCalculate" onClick={(event)=>this.recalculateAll(event)}>Пересчитать все</button>
            </div>
        )
    }
}

export default Table;