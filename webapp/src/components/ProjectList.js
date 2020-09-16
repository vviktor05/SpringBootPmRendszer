import React, { Component } from 'react';
import { Card, Table, Button, Form, Col } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { url } from '../util/BackendURL';
import './ProjectList.css';
import authHeader from '../helpers/authHeader';

export default class ProjectLista extends Component {

    constructor(props) {
        super(props)
        this.state = {
            projects: [],
            projectName: "",
            activeProjects: false
        };
    }

    componentDidMount() {
        axios.get(url("api/project_manager/projects"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ projects: data }));
    }

    deleteProject = (projectId) => {
        if (window.confirm('Biztosan törli a kiválasztott projektet?')) {
            axios.delete(url("api/project_manager/projects/" + projectId), { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        this.setState({
                            projects: this.state.projects.filter(project => project.id !== projectId)
                        });
                        alert("A projekt törölve!");
                    }
                });
        }
    }

    projectListChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    searchProject = () => {

    }

    refreshProjects = () => {

    }

    render() {
        const { projectName, activeProjects } = this.state;

        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Projektek</Card.Header>
                <Card.Body>
                    <Link to={"/projects/add"}><Button variant="success">Projekt hozzáadása</Button></Link>
                    <Form onSubmit={this.searchProject()} id="projektForm">
                        <Form.Row>
                            <Col xs="auto">
                                <Form.Label>Projekt neve:</Form.Label>
                            </Col>
                            <Col xs="auto">
                                <Form.Control required
                                    id="projectName"
                                    type="text" name="projectName"
                                    value={projectName}
                                    autoComplete="off"
                                    onChange={this.projectListChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a projekt nevét" />
                            </Col>
                            <Col xs="auto">
                                <Button className="mr-2 btn btn-sm btn-primary disabled" type="submit">Keresés</Button>
                            </Col>
                        </Form.Row>
                    </Form>
                    <Table className="marginTop" bordered hover striped variant="dark">
                        <thead>
                            <tr>
                                <th>Név</th>
                                <th>Megrendelő</th>
                                <th>Megrendelés dátuma</th>
                                <th>Határidő dátuma</th>
                                <th>Fejlesztési terület</th>
                                <th>Projekt állapot</th>
                                <th>Prioritás</th>
                                <th>Projektvezető</th>
                                <th>Státusz</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.projects.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="10">Nincs elérhető projekt.</td>
                                    </tr>
                                    :
                                    this.state.projects.sort((a, b) => a.id - b.id).map((project) => (
                                        <tr key={project.id}>
                                            <td>{project.name}</td>
                                            <td>{project.customer.name}</td>
                                            <td>{project.orderDate}</td>
                                            <td>{project.deadline}</td>
                                            <td>{project.developmentArea.name}</td>
                                            <td>{project.projectStatus.name}</td>
                                            <td>{project.priority.name}</td>
                                            <td>{project.projectLeader.name}</td>
                                            <td>{project.status.name}</td>
                                            <td>
                                                <Link to={"projects/details/" + project.id} className="mr-2 btn btn-sm btn-primary disabled">Információ</Link>
                                                <Link to={"projects/edit/" + project.id} className="mr-2 btn btn-sm btn-primary">Módosít</Link>
                                                <Button variant="danger" onClick={this.deleteProject.bind(this, project.id)} size="sm">Töröl</Button>
                                            </td>
                                        </tr>
                                    ))
                            }
                        </tbody>
                    </Table>
                </Card.Body>
                <Card.Footer>
                    <Form onSubmit={this.refreshProjects()} id="projektForm">
                        <div id="projectListFooter">
                            <span>Aktív projektek: </span>
                            <Form.Check
                                type="checkbox" name="activeProjects"
                                inline
                                value={activeProjects}
                                autoComplete="off"
                                onChange={this.projectListChange}
                                className={"bg-dark text-white"} />
                            <Button className="mr-2 btn btn-sm btn-primary disabled" type="submit">Frissítés</Button>
                        </div>
                    </Form>
                </Card.Footer>
            </Card>
        )
    }
}