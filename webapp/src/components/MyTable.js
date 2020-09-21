import React, { Component } from 'react';
import { Table, Button, Form, InputGroup, FormControl } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import './projects/ProjectList.css';

export default class MyTable extends Component {
    constructor(props) {
        super(props)
        this.state = {
            search: "",
            currentPage: 1,
            pageSize: 8,
            inputCurrentPage: "1"
        };
        this.totalPages = 1;
    }

    searchChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    changePage = event => {
        let pageNumber = event.target.value;
        this.setState({
            inputCurrentPage: pageNumber
        });

        if (pageNumber) {
            pageNumber = parseInt(pageNumber);

            if (pageNumber > 0 && pageNumber <= this.totalPages) {
                this.setState({
                    [event.target.name]: pageNumber
                });
            }
        }
    }

    firstPage = () => {
        this.setState({
            currentPage: 1,
            inputCurrentPage: 1
        });
    }

    prevPage = () => {
        this.setState({
            currentPage: this.state.currentPage - 1,
            inputCurrentPage: this.state.currentPage - 1
        });
    }

    nextPage = () => {
        let nextPage = this.state.currentPage + 1;
        this.setState({
            currentPage: nextPage,
            inputCurrentPage: nextPage
        });
    }

    lastPage = () => {
        this.setState({
            currentPage: this.totalPages,
            inputCurrentPage: this.totalPages
        });
    }

    pageNumberFocus = () => {
        this.setState({
            inputCurrentPage: ""
        });
    }

    pageNumberFocusOut = () => {
        this.setState({
            inputCurrentPage: this.state.currentPage
        });
    }

    check(data) {
        const { columns } = this.props;

        let i = 0;
        while (i < columns.length && this.getDataValue(data, columns[i].jsonFieldName).toLowerCase().indexOf(this.state.search.toLowerCase()) === -1) {
            i++;
        }

        if (i < columns.length) {
            return true;
        }
        return false;
    }

    getDataValue(data, jsonFieldName) {
        if (jsonFieldName.includes(".")) {
            let fieldNameArray = jsonFieldName.split(".");

            return data[fieldNameArray[0]][fieldNameArray[1]] + "";
        }
        return data[jsonFieldName] + "";
    }

    render() {
        const { columns, datas, addButtonLink, addButtonTitle, editButtonLink, deleteButtonOnClick } = this.props;
        const { search, currentPage, pageSize, inputCurrentPage } = this.state;

        let filteredDatas = datas.filter(
            (data) => {
                return this.check(data);
            }
        );

        const lastIndex = currentPage * pageSize;
        const firstIndex = lastIndex - pageSize;
        const currentDatas = filteredDatas.sort((a, b) => a.id - b.id).slice(firstIndex, lastIndex);
        this.totalPages = Math.ceil(filteredDatas.length / pageSize);

        return (
            <div>
                {
                    addButtonLink &&
                    <Link to={addButtonLink}><Button variant="success">{addButtonTitle}</Button></Link>
                }
                <div id="projektSearch" className="marginButton">
                    <span>Keresés:</span>
                    <Form.Control required
                        id="search"
                        type="text" name="search"
                        value={search}
                        autoComplete="off"
                        onChange={this.searchChange}
                        className={"bg-dark text-white"}
                        placeholder="Add meg a keresendő szöveget" />
                </div>
                <Table className="marginTop" bordered hover striped variant="dark">
                    <thead>
                        <tr>
                            {
                                columns.map((col, i) => (
                                    <th key={i}>{col.label}</th>
                                ))
                            }
                            <th></th>
                        </tr>
                    </thead>
                    <tbody
                    // className="clickable"
                    >
                        {
                            currentDatas.length === 0 ?
                                <tr align="center">
                                    <td colSpan={columns.length + 1}>Nincs elérhető adat.</td>
                                </tr>
                                :
                                currentDatas.map((data) => (
                                    <tr key={data.id} //{/* onClick={() => this.showProjectDetails(project)} */}
                                    >
                                        {
                                            columns.map((col, i) => (
                                                <td key={i}>{this.getDataValue(data, col.jsonFieldName)}</td>
                                            ))
                                        }
                                        <td>
                                            {<Link to={(editButtonLink ? editButtonLink : "") + "/" + data.id} className={editButtonLink ? "mr-2 btn btn-sm btn-outline-primary" : "mr-2 btn btn-sm btn-outline-primary disabled"}>Módosít</Link>}
                                            <Button variant="outline-danger" onClick={() => deleteButtonOnClick(data.id)} size="sm">Töröl</Button>
                                        </td>
                                    </tr>
                                ))
                        }
                    </tbody>
                </Table>

                <div style={{ "float": "left" }}>
                    Oldalszám: {currentPage} / {this.totalPages}
                </div>
                <div style={{ "float": "right" }}>
                    <InputGroup id="pagination" size="sm">
                        <InputGroup.Prepend>
                            <Button id="pageButtons" variant="outline-info" disabled={currentPage <= 1 ? true : false}
                                onClick={this.firstPage}>
                                Első
                                </Button>
                            <Button id="pageButtons" variant="outline-info" disabled={currentPage <= 1 ? true : false}
                                onClick={this.prevPage}>
                                Elöző
                                </Button>
                        </InputGroup.Prepend>
                        <FormControl id="projectListPageNumberInput" type="number" min={1} className="bg-dark" name="currentPage" onFocus={this.pageNumberFocus} onBlur={this.pageNumberFocusOut} value={inputCurrentPage}
                            onChange={this.changePage} />
                        <InputGroup.Prepend>
                            <Button id="pageButtons" variant="outline-info" disabled={currentPage >= this.totalPages ? true : false}
                                onClick={this.nextPage}>
                                Következő
                                </Button>
                            <Button id="pageButtons" variant="outline-info" disabled={currentPage >= this.totalPages ? true : false}
                                onClick={this.lastPage}>
                                Utolsó
                                </Button>
                        </InputGroup.Prepend>
                    </InputGroup>
                </div>
            </div>
        )
    }
}