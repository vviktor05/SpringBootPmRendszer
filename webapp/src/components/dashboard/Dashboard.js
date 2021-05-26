import React, { Component } from 'react';
import { Card, Table } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';
import './Dashboard.css';
import ProjectBarChart from "./charts/ProjectBarChart";
import ProjectPieChart from "./charts/ProjectPieChart";
import * as AiIcons from 'react-icons/ai';
import * as FaIcons from 'react-icons/fa';
import * as TiIcons from 'react-icons/ti';
import * as IoIcons from 'react-icons/io';
import MediaQuery from 'react-responsive'

class Dashboard extends Component {
    state = {
        activeProjects: 0,
        activeTasks: 0,
        customers: 0,
        employees: 0,
        projects: []
    }

    componentDidMount() {
        axios.get(url("api/project_manager/projects"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ projects: data }));

        axios.get(url("api/project_manager/projects/active/number_of"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ activeProjects: data }));

        axios.get(url("api/project_manager/tasks/active/number_of"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ activeTasks: data }));

        axios.get(url("api/project_manager/customers/number_of"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ customers: data }));

        axios.get(url("api/project_manager/employees/number_of"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ employees: data }));
    }

    getColor(projectStatusId) {
        let result = "";
        switch (projectStatusId) {
            case 1:
                result = "red";
                break;
            case 2:
                result = "purple";
                break;
            case 3:
                result = "blue";
                break;
            case 4:
                result = "yellow";
                break;
            case 5:
                result = "green";
                break;
            default:
                result = "orange";
        }
        return result;
    }

    render() {
        const { activeProjects, activeTasks, employees, customers, projects } = this.state;

        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Irányítópult</Card.Header>
                <Card.Body>
                    <div className="dashboard">
                        <div className="dashboard__mainCards">
                            <div className="dashboard__card">
                                <div className="dashboard__cardLeft">
                                    <AiIcons.AiOutlineProject className="dashboard__icon" size="50px" />
                                    <p>Aktív projektek</p>
                                </div>
                                <span>{activeProjects}</span>
                            </div>

                            <div className="dashboard__card">
                                <div className="dashboard__cardLeft">
                                    <FaIcons.FaTasks className="dashboard__icon" size="50px" />
                                    <p>Aktív feladatok</p>
                                </div>
                                <span>{activeTasks}</span>
                            </div>

                            <div className="dashboard__card">
                                <div className="dashboard__cardLeft">
                                    <TiIcons.TiBusinessCard className="dashboard__icon" size="50px" />
                                    <p>Megrendelők</p>
                                </div>
                                <span>{employees}</span>
                            </div>

                            <div className="dashboard__card">
                                <div className="dashboard__cardLeft">
                                    <IoIcons.IoMdPeople className="dashboard__icon" size="50px" />
                                    <p>Dolgozók</p>
                                </div>
                                <span>{customers}</span>
                            </div>
                        </div>

                        <div className="dashboard__chart">
                            <div className="dashboard__chartLeft">
                                <div>
                                    <h1>Utolsó 5 év adatai</h1>
                                    <p>Projektek száma az utolsó 5 évben</p>
                                </div>
                                <div className="dashboard-chart">
                                    <MediaQuery maxWidth={1860}>
                                        <ProjectBarChart projects={projects} height={300} width={440} />
                                    </MediaQuery>
                                    <MediaQuery minWidth={1860}>
                                        <ProjectBarChart projects={projects} height={400} width={725} />
                                    </MediaQuery>
                                </div>
                            </div>
                            <div className="dashboard__chartRight">
                                <div>
                                    <h1>Projektek fejlesztési területei</h1>
                                    <p>Projektek megoszlása a különböző fejlesztési területek között</p>
                                </div>
                                <div className="dashboard-chart">
                                    <MediaQuery maxWidth={1860}>
                                        <ProjectPieChart projects={projects} height={300} width={440} />
                                    </MediaQuery>
                                    <MediaQuery minWidth={1860}>
                                        <ProjectPieChart projects={projects} height={400} width={725} />
                                    </MediaQuery>
                                </div>
                            </div>
                        </div>

                        <div className="dashboard__tableContainer">
                            <h1>Projektek státusza</h1>
                            <div className="dashboard__table marginTop">
                                <Table bordered hover striped variant="dark">
                                    <thead>
                                        <tr>
                                            <th>Projekt</th>
                                            <th>Megrendelő</th>
                                            <th>Határidő dátuma</th>
                                            <th>Státusz</th>
                                            <th>Projekt státusz</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            projects.length === 0 ?
                                                <tr align="center">
                                                    <td colSpan="5">Nincs elérhető projekt.</td>
                                                </tr>
                                                :
                                                projects.filter((project) => project.projectStatus.id !== 6).sort((a, b) => b.projectStatus.id - a.projectStatus.id).map((project) => (
                                                    <tr key={project.id}>
                                                        <td>{project.name}</td>
                                                        <td>{project.customer.name}</td>
                                                        <td>{project.deadline}</td>
                                                        <td>{project.status.name}</td>
                                                        <td><progress className={"progress progress-" + this.getColor(project.projectStatus.id)} value={project.projectStatus.id} max="6"></progress></td>
                                                    </tr>
                                                ))
                                        }
                                    </tbody>
                                </Table>
                            </div>
                        </div>
                    </div>
                </Card.Body>
            </Card >
        );
    }
}

export default Dashboard;