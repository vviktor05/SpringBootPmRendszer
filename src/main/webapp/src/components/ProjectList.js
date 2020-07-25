import React, { Component } from 'react';
import { Card, Table, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { url } from '../util/BackendURL';

export default class ProjectLista extends Component {

    constructor(props) {
        super(props)
        this.state = {
            projects: []
        };
    }

    componentDidMount() {
        axios.get(url("api/project_manager/projects"))
            .then(response => response.data)
            .then((data) => this.setState({ projects: data }));
    }

    deleteProject = (projectId) => {
        axios.delete(url("api/project_manager/projects/" + projectId))
            .then(response => {
                if (response.data != null) {
                    alert("A projekt törölve!");
                    this.setState({
                        projects: this.state.projects.filter(project => project.id !== projectId)
                    });
                }
            });
    }

    render() {
        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Projektek</Card.Header>
                <Card.Body>
                    <Link to={"/projects/add"}><Button variant="success" disabled>Projekt hozzáadása</Button></Link>
                    <Table bordered hover striped variant="dark" style={{ marginTop: "20px" }}>
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
                                <th>Gombok</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.projects.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="10">Nincs elérhető projekt.</td>
                                    </tr> :
                                    this.state.projects.map((project) => (
                                        <tr key={project.id}>
                                            <td>{project.name}</td>
                                            <td>{project.customer.name}</td>
                                            <td>{project.developmentArea.name}</td>
                                            <td>{project.orderDate}</td>
                                            <td>{project.deadline}</td>
                                            <td>{project.projectStatus.name}</td>
                                            <td>{project.priority.name}</td>
                                            <td>{project.projectLeader.name}</td>
                                            <td>{project.status.name}</td>
                                            <td>
                                                <Link to={"projects/edit/" + project.id} className="mr-2 btn btn-sm btn-primary disabled">Módosít</Link>
                                                <Button variant="danger" onClick={this.deleteProject.bind(this, project.id)} size="sm">Töröl</Button>
                                            </td>
                                        </tr>
                                    ))
                            }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        )
    }
}