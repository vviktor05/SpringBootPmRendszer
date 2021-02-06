import React from 'react';
import './App.css';
import { Container, Row, Col } from 'reactstrap';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { PrivateRoute } from './components/PrivateRoute'

import Navbar from './components/menubar/navbar/Navbar';
import Sidebar from './components/menubar/sidebar/Sidebar';

import LoginForm from './components/login/LoginForm';
// import Dashboard from './components/dashboard/Dashboard';
import ProjectList from './components/projects/ProjectList';
import ProjectForm from './components/projects/ProjectForm';
import ProjectsAndTeams from './components/projects/projectsAndTeams/ProjectsAndTeams';
import CustomerList from './components/customers/CustomerList';
import CustomerForm from './components/customers/CustomerForm';
import TaskList from './components/tasks/TaskList';
import TaskForm from './components/tasks/TaskForm';
import ReportList from './components/reports/ReportList';
import ReportForm from './components/reports/ReportForm';
import EmployeeList from './components/employees/EmployeesList';
import EmployeeForm from './components/employees/EmployeesForm';
import TeamList from './components/teams/TeamList';
import TeamForm from './components/teams/TeamForm';
import TeamMembership from './components/teams/teamMemberships/TeamMembership';
import ProfilePage from './components/profile/ProfilePage'
import NotFound from './components/notfound/NotFound'

function App() {
  return (
    <div className="app">
      <Router>
        <Navbar />
        <div className="app__sidebarAndContent">
          <Sidebar />
          <Container className="app__content paddingTop" fluid={true}>
            <Row>
              <Col lg={12}>
                <Switch>
                  <Route path="/login" exact component={LoginForm} />
                  {/* <PrivateRoute path={["/", "/dashboard"]} exact component={Dashboard} /> */}
                  <PrivateRoute path={["/", "/projects"]} exact component={ProjectList} />
                  <PrivateRoute path="/projects/add" exact component={ProjectForm} />
                  <PrivateRoute path="/projects/edit/:id" exact component={ProjectForm} />
                  <PrivateRoute path="/projects/projects_and_teams" exact component={ProjectsAndTeams} />
                  <PrivateRoute path="/customers" exact component={CustomerList} />
                  <PrivateRoute path="/customers/add" exact component={CustomerForm} />
                  <PrivateRoute path="/customers/edit/:id" exact component={CustomerForm} />
                  <PrivateRoute path="/tasks" exact component={TaskList} />
                  <PrivateRoute path="/tasks/add" exact component={TaskForm} />
                  <PrivateRoute path="/tasks/edit/:id" exact component={TaskForm} />
                  <PrivateRoute path="/reports" exact component={ReportList} />
                  <PrivateRoute path="/reports/add" exact component={ReportForm} />
                  <PrivateRoute path="/reports/edit/:id" exact component={ReportForm} />
                  <PrivateRoute path="/employees" exact component={EmployeeList} />
                  <PrivateRoute path="/employees/add" exact component={EmployeeForm} />
                  <PrivateRoute path="/employees/edit/:id" exact component={EmployeeForm} />
                  <PrivateRoute path="/teams" exact component={TeamList} />
                  <PrivateRoute path="/teams/add" exact component={TeamForm} />
                  <PrivateRoute path="/teams/edit/:id" exact component={TeamForm} />
                  <PrivateRoute path="/teams/team_memberships" exact component={TeamMembership} />
                  <PrivateRoute path="/profile" exact component={ProfilePage} />
                  <Route exact component={NotFound} />
                </Switch>
              </Col>
            </Row>
          </Container>
        </div>
      </Router>
    </div>
  );
}

export default App;