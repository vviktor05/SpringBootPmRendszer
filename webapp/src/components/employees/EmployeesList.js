import React, { Component } from 'react';
import { Card } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';
import MyTable from '../MyTable';

export default class EmployeesList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            employees: []
        };
    }

    componentDidMount() {
        axios.get(url("api/project_manager/employees"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ employees: data }));
    }

    deleteTask = (employeeId) => {
        axios.delete(url("api/project_manager/employees/" + employeeId), { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A dolgozó törölve!");
                    this.setState({
                        employees: this.state.employees.filter(employee => employee.id !== employeeId)
                    });
                }
            });
    }

    render() {
        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Dolgozók</Card.Header>
                <Card.Body>
                    <MyTable columns={[
                        {
                            label: 'Név',
                            jsonFieldName: 'name'
                        },
                        {
                            label: 'E-mail',
                            jsonFieldName: 'email'
                        },
                        {
                            label: 'Beosztás',
                            jsonFieldName: 'job.name'
                        },
                        {
                            label: 'Fejlesztési terület',
                            jsonFieldName: 'developmentArea.name'
                        },
                        {
                            label: 'Tapasztalati szint',
                            jsonFieldName: 'skill.name'
                        },
                        {
                            label: 'Kezdés dátuma',
                            jsonFieldName: 'startDate'
                        },
                        {
                            label: 'Telefonszám',
                            jsonFieldName: 'phoneNumber'
                        },
                        {
                            label: 'Utolsó belépés',
                            jsonFieldName: 'lastLoginDate'
                        }
                    ]}
                        datas={this.state.employees}
                        addButtonLink={"/employees/add"}
                        addButtonTitle={"Dolgozó hozzáadása"}
                        editButtonLink={"/employees/edit"}
                        deleteButtonOnClick={this.deleteTask} />
                </Card.Body>
            </Card>
        )
    }
}