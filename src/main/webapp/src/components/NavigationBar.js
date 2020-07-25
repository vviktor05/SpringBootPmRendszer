import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';

class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={"/"} className="navbar-brand">
                    Projektmenedzsment rendszer
                </Link>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Link to={"/projects"} className="nav-link">Projektek</Link>
                        <Link to={"/customers"} className="nav-link">Megrendelők</Link>
                    </Nav>
                </Navbar.Collapse>
                <Navbar.Collapse className="justify-content-end">
                    <Navbar.Text>
                        Belépve: Viktor | <a href="/">kijelentkezés</a>
                    </Navbar.Text>
                </Navbar.Collapse>
            </Navbar>
        )
    }
}

export default NavigationBar;