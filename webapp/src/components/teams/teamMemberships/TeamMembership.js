import React, { Component } from 'react';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import * as RiIcons from 'react-icons/ri';
import { url } from '../../../util/BackendURL';
import authHeader from '../../../helpers/authHeader';
import './TeamMembership.css'

const getItemStyle = (isDragging, draggableStyle) => ({
    userSelect: 'none',
    padding: 15,
    marginBottom: 10,

    // change background colour if dragging
    background: isDragging ? 'rgba(81, 88, 96, 1)' : 'rgba(113, 120, 127, .2)',

    // styles we need to apply on draggables
    ...draggableStyle
});

const getListStyle1 = isDraggingOver => ({
    background: isDraggingOver ? 'rgba(113, 120, 127, .18)' : 'rgba(81, 88, 96, .2)',
    marginRight: '50px',
    padding: '10px',
    minHeight: '500px',
    width: '300px'
});

const getListStyle = isDraggingOver => ({
    background: isDraggingOver ? 'rgba(113, 120, 127, .18)' : 'rgba(81, 88, 96, .2)',
    padding: '10px',
    minHeight: '500px',
    width: '300px',
});

export default class TeamMembership extends Component {
    state = {
        selectedTeamId: 1,
        teams: [],
        selectedEmployees: [],
        employees: []
    };

    componentDidMount() {
        axios.get(url("api/project_manager/teams"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ teams: data }));

        this.getLists(this.state.selectedTeamId);
    }

    getLists(teamId) {
        axios.get(url("api/project_manager/employees/in_team/team_id/" + teamId), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ selectedEmployees: data }));

        axios.get(url("api/project_manager/employees/not_in_team/team_id/" + teamId), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ employees: data }));
    }

    // a little function to help us with reordering the result
    reorder = (list, startIndex, endIndex) => {
        const result = Array.from(list);
        const [removed] = result.splice(startIndex, 1);
        result.splice(endIndex, 0, removed);

        return result;
    };

    /**
     * Moves an item from one list to another list.
     */
    move = (source, destination, droppableSource, droppableDestination) => {
        const sourceClone = Array.from(source);
        const destClone = Array.from(destination);
        const [removed] = sourceClone.splice(droppableSource.index, 1);

        destClone.splice(droppableDestination.index, 0, removed);

        const result = {};
        result[droppableSource.droppableId] = sourceClone;
        result[droppableDestination.droppableId] = destClone;

        return result;
    };

    onDragEnd = result => {
        const { source, destination } = result;

        // dropped outside the list
        if (!destination) {
            return;
        }

        if (source.droppableId === destination.droppableId) {
            const employees = this.reorder(
                this.state[source.droppableId],
                source.index,
                destination.index
            );

            let state = { selectedEmployees: employees };

            if (source.droppableId === 'employees') {
                state = { employees: employees };
            }

            this.setState(state);
        } else {
            const result = this.move(
                this.state[source.droppableId],
                this.state[destination.droppableId],
                source,
                destination
            );

            this.setState({
                selectedEmployees: result.selectedEmployees,
                employees: result.employees
            });
        }
    };

    teamChange = (event) => {
        const teamId = event.target.value;

        this.setState({
            selectedTeamId: teamId
        });

        this.getLists(teamId);
    }

    save = () => {
        const { selectedTeamId, selectedEmployees } = this.state;

        axios.put(url("api/project_manager/employees/team_membership/team_id/" + selectedTeamId), selectedEmployees, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A csapattagok elmentve!");
                }
            });
    }

    findObjectInArray(id, array) {
        let idInt = parseInt(id);
        for (let i = 0; i < array.length; i++) {
            if (array[i].id === idInt) {
                return array[i];
            }
        }
    }

    render() {
        const { selectedTeamId, selectedEmployees, teams } = this.state;

        return (
            <Card className="teamMembership border border-dark bg-dark text-white">
                <Card.Header>Csapatok összeállítása</Card.Header>
                <Card.Body>
                    <div className="teamMembership__dragDropContext">
                        <Form id="teamMembershipForm">
                            <Form.Row>
                                <Form.Group as={Col} controlId="formTeamMembership">
                                    <Form.Label>Csapat</Form.Label>
                                    <Form.Control className={"bg-dark text-white"} as="select" name="selectedTeamId"
                                        value={selectedTeamId}
                                        onChange={this.teamChange}
                                    >
                                        {teams.sort((a, b) => a.id - b.id).map((team) => (
                                            <option key={team.id} value={team.id}>
                                                {team.name}
                                            </option>
                                        ))}
                                    </Form.Control>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <DragDropContext onDragEnd={this.onDragEnd}>
                                    <div className="teamMembership__list">
                                        <Form.Label>Csapattagok</Form.Label>
                                        <Droppable droppableId="selectedEmployees">
                                            {(provided, snapshot) => (
                                                <div
                                                    ref={provided.innerRef}
                                                    style={getListStyle1(snapshot.isDraggingOver)}>
                                                    {selectedEmployees.map((item, index) => (
                                                        <Draggable
                                                            key={item.id}
                                                            draggableId={"" + item.id}
                                                            index={index}>
                                                            {(provided, snapshot) => (
                                                                <div
                                                                    ref={provided.innerRef}
                                                                    {...provided.draggableProps}
                                                                    {...provided.dragHandleProps}
                                                                    style={getItemStyle(
                                                                        snapshot.isDragging,
                                                                        provided.draggableProps.style
                                                                    )}>
                                                                    <RiIcons.RiDragMove2Line className="teamMembership___icon" />
                                                                    {item.name}
                                                                </div>
                                                            )}
                                                        </Draggable>
                                                    ))}
                                                    {provided.placeholder}
                                                </div>
                                            )}
                                        </Droppable>
                                    </div>

                                    <div className="teamMembership__list">
                                        <Form.Label>Dolgozók</Form.Label>
                                        <Droppable droppableId="employees">
                                            {(provided, snapshot) => (
                                                <div
                                                    ref={provided.innerRef}
                                                    style={getListStyle(snapshot.isDraggingOver)}>
                                                    {this.state.employees.map((item, index) => (
                                                        <Draggable
                                                            key={item.id}
                                                            draggableId={"" + item.id}
                                                            index={index}>
                                                            {(provided, snapshot) => (
                                                                <div
                                                                    ref={provided.innerRef}
                                                                    {...provided.draggableProps}
                                                                    {...provided.dragHandleProps}
                                                                    style={getItemStyle(
                                                                        snapshot.isDragging,
                                                                        provided.draggableProps.style
                                                                    )}>
                                                                    <RiIcons.RiDragMove2Line className="projectsAndTeams___icon" />
                                                                    {item.name}
                                                                </div>
                                                            )}
                                                        </Draggable>
                                                    ))}
                                                    {provided.placeholder}
                                                </div>
                                            )}
                                        </Droppable>
                                    </div>
                                </DragDropContext>
                            </Form.Row>
                        </Form>
                    </div>
                </Card.Body>
                <Card.Footer>
                    <Link to={"/teams"}><Button variant="primary">Vissza</Button></Link>
                    <div className="formButtonsRight">
                        <Button size="bg" variant="success" onClick={() => this.save()}>Mentés</Button>
                    </div>
                </Card.Footer>
            </Card>
        );
    }
}