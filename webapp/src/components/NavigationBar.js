import React from 'react';
import { Navbar, Nav, NavDropdown } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import authService from '../services/AuthService';

export default class NavigationBar extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            loggedInUser: null
        };

        this.logout = this.logout.bind(this);
    }

    componentDidMount() {
        const user = authService.getLoggedInUser();

        if (user) {
            this.setState({
                loggedInUser: user
            });
        }
    }

    logout() {
        authService.logout();
        window.location.replace("/login");
    }

    render() {
        const { loggedInUser } = this.state;

        return (
            <Navbar bg="dark" variant="dark">
                <Link to={"/"} className="navbar-brand">
                    Projektmenedzsment rendszer
                </Link>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    {authService.isUserLoggedIn() &&
                        <Nav className="mr-auto">
                            <NavDropdown title="Projektek" id="collasible-nav-dropdown">
                                <NavDropdown.Item as={Link} to="/projects">Projektek</NavDropdown.Item>
                                {/* <NavDropdown.Divider /> */}
                                <NavDropdown.Item as={Link} to="/projects" className="disabled">Projekten dolgozó csapatok</NavDropdown.Item>
                                {/* Projekten dolgozó csapatok összeállítása */}
                            </NavDropdown>
                            <Link to={"/customers"} className="nav-link">Megrendelők</Link>
                            <Link to={"/tasks"} className="nav-link">Feladatok</Link>
                            <Link to={"/reports"} className="nav-link disabled">Jelentések</Link>
                            <Link to={"/employees"} className="nav-link disabled">Dolgozók</Link>
                            <Link to={"/teams"} className="nav-link">Csapatok</Link>
                            {/* Csapatok összeállítása */}
                            {/* Jelszóváltoztatás */}
                        </Nav>
                    }
                </Navbar.Collapse>
                <Navbar.Collapse className="justify-content-end">
                    <Navbar.Text id="menu_text">
                        {loggedInUser ?
                            (<span>Belépve : <Link to={"/profile"}>{loggedInUser.employee.name}</Link> | <Link to="" onClick={() => this.logout()}>Kijelentkezés</Link> </span>)
                            :
                            (<Link to="/login">Bejelentkezés</Link>)
                        }
                    </Navbar.Text>
                </Navbar.Collapse>
            </Navbar>
        )
    }
}