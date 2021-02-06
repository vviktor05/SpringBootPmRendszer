import React, { Component } from 'react';
import '../customers/CustomerForm.css';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';

export default class TeamForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: '',
            name: '',
            teamLeaderId: -1,
            teamLeaderList: []
        };

        this.teamChange = this.teamChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    initialState = {
        id: '', name: '', teamLeaderId: -1
    }

    componentDidMount() {
        this.findTeamLeaderList();

        const teamId = +this.props.match.params.id;
        if (teamId) {
            this.findTeamById(teamId);
        }
    }

    findTeamLeaderList() {
        axios.get(url("api/project_manager/employees/team_leaders"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ teamLeaderList: data }));
    }

    findTeamById(teamId) {
        axios.get(url("api/project_manager/teams/id/" + teamId), { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    this.setState({
                        id: response.data.id,
                        name: response.data.name,
                        teamLeaderId: response.data.teamLeader.id,
                    });
                }
            }).catch((error) => {
                console.error("Hiba - " + error);
            });
    }

    onSubmit(event) {
        event.preventDefault();
        var { name, teamLeaderId, teamLeaderList } = this.state;

        if(teamLeaderId === -1){
            teamLeaderId = teamLeaderList[0].id;
        }

        if (this.checkDetails()) {
            const team = {
                name: name,
                teamLeader: this.findObjectInArray(teamLeaderId, teamLeaderList)
            }

            if (this.state.id) {
                this.editTeam(team);
            } else {
                this.addTeam(team);
            }
        }
    }

    findObjectInArray(id, array) {
        let idInt = parseInt(id);
        for (let i = 0; i < array.length; i++) {
            if (array[i].id === idInt) {
                return array[i];
            }
        }
    }

    addTeam(team) {
        axios.post(url("api/project_manager/teams"), team, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    this.resetTeam();
                    alert("A csapat elmentve!");
                    this.teamList();
                }
            });
    }

    editTeam(team) {
        axios.put(url("api/project_manager/teams/" + this.state.id), team, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    this.resetTeam();
                    alert("A csapat elmentve!");
                    this.teamList();
                }
            });
    }

    checkDetails() {
        if (this.state.name.length >= 3) {
            return true;
        } else {
            alert("A név nem lehet rövidebb 3 karakternél!");
        }
        return false;
    }

    teamChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    resetTeam = () => {
        this.setState(() => this.initialState);
    }

    teamList = () => {
        return this.props.history.push("/teams");
    }

    render() {
        const { name, teamLeaderId, teamLeaderList } = this.state;

        return (
            <Card id="teamFormContainer" className="border border-dark bg-dark text-white">
                <Card.Header>{this.state.id ? "Csapat módosítása" : "Csapat hozzáadása"}</Card.Header>
                <Form onReset={this.resetTeam} onSubmit={this.onSubmit} id="teamForm">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formName">
                                <Form.Label>Csapat neve</Form.Label>
                                <Form.Control required
                                    type="text" name="name" value={name}
                                    autoComplete="off"
                                    onChange={this.teamChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Add meg a csapat nevét" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formTeamLeader">
                                <Form.Label>Csapatvezető</Form.Label>
                                <Form.Control className={"bg-dark text-white"}
                                    as="select" name="teamLeaderId"
                                    value={teamLeaderId}
                                    onChange={this.teamChange}>
                                    {
                                        teamLeaderList.map((teamLeader) => (
                                            <option key={teamLeader.id} value={teamLeader.id}>
                                                {teamLeader.name}
                                            </option>
                                        ))
                                    }
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer>
                        <Link to={"/teams"}><Button variant="primary">Vissza</Button></Link>
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