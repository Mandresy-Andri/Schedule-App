import '../App.css';
import React, {Component} from 'react'
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import TaskService from '../services/TaskService';

export default class AddTaskComponent extends Component {

    constructor(props){
        super(props)
        this.state = this.initialState;
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    };

    initialState = {
        id: '', title:'',description:'',link:'',start:'',end:''
    };

    // componentDidMount = () => {
    //     this.findTaskById(this.props.match.params.id);
    // }

    findTaskById = (taskId) => {
        TaskService.getTasksById(taskId)
            .then(response => {
                if(response.data!=null){
                    this.setState({
                        id: response.data.id,
                        title: response.data.title,
                        description: response.data.description,
                        link: response.data.link,
                        start: response.data.start,
                        end: response.data.end
                    })
                }
            }).catch((error) => {
                console.error("Error -"+error);
            });
    };

    handleSubmit = event => {
        event.preventDefault();
        const task = {  title:this.state.title,
                        description: this.state.description, 
                        link:this.state.link,
                        star: this.state.start, 
                        end: this.state.end
                    };
        TaskService.addTask(task)
                    .then( response => {
                        if(response.data != null) {
                            this.setState(this.initialState);
                            alert("Task added successfully");
                        }
                    });
    };

    handleChange = event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    };

    handleReset = () => {
        this.setState(() => this.initialState)
    };

    render() { 
        const {title,description,link,start,end} = this.state;

        return(
            <Card className='bg-dark text-white m-5'>

                <Form onSubmit={this.handleSubmit} onReset={this.handleReset} id='taskFormId'>
                    <Card.Header>
                        <Card.Title>Add Task</Card.Title>
                    </Card.Header>
                <Card.Body className='bg-dark-blue'>

                            <Row className="mb-3">
                            <Form.Group as={Col}  controlId="formTitle"> 
                                <Form.Label>Title</Form.Label>
                                <Form.Control 
                                type="text" 
                                name='title' 
                                value={title}
                                onChange={this.handleChange} required autoComplete='off'
                                placeholder='Enter title' />
                            </Form.Group>

                            <Form.Group as={Col} controlId="formDescription">
                                <Form.Label>Description</Form.Label>
                                <Form.Control 
                                type="text" 
                                name='description' 
                                value={description}
                                onChange={this.handleChange} required autoComplete='off'
                                placeholder="What is the task?" />
                            </Form.Group>
                            </Row>

                            <Row>
                            <Col xs="6">
                            <Form.Group className="mb-3" controlId="formLink">
                                <Form.Label>Link</Form.Label>
                                <Form.Control 
                                type="text" 
                                name='link' 
                                value={link}
                                onChange={this.handleChange} autoComplete='off'
                                placeholder="Link to task" />
                            </Form.Group>
                            </Col>

                            <Form.Group as={Col} className="mb-3" controlId="formStart">
                                <Form.Label>Start Date</Form.Label>
                                <Form.Control 
                                type="date" 
                                name='start'
                                value={start}
                                onChange={this.handleChange} required autoComplete='off'/>
                            </Form.Group>

                            <Form.Group as={Col} className="mb-3" controlId="formEnd">
                                <Form.Label>Deadline</Form.Label>
                                <Form.Control 
                                type="date" 
                                name='end' 
                                value={end}
                                onChange={this.handleChange} required autoComplete='off'/>
                            </Form.Group>
                            </Row>
                        </Card.Body>
                        <Card.Footer>
                        <Button variant="outline-success" type="submit">
                            Submit
                        </Button>{' '}
                        <Button variant="outline-info" type="reset">
                            Reset
                        </Button>
                        </Card.Footer>
                    </Form>  
            </Card>
        );
    }
}