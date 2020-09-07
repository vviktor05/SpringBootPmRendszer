import React, { Component } from 'react';
import './CustomerForm.css';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../util/BackendURL';

export default class CustomerForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: '',
            name: '',
            phone: '',
            email: '',
            website: '',
            zipCode: '',
            locality: '',
            streetAddress: ''
        };

        this.customerChange = this.customerChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    initialState = {
        id: '', name: '', phone: '', email: '', website: '', zipCode: '', locality: '', streetAddress: ''
    }

    componentDidMount() {
        const customerId = +this.props.match.params.id;
        if (customerId) {
            this.findCustomerById(customerId);
        }
    }

    findCustomerById(customerId) {
        axios.get(url("api/project_manager/customers/id/" + customerId))
            .then(response => {
                if (response.status === 200) {
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

    onSubmit(event) {
        event.preventDefault();

        if (this.checkDetails()) {
            const customer = {
                name: this.state.name,
                phone: this.state.phone,
                email: this.state.email,
                website: this.state.website,
                zipCode: this.state.zipCode,
                locality: this.state.locality,
                streetAddress: this.state.streetAddress,
            }

            if (this.state.id) {
                this.editCustomer(customer);
            } else {
                this.addCustomer(customer);
            }
        }
    }

    addCustomer(customer) {
        axios.post(url("api/project_manager/customers"), customer)
            .then(response => {
                if (response.status === 200) {
                    this.resetCustomer();
                    alert("A megrendelő elmentve!");
                    this.customerList();
                }
            });
    }

    editCustomer(customer) {
        axios.put(url("api/project_manager/customers/" + this.state.id), customer)
            .then(response => {
                if (response.status === 200) {
                    this.resetCustomer();
                    alert("A megrendelő elmentve!");
                    this.customerList();
                }
            });
    }

    checkDetails() {
        if (this.state.name.length >= 5) {
            if (this.state.phone.length >= 5) {
                if (this.state.email.length >= 5) {
                    if (this.state.website.length >= 5) {
                        if (this.state.zipCode.length >= 3) {
                            if (this.state.locality.length >= 3) {
                                if (this.state.streetAddress.length >= 5) {
                                    return true;
                                } else {
                                    alert("Az utca, házszám nem lehet rövidebb 5 karakternél!");
                                }
                            } else {
                                alert("A helység neve nem lehet rövidebb 3 karakternél!");
                            }
                        } else {
                            alert("Az irányítószám nem lehet rövidebb 3 karakternél!");
                        }
                    } else {
                        alert("A weboldal nem lehet rövidebb 5 karakternél!");
                    }
                } else {
                    alert("Az email nem lehet rövidebb 5 karakternél!");
                }
            } else {
                alert("A telefonszám nem lehet rövidebb 5 karakternél!");
            }
        } else {
            alert("A név nem lehet rövidebb 5 karakternél!");
        }
        return false;
    }

    customerChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    resetCustomer = () => {
        this.setState(() => this.initialState);
    }

    customerList = () => {
        return this.props.history.push("/customers");
    }

    render() {
        const { name, phone, email, website, zipCode, locality, streetAddress } = this.state;

        return (
            <Card id="customerFormContainer" className="border border-dark bg-dark text-white">
                <Card.Header>{this.state.id ? "Megrendelő módosítása" : "Megrendelő hozzáadása"}</Card.Header>
                <Form onReset={this.resetCustomer} onSubmit={this.onSubmit} id="customerForm">
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
                        <div className="formButtonsRight">
                            <Button size="bg" variant="success" type="submit">Mentés</Button>
                            {this.state.id ? null : <Button className="defaultButtonMarginLeft" size="bg" variant="info" type="reset">Alaphelyzet</Button>}
                        </div>
                    </Card.Footer>
                </Form>
            </Card >
        )
    }
}