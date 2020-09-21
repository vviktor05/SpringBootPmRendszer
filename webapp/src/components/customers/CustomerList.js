import React, { Component } from 'react';
import { Card } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';
import MyTable from '../MyTable';

export default class CustomerList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            customers: []
        };
    }

    componentDidMount() {
        axios.get(url("api/project_manager/customers"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ customers: data }));
    }

    deleteCustomer = (customerId) => {
        axios.delete(url("api/project_manager/customers/" + customerId), { headers: authHeader() })
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
                    <MyTable columns={[
                        {
                            label: 'Név',
                            jsonFieldName: 'name'
                        },
                        {
                            label: 'Telefonszám',
                            jsonFieldName: 'phone'
                        },
                        {
                            label: 'E-mail',
                            jsonFieldName: 'email'
                        },
                        {
                            label: 'Weboldal',
                            jsonFieldName: 'website'
                        },
                        {
                            label: 'Irányítószám',
                            jsonFieldName: 'zipCode'
                        },
                        {
                            label: 'Helység',
                            jsonFieldName: 'locality'
                        },
                        {
                            label: 'Utca, házszám',
                            jsonFieldName: 'streetAddress'
                        }
                    ]}
                        datas={this.state.customers}
                        addButtonLink={"/customers/add"}
                        addButtonTitle={"Megrendelő hozzáadása"}
                        editButtonLink={"/customers/edit"}
                        deleteButtonOnClick={this.deleteCustomer} />
                </Card.Body>
            </Card>
        )
    }
}