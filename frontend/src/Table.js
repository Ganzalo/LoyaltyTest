import React, { Component } from 'react'
import './Table.css';

class Table extends Component {
   constructor(props) {
      super(props)
      this.state = {
         averageVotes: ''
//                  averageVotes: [
//                     { id: 1, nameGenre: 'Wasif', averageVote: 7.3, timestamp: '2020-02-10T07:53:42.538+0000' },
//                     { id: 2, nameGenre: 'Ali', averageVote: 5.8, timestamp: '2020-02-10T07:53:42.538+0000' },
//                     { id: 3, nameGenre: 'Saad', averageVote: 7.8, timestamp: '2020-02-10T07:53:42.538+0000' },
//                     { id: 4, nameGenre: 'Asad', averageVote: 5.8, timestamp: '2020-02-10T07:53:42.538+0000' }
//                  ]
      }
   }

//  getAverageVotes() {
//    let response = fetch('/averageVotes');
//    let body = response.json();
//    this.setState({ averageVotes: body});
//  }

 renderTableData() {
      return this.state.averageVotes.map((averageVoteModel, index) => {
         const { id, nameGenre, averageVote, timestamp } = averageVoteModel //destructuring
         return (
            <tr key={id}>
               <td>{id}</td>
               <td>{nameGenre}</td>
               <td>{averageVote}</td>
               <td>{timestamp}</td>
            </tr>
         )
      })
   }

   renderTableHeader() {
      let header = Object.keys(this.state.averageVotes[0])
      return header.map((key, index) => {
         return <th key={index}>{key.toUpperCase()}</th>
      })
   }

render() {
      return (
         <div>
            <h1 id='title'>Average Votes</h1>
            <table id='averageVote'>
               <tbody>
                  <tr>{this.renderTableHeader()}</tr>
                  {this.renderTableData()}
               </tbody>
            </table>
         </div>
      )
   }
}
export default Table;