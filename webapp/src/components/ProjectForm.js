import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../util/BackendURL';

export default class Project extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: '',
            name: '',
            customerId: 1,
            orderDate: '',
            deadline: '',
            developmentAreaId: 1,
            projectStatusId: 1,
            priorityId: 1,
            statusId: 1,
            description: '',
            customerList: [],
            developmentAreaList: [],
            projectStatusList: [],
            priorityList: [],
            statusList: []
        };

        this.projectChange = this.projectChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    initialState = {
        id: '', name: '', customerId: 1, orderDate: '', deadline: '', developmentAreaId: 1, projectStatusId: 1, priorityId: 1, statusId: 1, description: ''
    }

    componentDidMount() {
        this.findAllLists();

        const projectId = +this.props.match.params.id;
        if (projectId) {
            this.findProjectById(projectId);
        }
    }

    findAllLists() {
        axios.get(url("api/project_manager/customers"))
            .then(response => response.data)
            .then((data) => this.setState({ customerList: data }));

        axios.get(url("api/project_manager/development_areas"))
            .then(response => response.data)
            .then((data) => this.setState({ developmentAreaList: data }));

        axios.get(url("api/project_manager/project_statuses"))
            .then(response => response.data)
            .then((data) => this.setState({ projectStatusList: data }));

        axios.get(url("api/project_manager/priorities"))
            .then(response => response.data)
            .then((data) => this.setState({ priorityList: data }));

        axios.get(url("api/project_manager/statuses"))
            .then(response => response.data)
            .then((data) => this.setState({ statusList: data }));
    }

    findProjectById(projectId) {
        axios.get(url("api/project_manager/projects/id/" + projectId))
            .then(response => {
                if (response.status === 200) {
                    this.setState({
                        id: response.data.id,
                        name: response.data.name,
                        customerId: this.findProjectIndexInArray(this.state.customerList, response.data.customer.id),
                        orderDate: response.data.orderDate,
                        deadline: response.data.deadline,
                        developmentAreaId: this.findProjectIndexInArray(this.state.developmentAreaList, response.data.developmentArea.id),
                        projectStatusId: this.findProjectIndexInArray(this.state.projectStatusList, response.data.projectStatus.id),
                        priorityId: this.findProjectIndexInArray(this.state.priorityList, response.data.priority.id),
                        statusId: this.findProjectIndexInArray(this.state.statusList, response.data.status.id),
                        description: response.data.description
                    });
                }
            }).catch((error) => {
                console.error("Hiba - " + error);
            });
    }

    findProjectIndexInArray(array, id) {
        for (let i = 0; i < array.length; i++) {
            if (array[i].id === id) {
                return i + 1;
            }
        }

        return 0;
    }

    onSubmit(event) {
        event.preventDefault();

        const testProjectLeader = {
            id: 1,
            name: "Horváth Krisztina",
            email: "manager@gmail.com",
            job: {
                id: 1,
                name: "Projektvezető"
            },
            developmentArea: {
                id: 1,
                name: "Asztali alkalmazás"
            },
            skill: {
                id: 4,
                name: "Senior"
            },
            startDate: "2020-01-31",
            phoneNumber: "06701122345",
            lastLoginDate: "2020-03-07 18:28:22"
        };

        if (this.checkDetails()) {
            const project = {
                name: this.state.name,
                customer: this.state.customerList[this.state.customerId - 1],
                orderDate: this.state.orderDate,
                deadline: this.state.deadline,
                developmentArea: this.state.developmentAreaList[this.state.developmentAreaId - 1],
                projectStatus: this.state.projectStatusList[this.state.projectStatusId - 1],
                priority: this.state.priorityList[this.state.priorityId - 1],
                projectLeader: testProjectLeader,
                status: this.state.statusList[this.state.statusId - 1],
                description: this.state.description
            }

            if (this.state.id) {
                this.editProject(project);
            } else {
                this.addProject(project);
            }
        }
    }

    addProject(project) {
        axios.post(url("api/project_manager/projects"), project)
            .then(response => {
                if (response.status === 200) {
                    this.resetProject();
                    alert("A project elmentve!");
                    this.projectList();
                }
            });
    }

    editProject(project) {
        axios.put(url("api/project_manager/projects/" + this.state.id), project)
            .then(response => {
                if (response.status === 200) {
                    this.resetProject();
                    alert("A project elmentve!");
                    this.projectList();
                }
            });
    }

    checkDetails() {
        if (this.state.name.length >= 5) {
            if (new Date(this.state.orderDate) < new Date(this.state.deadline)) {
                return true;
            } else {
                alert("A határidőnek késöbbi időpontnak kell lennie, mint a megrendelés dátumának!");
            }
        } else {
            alert("A projekt neve nem lehet rövidebb 5 karakternél!");
        }
        return false;
    }

    projectChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    projectList = () => {
        return this.props.history.push("/projects");
    }

    resetProject = () => {
        this.setState(() => this.initialState);
    }

    render() {
        const { name, customerId, orderDate, deadline, developmentAreaId, projectStatusId, priorityId, statusId, description } = this.state;

        return (
            <Card style={{ width: "75%", margin: "0px auto" }} className="border border-dark bg-dark text-white">
                <Card.Header> {this.state.id ? "Projekt módosítása" : "Projekt hozzáadása"} </Card.Header>
                <Form onReset={this.resetProject} onSubmit={this.onSubmit} id="projektForm">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Projekt neve</Form.Label>
                                <Form.Control required
                                    type="text" name="name"
                                    value={name}
                                    autoComplete="off"
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a projekt nevét" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formCustomer">
                                <Form.Label>Megrendelő</Form.Label>
                                <Form.Control className={"bg-dark text-white"}
                                    as="select" name="customerId"
                                    value={customerId}
                                    onChange={this.projectChange}>
                                    {
                                        this.state.customerList.map((customer) => (
                                            <option key={customer.id} value={customer.id}>
                                                {customer.name}
                                            </option>
                                        ))
                                    }
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formOrderDate">
                                <Form.Label>Megrendelés dátuma</Form.Label>
                                <Form.Control
                                    required
                                    value={orderDate}
                                    onChange={this.projectChange}
                                    type="date"
                                    className={"bg-dark text-white"}
                                    name="orderDate" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formDeadline">
                                <Form.Label>Határidő dátuma</Form.Label>
                                <Form.Control
                                    required
                                    value={deadline}
                                    onChange={this.projectChange}
                                    type="date"
                                    className={"bg-dark text-white"}
                                    name="deadline" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formDevelopmentArea">
                                <Form.Label>Fejlesztési terület</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="developmentAreaId"
                                    value={developmentAreaId}
                                    onChange={this.projectChange}>
                                    {this.state.developmentAreaList.map((developmentArea) => (
                                        <option key={developmentArea.id} value={developmentArea.id}>
                                            {developmentArea.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group as={Col} controlId="formProjectStatus">
                                <Form.Label>Projekt állapot</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="projectStatusId"
                                    value={projectStatusId}
                                    onChange={this.projectChange}>
                                    {this.state.projectStatusList.map((projectStatus) => (
                                        <option key={projectStatus.id} value={projectStatus.id}>
                                            {projectStatus.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formPriority">
                                <Form.Label>Prioritás</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="priorityId"
                                    value={priorityId}
                                    onChange={this.projectChange}
                                >
                                    {this.state.priorityList.map((priority) => (
                                        <option key={priority.id} value={priority.id}>
                                            {priority.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group as={Col} controlId="formStatus">
                                <Form.Label>Státusz</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="statusId"
                                    value={statusId}
                                    onChange={this.projectChange}
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
                                <Form.Label>Projekt leírása</Form.Label>
                                <Form.Control as="textarea" rows="10"
                                    value={description}
                                    onChange={this.projectChange}
                                    placeholder="Add meg a projekt leírását."
                                    className={"bg-dark text-white"}
                                    name="description" />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer>
                        <Link to={"/projects"}><Button variant="primary">Vissza</Button></Link>
                        <div style={{ "display": "inline", "float": "right" }}>
                            <Button size="bg" variant="success" type="submit">Mentés</Button>
                            {this.state.id ? null : <Button style={{ marginLeft: "10px" }} size="bg" variant="info" type="reset">Alaphelyzet</Button>}
                        </div>
                    </Card.Footer>
                </Form>
            </Card >
        )
    }
}