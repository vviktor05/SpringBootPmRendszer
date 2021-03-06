import React, { Component } from 'react';
import { Card, Form, Button, Col } from 'react-bootstrap';
import './LoginForm.css';
import authService from '../../services/AuthService';
import Context from '../../store/Context';

export default class LoginForm extends Component {
    static contextType = Context;

    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            loading: false,
            message: ""
        };

        this.loginChange = this.loginChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(event) {
        event.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        const { email, password } = this.state;
        const { globalDispatch } = this.context;

        if (this.checkDetails()) {
            authService.login(email, password).then(
                data => {
                    globalDispatch({ type: "LOGIN", payload: data });
                    globalDispatch({ type: "SHOW_SIDEBAR" });
                    this.props.history.push("/");
                },
                error => {
                    const resMessage =
                        error.response?.data?.message ||
                        error.message ||
                        error.toString();
                    console.log(resMessage);

                    this.setState({
                        password: "",
                        loading: false,
                        message: "Nem megfelelő adatok!"
                    });
                }
            );
        } else {
            this.setState({
                loading: false
            });
        }
    }

    checkDetails() {
        if (this.state.email.length < 5) {
            this.setState({
                message: "Az email nem lehet rövidebb 5 karakternél!"
            });
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
        const { email, password, loading, message } = this.state;

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
                        {message && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    {message}
                                </div>
                            </div>
                        )}
                    </Card.Body>
                    <Card.Footer>
                        <Button variant="primary" type="submit" disabled={loading}>
                            Bejelentkezés
                            {loading && <span className="spinner-border spinner-border-sm" />}
                        </Button>
                    </Card.Footer>
                </Form>
            </Card >
        )
    }
}