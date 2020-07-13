import React from 'react';
import './App.css';
import { Container, Row, Col } from 'reactstrap';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import NavigationBar from './components/NavigationBar';
import Project from './components/Project';
import ProjectList from './components/ProjectList';
import CustomerList from './components/CustomerList';
import Customer from './components/Customer';

function App() {
  const marginTop = {
    marginTop: "20px"
  };

  return (
    <Router>
      <NavigationBar />
      <Container fluid={true}>
        <Row>
          <Col lg={12} style={marginTop}>
            <Switch>
              <Route path="/" exact component={ProjectList} />
              <Route path="/projects" exact component={ProjectList} />
              <Route path="/projects/add" exact component={Project} />
              <Route path="/projects/edit/:id" exact component={Project} />
              <Route path="/customers" exact component={CustomerList} />
              <Route path="/customers/add" exact component={Customer} />
              <Route path="/customers/edit/:id" exact component={Customer} />
            </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
  );
}

export default App;