import React, { Component } from 'react';
import ModalTeamsTable from './ModalTeamsTable';
import ModalEmployeeTable from './ModalEmployeeTable';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';
import './ProjectDetails.css';

export default class ProjectDetailsModal extends Component {
  constructor(props) {
    super(props)
    this.state = {
      projectId: props.selectedProject.id,
      teams: [],
      selectedTeamID: 0,
      employees: [],
      teamLeaderName: ""
    }
  }

  componentDidMount() {
    this.getTeams();
  }

  getTeams = () => {
    axios.get(url("api/project_manager/teams/working_on/project_id/" + this.state.projectId), { headers: authHeader() })
      .then(response => response.data)
      .then((data) => { this.setState({ teams: data, teamLeaderName: data[0].teamLeader.name }); this.getEmployees(data[0].id) });
  }

  setSelectedTeamID = (selectedTeamID) => {
    this.setState({
      selectedTeamID: selectedTeamID
    });

    this.setTeamLeaderName(selectedTeamID);
    this.getEmployees(selectedTeamID);
  }

  getEmployees = (selectedTeamID) => {
    axios.get(url("api/project_manager/employees/in_team/team_id/" + selectedTeamID), { headers: authHeader() })
      .then(response => response.data)
      .then((data) => this.setState({ employees: data }));
  }

  setTeamLeaderName(selectedTeamID) {
    let teamLeaderName = "";
    for (let i = 0; i < this.state.teams.length; i++) {
      if (this.state.teams[i].id === selectedTeamID) {
        teamLeaderName = this.state.teams[i].teamLeader.name;
        break;
      }
    }
    this.setState({
      teamLeaderName: teamLeaderName
    });
  }

  render() {
    const { showModal, selectedProject, closeProjectDetails } = this.props;
    const { teams, selectedTeamID, employees, teamLeaderName } = this.state;

    return (
      <div>
        <Modal isOpen={showModal} size="lg" toggle={closeProjectDetails}>
          <ModalHeader toggle={closeProjectDetails}>További információ</ModalHeader>
          <ModalBody>
            <h4 id="modalTitle">{selectedProject.name}</h4>
            <div className="modalRow">
              <div className="modalLabel">Fejlesztési terület:</div><div className="modalData modalBoldLabel">{selectedProject.developmentAreaName}</div>
            </div>
            <div className="modalRow">
              <div className="modalLabel">Projekt állapot:</div><div className="modalData modalBoldLabel">{selectedProject.projectStatusName}</div>
            </div>
            <div className="modalRow">
              <div className="modalLabel">Prioritás:</div><div className="modalData modalBoldLabel">{selectedProject.priorityName}</div>
            </div>
            <div className="modalRow">
              <div className="modalLabel">Leírás:</div><div className="modalData">{selectedProject.description}</div>
            </div>
            <div className="modalTeamLeaderRow modalMarginTop">
              <div className="modalRow modalTeamLeaderWidth"><div className="modalTeamLeaderLabel">Csapatvezető:</div><div className="modalTeamLeader modalBoldLabel">{teamLeaderName}</div></div>
            </div>
            <div className="modalRow tableHeight">
              <ModalTeamsTable teams={teams} selectedTeamID={selectedTeamID} setSelectedTeamID={this.setSelectedTeamID} />
              <ModalEmployeeTable employees={employees} />
            </div>
          </ModalBody>
          <ModalFooter>
            <Button color="secondary" onClick={closeProjectDetails}>Bezárás</Button>
          </ModalFooter>
        </Modal>
      </div >
    );
  }
}