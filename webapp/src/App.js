import React from 'react';
import './App.css';
import { Container, Row, Col } from 'reactstrap';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { PrivateRoute } from './components/PrivateRoute'
import NavigationBar from './components/NavigationBar';
import LoginForm from './components/LoginForm';
import ProjectList from './components/ProjectList';
import ProjectForm from './components/ProjectForm';
// import ProjectDetails from './components/ProjectDetails';
import CustomerList from './components/CustomerList';
import CustomerForm from './components/CustomerForm';
import NotFound from './components/NotFound'

function App() {
  return (
    <Router>
      <NavigationBar />
      <Container fluid={true}>
        <Row>
          <Col lg={12} style={{ marginTop: "20px" }}>
            <Switch>
              <Route path="/login" exact component={LoginForm} />
              <PrivateRoute path={["/", "/projects"]} exact component={ProjectList} />
              <PrivateRoute path="/projects/add" exact component={ProjectForm} />
              <PrivateRoute path="/projects/edit/:id" exact component={ProjectForm} />
              {/* <PrivateRoute path="/projects/details/:id" exact component={ProjectDetails} /> */}
              <PrivateRoute path="/customers" exact component={CustomerList} />
              <PrivateRoute path="/customers/add" exact component={CustomerForm} />
              <PrivateRoute path="/customers/edit/:id" exact component={CustomerForm} />
              <Route exact component={NotFound} />
            </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
  );
}

export default App;