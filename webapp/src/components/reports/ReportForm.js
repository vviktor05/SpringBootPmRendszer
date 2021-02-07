import React, { Component } from 'react';
import './ReportForm.css';
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
            id: 0,
            projectId: 1,
            text: '',
            projectList: []
        };

        this.reportChange = this.reportChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    initialState = {
        id: 0, projectId: 1, employeeId: 1, text: ''
    }

    componentDidMount() {
        this.findAllLists();

        const reportId = +this.props.match.params.id;
        if (reportId) {
            this.findReportById(reportId);
        }
    }

    findAllLists() {
        axios.get(url("api/project_manager/projects"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ projectList: data }));
    }

    findReportById = (reportId) => {
        axios.get(url("api/project_manager/reports/id/" + reportId), { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    this.setState({
                        id: response.data.id,
                        projectId: response.data.project.id,
                        text: response.data.text
                    });
                }
            }).catch((error) => {
                console.error("Hiba - " + error);
            });
    }

    onSubmit = (event) => {
        event.preventDefault();

        if (this.checkDetails()) {
            var { id, projectId, text, projectList } = this.state;

            if (projectId === 1) {
                projectId = projectList[0].id;
            }

            const report = {
                project: this.findObjectInArray(projectId, projectList),
                recordingDate: this.getDateTime(),
                employee: authService.getLoggedInUser().employee,
                text: text
            }

            if (id) {
                this.editReport(report);
            } else {
                this.addReport(report);
            }
        }
    }

    getDateTime() {
        const today = new Date();
        var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
        var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
        var dateTime = date + ' ' + time;

        return dateTime;
    }

    findObjectInArray(id, array) {
        let idInt = parseInt(id);
        for (let i = 0; i < array.length; i++) {
            if (array[i].id === idInt) {
                return array[i];
            }
        }
    }

    addReport = (report) => {
        axios.post(url("api/project_manager/reports"), report, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A jelentés elmentve!");
                    this.resetReport();
                    this.reportList();
                }
            });
    }

    editReport = (report) => {
        axios.put(url("api/project_manager/reports/" + this.state.id), report, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A jelentés elmentve!");
                    this.resetReport();
                    this.reportList();
                }
            });
    }

    checkDetails() {
        // if (this.state.topic.length >= 5) {
        //     if (new Date() < new Date(this.state.deadline)) {
        //         return true;
        //     } else {
        //         alert("A határidőnek késöbbi időpontnak kell lennie, mint a jelenlegi dátum!");
        //     }
        // } else {
        //     alert("A feladat neve nem lehet rövidebb 5 karakternél!");
        // }
        return true;
    }

    reportChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    reportList = () => {
        return this.props.history.push("/reports");
    }

    resetReport = () => {
        this.setState(() => this.initialState);
    }

    render() {
        const { id, projectId, text, projectList } = this.state;

        return (
            <Card id="reportFormContainer" className="border border-dark bg-dark text-white">
                <Card.Header> {id ? "Jelentés módosítása" : "Jelentés hozzáadása"} </Card.Header>
                <Form onReset={this.resetReport} onSubmit={this.onSubmit} id="reportForm">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formProject">
                                <Form.Label>Projekt</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="projectId"
                                    value={projectId}
                                    onChange={this.reportChange}
                                >
                                    {projectList.sort((a, b) => a.id - b.id).map((project) => (
                                        <option key={project.id} value={project.id}>
                                            {project.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formText">
                                <Form.Label>Jelentés szövege</Form.Label>
                                <Form.Control as="textarea" rows="20"
                                    value={text}
                                    onChange={this.reportChange}
                                    placeholder="Add meg a jelentés szövegét."
                                    className={"bg-dark text-white"}
                                    name="text" />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer>
                        <Link to={"/reports"}><Button variant="primary">Vissza</Button></Link>
                        <div className="formButtonsRight">
                            {id ? null : <Button size="bg" variant="info" type="reset">Alaphelyzet</Button>}
                            <Button className="defaultButtonMarginLeft" size="bg" variant="success" type="submit">Mentés</Button>
                        </div>
                    </Card.Footer>
                </Form>
            </Card >
        )
    }
}