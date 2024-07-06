import { CommonModule, isPlatformBrowser } from '@angular/common';
import { AfterViewInit, Component, ElementRef, Inject, OnInit, PLATFORM_ID, ViewChild } from '@angular/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { Chart, plugins } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [BaseChartDirective, MatGridListModule, CommonModule, MatCardModule],
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
        const { ctx, data } = chart;
        const xCoor = chart.getDatasetMeta(0).data[0].x;
        const yCoor = chart.getDatasetMeta(0).data[0].y;
        ctx.save();
        ctx.font = 'bolder 25px poppins';
        ctx.fillStyle = '#373f51';
        ctx.textAlign = 'center';
        ctx.fillText('1325', xCoor, yCoor - 15);

        ctx.font = '15px poppins';
        ctx.fillStyle = '#373f51';
        ctx.fillText('Applications', xCoor, yCoor + 15);
      }
    };

    this.applicationCountChartPlugins = [textCenter];

    this.applicationCountChartOptions = {
      cutout: '75%',
      maintainAspectRatio: false,
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
