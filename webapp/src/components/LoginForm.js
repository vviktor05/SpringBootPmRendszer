import React, { Component } from 'react';
import './LoginForm.css';
import { Card, Form, Button, Col } from 'react-bootstrap';
import authService from '../services/AuthService';

export default class LoginForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',
            loginFailed: false,
            showSuccessMessage: false
        };

        this.loginChange = this.loginChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(event) {
        event.preventDefault();

        const { email, password } = this.state;

        if (this.checkDetails()) {
            authService.executeBasicAuth(email, password)
                .then((response) => {
                    authService.registerSuccessfulLogin(JSON.stringify(response.data));
                })
                .catch(() => {
                    alert("Nem megfelelő adatok!");
                    this.setState({
                        showSuccessMessage: false,
                        hasLoginFailed: true,
                        password: ''
                    });
                })
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
        const { email, password, loginFailed, showSuccessMessage } = this.state;

        return (
            <Card id="loginFormContainer" className="border border-dark bg-dark text-white">
                <Card.Header> Bejelentkezés </Card.Header>
                <Form onSubmit={this.onSubmit} id="projektForm">
                    <Card.Body>
                        <Form.Row>
                            {loginFailed && <div className="alert alert-warning">Sikertelen bejelentkezés!</div>}
                            {showSuccessMessage && <div>Sikeres bejelentkezés!</div>}
                        </Form.Row>
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