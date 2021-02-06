import React, { Component } from 'react';
import './Employees.css';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';

export default class EmployeesForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: 0,
            name: '',
            email: '',
            jobId: 1,
            developmentAreaId: 1,
            skillId: 1,
            startDate: '',
            phoneNumber: '',
            jobList: [],
            developmentAreaList: [],
            skillList: []
        };

        this.employeeChange = this.employeeChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    initialState = {
        id: 0, name: '', email: '', jobId: 1, developmentAreaId: 1, skillId: 1, startDate: '', phoneNumber: ''
    }

    componentDidMount() {
        this.findAllLists();

        const employeeId = +this.props.match.params.id;
        if (employeeId) {
            this.findTaskById(employeeId);
        }
    }

    findAllLists() {
        axios.get(url("api/project_manager/jobs"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ jobList: data }));

        axios.get(url("api/project_manager/development_areas"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ developmentAreaList: data }));

        axios.get(url("api/project_manager/skills"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ skillList: data }));
    }

    findTaskById = (employeeId) => {
        axios.get(url("api/project_manager/employees/id/" + employeeId), { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    this.setState({
                        id: response.data.id,
                        name: response.data.name,
                        email: response.data.email,
                        jobId: response.data.job.id,
                        developmentAreaId: response.data.developmentArea.id,
                        skillId: response.data.skill.id,
                        startDate: response.data.startDate,
                        phoneNumber: response.data.phoneNumber
                    });
                }
            }).catch((error) => {
                console.error("Hiba - " + error);
            });
    }

    onSubmit = (event) => {
        event.preventDefault();

        if (this.checkDetails()) {
            var { id, name, email, jobId, developmentAreaId, skillId, startDate,
                phoneNumber, jobList, developmentAreaList, skillList } = this.state;

            const employee = {
                name: name,
                email: email,
                job: this.findObjectInArray(jobId, jobList),
                developmentArea: this.findObjectInArray(developmentAreaId, developmentAreaList),
                skill: this.findObjectInArray(skillId, skillList),
                startDate: startDate,
                phoneNumber: phoneNumber
            }

            if (id) {
                this.editEmployee(employee);
            } else {
                this.addEmployee(employee);
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

    addEmployee = (employee) => {
        axios.post(url("api/project_manager/employees"), employee, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A dologzó elmentve!");
                    this.resetEmployee();
                    this.employeeList();
                }
            });
    }

    editEmployee = (employee) => {
        axios.put(url("api/project_manager/employees/" + this.state.id), employee, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A dologzó elmentve!");
                    this.resetEmployee();
                    this.employeeList();
                }
            });
    }

    checkDetails() {
        if (this.state.name.length >= 5) {
            if (this.state.phoneNumber.length >= 3) {
                if (this.state.name.match("^[a-zA-Z áéíóöőúüűÁÉÍÓÖŐÚÜŰ]+$")) {
                    if (this.state.phoneNumber.match("^[\\d]*$")) {
                        return true;
                    } else {
                        alert("A telefonszám csak számot tartalmazhat!");
                    }
                } else {
                    alert("A név csak kis és nagy betűket tartalmazhat!");
                }
            } else {
                alert("A dolgozó telefonszáma nem lehet rövidebb 3 karakternél!");
            }
        } else {
            alert("A dolgozó neve nem lehet rövidebb 5 karakternél!");
        }

        return false;
    }

    employeeChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    employeeList = () => {
        return this.props.history.push("/employees");
    }

    resetEmployee = () => {
        this.setState(() => this.initialState);
    }

    render() {
        const { id, name, email, jobId, developmentAreaId, skillId, startDate,
            phoneNumber, jobList, developmentAreaList, skillList } = this.state;

        return (
            <Card id="employeeFormContainer" className="border border-dark bg-dark text-white">
                <Card.Header> {id ? "Dolgozó módosítása" : "Dolgozó hozzáadása"} </Card.Header>
                <Form onReset={this.resetEmployee} onSubmit={this.onSubmit} id="employeeForm">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Dolgozó neve</Form.Label>
                                <Form.Control required
                                    type="text" name="name"
                                    value={name}
                                    autoComplete="off"
                                    onChange={this.employeeChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a dolgozó nevét" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formEmail">
                                <Form.Label>Dolgozó e-mail címe</Form.Label>
                                <Form.Control required
                                    type="text" name="email"
                                    value={email}
                                    autoComplete="off"
                                    onChange={this.employeeChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a dolgozó e-mail címét" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formJob">
                                <Form.Label>Beosztás</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="jobId"
                                    value={jobId}
                                    onChange={this.employeeChange}>
                                    {jobList.sort((a, b) => a.id - b.id).map((job) => (
                                        <option key={job.id} value={job.id}>
                                            {job.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group as={Col} controlId="formDevelopmentArea">
                                <Form.Label>Fejlesztési terület</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="developmentAreaId"
                                    value={developmentAreaId}
                                    onChange={this.employeeChange}
                                >
                                    {developmentAreaList.sort((a, b) => a.id - b.id).map((developmentArea) => (
                                        <option key={developmentArea.id} value={developmentArea.id}>
                                            {developmentArea.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formSkill">
                                <Form.Label>Tapasztalati szint</Form.Label>
                                <Form.Control className={"bg-dark text-white"} as="select" name="skillId"
                                    value={skillId}
                                    onChange={this.employeeChange}
                                >
                                    {skillList.sort((a, b) => a.id - b.id).map((skill) => (
                                        <option key={skill.id} value={skill.id}>
                                            {skill.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group as={Col} controlId="formPhoneNumber">
                                <Form.Label>Dolgozó telefonszáma</Form.Label>
                                <Form.Control required
                                    type="text" name="phoneNumber"
                                    value={phoneNumber}
                                    autoComplete="off"
                                    onChange={this.employeeChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a dolgozó telefonszámát" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formStartDate">
                                <Form.Label>Kezdés dátuma</Form.Label>
                                <Form.Control
                                    required
                                    value={startDate}
                                    onChange={this.employeeChange}
                                    type="date"
                                    className={"bg-dark text-white"}
                                    name="startDate" />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer>
                        <Link to={"/employees"}><Button variant="primary">Vissza</Button></Link>
                        <div className="formButtonsRight">
                            <Button size="bg" variant="success" type="submit">Mentés</Button>
                            {id ? null : <Button className="defaultButtonMarginLeft" size="bg" variant="info" type="reset">Alaphelyzet</Button>}
                        </div>
                    </Card.Footer>
                </Form>
            </Card >
        )
    }
}