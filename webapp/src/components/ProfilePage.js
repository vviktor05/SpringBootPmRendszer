import React, { Component } from "react";
import authService from "../services/AuthService";
import './ProfilePage.css';

export default class Profile extends Component {
    constructor(props) {
        super(props);

        this.state = {
            loggedInUser: authService.getLoggedInUser()
        };
    }

    render() {
        const { loggedInUser } = this.state;

        return (
            <div id="profile_container" className="container">
                <header className="jumbotron">
                    <h3>
                        <strong>{loggedInUser.employee.name}</strong> Profile
                    </h3>
                </header>
                <p>
                    <strong>Token:</strong>{" "}
                    {loggedInUser.token.substring(0, 20)}...{" "}
                    {loggedInUser.token.substr(loggedInUser.token.length - 20)}
                </p>
                <p>
                    <strong>Id:</strong>{" "}
                    {loggedInUser.employee.id}
                </p>
                <p>
                    <strong>Email:</strong>{" "}
                    {loggedInUser.employee.email}
                </p>
                <strong>Authorities:</strong>
                <ul>
                    {loggedInUser.role && <li>{loggedInUser.role}</li>}
                </ul>
            </div>
        );
    }
}