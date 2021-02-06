import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './NotFound.css';

export default class Project extends Component {
    render() {
        return (
            <div id="notFound">
                <h1>A keresett oldal nem található!</h1>
                <h3>
                    <Link to="/projects">Vissza a főoldalra</Link>
                </h3>
            </div>
        )
    }
}