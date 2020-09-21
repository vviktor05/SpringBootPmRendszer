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
      backgroundColor: "rgba(0, 0, 0, 0.54)"
    }
  },
  tableCell: {
    "$selected &": {
      color: "white"
    }
  },
  hover: {
  },
  selected: {}
});

function ModalTeamsTable(props) {
  const { classes, teams, selectedTeamID, setSelectedTeamID } = props;

  return (
    <Paper className={classes.root}>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <TableCell id="modalTableHead">Projekten dolgozó csapatok</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {teams.length === 0 ?
            <TableRow>
              <TableCell>
                Nincs elérhető csapat.
            </TableCell>
            </TableRow>
            :
            teams.map((team, i) => (
              <TableRow
                hover
                key={team.id}
                onClick={() => {
                  setSelectedTeamID(team.id);
                }}
                selected={selectedTeamID === team.id || (i === 0 && selectedTeamID === 0)}
                classes={{ hover: classes.hover, selected: classes.selected }}
                className={classes.tableRow + " clickable"}
              >
                <TableCell
                  className={classes.tableCell}
                  component="th"
                  scope="row"
                >
                  {team.name}
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </Paper>
  );
}

ModalTeamsTable.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(ModalTeamsTable);