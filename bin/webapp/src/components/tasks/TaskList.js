import React, { Component } from 'react';
import { Card } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';
import MyTable from '../MyTable';

export default class TaskList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tasks: []
        };
    }

    componentDidMount() {
        axios.get(url("api/project_manager/tasks"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ tasks: data }));
    }

    deleteTask = (taskId) => {
        axios.delete(url("api/project_manager/tasks/" + taskId), { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A feladat törölve!");
                    this.setState({
                        tasks: this.state.tasks.filter(task => task.id !== taskId)
                    });
                }
            });
    }

    render() {
        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Feladatok</Card.Header>
                <Card.Body>
                    <MyTable columns={[
                        {
                            label: 'Tárgy',
                            jsonFieldName: 'topic'
                        },
                        {
                            label: 'Határidő',
                            jsonFieldName: 'deadline'
                        },
                        {
                            label: 'Csapatvezető',
                            jsonFieldName: 'teamLeader.name'
                        },
                        {
                            label: 'Projekt',
                            jsonFieldName: 'project.name'
                        },
                        {
                            label: 'Státusz',
                            jsonFieldName: 'status.name'
                        }
                    ]}
                        datas={this.state.tasks}
                        // addButtonLink={"/tasks/add"}
                        addButtonTitle={"Feladat hozzáadása"}
                        // editButtonLink={"/tasks/edit"}
                        deleteButtonOnClick={this.deleteTask} />
                </Card.Body>
            </Card>
        )
    }
}