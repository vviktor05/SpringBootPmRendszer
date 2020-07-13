import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import axios from 'axios';

export default class Customer extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.customerChange = this.customerChange.bind(this);
        this.submitCustomer = this.submitCustomer.bind(this);
    }

    initialState = {
        id: '', name: '', phone: '', email: '', website: '', zipCode: '', locality: '', streetAddress: ''
    }

    componentDidMount() {
        const customerId = +this.props.match.params.id;
        if (customerId) {
            this.findProjectById(customerId);
        }
    }

    findProjectById = (customerId) => {
        axios.get("/api/project_manager/customers/id/" + customerId)
            .then(response => {
                if (response.data != null) {
                    this.setState({
                        id: response.data.id,
                        name: response.data.name,
                        phone: response.data.phone,
                        email: response.data.email,
                        website: response.data.website,
                        zipCode: response.data.zipCode,
                        locality: response.data.locality,
                        streetAddress: response.data.streetAddress
                    });
                }
            }).catch((error) => {
                console.error("Hiba - " + error);
            });
    }

    resetCustomer = () => {
        this.setState(() => this.initialState);
    }

    submitCustomer = (event) => {
        event.preventDefault();

        const customer = {
            name: this.state.name,
            phone: this.state.phone,
            email: this.state.email,
            website: this.state.website,
            zipCode: this.state.zipCode,
            locality: this.state.locality,
            streetAddress: this.state.streetAddress,
        }

        axios.post("/api/project_manager/customers", customer)
            .then(response => {
                if (response.status === 200) {
                    this.setState(this.initialState);
                    alert("A megrendelő elmentve!");
                }
            });
    }

    updateCustomer = (event) => {
        event.preventDefault();

        const customer = {
            name: this.state.name,
            phone: this.state.phone,
            email: this.state.email,
            website: this.state.website,
            zipCode: this.state.zipCode,
            locality: this.state.locality,
            streetAddress: this.state.streetAddress,
        }

        axios.put("/api/project_manager/customers/" + this.state.id, customer)
            .then(response => {
                if (response.status === 200) {
                    this.setState(this.initialState);
                    alert("A megrendelő elmentve!");
                    this.customerList();
                }
            });
    }

    customerChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    customerList = () => {
        return this.props.history.push("/customers");
    }

    render() {
        const { name, phone, email, website, zipCode, locality, streetAddress } = this.state;

        return (
            <Card style={{ width: "75%", margin: "0px auto" }} className="border border-dark bg-dark text-white">
                <Card.Header>{this.state.id ? "Megrendelő módosítása" : "Megrendelő hozzáadása"}</Card.Header>
                <Form onReset={this.resetCustomer} onSubmit={this.state.id ? this.updateCustomer : this.submitCustomer} id="customerForm">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Megrendelő neve</Form.Label>
                                <Form.Control required
                                    type="text" name="name" value={name}
                                    autoComplete="off"
                                    onChange={this.customerChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a megrendelő nevét" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Megrendelő telefonszáma</Form.Label>
                                <Form.Control required
                                    type="text" name="phone" value={phone}
                                    autoComplete="off"
                                    onChange={this.customerChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a megrendelő telefonszámát" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Megrendelő email címe</Form.Label>
                                <Form.Control required
                                    type="text" name="email" value={email}
                                    autoComplete="off"
                                    onChange={this.customerChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a megrendelő email címét" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Megrendelő weboldala</Form.Label>
                                <Form.Control required
                                    type="text" name="website" value={website}
                                    autoComplete="off"
                                    onChange={this.customerChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a megrendelő weboldalát" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Megrendelő irányítószáma</Form.Label>
                                <Form.Control required
                                    type="text" name="zipCode" value={zipCode}
                                    autoComplete="off"
                                    onChange={this.customerChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a megrendelő irányítószámát" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Helység neve</Form.Label>
                                <Form.Control required
                                    type="text" name="locality" value={locality}
                                    autoComplete="off"
                                    onChange={this.customerChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a helység nevét" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Utca, házszám</Form.Label>
                                <Form.Control required
                                    type="text" name="streetAddress" value={streetAddress}
                                    autoComplete="off"
                                    onChange={this.customerChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg az utcát és a házszámot" />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer>
                        <Link to={"/customers"}><Button variant="primary">Vissza</Button></Link>
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