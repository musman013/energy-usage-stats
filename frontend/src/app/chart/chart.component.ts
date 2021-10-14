import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ChartType } from 'chart.js';
import { EnergyService } from '../energy.service';
import { IEnergyUsage, SourceType } from '../Models';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import * as moment from 'moment';
import { IEnergyUsageStats } from '../Models';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss'],
})
export class ChartComponent implements OnInit, OnDestroy {

  energyUsage: IEnergyUsage[] = [];
  dataArr:any= [];
  
  durationControl = new FormControl('month');

  sourceTypes = Object.values(SourceType);
  sourceControl = new FormControl([...this.sourceTypes]);
  sourceFilterControl = new FormControl();
  selectedSourceTypes = [...this.sourceTypes];

  chartType: ChartType = 'bar';
  chartDatasets: Array<any> = [] ;
  chartLabels: Array<any> = [];

  chartOptions: any = {
    responsive: true,
    scales: {
      yAxes: {
        stacked: true,
        title: {
          text: "Cost",
          display: true
        }
      },
      xAxes: {
        title: {
          text: "Month",
          display: true
        }
      },
    },
  };

  protected _onDestroy = new Subject<void>();
  energyUsageStats: IEnergyUsageStats[];
  constructor(private energyService: EnergyService){}
  
  ngOnInit(): void {
    this.getEnergyUsageStats();
    this.sourceFilterControl.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe(() => {
        this.search();
      });

    this.sourceControl.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe((val) => {
        this.getEnergyUsageStats();
      });
    
    this.durationControl.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe((val) => {
        this.getEnergyUsageStats();
      });

  }

  getEnergyUsageStats() {
    this.energyService.getEnergyUsageStats(this.durationControl.value, this.sourceControl.value).subscribe(res => {
      this.energyUsageStats = res;
      this.setChartData();
    });
  }

  search() {
    let filter = this.sourceFilterControl.value?.toLowerCase() || '';
    this.selectedSourceTypes = this.sourceTypes.filter(
      (option: any) => option.toLocaleLowerCase().includes(filter)
    );
  }

  setChartData() {
    this.chartDatasets = [];

    let chartDataMap: any = {};
    this.sourceTypes.forEach( type => {
      chartDataMap[type] = {
        data: [],
        label: type,
        stack: 'a'
      };
    });

    let labelSet = new Set();
    this.energyUsageStats.forEach(stat => {
      chartDataMap[stat.sourceType].data.push({
        y: stat.cost,
        x: moment(stat.timestamp)
      });
      if (this.durationControl.value === 'week') {
        labelSet.add(moment(stat.timestamp).format('MM/DD/YYYY'));
        this.chartOptions.scales.xAxes.title.text = 'Week';
      } else {
        labelSet.add(moment(stat.timestamp).format('MMM-YYYY'));
        this.chartOptions.scales.xAxes.title.text = 'Month';
      }
    });
    
    this.sourceTypes.forEach(type => {
      this.chartDatasets = [...this.chartDatasets, ...[chartDataMap[type]]];
    });
    this.chartLabels = Array.from(labelSet);
  }

  ngOnDestroy() {
    this._onDestroy.next();
    this._onDestroy.complete();
  }
}
