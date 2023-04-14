import React, {Component} from 'react'

export default class Welcome extends Component {
    render() {
        return ( 
            <div className="bg-dark p-5 rounded-lg m-3 text-white">
                <h1 className="display-4">Welcome to the task manager</h1>
                <p className="lead">Here you can manage your tasks for a maximum of productivity</p>
                <p>You can add, delete, edit, or mark them as done.</p>
            </div> 
        );
    }
}