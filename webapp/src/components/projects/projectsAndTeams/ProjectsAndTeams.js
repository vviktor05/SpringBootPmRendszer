import React, { Component } from 'react';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Card, Form, Button, Col } from 'react-bootstrap';
import * as RiIcons from 'react-icons/ri';
import { url } from '../../../util/BackendURL';
import authHeader from '../../../helpers/authHeader';
import './ProjectsAndTeams.css'

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

export default class ProjectsAndTeams extends Component {
    state = {
        selectedProjectId: 1,
        projects: [],
        selectedTeams: [],
        teams: []
    };

    componentDidMount() {
        axios.get(url("api/project_manager/projects"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ projects: data }));

        this.getLists(this.state.selectedProjectId);
    }

    getLists(projectId) {
        axios.get(url("api/project_manager/teams/working_on/project_id/" + projectId), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ selectedTeams: data }),
                error => {
                    this.setState({
                        selectedTeams: []
                    });
                });

        axios.get(url("api/project_manager/teams/not_working_on/project_id/" + projectId), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ teams: data }),
                error => {
                    this.setState({
                        teams: []
                    });
                });
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
            const teams = this.reorder(
                this.state[source.droppableId],
                source.index,
                destination.index
            );

            let state = { selectedTeams: teams };

            if (source.droppableId === 'teams') {
                state = { teams };
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
                selectedTeams: result.selectedTeams,
                teams: result.teams
            });
        }
    };

    projectChange = (event) => {
        const projectId = event.target.value;

        this.setState({
            selectedProjectId: projectId
        });

        this.getLists(projectId);
    }

    save = () => {
        const { selectedProjectId, selectedTeams } = this.state;

        axios.put(url("api/project_manager/teams/working_on/project_id/" + selectedProjectId), selectedTeams, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A projekten dolgozó csapatok elmentve!");
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
        const { selectedProjectId, projects } = this.state;

        return (
            <Card className="projectsAndTeams border border-dark bg-dark text-white">
                <Card.Header>Projekten dolgozó csapatok</Card.Header>
                <Card.Body>
                    <div className="projectsAndTeams__dragDropContext">
                        <Form onReset={this.resetProject} onSubmit={this.onSubmit} id="projectsAndTeamsForm">
                            <Form.Row>
                                <Form.Group as={Col} controlId="formProject">
                                    <Form.Label>Projekt</Form.Label>
                                    <Form.Control className={"bg-dark text-white"} as="select" name="selectedProjectId"
                                        value={selectedProjectId}
                                        onChange={this.projectChange}
                                    >
                                        {projects.sort((a, b) => a.id - b.id).map((project) => (
                                            <option key={project.id} value={project.id}>
                                                {project.name}
                                            </option>
                                        ))}
                                    </Form.Control>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <DragDropContext onDragEnd={this.onDragEnd}>
                                    <div className="projectsAndTeams__list">
                                        <Form.Label>Projekten dolgozó csapatok</Form.Label>
                                        <Droppable droppableId="selectedTeams">
                                            {(provided, snapshot) => (
                                                <div
                                                    ref={provided.innerRef}
                                                    style={getListStyle1(snapshot.isDraggingOver)}>
                                                    {this.state.selectedTeams.map((item, index) => (
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

                                    <div className="projectsAndTeams__list">
                                        <Form.Label>Csapatok</Form.Label>
                                        <Droppable droppableId="teams">
                                            {(provided, snapshot) => (
                                                <div
                                                    ref={provided.innerRef}
                                                    style={getListStyle(snapshot.isDraggingOver)}>
                                                    {this.state.teams.map((item, index) => (
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
                    <Link to={"/projects"}><Button variant="primary">Vissza</Button></Link>
                    <div className="formButtonsRight">
                        <Button size="bg" variant="success" onClick={() => this.save()}>Mentés</Button>
                    </div>
                </Card.Footer>
            </Card>
        );
    }
}