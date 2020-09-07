import React, { Component } from 'react';
import { Card, Table, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { url } from '../util/BackendURL';

export default class CustomerList extends Component {

    constructor(props) {
        super(props)
        this.state = {
            customers: []
        };
    }

    componentDidMount() {
        axios.get(url("api/project_manager/customers"))
            .then(response => response.data)
            .then((data) => this.setState({ customers: data }));
    }

    deleteCustomer = (customerId) => {
        axios.delete(url("api/project_manager/customers/" + customerId))
            .then(response => {
                if (response.status === 200) {
                    alert("A megrendelő törölve!");
                    this.setState({
                        customers: this.state.customers.filter(customer => customer.id !== customerId)
                    });
                }
            });
    }

    render() {
        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Megrendelők</Card.Header>
                <Card.Body>
                    <Link to={"/customers/add"}><Button variant="success">Megrendelő hozzáadása</Button></Link>
                    <Table bordered hover striped variant="dark" style={{ marginTop: "20px" }}>
                        <thead>
                            <tr>
                                <th>Név</th>
                                <th>Telefonszám</th>
                                <th>E-mail</th>
                                <th>Weboldal</th>
                                <th>Irányítószám</th>
                                <th>Helység</th>
                                <th>Utca, házszám</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.customers.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="10">Nincs elérhető megrendelő.</td>
                                    </tr> :
                                    this.state.customers.sort((a, b) => a.id - b.id).map((customer) => (
                                        <tr key={customer.id}>
                                            <td>{customer.name}</td>
                                            <td>{customer.phone}</td>
                                            <td>{customer.email}</td>
                                            <td>{customer.website}</td>
                                            <td>{customer.zipCode}</td>
                                            <td>{customer.locality}</td>
                                            <td>{customer.streetAddress}</td>
                                            <td>
                                                <Link to={"customers/edit/" + customer.id} className="mr-2 btn btn-sm btn-primary">Módosít</Link>
                                                <Button variant="danger" onClick={this.deleteCustomer.bind(this, customer.id)} size="sm">Töröl</Button>
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