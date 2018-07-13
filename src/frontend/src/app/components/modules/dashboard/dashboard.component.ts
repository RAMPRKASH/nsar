import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { chart, ExportingOptions } from 'highcharts';
import * as Highcharts from 'highcharts';
//require('highcharts/modules/exporting')(Highcharts);
import { StatusService } from './status.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
  chart: Highcharts.ChartObject;
  
  status2g =  {'Total': 450, 'Active': 380, 'Inactive': 20};
  status3g =  {'Total': 547, 'Active': 455, 'Inactive': 92};
  status4g =  {'Total': 40, 'Active': 48, 'Inactive': 1};
  //checking for enitial commit 
 options: Highcharts.Options = {
    chart: {
      type: 'column'
    },
    credits: {
      enabled: false
  },
 exporting:{buttons:{
   contextButton:{enabled:true}

   }
 },

    subtitle: {
      text: 'Site wise overview'
    },
    yAxis: {
      min: 0,
      title: {
        text: 'Availity (count)'
      }
    },
    tooltip: {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
        '<td style="padding:0"><b>{point.y} </b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
    },
    plotOptions: {
      column: {
        pointPadding: 0.2,
        borderWidth: 0
      }
    }
  };

  @ViewChild('chart2G') chartTarget: ElementRef;
  @ViewChild('chart3G') chartTarget1: ElementRef;
  @ViewChild('chart4G') chartTarget2: ElementRef;
  constructor(private statusService: StatusService) { }
ngOnInit() {
    this.networkStatus() ;
  }

  networkStatus() {
     this.statusService.getStatusOverview()
     .subscribe(
       (response) => console.log(response),
       (error) => console.log(error)
     );

  }

  render3GTrends() {
    const options = this.options;
    options['series'] = [{
      name: 'Total',
      color:'black',
      data: [497, 500, 330, 600, 586, 478, 189, 148, 216, 900, 800, 400]

    }, {
      name: 'Active',
      color: 'Lime',
      data: [300, 450, 300, 555, 500, 301, 150, 140, 200, 850, 750, 380]

    }, {
      name: 'In-active',
      color:'red',
      data: [197, 50, 30, 145, 4, 48, 177, 39, 8, 16, 50, 20]

    }];
    options['xAxis'] = {
      categories: ['MDRNC-1', 'MDRNC-2', 'MDRNC-3', 'MDRNC-4', 'MDRNC-5', 'MDRNC-6', 'MDRNC-7', 'MDRNC-8', 'MDRNC-9', 'MDRNC-19', 'MDRNC-20', 'MDRNC-12'],
      crosshair: true
    };

    options['title'] = {
      text: ' RNC Network Availabilty'
    };

    this.chart = chart(this.chartTarget1.nativeElement, options);
  }

  render4GTrends() {
    const options = this.options;
    options['series'] = [{
      name: 'Total',
      color:'Navy',
      data: [497, 500, 330, 600, 586, 478, 189, 148, 216, 900, 800, 400]

    }, {
      name: 'Active',
      color:'Lime',
      data: [300, 450, 300, 555, 500, 301, 150, 140, 200, 850, 750, 380]

    }, {
      name: 'In-active',
      color:'red',
      data: [197, 50, 30, 145, 4, 48.3, 177, 39, 8, 16, 50, 20]

    }];
    options['xAxis'] = {
      categories: ['LTE-1', 'LTE-2', 'LTE-3', 'LTE-4', 'LTE-5', 'LTE-6', 'LTE-7', 'LTE-8', 'LTE-9', 'LTE-19', 'LTE-20', 'LTE-12'],
      crosshair: true
    };

    options['title'] = {
      text: ' LTE Network Availabilty'
    };

    this.chart = chart(this.chartTarget2.nativeElement, options);
  }

  render2GTrends() {
    const options = this.options;
    options['series'] = [{
      name: 'Total',
      color:'Navy',
      data: [497, 500, 330, 600, 586, 478, 189, 148, 216, 900, 800, 400]

    }, {
      name: 'Active',
      color:'Lime',
      data: [300, 450, 300, 555, 500, 301, 150, 140, 200, 850, 750, 380]

    }, {
      name: 'In-active',
      color:'red',
      data: [197, 50, 30, 145, 4, 48.3, 177, 39, 8, 16, 50, 20]

    }];
    options['xAxis'] = {
      categories: ['MDE-BSC-1', 'MDE-BSC-2', 'MDE-BSC-3', 'MDE-BSC-4', 'MDE-BSC-5', 'MDE-BSC-6', 'MDE-BSC-7', 'MDE-BSC-8', 'MDE-BSC-9', 'MDE-BSC-19', 'MDE-BSC-20', 'MDE-BSC-12'],
      crosshair: true
    };

    options['title'] = {
      text: ' BSC Network Availabilty'
    };

    this.chart = chart(this.chartTarget.nativeElement, options);
  }

  ngAfterViewInit() {
    this.render3GTrends();
    this.render2GTrends();
    this.render4GTrends();
  }

}
