import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import authService from '../services/AuthService';

export default class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark" >
                <Link to={"/"} className="navbar-brand">
                    Projektmenedzsment rendszer
                </Link>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    {authService.isUserLoggedIn() &&
                        <Nav className="mr-auto">
                            <Link to={"/projects"} className="nav-link">Projektek</Link>
                            {/* Projekten dolgozó csapatok */}
                            {/* Részletes keresés */}
                            <Link to={"/customers"} className="nav-link">Megrendelők</Link>
                            <Link to={"/tasks"} className="nav-link disabled">Feladatok</Link>
                            <Link to={"/reports"} className="nav-link disabled">Jelentések</Link>
                            <Link to={"/employees"} className="nav-link disabled">Dolgozók</Link>
                            <Link to={"/teams"} className="nav-link disabled">Csapatok</Link>
                            {/* Csapatok összeállítása */}
                        </Nav>
                    }
                </Navbar.Collapse>
                <Navbar.Collapse className="justify-content-end">
                    <Navbar.Text id="menu_text">
                        {authService.isUserLoggedIn() &&
                            <span>Belépve : {authService.getLoggedInUsername()} | <Link to="/login" onClick={() => { authService.logout() }}>Kijelentkezés</Link> </span>
                        }
                    </Navbar.Text>
                </Navbar.Collapse>
            </Navbar>
        )
    }
}