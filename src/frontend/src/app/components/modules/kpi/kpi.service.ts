import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';

@Injectable()
export class KpiService {
    constructor(private http: Http) { }

    getKpi(kpi: String) {
        return this.http.get('http://localhost:8080/kpi/' + kpi);
    }
}
