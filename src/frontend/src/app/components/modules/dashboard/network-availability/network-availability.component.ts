import { Component, OnInit, Injectable, ViewChild, AfterViewInit, OnDestroy  } from '@angular/core';
import { StatusService } from '../status.service';
import {  Http, Headers, Response } from '@angular/http';
import { LoggingService } from '../../../../logging.service';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs/Subject';

@Component({
  selector: 'app-network-availability',
  templateUrl: './network-availability.component.html',
  styleUrls: ['./network-availability.component.css']
})

export class NetworkAvailabilityComponent implements OnInit {
  @ViewChild(DataTableDirective)
  datatableElement: DataTableDirective;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  responseData: any;
  data = [];

  constructor(
    private http: Http,
    private statusService: StatusService,
    private loggingService: LoggingService
  ) { }

  ngOnInit() {
    
    this.dtOptions = {
     // pagingType: 'full_numbers',
     ajax: 'data/data.json',
        //pageLength: "10",
         columns: [{
         title: 'ID',
         data: "id"
       }, {
         title: 'RNC Name',
       data: "rncName"
       }, {
         title: 'Site Name',
        data: "siteName"
       },
       {
         title: 'Cell Name',
         data: "cellName"
       }, {
         title: 'Site Status',
         data: "siteStatus"
       }, {
         title: 'Cycle Time',
         data: "cycleTime"
       }
     ],
      dom: 'Bfrtip',
      // Configure the buttons
      buttons: [
        'excel',
       
      ]
    };

    this.statusService.getStatusDetails().subscribe(
     (response: Response) => {
             this.responseData = response.json();
             this.data = response.json()
             //this.dtTrigger.next();
            //this.loadStatusDetails(response);
            //this.dtTrigger.next();
            console.log(JSON.stringify(this.data));
           this.loggingService.succesLog(" NetworkAvailabilityComponent | getStatusOverview : successful");
      },
      (error) => {
            this.loggingService.errorLog(error);
          }
    );
    
  }
 
  // loadStatusDetails(responseData: any) {
  // // console.log( 'loadStatusDetails : '+JSON.stringify(this.responseData));
  //   this.dtOptions = {
  //     pagingType: 'full_numbers',
  //     data:responseData,
  //     pageLength: 10,
  //     columns: [{
  //       title: 'ID',
  //       data: responseData
  //     }, {
  //       title: 'RNC Name',
  //       data: responseData
  //     }, {
  //       title: 'Site Name',
  //       data: responseData
  //     },
  //     {
  //       title: 'Cell Name',
  //       data: responseData
  //     }, {
  //       title: 'Site Status',
  //       data: responseData
  //     }, {
  //       title: 'Cycle Time',
  //       data: responseData
  //     }
  //   ]
  //   };
  // }

}
