import React, { Component } from 'react';
import './LoginForm.css';
// import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
// import axios from 'axios';
// import { url } from '../util/BackendURL';
// import { userService } from '../services/userService';

export default class Project extends Component {
    constructor(props) {
        super(props);

        // userService.logout();

        this.state = {
            email: '',
            password: ''
        };

        this.loginChange = this.loginChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(event) {
        event.preventDefault();

        if (this.checkDetails()) {
            // const user = {
            //     email: this.state.email,
            //     password: this.state.password,
            // }

            // axios.post(url("api/project_manager/projects"), project)
            //     .then(response => {
            //         if (response.status === 200) {
            //             this.resetProject();
            //             alert("A project elmentve!");
            //             this.projectList();
            //         }
            //     });
            this.props.history.push("/projects");
        }
    }

    checkDetails() {
        if (this.state.email.length < 5) {
            alert("Az email nem lehet rövidebb 5 karakternél!");
            return false;
        }

        return true;
    }

    loginChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {
        const { email, password } = this.state;

        return (
            <Card id="loginFormContainer" className="border border-dark bg-dark text-white">
                <Card.Header> Bejelentkezés </Card.Header>
                <Form onSubmit={this.onSubmit} id="projektForm">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formEmail">
                                <Form.Label>Email</Form.Label>
                                <Form.Control required
                                    type="email" name="email"
                                    value={email}
                                    autoComplete="off"
                                    onChange={this.loginChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg az email címed" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formPassword">
                                <Form.Label>Jelszó</Form.Label>
                                <Form.Control required
                                    type="password" name="password"
                                    value={password}
                                    autoComplete="off"
                                    onChange={this.loginChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a jelszavad" />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer>
                        <Button variant="primary" type="submit">Bejelentkezés</Button>
                    </Card.Footer>
                </Form>
            </Card >
        )
    }
}