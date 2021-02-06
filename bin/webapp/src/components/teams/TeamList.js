import React, { Component } from 'react';
import { Card } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';
import MyTable from '../MyTable';

export default class TeamList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            teams: []
        };
    }

    componentDidMount() {
        axios.get(url("api/project_manager/teams"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ teams: data }));
    }

    deleteTeam = (teamId) => {
        axios.delete(url("api/project_manager/teams/" + teamId), { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A csapat törölve!");
                    this.setState({
                        teams: this.state.teams.filter(team => team.id !== teamId)
                    });
                }
            });
    }

    render() {
        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Csapatok</Card.Header>
                <Card.Body>
                    <MyTable columns={[
                        {
                            label: 'Név',
                            jsonFieldName: 'name'
                        },
                        {
                            label: 'Csapatvezető',
                            jsonFieldName: 'teamLeader.name'
                        }
                    ]}
                        datas={this.state.teams}
                        addButtonLink={"/teams/add"}
                        addButtonTitle={"Csapat hozzáadása"}
                        editButtonLink={"/teams/edit"}
                        deleteButtonOnClick={this.deleteTeam} />
                </Card.Body>
            </Card>
        )
    }
}