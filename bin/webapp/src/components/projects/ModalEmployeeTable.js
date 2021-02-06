import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";

const styles = (theme) => ({
    root: {
        width: "48%",
        overflowX: "auto",
        margin: "1%"
    },
    table: {
    },
    tableRow: {
        "&$selected, &$selected:hover": {
        }
    },
    tableCell: {
        "$selected &": {
        }
    },
    hover: {
    },
    selected: {}
});

function ModalEmployeeTable(props) {
    const { classes, employees } = props;

    return (
        <Paper className={classes.root}>
            <Table className={classes.table}>
                <TableHead>
                    <TableRow>
                        <TableCell id="modalTableHead">Csapatban dolgozók</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {employees.length === 0 ?
                        <TableRow>
                            <TableCell>
                                Nincs elérhető fejlesztő.
                            </TableCell>
                        </TableRow>
                        :
                        employees.map((employee) => (
                            <TableRow
                                key={employee.id}
                                className={classes.tableRow}
                            >
                                <TableCell
                                    className={classes.tableCell}
                                    component="th"
                                    scope="row"
                                >
                                    {employee.name}
                                </TableCell>
                            </TableRow>
                        ))}
                </TableBody>
            </Table>
        </Paper>
    );
}

ModalEmployeeTable.propTypes = {
    classes: PropTypes.object.isRequired
};

export default withStyles(styles)(ModalEmployeeTable);