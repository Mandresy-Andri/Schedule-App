import React, {Component} from 'react'
import { Button, ButtonGroup, Card } from 'react-bootstrap'
import TaskService from '../services/TaskService'
import Table from 'react-bootstrap/Table';
import {Link} from 'react-router-dom';

export default class TaskComponent extends Component {

    constructor(props){
        super(props)
        this.state = {
            tasks:[]
        }
    }

    componentDidMount() {
        TaskService.getTasks().then((response) => {
            this.setState({tasks: response.data})
        })
    }

    //function to delete task from list. we need a task id
    deleteTask = (id) => {
        //call delete service and pass id
        TaskService.deleteTask(id)
        .then(response => {
            if(response.data != null){
                alert("Task deleted successfully");
                //change the sate of the component (the initial state is an array of tasks)
                this.setState({
                    //stream through list of task and filter out the task with the id given
                    tasks: this.state.tasks.filter(task => task.id !== id)
                })
            }
        });
    }


    render() {
        return (
                <Card className='text-white bg-dark m-3'>
                    <Card.Body>
                        <h1 className="text-center text-white"> Tasks List</h1>
                        <Table  striped hover variant="dark">
                            <thead>
                                <tr>
                                    <td> Task Id</td>
                                    <td> Task Title</td>
                                    <td> Task Description</td>
                                    <td> Task Link</td>
                                    <td> Start Date</td>
                                    <td> Deadline</td>
                                    <td>Actions</td>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.tasks.map(
                                        task =>
                                        <tr key = {task.id}>
                                            <td> {task.id}</td>
                                            <td> {task.title}</td>
                                            <td> {task.description}</td>
                                            <td> {task.link}</td>
                                            <td> {task.start}</td>
                                            <td> {task.end}</td>
                                            <td>
                                                <ButtonGroup>
                                                    <Link style={{textDecoration: 'none'}} to={'/edit/'+task.id} >
                                                        <Button variant='outline-primary' className='btn-sm me-1'>Edit</Button>
                                                    </Link>
                                                    <Button variant='outline-danger' className='btn-sm me-1' onClick={this.deleteTask.bind(this,task.id)}>Delete</Button>
                                                </ButtonGroup>
                                            </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </Table>
                    </Card.Body>
                </Card>
        )
    }
}