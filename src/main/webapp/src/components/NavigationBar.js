import React, {Component} from 'react'
import {Navbar,Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom';

export default class NavigationBar extends Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                    <Link to={''} className="navbar-brand">
                        <img alt="task" src="/amflogo.png" width="30" height="35"/>{' '}
                        Task Manager
                    </Link>
                    <Nav className="me-auto">
                        <Link to={'list'} className="nav-link">Task List</Link>
                        <Link to={'add'} className="nav-link">Add Task</Link>
                    </Nav>
             </Navbar>
        );
    }
}