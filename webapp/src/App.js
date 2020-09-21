import React from 'react';
import './App.css';
import { Container, Row, Col } from 'reactstrap';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import ProfilePage from './components/ProfilePage'
import { PrivateRoute } from './components/PrivateRoute'
import NavigationBar from './components/NavigationBar';
import LoginForm from './components/LoginForm';
import ProjectList from './components/projects/ProjectList';
import ProjectForm from './components/projects/ProjectForm';
import CustomerList from './components/customers/CustomerList';
import CustomerForm from './components/customers/CustomerForm';
import TaskList from './components/tasks/TaskList';
// import ReportList from './components/reports/ReportList';
// import EmployeeList from './components/employees/EmployeeList';
import TeamList from './components/teams/TeamList';
import TeamForm from './components/teams/TeamForm';
import NotFound from './components/NotFound'

function App() {
  return (
    <Router>
      <NavigationBar />
      <Container className="marginTop marginButton" fluid={true}>
        <Row>
          <Col lg={12}>
            <Switch>
              <Route path="/login" exact component={LoginForm} />
              <PrivateRoute path="/profile" exact component={ProfilePage} />
              <PrivateRoute path={["/", "/projects"]} exact component={ProjectList} />
              <PrivateRoute path="/projects/add" exact component={ProjectForm} />
              <PrivateRoute path="/projects/edit/:id" exact component={ProjectForm} />
              <PrivateRoute path="/customers" exact component={CustomerList} />
              <PrivateRoute path="/customers/add" exact component={CustomerForm} />
              <PrivateRoute path="/customers/edit/:id" exact component={CustomerForm} />
              <PrivateRoute path="/tasks" exact component={TaskList} />
              {/* <PrivateRoute path="/reports" exact component={ReportList} /> */}
              {/* <PrivateRoute path="/employees" exact component={EmployeeList} /> */}
              <PrivateRoute path="/teams" exact component={TeamList} />
              <PrivateRoute path="/teams/add" exact component={TeamForm} />
              <PrivateRoute path="/teams/edit/:id" exact component={TeamForm} />
              <Route exact component={NotFound} />
            </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
  );
}

export default App;