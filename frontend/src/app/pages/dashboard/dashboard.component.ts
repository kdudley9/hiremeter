import { CommonModule, isPlatformBrowser } from '@angular/common';
import { AfterViewInit, Component, ElementRef, Inject, OnInit, PLATFORM_ID, ViewChild } from '@angular/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { Chart, plugins } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [BaseChartDirective, MatGridListModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  isBrowser: boolean = false;
  applicationCountChartData: any;
  applicationCountChartOptions: any;
  applicationCountChartPlugins: any[] = [];
  constructor(@Inject(PLATFORM_ID) private platformId: any) { }

  ngOnInit(): void {
    this.isBrowser = isPlatformBrowser(this.platformId);
    this.createApplicationCountChart();
  }

  createApplicationCountChart(): void {
    const textCenter = {
      id: 'textCenter',
      beforeDatasetsDraw(chart: any, args: any, pluginOptions: any) {
        console.log('working');
        const { ctx, data } = chart;
        const xCoor = chart.getDatasetMeta(0).data[0].x;
        const yCoor = chart.getDatasetMeta(0).data[0].y;
        ctx.save();
        ctx.font = 'bolder 30px sans-serif';
        ctx.fillStyle = 'black';
        ctx.textAlign = 'center';
        ctx.fillText('Value', xCoor, yCoor - 15);

        ctx.font = 'bolder 50px sans-serif';
        ctx.fillStyle = 'red';
        ctx.fillText(data.datasets[0].data[0], xCoor, yCoor + 25);
      }
    };

    this.applicationCountChartPlugins = [textCenter];

    this.applicationCountChartOptions = {
      cutout: '75%',
      plugins: {
        legend: {
          display: true,
          position: 'right',
          labels: {
            usePointStyle: true,
            pointStyle: 'circle'
          }
        }
      }
    };

    this.applicationCountChartData = {
      datasets: [{
        data: [10, 20, 30]
      }],

      labels: [
        'Accepted',
        'No Response',
        'Rejected'
      ]
    };
  }
}
