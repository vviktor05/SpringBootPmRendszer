import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import axios from 'axios';

export default class Project extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.lists = {
            customerList: [],
            developmentAreaList: [],
            projectStatusList: [],
            priorityList: [],
            statusList: []
        };
        this.findAllLists();
        this.projectChange = this.projectChange.bind(this);
        this.submitProject = this.submitProject.bind(this);
    }

    initialState = {
        id: '', name: '', customer: {}, orderDate: '', deadline: '', developmentArea: {}, projectStatus: {}, priority: {}, status: {}, description: ''
    }

    componentDidMount() {
        const projectId = +this.props.match.params.id;
        if (projectId) {
            this.findProjectById(projectId);
        }
    }

    findAllLists = () => {
        axios.get("http://localhost:8080/api/project_manager/customers")
            .then(response => response.data)
            .then((data) => this.lists.customerList = data);

        axios.get("http://localhost:8080/api/project_manager/development_areas")
            .then(response => response.data)
            .then((data) => this.lists.developmentAreaList = data);

        axios.get("http://localhost:8080/api/project_manager/project_statuses")
            .then(response => response.data)
            .then((data) => this.lists.projectStatusList = data);

        axios.get("http://localhost:8080/api/project_manager/priorities")
            .then(response => response.data)
            .then((data) => this.lists.priorityList = data);

        axios.get("http://localhost:8080/api/project_manager/statuses")
            .then(response => response.data)
            .then((data) => this.lists.statusList = data);
    }

    findProjectById = (projectId) => {
        axios.get("http://localhost:8080/api/project_manager/projects/id/" + projectId)
            .then(response => {
                if (response.status === 200) {
                    this.setState({
                        id: response.data.id,
                        name: response.data.name,
                        customer: response.data.customer,
                        orderDate: response.data.orderDate,
                        deadline: response.data.deadline,
                        developmentArea: response.data.developmentArea,
                        projectStatus: response.data.projectStatus,
                        priority: response.data.priority,
                        status: response.data.status,
                        description: response.data.description
                    });
                }
            }).catch((error) => {
                console.error("Hiba - " + error);
            });
    }

    resetProject = () => {
        this.setState(() => this.initialState);
    }

    submitProject = (event) => {
        event.preventDefault();

        const project = {
            name: this.state.name,
            customer: this.state.customer,
            orderDate: this.state.orderDate,
            deadline: this.state.deadline,
            developmentArea: this.state.developmentArea,
            projectStatus: this.state.projectStatus,
            priority: this.state.priority,
            status: this.state.status,
            description: this.state.description
        }

        axios.post("http://localhost:8080/api/project_manager/projects", project)
            .then(response => {
                if (response.status === 200) {
                    this.setState(this.initialState);
                    alert("A project elmentve!");
                }
            });
    }

    updateProject = (event) => {
        event.preventDefault();

        const project = {
            name: this.state.name,
            customer: this.state.customer,
            orderDate: this.state.orderDate,
            deadline: this.state.deadline,
            developmentArea: this.state.developmentArea,
            projectStatus: this.state.projectStatus,
            priority: this.state.priority,
            status: this.state.status,
            description: this.state.description
        }

        axios.put("http://localhost:8080/api/project_manager/projects/" + this.state.id, project)
            .then(response => {
                if (response.status === 200) {
                    this.setState(this.initialState);
                    alert("A project elmentve!");
                    this.projectList();
                }
            });
    }

    projectChange = (event) => {
        this.setState({
            name: event.target.value,
            customer: this.getObject(this.lists.customerList, event.target.value),
            orderDate: event.target.value,
            deadline: event.target.value,
            developmentArea: this.getObject(this.lists.developmentAreaList, event.target.value),
            projectStatus: this.getObject(this.lists.projectStatusList, event.target.value),
            priority: this.getObject(this.lists.priorityList, event.target.value),
            status: this.getObject(this.lists.statusList, event.target.value),
            description: event.target.value
        });
        console.log(this.state);
    }

    projectList = () => {
        return this.props.history.push("/projects");
    }

    getObject = (list, id) => {
        for (let index = 0; index < list.length; index++) {
            if (list[index].id === id) {
                return list[index];
            }
        }
        return null;
    }

    getIndex = (list, object) => {
        for (let index = 0; index < list.length; index++) {
            if (list[index].id === object.id) {
                return index + 1;
            }
        }
        return 0;
    }

    render() {
        const { name, orderDate, deadline, description } = this.state;

        return (
            <Card style={{ width: "75%", margin: "0px auto" }} className="border border-dark bg-dark text-white">
                <Card.Header> {this.state.id ? "Projekt módosítása" : "Projekt hozzáadása"} </Card.Header>
                <Form onReset={this.resetProject} onSubmit={this.state.id ? this.updateProject : this.submitProject} id="projektForm">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Projekt neve</Form.Label>
                                <Form.Control required
                                    type="text" name="name" value={name}
                                    autoComplete="off"
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a projekt nevét" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formCustomer">
                                <Form.Label>Megrendelő</Form.Label>
                                <Form.Control className={"bg-dark text-white"}
                                    as="select" name="customer" value={this.getIndex(this.lists.customerList, this.state.customer)}
                                    onChange={this.projectChange}>
                                    {this.lists.customerList.map((customer) => (
                                        <option key={customer.id} value={customer.id}>
                                            {customer.name}
                                        </option>
                                    ))}
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
                                <Form.Control className={"bg-dark text-white"} as="select" name="developmentArea" value={this.getIndex(this.lists.developmentAreaList, this.state.developmentArea)}
                                    onChange={this.projectChange}>
                                    {this.lists.developmentAreaList.map((developmentArea) => (
                                        <option key={developmentArea.id} value={developmentArea.id}>
                                            {developmentArea.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group as={Col} controlId="formProjectStatus">
                                <Form.Label>Projekt állapot</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="projectStatus" value={this.getIndex(this.lists.projectStatusList, this.state.projectStatus)}
                                    onChange={this.projectChange}>
                                    {this.lists.projectStatusList.map((projectStatus) => (
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
                                <Form.Control className={"bg-dark text-white"} as="select" name="priority" value={this.getIndex(this.lists.priorityList, this.state.priority)}
                                    onChange={this.projectChange}>
                                    {this.lists.priorityList.map((priority) => (
                                        <option key={priority.id} value={priority.id}>
                                            {priority.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group as={Col} controlId="formStatus">
                                <Form.Label>Státusz</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="status" value={this.getIndex(this.lists.statusList, this.state.status)}
                                    onChange={this.projectChange}>
                                    {this.lists.statusList.map((status) => (
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
                            <Button style={{ marginLeft: "10px" }} size="bg" variant="info" type="reset">Alaphelyzet</Button>
                        </div>
                    </Card.Footer>
                </Form>
            </Card >
        )
    }
}