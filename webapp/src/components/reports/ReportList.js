import React, { Component } from 'react';
import { Card, Form } from 'react-bootstrap';
import axios from 'axios';
import { url } from '../../util/BackendURL';
import authHeader from '../../helpers/authHeader';
import MyTable from '../MyTable';
import './ReportForm.css';

export default class TaskList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            reports: [],
            selectedReport: {
                text: ""
            }
        };
    }

    componentDidMount() {
        axios.get(url("api/project_manager/reports"), { headers: authHeader() })
            .then(response => response.data)
            .then((data) => this.setState({ reports: data }));
    }

    deleteReport = (reportId) => {
        axios.delete(url("api/project_manager/reports/" + reportId), { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    alert("A jelentés törölve!");
                    this.setState({
                        reports: this.state.reports.filter(report => report.id !== reportId)
                    });
                }
            });
    }

    showReportText = (report) => {
        this.setState({
            selectedReport: report
        });
    }

    render() {
        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Jelentések</Card.Header>
                <Card.Body className="reportList">
                    <MyTable className="reportList__table" columns={[
                        {
                            label: 'Projekt',
                            jsonFieldName: 'project.name'
                        },
                        {
                            label: 'Felvitel dátuma',
                            jsonFieldName: 'recordingDate'
                        },
                        {
                            label: 'Felvitte',
                            jsonFieldName: 'employee.name'
                        }
                    ]}
                        datas={this.state.reports}
                        addButtonLink={"/reports/add"}
                        addButtonTitle={"Jelentés hozzáadása"}
                        editButtonLink={"/reports/edit"}
                        deleteButtonOnClick={this.deleteReport}
                        clickable={true}
                        handleRowClick={this.showReportText}
                    />
                    <div className="reportList__right">
                        <Form.Label>Jelentés szövege</Form.Label>
                        <Form.Control as="textarea"
                            value={this.state.selectedReport.text}
                            className={"bg-dark text-white"}
                            name="selectedReport"
                            disabled />
                    </div>
                </Card.Body>
                <Card.Footer>
                    <span>*A jelentés leírásáért kattints egy sorra.</span>
                </Card.Footer>
            </Card>
        )
    }
}