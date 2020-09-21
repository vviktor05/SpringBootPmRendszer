import React, { Component } from 'react';
import './ProjectForm.css';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';
import authService from '../../services/AuthService';

export default class Project extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: '',
            name: '',
            customerId: -1,
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
        id: '', name: '', customerId: -1, orderDate: '', deadline: '', developmentAreaId: 1, projectStatusId: 1, priorityId: 1, statusId: 1, description: ''
    }

    componentDidMount() {
        this.findAllLists();

        const projectId = +this.props.match.params.id;
        if (projectId) {
            this.findProjectById(projectId);
        }
    }

    findAllLists() {
        axios.get(url("api/project_manager/customers"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ customerList: data }));

        axios.get(url("api/project_manager/development_areas"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ developmentAreaList: data }));

        axios.get(url("api/project_manager/project_statuses"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ projectStatusList: data }));

        axios.get(url("api/project_manager/priorities"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ priorityList: data }));

        axios.get(url("api/project_manager/statuses"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ statusList: data }));
    }

    findProjectById = (projectId) => {
        axios.get(url("api/project_manager/projects/id/" + projectId), { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    this.setState({
                        id: response.data.id,
                        name: response.data.name,
                        customerId: response.data.customer.id,
                        orderDate: response.data.orderDate,
                        deadline: response.data.deadline,
                        developmentAreaId: response.data.developmentArea.id,
                        projectStatusId: response.data.projectStatus.id,
                        priorityId: response.data.priority.id,
                        statusId: response.data.status.id,
                        description: response.data.description
                    });
                }
            }).catch((error) => {
                console.error("Hiba - " + error);
            });
    }

    onSubmit = (event) => {
        event.preventDefault();

        if (this.checkDetails()) {
            var { name, customerId, orderDate, deadline, developmentAreaId, projectStatusId, priorityId, statusId, description, customerList,
                developmentAreaList, projectStatusList, priorityList, statusList } = this.state;

            if (customerId === -1) {
                customerId = customerList[0].id;
            }

            const project = {
                name: name,
                customer: this.findObjectInArray(customerId, customerList),
                orderDate: orderDate,
                deadline: deadline,
                developmentArea: this.findObjectInArray(developmentAreaId, developmentAreaList),
                projectStatus: this.findObjectInArray(projectStatusId, projectStatusList),
                priority: this.findObjectInArray(priorityId, priorityList),
                projectLeader: authService.getLoggedInUser().employee,
                status: this.findObjectInArray(statusId, statusList),
                description: description
            }

            if (this.state.id) {
                this.editProject(project);
            } else {
                this.addProject(project);
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

    addProject = (project) => {
        axios.post(url("api/project_manager/projects"), project, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A project elmentve!");
                    this.resetProject();
                    this.projectList();
                }
            });
    }

    editProject = (project) => {
        axios.put(url("api/project_manager/projects/" + this.state.id), project, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A project elmentve!");
                    this.resetProject();
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
            <Card id="projectFormContainer" className="border border-dark bg-dark text-white">
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
                        <div className="formButtonsRight">
                            <Button size="bg" variant="success" type="submit">Mentés</Button>
                            {this.state.id ? null : <Button className="defaultButtonMarginLeft" size="bg" variant="info" type="reset">Alaphelyzet</Button>}
                        </div>
                    </Card.Footer>
                </Form>
            </Card >
        )
    }
}