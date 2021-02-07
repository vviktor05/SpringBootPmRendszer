import React, { Component } from 'react';
import './TaskForm.css';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';

export default class Project extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: 0,
            topic: '',
            deadline: '',
            teamLeaderId: 1,
            projectId: 1,
            statusId: 1,
            description: '',
            teamLeaderList: [],
            projectList: [],
            statusList: []
        };

        this.taskChange = this.taskChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    initialState = {
        id: 0, topic: '', deadline: '', teamLeaderId: 1, projectId: 1, statusId: 1, description: ''
    }

    componentDidMount() {
        this.findAllLists();

        const taskId = +this.props.match.params.id;
        if (taskId) {
            this.findTaskById(taskId);
        }
    }

    findAllLists() {
        axios.get(url("api/project_manager/employees/team_leaders"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ teamLeaderList: data }));

        axios.get(url("api/project_manager/projects"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ projectList: data }));

        axios.get(url("api/project_manager/statuses"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ statusList: data }));
    }

    findTaskById = (taskId) => {
        axios.get(url("api/project_manager/tasks/id/" + taskId), { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    this.setState({
                        id: response.data.id,
                        topic: response.data.topic,
                        deadline: this.datetimeFormatter(response.data.deadline),
                        teamLeaderId: response.data.teamLeader.id,
                        projectId: response.data.project.id,
                        statusId: response.data.status.id,
                        description: response.data.description
                    });
                }
            }).catch((error) => {
                console.error("Hiba - " + error);
            });
    }

    datetimeFormatter(datetime) {
        if (datetime.includes("T")) {
            return datetime.replace('T', ' ');
        } else {
            return datetime.replace(' ', 'T');
        }
    }

    onSubmit = (event) => {
        event.preventDefault();

        if (this.checkDetails()) {
            var { topic, deadline, teamLeaderId, projectId, statusId, description, teamLeaderList,
                projectList, statusList } = this.state;

            if (teamLeaderId === 1) {
                teamLeaderId = teamLeaderList[0].id;
            }

            const task = {
                topic: topic,
                deadline: this.datetimeFormatter(deadline),
                teamLeader: this.findObjectInArray(teamLeaderId, teamLeaderList),
                project: this.findObjectInArray(projectId, projectList),
                status: this.findObjectInArray(statusId, statusList),
                description: description
            }

            if (this.state.id) {
                this.editTask(task);
            } else {
                this.addTask(task);
            }
        }
    }

    findObjectInArray(id, array) {
        let idInt = parseInt(id);
        for (let i = 0; i < array.length; i++) {
            if (array[i].id === idInt) {
                return array[i];
            }
        }
    }

    addTask = (task) => {
        axios.post(url("api/project_manager/tasks"), task, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A feladat elmentve!");
                    this.resetTask();
                    this.taskList();
                }
            });
    }

    editTask = (task) => {
        axios.put(url("api/project_manager/tasks/" + this.state.id), task, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A feladat elmentve!");
                    this.resetTask();
                    this.taskList();
                }
            });
    }

    checkDetails() {
        if (this.state.topic.length >= 5) {
            if (new Date() < new Date(this.state.deadline)) {
                return true;
            } else {
                alert("A határidőnek késöbbi időpontnak kell lennie, mint a jelenlegi dátum!");
            }
        } else {
            alert("A feladat neve nem lehet rövidebb 5 karakternél!");
        }
        return false;
    }

    taskChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    taskList = () => {
        return this.props.history.push("/tasks");
    }

    resetTask = () => {
        this.setState(() => this.initialState);
    }

    render() {
        const { topic, deadline, teamLeaderId, projectId, statusId, description } = this.state;

        return (
            <Card id="taskFormContainer" className="border border-dark bg-dark text-white">
                <Card.Header> {this.state.id ? "Feladat módosítása" : "Feladat hozzáadása"} </Card.Header>
                <Form onReset={this.resetTask} onSubmit={this.onSubmit} id="taskForm">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Feladat neve</Form.Label>
                                <Form.Control required
                                    type="text" name="topic"
                                    value={topic}
                                    autoComplete="off"
                                    onChange={this.taskChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a feladat nevét" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formDeadline">
                                <Form.Label>Határidő dátuma</Form.Label>
                                <Form.Control
                                    required
                                    value={deadline}
                                    onChange={this.taskChange}
                                    type="datetime-local"
                                    className={"bg-dark text-white"}
                                    name="deadline" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formTeamLeader">
                                <Form.Label>Csapatvezetők</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="teamLeaderId"
                                    value={teamLeaderId}
                                    onChange={this.taskChange}>
                                    {this.state.teamLeaderList.sort((a, b) => a.id - b.id).map((teamLeader) => (
                                        <option key={teamLeader.id} value={teamLeader.id}>
                                            {teamLeader.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formProject">
                                <Form.Label>Projekt</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="projectId"
                                    value={projectId}
                                    onChange={this.taskChange}
                                >
                                    {this.state.projectList.sort((a, b) => a.id - b.id).map((project) => (
                                        <option key={project.id} value={project.id}>
                                            {project.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group as={Col} controlId="formStatus">
                                <Form.Label>Státusz</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="statusId"
                                    value={statusId}
                                    onChange={this.taskChange}
                                >
                                    {this.state.statusList.map((status) => (
                                        <option key={status.id} value={status.id}>
                                            {status.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formDescription">
                                <Form.Label>Feladat leírása</Form.Label>
                                <Form.Control as="textarea" rows="10"
                                    value={description}
                                    onChange={this.taskChange}
                                    placeholder="Add meg a feladat leírását."
                                    className={"bg-dark text-white"}
                                    name="description" />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer>
                        <Link to={"/tasks"}><Button variant="primary">Vissza</Button></Link>
                        <div className="formButtonsRight">
                            {this.state.id ? null : <Button size="bg" variant="info" type="reset">Alaphelyzet</Button>}
                            <Button className="defaultButtonMarginLeft" size="bg" variant="success" type="submit">Mentés</Button>
                        </div>
                    </Card.Footer>
                </Form>
            </Card >
        )
    }
}