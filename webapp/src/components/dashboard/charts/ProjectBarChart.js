import React, { Component } from 'react';
import { Bar, defaults } from 'react-chartjs-2'

// defaults.global.tooltips.enabled = false
// defaults.global.legend.position = 'bottom'
defaults.global.defaultFontColor = 'white';

class ProjectChart extends Component {
  state = {
    years: Array(5).fill("2021")
  }

  componentDidMount() {
    this.getDates();
  }

  getDates() {
    let beforeTwelveMounths = new Date();
    beforeTwelveMounths.setFullYear(beforeTwelveMounths.getFullYear() - 5);

    let years = [];
    let actualYear;
    for (let i = 0; i < 5; i++) {
      beforeTwelveMounths.setFullYear(beforeTwelveMounths.getFullYear() + 1);
      actualYear = beforeTwelveMounths.toLocaleDateString().substring(0, 4);
      years.push(actualYear);
    }

    this.setState({ years: years });
  }

  getDatas() {
    const years = this.state.years;
    const projects = this.props.projects;
    let datas = [];

    let piece = 0;
    for (const year of years) {
      piece = 0;
      for (const project of projects) {
        if (project.orderDate.includes(year)) {
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
        <Bar
          data={{
            labels: this.state.years,
            datasets: [
              {
                label: ' projektek száma',
                data: this.getDatas(),
                backgroundColor: [
                  'rgba(255, 99, 132, .2)',
                  'rgba(131, 79, 233, .2)',
                  'rgba(54, 162, 235, .2)',
                  'rgba(255, 206, 86, .2)',
                  'rgba(75, 192, 192, .2)'
                ],
                borderColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(131, 79, 233, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)',
                  'rgba(75, 192, 192, 1)'
                ],
                borderWidth: 1,
              }
            ],
          }}
          height={this.props.height}
          width={this.props.width}
          options={{
            maintainAspectRatio: false,
            scales: {
              yAxes: [
                {
                  ticks: {
                    beginAtZero: true,
                  },
                },
              ],
            },
            legend: {
              // labels: {
              //   fontSize: 25
              // },
              display: false
            }
          }}
        />
      </div>
    );
  }
}

export default ProjectChart;