import React, { Component } from 'react';
import { Card, Table, Button, Form, InputGroup, FormControl } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import './ProjectList.css';
import authHeader from '../../helpers/authHeader';
import './ProjectDetails.css';
import ProjectDetailsModal from './ProjectDetailsModal';

export default class ProjectLista extends Component {

    constructor(props) {
        super(props)
        this.state = {
            projects: [],
            search: "",
            activeProjects: false,
            currentPage: 1,
            pageSize: 8,
            inputCurrentPage: "1",
            selectedProject: {
                id: 0,
                developmentAreaName: "",
                projectStatusName: "",
                priorityName: ""
            },
            showModal: false
        };
        this.totalPages = 1;
    }

    componentDidMount() {
        this.getProjects();
    }

    getProjects = () => {
        axios.get(url("api/project_manager/projects"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ projects: data }));
    }

    getActiveProjects = () => {
        axios.get(url("api/project_manager/projects/active"), { headers: authHeader() })
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

    activeProjectsChange = () => {
        this.setState({
            activeProjects: !this.state.activeProjects
        });
    }

    refreshProjects = () => {
        if (this.state.activeProjects) {
            this.getActiveProjects();
        } else {
            this.getProjects();
        }
        this.setState({ currentPage: 1 });
    }

    changePage = event => {
        let pageNumber = event.target.value;
        this.setState({
            inputCurrentPage: pageNumber
        });

        if (pageNumber) {
            pageNumber = parseInt(pageNumber);

            if (pageNumber > 0 && pageNumber <= this.totalPages) {
                this.setState({
                    [event.target.name]: pageNumber
                });
            }
        }
    }

    firstPage = () => {
        this.setState({
            currentPage: 1,
            inputCurrentPage: 1
        });
    }

    prevPage = () => {
        this.setState({
            currentPage: this.state.currentPage - 1,
            inputCurrentPage: this.state.currentPage - 1
        });
    }

    nextPage = () => {
        let nextPage = this.state.currentPage + 1;
        this.setState({
            currentPage: nextPage,
            inputCurrentPage: nextPage
        });
    }

    lastPage = () => {
        this.setState({
            currentPage: this.totalPages,
            inputCurrentPage: this.totalPages
        });
    }

    pageNumberFocus = () => {
        this.setState({
            inputCurrentPage: ""
        });
    }

    pageNumberFocusOut = () => {
        this.setState({
            inputCurrentPage: this.state.currentPage
        });
    }

    showProjectDetails = (project) => {
        this.setState({
            selectedProject: {
                id: project.id,
                name: project.name,
                developmentAreaName: project.developmentArea.name,
                projectStatusName: project.projectStatus.name,
                priorityName: project.priority.name,
                description: project.description
            },
            showModal: true
        });
    }

    closeProjectDetails = () => {
        this.setState({
            selectedProject: {},
            showModal: false
        });
    }

    render() {
        const { search, activeProjects, projects, pageSize, currentPage, inputCurrentPage, selectedProject, showModal } = this.state;

        let filteredProjects = projects.filter(
            (project) => {
                return project.name.toLowerCase().indexOf(search.toLowerCase()) !== -1
                    || project.customer.name.toLowerCase().indexOf(search.toLowerCase()) !== -1
                    || project.orderDate.toLowerCase().indexOf(search.toLowerCase()) !== -1
                    || project.deadline.toLowerCase().indexOf(search.toLowerCase()) !== -1
                    || project.developmentArea.name.toLowerCase().indexOf(search.toLowerCase()) !== -1
                    || project.projectStatus.name.toLowerCase().indexOf(search.toLowerCase()) !== -1
                    || project.priority.name.toLowerCase().indexOf(search.toLowerCase()) !== -1
                    || project.projectLeader.name.toLowerCase().indexOf(search.toLowerCase()) !== -1
                    || project.status.name.toLowerCase().indexOf(search.toLowerCase()) !== -1;
            }
        );

        const lastIndex = currentPage * pageSize;
        const firstIndex = lastIndex - pageSize;
        const currentProjects = filteredProjects.sort((a, b) => a.id - b.id).slice(firstIndex, lastIndex);
        this.totalPages = Math.ceil(filteredProjects.length / pageSize);

        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Projektek</Card.Header>
                <Card.Body>
                    {showModal && <ProjectDetailsModal showModal={showModal} selectedProject={selectedProject} closeProjectDetails={this.closeProjectDetails} />}
                    <Link to={"/projects/add"}><Button variant="success">Projekt hozzáadása</Button></Link>
                    <div id="projektSearch">
                        <span>Keresés:</span>
                        <Form.Control required
                            id="search"
                            type="text" name="search"
                            value={search}
                            autoComplete="off"
                            onChange={this.projectListChange}
                            className={"bg-dark text-white"}
                            placeholder="Add meg a keresendő szöveget" />
                    </div>
                    <Table className="marginTop" bordered hover striped variant="dark">
                        <thead>
                            <tr>
                                <th>Név</th>
                                <th>Megrendelő</th>
                                <th>Megrendelés dátuma</th>
                                <th>Határidő dátuma</th>
                                <th>Projektvezető</th>
                                <th>Státusz</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                currentProjects.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="10">Nincs elérhető projekt.</td>
                                    </tr>
                                    :
                                    currentProjects.map((project) => (
                                        <tr key={project.id}>
                                            <td className="clickable" onClick={() => this.showProjectDetails(project)}>{project.name}</td>
                                            <td className="clickable" onClick={() => this.showProjectDetails(project)}>{project.customer.name}</td>
                                            <td className="clickable" onClick={() => this.showProjectDetails(project)}>{project.orderDate}</td>
                                            <td className="clickable" onClick={() => this.showProjectDetails(project)}>{project.deadline}</td>
                                            <td className="clickable" onClick={() => this.showProjectDetails(project)}>{project.projectLeader.name}</td>
                                            <td className="clickable" onClick={() => this.showProjectDetails(project)}>{project.status.name}</td>
                                            <td>
                                                <Link to={"projects/edit/" + project.id} className="mr-2 btn btn-sm btn-outline-primary">Módosít</Link>
                                                <Button variant="outline-danger" onClick={this.deleteProject.bind(this, project.id)} size="sm">Töröl</Button>
                                            </td>
                                        </tr>
                                    ))
                            }
                        </tbody>
                    </Table>
                    <div style={{ "float": "left" }}>
                        Oldalszám: {currentPage} / {this.totalPages}
                    </div>
                    <div style={{ "float": "right" }}>
                        <InputGroup id="pagination" size="sm">
                            <InputGroup.Prepend>
                                <Button id="pageButtons" variant="outline-info" disabled={currentPage <= 1 ? true : false}
                                    onClick={this.firstPage}>
                                    Első
                                </Button>
                                <Button id="pageButtons" variant="outline-info" disabled={currentPage <= 1 ? true : false}
                                    onClick={this.prevPage}>
                                    Elöző
                                </Button>
                            </InputGroup.Prepend>
                            <FormControl id="projectListPageNumberInput" type="number" min={1} className="bg-dark" name="currentPage" onFocus={this.pageNumberFocus} onBlur={this.pageNumberFocusOut} value={inputCurrentPage}
                                onChange={this.changePage} />
                            <InputGroup.Prepend>
                                <Button id="pageButtons" variant="outline-info" disabled={currentPage >= this.totalPages ? true : false}
                                    onClick={this.nextPage}>
                                    Következő
                                </Button>
                                <Button id="pageButtons" variant="outline-info" disabled={currentPage >= this.totalPages ? true : false}
                                    onClick={this.lastPage}>
                                    Utolsó
                                </Button>
                            </InputGroup.Prepend>
                        </InputGroup>
                    </div>
                </Card.Body>
                <Card.Footer>
                    <div id="projectListRefresh">
                        <span className="clickable" onClick={() => this.activeProjectsChange()}>Aktív projektek:</span>
                        <input type="checkbox" className="clickable" name="activeProjects" id="activeProjects" checked={activeProjects} value={activeProjects} onChange={this.activeProjectsChange} />
                        <Button variant="primary" onClick={() => this.refreshProjects()}>Frissítés</Button>
                    </div>
                </Card.Footer>
            </Card>
        )
    }
}