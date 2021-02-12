import React, { Component } from 'react';
import { Pie, defaults } from 'react-chartjs-2'

// defaults.global.tooltips.enabled = false
defaults.global.legend.position = 'bottom'

class ProjectChart extends Component {
  developmentAreas = [];

  getDevelopmentAreas() {
    const projects = this.props.projects;

    let developmentAreas = new Set();
    for (const project of projects) {
      developmentAreas.add(project.developmentArea.name);
    }

    this.developmentAreas = Array.from(developmentAreas);
    return Array.from(developmentAreas);
  }

  getDatas() {
    const projects = this.props.projects;

    let datas = [];
    let piece = 0;

    for (const developmentArea of this.developmentAreas) {
      piece = 0;
      for (const project of projects) {
        if (project.developmentArea.name === developmentArea) {
          piece++;
        }
      }

      datas.push(piece);
    }

    return datas;
  }

  render() {
    return (
      <div>
        <Pie
          data={{
            labels: this.getDevelopmentAreas(),
            datasets: [
              {
                data: this.getDatas(),
                backgroundColor: [
                  'rgba(75, 192, 192, .2)',
                  'rgba(255, 206, 86, .2)',
                  'rgba(54, 162, 235, .2)',
                  'rgba(131, 79, 233, .2)',
                  'rgba(255, 99, 132, .2)',
                  'rgba(39, 18, 228, .2)'
                ],
                borderColor: [
                  'rgba(75, 192, 192, .2)',
                  'rgba(255, 206, 86, .2)',
                  'rgba(54, 162, 235, .2)',
                  'rgba(131, 79, 233, .2)',
                  'rgba(255, 99, 132, .2)',
                  'rgba(39, 18, 228, 1)'
                ],
                borderWidth: 1,
              }
            ],
          }}
          options={{
            maintainAspectRatio: false,
            legend: {
              labels: {
                fontSize: 20
              }
            }
          }}
          height={this.props.height}
          width={this.props.width}
        />
      </div>
    );
  }
}

export default ProjectChart;