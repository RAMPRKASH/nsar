import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { KpiService } from '../kpi.service';

@Component({
  selector: 'app-rnc',
  templateUrl: './rnc.component.html',
  styleUrls: ['./rnc.component.css']
})
export class RncComponent implements OnInit {

  constructor(private kpiService: KpiService) { }

  ngOnInit() {
    this.loadKpiValue();
  }

  loadKpiValue() {
    this.kpiService.getKpi("rnc").subscribe(
      (response: Response) => {
        console.log(response.json());
      }
    );
  }
}
